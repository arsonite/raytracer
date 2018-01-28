package günaydin853872.lum;

import günaydin853872.engine.Ray;

import günaydin853872.engine.cgtools.Vec3;

import günaydin853872.engine.util.Group;

public class Focus extends Light {
	public Focus(Vec3 pos, Vec3 dir, Vec3 lum) {
		super(pos, dir, lum);
	}

	@Override
	public Light getMap(Vec3 map, Group g) {
		dir = Vec3.subtract(pos, Vec3.normalize(map));
		lum = new Vec3(0.0, 0.0, 0.0);
		Light result = new Light(new Vec3(0.0, 0.0, 0.0), dir, new Vec3(0.0, 0.0, 0.0));
		if(g.visible(new Ray(map, dir))) {
			double d = Math.pow(Vec3.subtract(map, pos).length(), 2);
			lum = Vec3.multiply(1/d, lum);
		}
		return result;
	}
}
