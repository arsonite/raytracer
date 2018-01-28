package günaydin853872.engine;

import günaydin853872.engine.cgtools.Vec3;

/**
 * The class {@code Ray}
 * 
 * @since JDK 1.91 ~ <i>2017</i>
 * @author Burak Günaydin <b>{@code (853872)}</b>
 * @see 
 */
public class Ray {
	public double t0, t1;
	public final Vec3 pos, dir;

	public Ray(Vec3 loc, Vec3 dir) {
		this.pos = loc;
		this.dir = Vec3.normalize(dir);
		//this.dir = dir;
		t0 = -10;
		t1 = Double.POSITIVE_INFINITY;
	}

	public Vec3 pointAt(double t) {
		if(t >= t0 && t <= t1) {
			return Vec3.add(pos, Vec3.multiply(t, dir));
		} else {
			throw new IllegalArgumentException("The argument \"" + t + "\" is out of bounds (\"" + t0 + " - " + t1 + "\").");
		}
	}
}