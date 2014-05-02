package by.scodax.bird.control;

import by.scodax.bird.model.Fishka;

/**
 * patrick 27.04.14.
 */
public class NewbieTask implements Task {

    private float alpha = 0;

    public NewbieTask() {
    }

    @Override
    public boolean execute(float delta, Fishka fishka) {
        boolean done = false;
        alpha += delta * ALPHA_SPEED;
        if (alpha > 1) {
            alpha = 1;
            done = true;
        }
        fishka.setAlpha(alpha);
        return done;
    }

    @Override
    public float getTime() {
        return 1 / 7;
    }
}
