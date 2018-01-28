package günaydin853872.engine.util;

import java.util.concurrent.Callable;

import günaydin853872.cam.Camera;
import günaydin853872.engine.Image;
import günaydin853872.engine.Main;
import günaydin853872.engine.cgtools.Random;

/**
 * The class {@code ImageRunnable} is my thread-pooled sampling-implementation.
 * 
 * @since JDK 1.91 ~ <i>2017</i>
 * @author Burak Günaydin {@code (853872)}
 * @see
 */
public class ImageRunnable implements Callable<Image> {
	//public class ImageRunnable implements Runnable {
	final int d;
	final Camera c;
	final Group g;
	public final Image img;

	public ImageRunnable(final Image img, final Camera c, final Group g, final int d) {
		this.img = img;
		this.c = c;
		this.g = g;
		this.d = d;
	}

	public Image call() throws Exception {
		for(int x = 0; x < img.wdt; x++) {
			for(int y = 0; y < img.hgt; y++) {
				double rx = Random.random();
				double ry = Random.random();
				double xs = x + rx;
				double ys = y + ry;
				img.setPixel(x, y, Main.radiance(c.generateRay(xs, ys), g, d));
			}
		}
		return img;
	}
}