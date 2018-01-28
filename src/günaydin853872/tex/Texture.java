package günaydin853872.tex;

import günaydin853872.engine.cgtools.Vec3;

/**
 * The class {@code Texture}
 * 
 * @since JDK 1.91 ~ <i>2017</i>
 * @author Burak Günaydin <b>{@code (853872)}</b>
 * @see 
 */
public abstract class Texture implements Tex {
	public Vec3 getMap(Vec3 tex) { return null; }

	/**
	 * Kreis-Formel von seinen Folien
	 * 
	 * @param nrm
	 * @return
	 */
	public static Vec3 bounce(Vec3 nrm) {
		return new Vec3((Math.atan2(nrm.x, nrm.z) + Math.PI) / (2 * Math.PI), Math.acos(nrm.y) / Math.PI, 0);
	}
}