package günaydin853872.mat;

import günaydin853872.engine.Bounce;
import günaydin853872.engine.Hit;
import günaydin853872.engine.Ray;

import günaydin853872.engine.cgtools.Random;
import günaydin853872.engine.cgtools.Vec3;

import günaydin853872.tex.Texture;

//TODO: Generalisierung, funktioniert bislang nur bei Shapes die keine Plane sind

/**
 * The class {@code Glass}
 * 
 * @since JDK 1.91 ~ <i>2017</i>
 * @author Burak Günaydin <b>{@code (853872)}</b>
 * @see 
 */
public class Glass extends Material {
	final double N1 = 1.0, N2 = 1.5;
	
	public Glass(Texture tex) {
		super(tex);
	}

	public Vec3 emittedRadiance(Ray r, Hit h) { return new Vec3(0.0, 0.0, 0.0); }

	public Bounce scatteredRay(Ray r, Hit h) {
		if(refraction(r, h) != null) {
			if(Random.random() > schlick(r, h)) {
				return new Bounce(tex.getMap(h.tex), refraction(r, h), true, false);
			} else {
				return new Bounce(tex.getMap(h.tex), reflection(r, h), true, false);
			}
		} else {
			return new Bounce(tex.getMap(h.tex), reflection(r, h), true, false);
		}
	}

	final double schlick(Ray r, Hit h) {
		double r0 = Math.pow((N1-N2)/(N1+N2), 2);
		return r0 + (1 - r0) * Math.pow(1 + Vec3.dotProduct(h.nrm, r.dir), 5);
	}
	
	final Ray refraction(Ray r, Hit h) {
		double rf = N1/N2;
		Vec3 n = h.nrm;

		if(Vec3.dotProduct(n, r.dir) > 0) {
			n = Vec3.multiply(-1, h.nrm);
			rf = N2/N1;
		}
		
		double c = Vec3.dotProduct(Vec3.multiply(-1, n), r.dir);
		double d = 1 - Math.pow(rf, 2) * (1 - Math.pow(c, 2));

		if(d >= 0) {
			return new Ray(h.pos, Vec3.normalize(Vec3.add(Vec3.multiply(rf, r.dir), Vec3.multiply(rf * c - Math.sqrt(d), n))));
		}
		return null;
	}

	final Ray reflection(Ray r, Hit h) { return new Mirror(tex).scatteredRay(r, h).r; }
}