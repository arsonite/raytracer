package günaydin853872.cam;

import günaydin853872.engine.Ray;
import günaydin853872.engine.cgtools.Mat4;

/**
 * The class {@code Camera_I}
 * 
 * @since JDK 1.91 ~ <i>2017</i>
 * @author Burak Günaydin <b>{@code (853872)}</b>
 * @see 
 */
public interface Cam {
	public Ray generateRay(double x, double y);
	public Mat4 frontal(double z);
	public Mat4 frontalHigh(double y, double z, double phi);
	public Mat4 frontalSide(double rad);
	public Mat4 bird(double x, double y, double z);
	public Mat4 all(double x, double y, double z, double radX, double radY, double radZ);
}