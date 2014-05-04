package by.scodax.bird.control;

import by.scodax.bird.model.Fishka;

/**
 * patrick 27.04.14.
 */
public interface Task {

    public static final float SHIFT_SPEED = 20;
    public static final int ALPHA_SPEED = 5;
    public static final float UNION_COEF = 0.7f;

    boolean execute(float delta, Fishka fishka);

    float getTime();


}
