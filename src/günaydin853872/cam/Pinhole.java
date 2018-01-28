package günaydin853872.cam;

import günaydin853872.engine.cgtools.Mat4;
import günaydin853872.engine.cgtools.Vec3;

/**
 * The class {@code Pinhole}
 * 
 * @since JDK 1.91 ~ <i>2017</i>
 * @author Burak Günaydin <b>{@code (853872)}</b>
 * @see 
 */
public class Pinhole extends Camera {
	public Pinhole(int wdt, int hgt, double apt) {
		super(wdt, hgt, apt);
	}

	public Mat4 frontal(double z) {
		t.t = Mat4.translate(new Vec3(0, 0, z));
		return t.t;
	}

	public Mat4 frontalHigh(double y, double z, double phi) {
		t.t = Mat4.translate(new Vec3(0, y, z)).multiply(Mat4.rotate(new Vec3(1, 0, 0), phi));
		return t.t;
	}

	// TODO: Math.sin - .toRadians() formula
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