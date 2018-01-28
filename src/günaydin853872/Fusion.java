package günaydin853872;

import günaydin853872.engine.Hit;
import günaydin853872.engine.Ray;

import günaydin853872.engine.util.Shape;

import günaydin853872.prim.CSG;

public class Fusion extends CSG {
	public Fusion(Shape... arr) {
		super(arr);
	}

	public Hit intersect(Ray r) {
		Hit hit = null;
		for(Shape s : list) {
			Hit h = s.intersect(r);
			if(h == null) {
				continue;
			}
			if(hit == null) {
				hit = h;
			} else if(h.t0 < hit.t0) {
				hit = h;
			}
		}
		return hit;
	}
}