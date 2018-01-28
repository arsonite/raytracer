package günaydin853872.engine;

import günaydin853872.engine.cgtools.Vec3;
import günaydin853872.mat.Mat;

/**
 * The class {@code Hit}
 * 
 * @since JDK 1.91 ~ <i>2017</i>
 * @author Burak Günaydin <b>{@code (853872)}</b>
 * @see 
 */
public class Hit implements Comparable<Hit> {
	public final double t0, t1;
	public final Vec3 pos, nrm, tex;
	public final Mat mat;

	public Hit(double t0, double t1, Vec3 pos, Vec3 nrm, Vec3 tex, Mat mat) {
		this.t0 = t0;
		this.t1 = t1;
		this.pos = pos;
		this.nrm = nrm;
		//nrm = Vec3.normalize(pos);
		this.tex = tex;
		this.mat = mat;
	}

	public int compareTo(Hit h) {
		if(t0 < h.t0) {
			return -1;
		} else if(t0 > h.t0) {
			return 1;
		}
		return 0;
	}
}