package by.scodax.bird.control;

import by.scodax.bird.model.Fishka;

/**
 * patrick 27.04.14.
 */
public class ShiftRightTask extends AbstractTask {

    private float shiftRight;

    public ShiftRightTask(Fishka fishka, float shiftRight) {
        super(fishka);
        this.shiftRight = shiftRight;
    }

    @Override
    public boolean execute(float delta) {
        boolean done = false;
        shiftRight -= delta * SHIFT_SPEED;
        if (shiftRight < 0.0) {
            shiftRight = 0;
            done = true;
        }
        getFishka().setShiftRight(shiftRight);
        return done;
    }

    @Override
    public float getShiftTime() {
        return shiftRight;
    }

}
