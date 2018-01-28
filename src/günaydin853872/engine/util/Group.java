package günaydin853872.engine.util;

import java.util.Collections;
import java.util.LinkedList;

import günaydin853872.engine.Hit;
import günaydin853872.engine.Ray;
import günaydin853872.engine.Transform;

import günaydin853872.engine.cgtools.BoundingBox;

/**
 * The class {@code Group}
 * 
 * @since JDK 1.91 ~ <i>2017</i>
 * @author Burak Günaydin <b>{@code (853872)}</b>
 * @see 
 */
public class Group implements Shape {
	public Transform t;
	public LinkedList<Shape> list;

	public Group() {
		list = new LinkedList<Shape>();
		t = new Transform();
	}

	public Group(Group g) {
		this.list = g.list;
		this.t = g.t;
	}

	public Group(Shape... arr) {
		list = new LinkedList<Shape>();
		for(Shape s : arr) {
			list.add(s);
		}
		t = new Transform();
	}

	public Group(Transform t, Shape... arr) {
		list = new LinkedList<Shape>();
		for(Shape s : arr) {
			list.add(s);
		}
		this.t = t;
	}

	public Hit intersect(Ray r) {
		Ray rt =  t.toLocal(r);
		LinkedList<Hit> hl = new LinkedList<Hit>();
		for(Shape s : list) {
			if(s.intersect(rt) != null) {
				hl.add(s.intersect(rt));
			}
		}
		if(hl.isEmpty()) {
			return null;
		}
		Collections.sort(hl);
		Hit h = hl.getFirst();
		return t.toWorld(h);
	}

	public boolean visible(Ray r) {
		for(Shape s : list) {
			Hit h = s.intersect(r);
			if(h != null && h.t0 < r.t1) {
				return false;
			}
		}
		return true;
	}

	public void addShape(Shape s) { list.add(s); }

	public final void move(double x, double y, double z) { t.move(x, y, z); }

	public final void rotateX(double phi) { t.rotateX(phi); }

	public final void rotateY(double phi) { t.rotateY(phi); }

	public final void rotateZ(double phi) { t.rotateZ(phi); }

	public final void scale(double x, double y, double z) { t.scale(x, y, z); }

	public BoundingBox bounds() { return null; }
}