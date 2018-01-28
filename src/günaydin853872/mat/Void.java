package günaydin853872.mat;

import günaydin853872.engine.Bounce;
import günaydin853872.engine.Hit;
import günaydin853872.engine.Ray;

import günaydin853872.engine.cgtools.Vec3;
import günaydin853872.tex.Texture;

/**
 * The class {@code Void}
 * 
 * @since JDK 1.91 ~ <i>2017</i>
 * @author Burak Günaydin <b>{@code (853872)}</b>
 * @see 
 */
public class Void extends Material {
	public Void(Texture tex) {
		super(tex);
	}
	
	public Vec3 emittedRadiance(Ray r, Hit h) { return tex.getMap(h.tex); }

	public Bounce scatteredRay(Ray r, Hit h) { return new Bounce(null, null, false, true); }
}