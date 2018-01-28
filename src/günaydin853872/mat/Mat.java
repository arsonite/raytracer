package günaydin853872.mat;

import günaydin853872.engine.Bounce;
import günaydin853872.engine.Hit;
import günaydin853872.engine.Ray;
import günaydin853872.engine.cgtools.Vec3;

/**
 * The interface {@code Material_I}
 * 
 * @since JDK 1.91 ~ <i>2017</i>
 * @author Burak Günaydin <b>{@code (853872)}</b>
 * @see 
 */
public abstract interface Mat {
	public Vec3 emittedRadiance(Ray r, Hit h);
	public Bounce scatteredRay(Ray r, Hit h);
}