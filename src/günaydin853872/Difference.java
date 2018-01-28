package günaydin853872;

import günaydin853872.engine.Hit;
import günaydin853872.engine.Ray;

import günaydin853872.engine.util.Shape;

import günaydin853872.prim.CSG;

public class Difference extends CSG {
	public Difference(Shape... arr) {
		super(arr);
	}

	public Hit intersect(Ray r) {
		double t1 = 0;
		Hit h = list.get(0).intersect(r);
		Hit h2 = h;
		if(h == null) {
			return null;
		}
		for(Shape sh : list) {
			if(sh.equals(list.get(0))) {
				continue;
			}
			Hit hs = sh.intersect(r);
			if(hs == null) {
				continue;
			}
			if(h2 != h) { //Cut from sum
				if(h2.t0 < hs.t0 && h2.t1 < hs.t1) {
					return null;
				}
			}
			if(hs.t1 > t1 && (hs.t1 < h.t1 && hs.t1 > h.t0)) { //Carve
				h2 = hs;
			}
			if((h.t0 > hs.t0 && h.t0 < h.t1)) { //Cut exterior
				h2 = hs;
			}
		}
		if(h2 == h) {
			return h;
		}
		return new Hit(h2.t1, h.t1, r.pointAt(h2.t1), h2.nrm, null, h2.mat);
	}
}