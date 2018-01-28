package günaydin853872.tex;

import günaydin853872.engine.Transform;

import günaydin853872.engine.cgtools.ImageTexture;
import günaydin853872.engine.cgtools.Vec3;

/**
 * The class {@code Texture}
 * 
 * @since JDK 1.91 ~ <i>2017</i>
 * @author Burak Günaydin <b>{@code (853872)}</b>
 * @see 
 */
public class Import extends Texture {
	public static String path, type;
	public ImageTexture tex;
	public Transform t;

	public Import(String file) {
		this.t = new Transform();
		try {
			tex = new ImageTexture(path + file + type);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public Import(String file, Transform t) {
		this(file);
		this.t = t;
	}

	public static void setTexturePath(String path, String type) {
		Import.path = path;
		Import.type = "." + type;
	}

	@Override
	public Vec3 getMap(Vec3 map) {
		Vec3 t = this.t.t.transformPoint(map);
		return tex.samplePoint(t.x, t.y);
	}
}