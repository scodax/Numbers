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
public class YesButton extends AbstractButton {

    private final GameWorld gameWorld;

    public YesButton(float x, float y, GameWorld gameWorld) {
        super(x, y, new Rectangle(x - 10, y - 10, 100 + 20, 60 + 20));
        this.gameWorld = gameWorld;
    }

    @Override
    public void draw(SpriteBatch batch) {
        if (gameWorld.isRestart() || gameWorld.isExit()) {
            if (isPressed())
                batch.draw(AssetLoader.yesPressed, x, y, 100, 60);
            else
                batch.draw(AssetLoader.yes, x, y, 100, 60);
        }
    }

}
