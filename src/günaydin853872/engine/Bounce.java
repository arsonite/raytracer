package günaydin853872.engine;

import günaydin853872.engine.cgtools.Vec3;

/**
 * The class {@code Bounce}
 * 
 * @since JDK 1.91 ~ <i>2017</i>
 * @author Burak Günaydin <b>{@code (853872)}</b>
 * @see 
 */
public class Bounce {
    public final boolean sct, abs;
    public final Vec3 alb;
    public final Ray r;

    public Bounce(Vec3 alb, Ray r, boolean sct, boolean abs) {
        this.alb = alb;
        this.r = r;
        this.sct = sct;
        this.abs = abs;
    }
}