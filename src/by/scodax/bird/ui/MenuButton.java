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
public class MenuButton extends AbstractButton {

    private final GameWorld gameWorld;

    public MenuButton(float x, float y, GameWorld gameWorld) {
        super(x, y, new Rectangle(x - 10, y - 10, 40 + 20, 32 + 20));
        this.gameWorld = gameWorld;
    }

    @Override
    public void draw(SpriteBatch batch) {
        if (isPressed() || !gameWorld.isRunning())
            batch.draw(AssetLoader.menuPressed, x, y, 40, 32);
        else
            batch.draw(AssetLoader.menu, x, y, 40, 32);
    }

}
