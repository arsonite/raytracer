package günaydin853872.cam;

import günaydin853872.engine.Ray;
import günaydin853872.engine.cgtools.Mat4;
import günaydin853872.engine.cgtools.Vec3;

public class Panorama extends Camera {
	public Panorama(int wdt, int hgt) {
		super(wdt, hgt);
	}

	public Ray generateRay(double x, double y) {
		Ray r = new Ray(pos, new Vec3(
				- Math.cos(2*Math.PI*(x/wdt)),
				Math.cos(Math.PI*(y/(hgt))),
				- Math.sin(2*Math.PI*(x/wdt)))
				);
		return t.toWorld(r);
	}

	public Mat4 frontal(double z) {
		t.t = Mat4.translate(new Vec3(0, 0, z));
		return t.t;
	}

	public Mat4 frontalHigh(double y, double z, double phi) {
		t.t = Mat4.translate(new Vec3(0, y, z)).multiply(Mat4.rotate(new Vec3(1, 0, 0), phi));
		return t.t;
	}

	public Mat4 frontalSide(double rad) {
		if(rad < 0) {
			t.t = Mat4.translate(new Vec3(-5, 0, -2.5)).multiply((Mat4.rotate(new Vec3(0, 1, 0), rad)));
		} else {
			t.t = Mat4.translate(new Vec3(5, 0, 2.5)).multiply((Mat4.rotate(new Vec3(0, 1, 0), rad)));
		}
		return t.t;
	}

	public Mat4 bird(double x, double y, double z) {
		t.t = Mat4.translate(new Vec3(x, y, z)).multiply((Mat4.rotate(new Vec3(1, 0, 0), -90)));
		return t.t;
	}

	public Mat4 all(double x, double y, double z, double radX, double radY, double radZ) {
		t.t = Mat4.translate(new Vec3(x, y, z)).multiply((Mat4.rotate(new Vec3(0, 1, 0), radY)));
		return t.t;
	}
}