package by.scodax.bird.control;

import by.scodax.bird.model.Fishka;

/**
 * patrick 27.04.14.
 */
public class ShiftTask implements Task {

    private float shift;
    private final Direction direction;

    public ShiftTask(float shift, Direction direction) {
        this.shift = shift;
        this.direction = direction;
    }

    @Override
    public boolean execute(float delta, Fishka fishka) {
        boolean done = false;
        shift -= delta * SHIFT_SPEED;
        if (shift < 0.0) {
            shift = 0;
            done = true;
        }
        switch (direction) {
            case Up:
                fishka.setShiftUp(shift);
                break;
            case Down:
                fishka.setShiftDown(shift);
                break;
            case Left:
                fishka.setShiftLeft(shift);
                break;
            case Right:
                fishka.setShiftRight(shift);
                break;
        }
        return done;
    }

    @Override
    public float getTime() {
        return shift / SHIFT_SPEED;
    }

}
