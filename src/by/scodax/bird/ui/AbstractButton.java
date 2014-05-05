package by.scodax.bird.ui;

import com.badlogic.gdx.math.Rectangle;

/**
 * patrick 05.05.14.
 */
public abstract class AbstractButton implements Button {

    protected final float x;
    protected final float y;
    private final Rectangle bounds;
    private boolean pressed = false;
    private OnClickListener listener;

    protected AbstractButton(float x, float y, Rectangle bounds) {
        this.x = x;
        this.y = y;
        this.bounds = bounds;
    }

    @Override
    public boolean isClicked(float x, float y) {
        return bounds.contains(x, y);
    }

    @Override
    public void click() {
        if (listener != null)
            listener.click();
    }

    @Override
    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }

    public boolean isPressed() {
        return pressed;
    }

    public void setPressed(boolean pressed) {
        this.pressed = pressed;
    }
}
