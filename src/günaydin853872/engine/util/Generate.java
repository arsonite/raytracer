package günaydin853872.engine.util;

import günaydin853872.engine.cgtools.Random;
import günaydin853872.engine.cgtools.Vec3;

import günaydin853872.mat.Lambert;
import günaydin853872.mat.Material;
import günaydin853872.mat.Mirror;

import günaydin853872.prim.Sphere;

import günaydin853872.tex.Color;

/**
 * The class {@code Generate}
 * 
 * @since JDK 1.91 ~ <i>2017</i>
 * @author Burak Günaydin <b>{@code (853872)}</b>
 * @see 
 */
public class Generate {
	public static void sphericalCube(Group g, int x, int y, int z, int n) {
		for(int x2 = 0; x2 < n; x2++) {
			for(int y2 = 0; y2 < n; y2++) {
				for(int z2 = 0; z2 < n; z2++) {
					Vec3 pos = new Vec3(x+(x2+x2), y+(y2+y2), z-(z2+z2));
					Vec3 col = new Vec3(Random.random(), Random.random(), Random.random());
					Material mat = null;
					switch(new Random().nextInt(2)) {
					case 0:
						mat = new Lambert(new Color(col));
						break;
					case 1:
						mat = new Mirror(new Color(col));
						break;
					}
					g.addShape(new Sphere(pos, 1, mat));
				}
			}
		}
	}
}