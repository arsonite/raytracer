package günaydin853872.engine;

import günaydin853872.engine.cgtools.BoundingBox;
import günaydin853872.engine.cgtools.Vec3;
import günaydin853872.engine.util.Shape;
import günaydin853872.mat.Mat;
import günaydin853872.tex.Texture;

/**
 * The class {@code Background}
 * 
 * @since JDK 1.91 ~ <i>2017</i>
 * @author Burak Günaydin <b>{@code (853872)}</b>
 * @see 
 */
public class Background implements Shape {
	public final double t;
	public final Mat mat;

	public Background(double t, final Mat mat) {
		this.t = t;
		this.mat = mat;
	}

	public Hit intersect(Ray r) {
		return new Hit(t, t, r.pointAt(t), Vec3.normalize(r.pointAt(t)), Texture.bounce(r.dir), mat);
	}

	public BoundingBox bounds() { return new BoundingBox(); }
}