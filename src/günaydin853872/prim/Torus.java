package günaydin853872.prim;

import günaydin853872.engine.Hit;
import günaydin853872.engine.Ray;

import günaydin853872.engine.cgtools.Vec3;

import günaydin853872.mat.Material;

/**
 * The class {@code Torus}
 * 
 * @since JDK 1.91 ~ <i>2017</i>
 * @author Burak Günaydin <b>{@code (853872)}</b>
 * @see 
 */
public class Torus extends Primitive {
	public final double in, ex;

	public Torus(Vec3 pos, double in, double ex, Material mat) {
		super(pos, mat);
		this.in = in;
		this.ex = ex;
	}

	public Hit intersect(Ray ray) {
		/*
		double R = ex;
		double r = in;
		Vec3 d = ray.dir;
		Vec3 p = ray.pos;

		double a = Vec3.dotProduct(d, d);
		double b = 2 * Vec3.dotProduct(p, d);
		double g = Vec3.dotProduct(p, p) - Math.pow(r, 2) - Math.pow(R, 2);

		double[] pol = new double[]{
				Math.pow(g, 2) + 4 * Math.pow(R, 2) * Math.pow(p.x, 2) -
				4 * Math.pow(R, 2) * Math.pow(r, 2),
				2 * b * g + 8 * Math.pow(R, 2) * p.x * d.x,
				Math.pow(b, 2) + 2 * a * g + 4 * Math.pow(R, 2) * Math.pow(d.x, 2),
				2 * a * b,
				Math.pow(a, 2)
		};

		Set<Double> sol = null;
		if(sol.size() == 0) {
			return null;
		}

		Vec3 cI = null;
		double sD = ray.t1;
		for(Double t: sol) {
			Vec3 iP = Vec3.add(ray.pos, Vec3.multiply(t, ray.dir));
			double dis = Vec3.dotProduct(ray.pos, iP);
			if(dis < sD) {
				sD = dis;
				cI = iP;
			}
		}
		if(sD < Primitive.LIM) {
			return null;
		}
		Vec3 nrm = normalize(cI);
		return new Hit(sD, ray.pointAt(sD), nrm, Texture.bounce(nrm), mat);
		*/
		return null;
	}

	private final Vec3 normalize(Vec3 pos) {
		double c = Math.pow(pos.x, 2) + Math.pow(pos.y, 2) + Math.pow(pos.z, 2) - Math.pow(in, 2) - Math.pow(ex, 2);
		return new Vec3(4*pos.x*c + 8*Math.pow(ex, 2)*pos.x, 4*pos.y*c, 4*pos.z*c);
	}
}