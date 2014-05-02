package by.scodax.bird.control;

import by.scodax.bird.model.Fishka;

/**
 * patrick 27.04.14.
 */
public class ShiftLeftTask extends AbstractTask {

    private float shiftLeft;

    public ShiftLeftTask(Fishka fishka, float shiftLeft) {
        super(fishka);
        this.shiftLeft = shiftLeft;
    }

    @Override
    public boolean execute(float delta) {
        boolean done = false;
        shiftLeft -= delta * SHIFT_SPEED;
        if (shiftLeft < 0.0) {
            shiftLeft = 0;
            done = true;
        }
        getFishka().setShiftLeft(shiftLeft);
        return done;
    }

    @Override
    public float getShiftTime() {
        return shiftLeft;
    }

}
