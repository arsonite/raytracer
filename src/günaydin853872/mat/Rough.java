package günaydin853872.mat;

import günaydin853872.engine.Bounce;
import günaydin853872.engine.Hit;
import günaydin853872.engine.Ray;

import günaydin853872.engine.cgtools.Random;
import günaydin853872.engine.cgtools.Vec3;
import günaydin853872.tex.Texture;

//TODO: Generalisierung, funktioniert bislang nur bei Planes

/**
 * The class {@code Roughs}
 * 
 * @since JDK 1.91 ~ <i>2017</i>
 * @author Burak Günaydin <b>{@code (853872)}</b>
 * @see 
 */
public class Rough extends Material {
	public final double rgh;

	public Rough(Texture tex, final double rgh) {
		super(tex);
		this.rgh = rgh;
	}

	public Vec3 emittedRadiance(Ray r, Hit h) { return new Vec3(0.0, 0.0, 0.0); }

	public Bounce scatteredRay(Ray r, Hit h) {
		Vec3 a = Vec3.multiply(h.nrm, r.dir);
		Vec3 b = Vec3.multiply(a, h.nrm);
		Vec3 c = Vec3.multiply(2, b);
		Vec3 d = Vec3.subtract(r.dir, c);
		Vec3 rnd;
		do {
			rnd = new Vec3(Random.random()*2-1, Random.random()*2-1, Random.random()*2-1);
		} while(rnd.length() > 1); 
		if(Vec3.dotProduct(d, h.nrm) < 0) {
			return new Bounce(null, null, false, false);
        }
		Vec3 dir = Vec3.add(Vec3.multiply(rgh, rnd), d);
		//return new Ray(h.pos, Vec3.add(Vec3.multiply(rgh, rnd), d));
		return new Bounce(tex.getMap(h.tex), new Ray(h.pos, dir), true, false);
	}
}