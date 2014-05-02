package by.scodax.bird.control;

import by.scodax.bird.model.Fishka;

/**
 * patrick 27.04.14.
 */
public class DisappearInTask extends AbstractTask {

    private float shift;
    private float shifted = 0;
    private float alpha = 1;
    private float waitTime;
    private final Direction direction;

    public DisappearInTask(Fishka fishka, float shift, float waitTime, Direction direction) {
        super(fishka);
        this.shift = shift;
        this.waitTime = waitTime;
        this.direction = direction;
    }

    @Override
    public boolean execute(float delta) {
        boolean done = false;
        if (waitTime > 0) {
            waitTime -= delta * SHIFT_SPEED;
        } else {
            shifted += delta * SHIFT_SPEED;
            if (alpha > 0) {
                alpha -= delta * ALPHA_SPEED;
                alpha = 0;
            }
            if (shifted >= shift) {
                done = true;
            }
        }
        switch (direction) {
            case Left:
                getFishka().setShiftLeft(-shifted);
                break;
            case Right:
                getFishka().setShiftRight(-shifted);
                break;
            case Up:
                getFishka().setShiftUp(-shifted);
                break;
            case Down:
                getFishka().setShiftDown(-shifted);
                break;
        }
        getFishka().setAlpha(alpha);
        return done;
    }

    @Override
    public float getShiftTime() {
        return shift;
    }

}
