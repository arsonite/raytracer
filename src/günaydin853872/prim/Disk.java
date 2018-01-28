package günaydin853872.prim;

import günaydin853872.engine.Hit;
import günaydin853872.engine.Ray;

import günaydin853872.engine.cgtools.Vec3;

import günaydin853872.mat.Material;

import günaydin853872.tex.Texture;

/**
 * The class {@code Disk}
 * 
 * @since JDK 1.91 ~ <i>2017</i>
 * @author Burak Günaydin <b>{@code (853872)}</b>
 * @see 
 */
public class Disk extends Primitive {
	public final double rad;

	public Disk(Vec3 pos, double rad, Material mat) {
		super(pos, mat);
		this.rad = rad;
	}

	public Hit intersect(Ray r) {
		Vec3 nrm = Vec3.normalize(pos);
		double t = (Vec3.dotProduct(Vec3.subtract(pos, r.pos), nrm)) / (Vec3.dotProduct(r.dir, nrm));
		if(t < 0.0001) {
			return null;
		}
		if(Vec3.subtract(pos, r.pointAt(t)).length() < Math.pow(rad, 2)) {
			return new Hit(t, t, r.pointAt(t), Vec3.normalize(nrm), Texture.bounce(nrm), mat);	
		} else {
			return null;
		}
	}
}