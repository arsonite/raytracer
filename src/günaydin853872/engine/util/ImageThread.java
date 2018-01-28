package günaydin853872.engine.util;

import günaydin853872.cam.Camera;
import günaydin853872.engine.Image;
import günaydin853872.engine.Main;

import günaydin853872.engine.cgtools.Random;
import günaydin853872.engine.cgtools.Vec3;

/**
 * The class {@code ImageThread} is my multi-threaded sampling-implementation.
 * 
 * @since JDK 1.91 ~ <i>2017</i>
 * @author Burak Günaydin {@code (853872)}
 * @see
 */
public class ImageThread extends Thread {
	final int sR, d;
	final Camera c;
	final Group g;
	public final Image img;

	public ImageThread(final Image img, final Camera c, final Group g, final int sR, final int d) {
		this.img = img;
		this.c = c;
		this.g = g;
		this.sR = sR;
		this.d = d;
	}

	public void run() {
		for(int x = 0; x < img.wdt; x++) {
			for(int y = 0; y < img.hgt; y++) {
				Vec3 col = new Vec3(0, 0, 0);
				for(int xi = 0; xi < sR; xi++) {
					for(int yi = 0; yi < sR; yi++) {
						double xs = x + ((xi + Random.random()) / sR);
						double ys = y + ((yi + Random.random()) / sR);
						col = Vec3.add(col, Main.radiance(c.generateRay(xs, ys), g, d));
					}
				}
				col = Vec3.divide(col, Math.pow(sR, 2));
				img.setPixel(x, y, col);
			}
		}
	}
}