package by.scodax.bird.control;

import by.scodax.bird.model.Fishka;

/**
 * patrick 27.04.14.
 */
public class ShiftUpTask extends AbstractTask {

    private float shiftUp;

    public ShiftUpTask(Fishka fishka, float shiftUp) {
        super(fishka);
        this.shiftUp = shiftUp;
    }

    @Override
    public boolean execute(float delta) {
        boolean done = false;
        shiftUp -= delta * SHIFT_SPEED;
        if (shiftUp < 0.0) {
            shiftUp = 0;
            done = true;
        }
        getFishka().setShiftUp(shiftUp);
        return done;
    }

    @Override
    public float getShiftTime() {
        return shiftUp;
    }

}
