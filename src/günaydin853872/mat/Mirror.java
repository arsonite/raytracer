package günaydin853872.mat;

import günaydin853872.engine.Bounce;
import günaydin853872.engine.Hit;
import günaydin853872.engine.Ray;

import günaydin853872.engine.cgtools.Vec3;
import günaydin853872.tex.Texture;

//TODO: Generalisierung, funktioniert bislang nur bei Shapes die keine Plane sind

/**
 * The class {@code Mirror}
 * 
 * @since JDK 1.91 ~ <i>2017</i>
 * @author Burak Günaydin <b>{@code (853872)}</b>
 * @see 
 */
public class Mirror extends Material {
	public Mirror(Texture tex) {
		super(tex);
	}

	public Vec3 emittedRadiance(Ray r, Hit h) { return new Vec3(0.0, 0.0, 0.0); }

	public Bounce scatteredRay(Ray r, Hit h) {
		Vec3 a = Vec3.multiply(h.nrm, r.dir);
		Vec3 b = Vec3.multiply(a, h.nrm);
		Vec3 c = Vec3.multiply(2, b);
		Vec3 d = Vec3.subtract(r.dir, c);
		return new Bounce(tex.getMap(h.tex), new Ray(h.pos, Vec3.add(d, h.nrm)), true, false);
	}
}