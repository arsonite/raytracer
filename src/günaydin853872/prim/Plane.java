package günaydin853872.prim;

import günaydin853872.engine.Hit;
import günaydin853872.engine.Ray;

import günaydin853872.engine.cgtools.BoundingBox;
import günaydin853872.engine.cgtools.Vec3;

import günaydin853872.mat.Material;

import günaydin853872.tex.Texture;

/**
 * The class {@code Plane}
 * 
 * @since JDK 1.91 ~ <i>2017</i>
 * @author Burak Günaydin <b>{@code (853872)}</b>
 * @see 
 */
public class Plane extends Primitive {
	public final Vec3 dir;

	public Plane(Vec3 pos, Vec3 dir, Material mat) {
		super(pos, mat);
		this.dir = dir;
	}

	public Hit intersect(Ray r) {
		double t = (Vec3.dotProduct(Vec3.subtract(pos, r.pos), dir)) / (Vec3.dotProduct(r.dir, dir));
		if(t < 0.0001) {
			return null;
		}
		return new Hit(t, t, r.pointAt(t), Vec3.normalize(dir), Texture.bounce(r.dir), mat);
	}

	public BoundingBox bounds() { return null; }
}