package günaydin853872.engine;

import java.io.IOException;

import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

import günaydin853872.cam.Pinhole;
import günaydin853872.engine.cgtools.Vec3;

import günaydin853872.engine.util.Group;
import günaydin853872.engine.util.ImageThread;
import günaydin853872.engine.util.Shape;
import günaydin853872.engine.util.Time;

import günaydin853872.lum.Light;

import günaydin853872.mat.Lambert;
import günaydin853872.mat.Mirror;
import günaydin853872.mat.Void;

import günaydin853872.prim.Plane;
import günaydin853872.prim.Primitive;
import günaydin853872.prim.Sphere;
import günaydin853872.tex.Import;

/**
 * @since JDK 1.91 ~ <i>2017</i>
 * @author Burak Günaydin <b>{@code (853872)}</b>
 */
public class Main {
	static final int WIDTH = 1280, HEIGHT = 720;
	static final String PATH = "./doc/%s.png", FILE = "cgg-competition-ws-17-853872";
	static LinkedList<Light> list;

	public static void main(String[] args) {
		try {
			writeImage();
		} catch(Exception e) {
			System.exit(0);
		}
	}

	final static void writeImage() throws Exception {
		Import.setTexturePath("./res/textures/", "png");

		//Panorama pn = new Panorama(WIDTH, HEIGHT);
		//pn.frontal(12.5);

		Pinhole p = new Pinhole(WIDTH, HEIGHT, Math.PI/2);
		p.frontalHigh(15, 30, -25);

		Shape bg = new Background(150, new Void(new Import("clouds7")));

		Shape sun = new Sphere(new Vec3(0, 4, 0), 5, new Lambert(new Import("sun")));
		Shape mer = new Sphere(new Vec3(-5.85, 0, -1), 1, new Lambert(new Import("sphere")));
		Shape ven = new Sphere(new Vec3(6.5, 0, 2.0), 1, new Lambert(new Import("venus")));
		Shape ear = new Sphere(new Vec3(-9, 0.5, 5), 1.5, new Lambert(new Import("earth")));
		Shape moo = new Sphere(new Vec3(-6.5, -0.5, 6), 0.5, new Lambert(new Import("moon")));
		Shape mar = new Sphere(new Vec3(3, 0.5, 9), 1.5, new Lambert(new Import("mars")));
		Shape jup = new Sphere(new Vec3(11, 2, 12), 3, new Lambert(new Import("jupiter")));
		Shape sat = new Sphere(new Vec3(-14, 2, 14), 3, new Lambert(new Import("saturn")));
		Shape ura = new Sphere(new Vec3(-15, 2, -15), 3, new Lambert(new Import("uranus")));
		Shape nep = new Sphere(new Vec3(15, 2, -20), 3, new Lambert(new Import("neptune")));

		Shape pl = new Plane(new Vec3(0, -1, 0), new Vec3(0, 1, 0), Primitive.RGH_25);

		list = null;

		Group es = new Group(bg, pl);
		Group s = new Group(sun, mer, ven, ear, moo, mar, jup, sat, ura, nep);
		Group g = new Group(es, s);
		
		int n = 150;
		for(int i = 0; i < n; i++) {
			g.addShape(new Sphere(new Vec3(-25+Math.random()*50, 0+Math.random()*15, -15+Math.random()*35), 0.05, new Mirror(new Import("glass"))));
		}

		Time t = new Time();
		t.start();

		int sF = 24;
		int dpth = 10;
		int core = Runtime.getRuntime().availableProcessors();
		if(sF < core) {
			sF = core;
		}
		int sR = sF/core;
		ImageThread[] arr = new ImageThread[core];
		ConcurrentHashMap<Integer, Image> img = new ConcurrentHashMap<Integer, Image>();
		for(int i = 0; i < core; i++) {
			arr[i] = new ImageThread(new Image(WIDTH, HEIGHT), p, g, sR, dpth);
			arr[i].start();
			img.put(i, arr[i].img);
		}
		for(ImageThread i : arr) {
			i.join();
		}

		/*
		int dpth = 10;
		int sR = 16;
		ConcurrentHashMap<Integer, Image> img = new ConcurrentHashMap<Integer, Image>();
		ThreadPoolExecutor ex = (ThreadPoolExecutor) Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		ArrayList<Future<Image>> list = new ArrayList<Future<Image>>();
		for(int i = 0; i < sR; i++) {
			Future<Image> result = ex.submit(new ImageRunnable(new Image(WIDTH, HEIGHT, 2.2), p, g, dpth));
			list.add(result);
			img.put(i, list.get(i).get());
		}
		ex.shutdown();
		 */

		Image fin = merge(img);
		t.stop();
		System.out.print("Render-Time: ");
		t.println(true, true, true);
		try {
			fin.write(String.format(PATH, FILE));
			System.out.println("Wrote image: " + String.format(PATH, FILE));
		} catch (IOException e) {
			System.err.println(String.format("Something went wrong writing: %s: %s", PATH, e));
		}
	}

	final static Image merge(ConcurrentHashMap<Integer, Image> img) {
		Image fin = new Image(WIDTH, HEIGHT, 2.2);
		for(int x = 0; x < fin.wdt; x++) {
			for(int y = 0; y < fin.hgt; y++) {
				Vec3 color = new Vec3(0, 0, 0);
				for(int i = 0; i < img.size(); i++) {
					Vec3 col = new Vec3(img.get(i).col[3*(WIDTH*y+x)], img.get(i).col[3*(WIDTH*y+x)+1], img.get(i).col[3*(WIDTH*y+x)+2]);
					color = Vec3.add(color, col);
				}
				color = Vec3.divide(color, img.size());
				fin.setPixel(x, y, color);
			}
		}
		return fin;
	}

	public final static Vec3 radiance(Ray r, Group g, int d) {
		Hit h = g.intersect(r);
		Vec3 e = h.mat.emittedRadiance(r, h);
		Bounce b = h.mat.scatteredRay(r, h);
		if(b.r != null && d > 0) {
			//if(b.r != null && !b.abs && d > 0) {
			//Vec3 dir = new Vec3(0, 0, 0);
			//if(!b.sct) {
			//for(Light l : list) {
			//Light temp = l.getMap(h.pos, g);
			//dir = Vec3.add(dir, Vec3.multiply(Vec3.dotProduct(h.nrm, temp.dir), temp.lum));
			//dir = Vec3.add(dir, Vec3.multiply(Vec3.multiply(h.nrm, temp.dir), temp.lum));
			//}
			//}
			//return Vec3.multiply(b.alb, Vec3.add(dir, Vec3.add(e, radiance(b.r, g, d-1))));
			return Vec3.multiply(b.alb, Vec3.add(e, radiance(b.r, g, d-1)));
		} else {
			return e;
		}
	}

	final static double gradientIncrease(double x, boolean b) {
		double formula = (double) x/(WIDTH-1);
		if(b) {
			return new Double(formula);
		} else {
			return new Double(1.0 - formula);
		}
	}
}