package by.scodax.bird.control;

import by.scodax.bird.model.Fishka;

/**
 * User: Administrator
 * Date: 02.05.14
 * Time: 15:48
 */
public class ZoomTask implements Task {

    public static final float MAX_ZOOM = 1.07f;
    private float zoom = 1;
    private boolean grow = true;

    @Override
    public boolean execute(float delta, Fishka fishka) {
        boolean done = false;
        if (grow) {
            zoom += delta * UNION_COEF;
            if (zoom >= MAX_ZOOM) {
                zoom = MAX_ZOOM;
                grow = false;
            }
        } else {
            zoom -= delta * UNION_COEF;
            if (zoom <= 1) {
                zoom = 1;
                done = true;
            }
        }
        fishka.setZoom(zoom);
        return done;
    }

    @Override
    public float getTime() {
        return (MAX_ZOOM - 1) / UNION_COEF;
    }
}
