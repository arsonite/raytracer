package günaydin853872.cam;

import günaydin853872.engine.Ray;
import günaydin853872.engine.Transform;
import günaydin853872.engine.cgtools.Mat4;
import günaydin853872.engine.cgtools.Vec3;
import günaydin853872.prim.Primitive;

/**
 * The class {@code Camera_C}
 * 
 * @since JDK 1.91 ~ <i>2017</i>
 * @author Burak Günaydin <b>{@code (853872)}</b>
 * @see 
 */
public abstract class Camera implements Cam {
	public final int wdt, hgt;
	public double apt;
	public final Vec3 pos;
	public Transform t;

	public Camera(int wdt, int hgt, double apt) {
		this.wdt = wdt;
		this.hgt = hgt;
		this.apt = apt;
		pos = Primitive.NULL_POS;
		t = new Transform();
		frontal(5);
	}
	
	public Camera(int wdt, int hgt) {
		this.wdt = wdt;
		this.hgt = hgt;
		pos = Primitive.NULL_POS;
		t = new Transform();
		frontal(5);
	}
	
	public Ray generateRay(double x, double y) {
		Ray r = new Ray(pos, new Vec3(x-(wdt/2), (hgt/2)-y, -((wdt/2)/Math.tan(apt/2))));
		return t.toWorld(r);
	}
	
	public Mat4 frontal(double z) { return null; }
	
	public Mat4 frontalHigh(double y, double z, double phi) { return null; }
	
	public Mat4 frontalSide(double rad) { return null; }
	
	public Mat4 bird(double x, double y, double z) { return null; }
	
	public Mat4 all(double x, double y, double z, double radX, double radY, double radZ) { return null; }
}