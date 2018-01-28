package günaydin853872.engine;

import günaydin853872.engine.cgtools.Mat4;
import günaydin853872.engine.cgtools.Vec3;

/**
 * The class {@code Transform}
 * 
 * @since JDK 1.91 ~ <i>2017</i>
 * @author Burak Günaydin <b>{@code (853872)}</b>
 * @see 
 */
public class Transform {
	public Mat4 t, rt;
	
	public Transform(Mat4 t) {
		this.t = t;
		invert();
	}

	public Transform() {
		emptyCache();
		invert();
	}
	
	final void emptyCache() { t = Mat4.identity; };

	final void invert() { rt = t.invertFull(); }
	
	final void foo(Mat4 m) {
		//emptyCache();
		t = t.multiply(m);
		invert();
	}

	public final void move(double x, double y, double z) { foo(Mat4.translate(new Vec3(x, y, z))); }

	public final void scale(double x, double y, double z) { foo(Mat4.scale(new Vec3(x, y, z))); }

	public final void rotateX(double phi) { foo(Mat4.rotate(new Vec3(1, 0, 0), phi)); }
	
	public final void rotateY(double phi) { foo(Mat4.rotate(new Vec3(0, 1, 0), phi)); }
	
	public final void rotateZ(double phi) { foo(Mat4.rotate(new Vec3(0, 0, 1), phi)); }

	public final Ray toLocal(Ray r) { return new Ray(rt.transformPoint(r.pos), rt.transformDirection(r.dir)); }
	
	public final Ray toWorld(Ray r) { return new Ray(t.transformPoint(r.pos), t.transformDirection(r.dir)); }
	
	public final Hit toWorld(Hit h) { return new Hit(h.t0, h.t1, t.transformPoint(h.pos), t.transformDirection(h.nrm), h.tex, h.mat); }
}