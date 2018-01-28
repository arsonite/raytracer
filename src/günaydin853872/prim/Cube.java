package günaydin853872.prim;

import günaydin853872.engine.Hit;
import günaydin853872.engine.Ray;

import günaydin853872.engine.cgtools.Vec3;

import günaydin853872.mat.Material;
import günaydin853872.tex.Texture;

/**
 * The class {@code Cube}
 * 
 * @since JDK 1.91 ~ <i>2017</i>
 * @author Burak Günaydin <b>{@code (853872)}</b>
 * @see 
 */
public class Cube extends Primitive {
	public final Vec3 size;

	public Cube(Vec3 pos, Vec3 size, Material mat) {
		super(pos, mat);
		this.size = size;
	}

	public Hit intersect(Ray r){
		double dx = 1.0 / r.dir.x;
		double dy = 1.0 / r.dir.y;
		double dz = 1.0 / r.dir.z;

		double t1 = (pos.x - r.pos.x) * dx;
		double t2 = (size.x - r.pos.x) * dx;
		double t3 = (pos.y - r.pos.y) * dy;
		double t4 = (size.y - r.pos.y) * dy;
		double t5 = (pos.z - r.pos.z) * dz;
		double t6 = (size.z - r.pos.z) * dz;

		double min1 = Math.max(Math.min(t1, t2), Math.min(t3, t4));
		double tmin = Math.max(min1, Math.min(t5, t6));

		double max1 = Math.min(Math.max(t1, t2), Math.max(t3, t4));
		double tmax = Math.min(max1, Math.max(t5, t6));
		double t;

		if(tmax < 0) {
			return null;
		}

		if(tmin > tmax) {
			return null;
		}

		t = tmin;

		Vec3 h = r.pointAt(t);
		Vec3 xDir = new Vec3(1, 0, 0);
		Vec3 yDir = new Vec3(0, 1, 0);
		Vec3 zDir = new Vec3(0, 0, 1);
		Vec3 nrm = Vec3.normalize(Vec3.subtract(r.pointAt(t), pos));

		if(Math.abs(pos.x - h.x) < Primitive.LIM) {
			return new Hit(t, t2, h, xDir, Texture.bounce(nrm), mat);
		}
		if(Math.abs(pos.y - h.y) < Primitive.LIM) {
			return new Hit(t, t2, h, yDir, Texture.bounce(nrm), mat);
		}
		if(Math.abs(pos.z - h.z) < Primitive.LIM) {
			return new Hit(t, t2, h, zDir, Texture.bounce(nrm), mat);
		}
		if(Math.abs(size.x - h.x) < Primitive.LIM) {
			return new Hit(t, t2, h, Vec3.multiply(1,xDir), Texture.bounce(nrm), mat);
		}
		if(Math.abs(size.y - h.y) < Primitive.LIM) {
			return new Hit(t, t2, h, Vec3.multiply(1,yDir), Texture.bounce(nrm), mat);
		}
		if(Math.abs(size.z - h.z) < Primitive.LIM) {
			return new Hit(t, t2, h, Vec3.multiply(1.0,zDir), Texture.bounce(nrm), mat);
		}
		return new Hit(t, t2, h, nrm, Texture.bounce(nrm), mat);
	}
}
/*
	public Hit intersect(Ray r) {
		double xMin, yMin, zMin, xMax, yMax, zMax;
		if(r.dir.x >= 0) {
			xMin = pos.x - r.pos.x / r.dir.x;
			xMax = size.x - r.pos.x / r.dir.x;
		} else {
			xMin = size.x - r.pos.x / r.dir.x;
			xMax = pos.x - r.pos.x / r.dir.x;
		}
		if(r.dir.y >= 0) {
			yMin = pos.y - r.pos.y / r.dir.y;
			yMax = size.y - r.pos.y / r.dir.y;
		} else {
			yMin = size.y - r.pos.y / r.dir.y;
			yMax = pos.y - r.pos.y / r.dir.y;
		}
		if(xMin > yMax || yMin > xMax) {
			return null;
		}
		if(yMin > xMin) {
			xMin = yMin;
		}
		if(yMax < xMax) {
			xMax = yMax;
		}
		if(r.dir.z >= 0) {
			zMin = pos.z - r.pos.z / r.dir.z;
			zMax = size.z - r.pos.z / r.dir.z;
		} else {
			zMin = size.z - r.pos.z / r.dir.z;
			zMax = pos.z - r.pos.z / r.dir.z;
		}
		if(xMin > zMax || zMin > xMax) {
			return null;
		}
		if(zMin > xMin) {
			xMin = zMin;
		}
		if(zMax < xMax) {
			xMax = zMax;
		}
		double minT = Math.max(xMin, Math.max(yMin, zMin));
		double maxT = Math.min(xMax, Math.min(yMax, zMax));
		if(minT > maxT || minT > r.t1 || minT < r.t0) {
			return null;
		}
		double t = minT;
		Vec3 nrm = Vec3.normalize(Vec3.subtract(r.pointAt(t), pos));
		return new Hit(t, r.pointAt(t), nrm, Texture.bounce(nrm), mat);
	}
 */

/*
	public Hit intersect(Ray r) {
		double xMin, yMin, zMin, xMax, yMax, zMax;

		double inx = 1 / r.dir.x;
		double x1 = (pos.x - r.pos.x) * inx;
		double x2 = (size.x - r.pos.x) * inx;
		xMin = Math.min(x1, x2);
		xMax = Math.max(x1, x2);

		double iny = 1 / r.dir.y;
		double y1 = (pos.y - r.pos.y) * iny;
		double y2 = (size.x - r.pos.y) * iny;
		yMin = Math.min(y1, y2);
		yMax = Math.max(y1, y2);

		double inz = 1 / r.dir.z;
		double z1 = (pos.z - r.pos.z) * inz;
		double z2 = (size.x - r.pos.z) * inz;
		zMin = Math.min(z1, z2);
		zMax = Math.max(z1, z2);

		double minT = Math.max(xMin, Math.max(yMin, zMin));
		double maxT = Math.min(xMax, Math.min(yMax, zMax));
		if(minT > maxT || minT > r.t1 || minT < r.t0) {
			return null;
		}

		double t = minT;
		Vec3 scale = Vec3.multiply(t, r.dir);
		Vec3 loc = Vec3.add(r.pos, scale);
		Vec3 nrm = Vec3.normalize(Vec3.subtract(r.pointAt(t), pos));
		return new Hit(t, r.pointAt(t), nrm, Texture.bounce(nrm), mat);
	}
 */