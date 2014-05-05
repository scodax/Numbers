package by.scodax.bird.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * patrick 04.05.14.
 */
public interface Button {

    boolean isClicked(float x, float y);

    void draw(SpriteBatch batch);

    void click();

    void setOnClickListener(OnClickListener listener);

    void setPressed(boolean pressed);

}
