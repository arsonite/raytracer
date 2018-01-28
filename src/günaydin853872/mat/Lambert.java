package günaydin853872.mat;

import günaydin853872.engine.Bounce;
import günaydin853872.engine.Hit;
import günaydin853872.engine.Ray;

import günaydin853872.engine.cgtools.Random;
import günaydin853872.engine.cgtools.Vec3;
import günaydin853872.tex.Texture;

/**
 * The class {@code Lambert}
 * 
 * @since JDK 1.91 ~ <i>2017</i>
 * @author Burak Günaydin <b>{@code (853872)}</b>
 * @see 
 */
public class Lambert extends Material {
	public Lambert(Texture tex) {
		super(tex);
	}

	public Vec3 emittedRadiance(Ray r, Hit h) { return new Vec3(0,0,0); }

	public Bounce scatteredRay(Ray r, Hit h) {
		Vec3 rnd;
		do {
			rnd = new Vec3(Random.random()*2-1, Random.random()*2-1, Random.random()*2-1);
		} while(rnd.length() > 1);
		Vec3 d = Vec3.normalize(Vec3.add(h.nrm, rnd));
		return new Bounce(Vec3.multiply(Vec3.dotProduct(d, h.nrm), tex.getMap(h.tex)), new Ray(h.pos, d), false, false);
	}
}