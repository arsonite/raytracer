package günaydin853872.prim;

import günaydin853872.engine.Hit;
import günaydin853872.engine.Ray;

import günaydin853872.engine.cgtools.BoundingBox;
import günaydin853872.engine.cgtools.Random;
import günaydin853872.engine.cgtools.Vec3;
import günaydin853872.engine.util.Shape;
import günaydin853872.mat.Glass;
import günaydin853872.mat.Lambert;
import günaydin853872.mat.Mat;
import günaydin853872.mat.Material;
import günaydin853872.mat.Mirror;
import günaydin853872.mat.Rough;

import günaydin853872.tex.Color;

/**
 * The class {@code Primitive}
 * 
 * @since JDK 1.91 ~ <i>2017</i>
 * @author Burak Günaydin <b>{@code (853872)}</b>
 * @see 
 */
public abstract class Primitive implements Shape {
	public final static double LIM = 0.0001;
	
	public final static Vec3 NULL_POS = new Vec3(0, 0, 0);
	public final static Vec3 POS_L3 = new Vec3(-3, 0, 0);
	public final static Vec3 POS_L6 = new Vec3(-6, 0, 0);
	public final static Vec3 POS_L9 = new Vec3(-9, 0, 0);
	public final static Vec3 POS_R3 = new Vec3(3, 0, 0);
	public final static Vec3 POS_R6 = new Vec3(6, 0, 0);
	public final static Vec3 POS_R9 = new Vec3(9, 0, 0);
	public final static Vec3 NEG_POS = new Vec3(0, -1, 0);
	public final static Vec3 NEG_L3 = new Vec3(-3, -1, 0);
	public final static Vec3 NEG_L6 = new Vec3(-6, -1, 0);
	public final static Vec3 NEG_L9 = new Vec3(-9, -1, 0);
	public final static Vec3 NEG_R3 = new Vec3(3, -1, 0);
	public final static Vec3 NEG_R6 = new Vec3(6, -1, 0);
	public final static Vec3 NEG_R9 = new Vec3(9, -1, 0);

	public final static Lambert LAM_BLU = new Lambert(new Color(Vec3.blue));
	public final static Lambert LAM_RED = new Lambert(new Color(Vec3.red));
	public final static Lambert LAM_GREN = new Lambert(new Color(Vec3.green));
	public final static Lambert LAM_BLCK = new Lambert(new Color(Vec3.black));
	public final static Lambert LAM_WHI = new Lambert(new Color(Vec3.white));
	public final static Lambert LAM_GRAY = new Lambert(new Color(new Vec3(0.5, 0.5, 0.5)));
	public final static Lambert LAM_ANTR = new Lambert(new Color(new Vec3(0.9, 0.95, 0.9)));
	public final static Lambert LAM_RND = new Lambert(new Color(new Vec3(Random.random(), Random.random(), Random.random())));
	
	public final static Mirror MIR = new Mirror(new Color(Vec3.white));
	public final static Rough RGH_1 = new Rough(new Color(new Vec3(0.5, 0.5, 0.5)), 0.1);
	public final static Rough RGH_25 = new Rough(new Color(new Vec3(0.5, 0.5, 0.5)), 0.25);
	public final static Rough RGH_5 = new Rough(new Color(new Vec3(0.5, 0.5, 0.5)), 0.5);
	public final static Glass GLS = new Glass(new Color(Vec3.white));

	public final Vec3 pos;
	public final Material mat;

	public Primitive(final Vec3 pos, final Material mat) {
		this.pos = pos;
		this.mat = mat;
	}
	
	public Hit intersect(Ray r) { return null; }
	
	public BoundingBox bounds() { return null; }
}