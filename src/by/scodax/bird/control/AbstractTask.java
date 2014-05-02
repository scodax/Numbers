package by.scodax.bird.control;

import by.scodax.bird.model.Fishka;

/**
 * patrick 27.04.14.
 */
public abstract class AbstractTask implements Task {

    public static final double SHIFT_SPEED = 25;
    public static final int ALPHA_SPEED = 7;

    private final Fishka fishka;

    public AbstractTask(Fishka fishka) {
        this.fishka = fishka;
    }

    public Fishka getFishka() {
        return fishka;
    }
}
