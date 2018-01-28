package günaydin853872.engine.util;

import günaydin853872.engine.Hit;
import günaydin853872.engine.Ray;

import günaydin853872.engine.cgtools.BoundingBox;

/**
 * The interface {@code Shape}
 * 
 * @since JDK 1.91 ~ <i>2017</i>
 * @author Burak Günaydin <b>{@code (853872)}</b>
 * @see 
 */
public interface Shape {
	public Hit intersect(Ray r);
	public BoundingBox bounds();
}