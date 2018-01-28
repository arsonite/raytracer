package günaydin853872.prim;

import günaydin853872.engine.Hit;
import günaydin853872.engine.Ray;

import günaydin853872.engine.cgtools.Vec3;

import günaydin853872.mat.Material;

import günaydin853872.tex.Texture;

/**
 * The class {@code Cone}
 * 
 * @since JDK 1.91 ~ <i>2017</i>
 * @author Burak Günaydin <b>{@code (853872)}</b>
 * @see 
 */
public class Cone extends Primitive {
	public final double hgt, rad;

	public Cone(Vec3 pos, double hgt, double rad, Material mat) {
		super(pos, mat);
		this.hgt = hgt;
		this.rad = rad;
	}

	public Hit intersect(Ray r) {
		double qdr = Math.pow(rad, 2) / Math.pow(hgt, 2);
		double a = (r.dir.x * r.dir.x) + (r.dir.z * r.dir.z) - (qdr * (r.dir.y * r.dir.y));
		double b = (2 * (r.pos.x - pos.x) * r.dir.x) + (2 * (r.pos.z - pos.z) * r.dir.z) + (2 * qdr * (hgt - r.pos.y + pos.y) * r.dir.y);
		double c = (Math.pow(r.pos.x - pos.x, 2)) + (Math.pow(r.pos.z - pos.z, 2)) - (qdr * (Math.pow(hgt - r.pos.y + pos.y, 2)));
		double d = Math.pow(b, 2) - 4 * (a * c);
		if(d < 0.0) {
			return null;
		}

		double t1 = (-b - Math.sqrt(d))/(2*a);
		double t2 = (-b + Math.sqrt(d))/(2*a);
		double t = Math.min(t1, t2);
		if(t < Primitive.LIM) {
			return null;
		}

		double roo = r.pos.y + t * r.dir.y;
		if(roo >= pos.y && roo <= pos.y + hgt) {
			//Vec3 nrm = Vec3.normalize(Vec3.subtract(r.pointAt(t), pos));
			Vec3 nrm = Vec3.normalize(r.pointAt(t));
			//Vec3 nrm = Vec3.normalize(pos);
			//Vec3 nrm = Vec3.normalize(loc);
			return new Hit(t, t2, r.pointAt(t), nrm, Texture.bounce(nrm), mat);
		} else {
			return null;
		}
	}
}