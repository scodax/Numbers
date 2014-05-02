package by.scodax.bird.control;

import by.scodax.bird.model.Fishka;

/**
 * patrick 27.04.14.
 */
public class NewbieTask extends AbstractTask {

    private float alpha = 0;

    public NewbieTask(Fishka fishka) {
        super(fishka);
    }

    @Override
    public boolean execute(float delta) {
        boolean done = false;
        alpha += delta * ALPHA_SPEED;
        if (alpha > 1) {
            alpha = 1;
            done = true;
        }
        getFishka().setAlpha(alpha);
        return done;
    }

    @Override
    public float getShiftTime() {
        return 1;
    }
}
