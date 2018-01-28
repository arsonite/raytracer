package günaydin853872.tex;

import günaydin853872.engine.cgtools.Vec3;

public class Color extends Texture {
	public final Vec3 col;

	public Color(Vec3 col) {
		this.col = col;
	}

	@Override
	public Vec3 getMap(Vec3 tex) { return col; }
}