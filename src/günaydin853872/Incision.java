package günaydin853872;

import günaydin853872.engine.Hit;
import günaydin853872.engine.Ray;

import günaydin853872.engine.util.Shape;

import günaydin853872.prim.CSG;

public class Incision extends CSG {
	public Incision(Shape... arr) {
		super(arr);
	}

	public Hit intersect(Ray r) {
		Hit h = null;
		for(Shape s : list) {
			Hit foo = s.intersect(r);
			if(foo == null) {
				return null;
			}
			if(h == null) {
				h = foo;
			} else if(foo.t0 >= h.t0) {
				h = foo;
			}
		}
		return h;
	}
}
