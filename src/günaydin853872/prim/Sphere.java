package günaydin853872.prim;

import günaydin853872.engine.Hit;
import günaydin853872.engine.Ray;

import günaydin853872.engine.cgtools.BoundingBox;
import günaydin853872.engine.cgtools.Vec3;

import günaydin853872.mat.Material;

import günaydin853872.tex.Texture;

/**
 * The class {@code Sphere}
 * 
 * @since JDK 1.91 ~ <i>2017</i>
 * @author Burak Günaydin <b>{@code (853872)}</b>
 * @see 
 */
public class Sphere extends Primitive {
	public final double rad;

	public Sphere(Vec3 pos, double rad, Material mat) {
		super(pos, mat);
		this.rad = rad;
	}

	public Hit intersect(Ray r) {
		Vec3 loc = Vec3.subtract(r.pos, pos);
		double a = Vec3.dotProduct(r.dir, r.dir);
		double b = Vec3.dotProduct(Vec3.multiply(2, loc), r.dir);
		double c = Vec3.dotProduct(loc, loc) - Math.pow(rad, 2);
		double sqr = Math.pow(b, 2)-(4*a*c);
		double t, t2;
		t = t2 = 0;
		if(sqr < 0) {
			return null;
		} else if(sqr == 0) {
			t = -b/(2*a);
		} else if(sqr > 0) {
			t = (-b+Math.sqrt(sqr))/(2*a);
			t2 = (-b-Math.sqrt(sqr))/(2*a);
			t = Double.min(t, t2);
		}
		if(t < Primitive.LIM) {
			return null;
		}
		Vec3 nrm = Vec3.normalize(Vec3.subtract(r.pointAt(t), pos));
		//Vec3 nrm = Vec3.normalize(r.pointAt(t));
		//Vec3 nrm = Vec3.normalize(pos);
		//Vec3 nrm = Vec3.normalize(loc);
		return new Hit(t, t2, r.pointAt(t), nrm, Texture.bounce(nrm), mat);
	}

	public BoundingBox bounds() {
		/*
		double x1, x2, y1, y2, z1, z2;
		x1 = pos.x - rad;
		x2 = pos.x + rad;
		y1 = pos.y - rad;
		y2 = pos.y + rad;
		z1 = pos.z - rad;
		z2 = pos.z + rad;
		Vec3 min = new Vec3(Math.min(x1, x2), Math.min(y1, y2), Math.min(z1, z2));
		Vec3 max = new Vec3(Math.max(x1, x2), Math.max(y1, y2), Math.max(z1, z2));
		*/
		
		Vec3 p = new Vec3(pos.x+rad, pos.y+rad, pos.z+rad);
		Vec3 n = new Vec3(pos.x-rad, pos.y-rad, pos.z-rad);
		Vec3 min = new Vec3(Math.min(p.x, n.x), Math.min(p.y, n.y), Math.min(p.y, n.y));
		Vec3 max = new Vec3(Math.max(p.x, n.x), Math.max(p.y, n.y), Math.max(p.y, n.x));
		return new BoundingBox(min, max);
	}
}