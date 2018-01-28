package günaydin853872.engine;

import java.io.IOException;

import günaydin853872.engine.cgtools.ImageWriter;
import günaydin853872.engine.cgtools.Vec3;

/**
 * The class {@code Image}
 * 
 * @since JDK 1.91 ~ <i>2017</i>
 * @author Burak Günaydin <b>{@code (853872)}</b>
 * @see 
 */
public class Image {
	public final int wdt, hgt;
	public double gam;
	public double[] col;

	public Image(final int wdt, final int hgt, final double gam) {
		this.wdt = wdt;
		this.hgt = hgt;
		this.gam = gam;
		col = new double[3*(wdt*hgt)];
	}
	
	public Image(final int wdt, final int hgt) {
		this.wdt = wdt;
		this.hgt = hgt;
		gam = 0;
		col = new double[3*(wdt*hgt)];
	}

	public void setPixel(int x, int y, Vec3 color) {
		if(gam != 0) {
			color = gammaCorrection(color);
		}
		col[3*(wdt*y+x)] = color.x;
		col[3*(wdt*y+x)+1] = color.y;
		col[3*(wdt*y+x)+2] = color.z;
	}

	public void write(String filename) throws IOException {
		ImageWriter img = new ImageWriter(col, wdt, hgt);
		img.write(filename);
	}

	final Vec3 gammaCorrection(Vec3 color) { return new Vec3(Math.pow(color.x, 1/gam), Math.pow(color.y, 1/gam), Math.pow(color.z, 1/gam)); }
}