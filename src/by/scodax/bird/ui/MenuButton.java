package by.scodax.bird.ui;

import by.scodax.bird.GameWorld;
import by.scodax.bird.helpers.AssetLoader;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * User: Administrator
 * Date: 05.05.14
 * Time: 11:18
 */
public class MenuButton implements Button {

    private final float x;
    private final float y;
    private final GameWorld gameWorld;
    private final Rectangle bounds;
    private boolean pressed = false;

    public MenuButton(float x, float y, GameWorld gameWorld) {
        this.x = x;
        this.y = y;
        this.gameWorld = gameWorld;
        bounds = new Rectangle(x - 10, y - 10, 40 + 20, 32 + 20);
    }

    @Override
    public boolean isClicked(float x, float y) {
        return bounds.contains(x, y);
    }

    @Override
    public void draw(SpriteBatch batch) {
        if (pressed || !gameWorld.isRunning())
            batch.draw(AssetLoader.menuPressed, x, y, 40, 32);
        else
            batch.draw(AssetLoader.menu, x, y, 40, 32);
    }

    public void setPressed(boolean pressed) {
        this.pressed = pressed;
    }
}
