package by.scodax.bird.control;

import by.scodax.bird.model.Fishka;

/**
 * patrick 27.04.14.
 */
public class ShiftDownTask extends AbstractTask {

    private float shiftDown;

    public ShiftDownTask(Fishka fishka, float shiftDown) {
        super(fishka);
        this.shiftDown = shiftDown;
    }

    @Override
    public boolean execute(float delta) {
        boolean done = false;
        shiftDown -= delta * SHIFT_SPEED;
        if (shiftDown < 0.0) {
            shiftDown = 0;
            done = true;
        }
        getFishka().setShiftDown(shiftDown);
        return done;
    }

    @Override
    public float getShiftTime() {
        return shiftDown;
    }

}
