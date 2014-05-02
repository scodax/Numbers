package by.scodax.bird.control;

import by.scodax.bird.model.Fishka;

/**
 * User: Administrator
 * Date: 02.05.14
 * Time: 16:18
 */
public class WaitTask implements Task {

    private final float time2wait;
    private float elapsed = 0;

    public WaitTask(float time2wait) {
        this.time2wait = time2wait;
    }

    @Override
    public boolean execute(float delta, Fishka fishka) {
        elapsed += delta;
        return elapsed > time2wait;
    }

    @Override
    public float getTime() {
        return time2wait;
    }
}
