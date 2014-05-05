package by.scodax.bird.ui;

import by.scodax.bird.GameWorld;
import by.scodax.bird.helpers.AssetLoader;
import by.scodax.bird.helpers.ColorUtils;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * patrick 04.05.14.
 */
public class RestartButton extends AbstractButton {
    public static final String RESTART = "RESTART";
    private final GameWorld gameWorld;

    public RestartButton(float x, float y, GameWorld gameWorld) {
        super(x, y, new Rectangle(x - 20, y - 20, AssetLoader.scoreFont.getBounds(RESTART).width + 40, 70));
        this.gameWorld = gameWorld;
    }

    public void draw(SpriteBatch batch) {
        Color color = AssetLoader.scoreFont.getColor();
        if (!gameWorld.isRunning()) {
            AssetLoader.scoreFont.setColor(ColorUtils.COLOR_RESTART_INACTIVE);
        } else {
            AssetLoader.scoreFont.setColor(ColorUtils.COLOR_RESTART_ACTIVE);
        }
        if (isPressed())
            AssetLoader.scoreFont.getColor().a = .8f;
        AssetLoader.scoreFont.draw(batch, RESTART, x, y);
        AssetLoader.scoreFont.setColor(color);
    }

}
