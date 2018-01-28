package günaydin853872.lum;

import günaydin853872.engine.cgtools.Vec3;

import günaydin853872.engine.util.Group;

public class Light implements Lum {
	public Vec3 pos, dir, lum;
	
	public Light(Vec3 pos, Vec3 dir, Vec3 lum) {
		this.pos = pos;
		this.dir = dir;
		this.lum = lum;
	}

	@Override
	public Light getMap(Vec3 map, Group g) { return null; }
}