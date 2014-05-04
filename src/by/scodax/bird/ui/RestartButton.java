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
public class RestartButton implements Button {
    public static final String RESTART = "RESTART";
    private final float x;
    private final float y;
    private final GameWorld gameWorld;
    private final Rectangle bounds;

    public RestartButton(float x, float y, GameWorld gameWorld) {
        this.x = x;
        this.y = y;
        this.gameWorld = gameWorld;

        bounds = new Rectangle(x - 20, y - 20, AssetLoader.scoreFont.getBounds(RESTART).width + 40, 70);
    }

    public boolean isClicked(float x, float y) {
        return bounds.contains(x, y);
    }

    public void draw(SpriteBatch batch) {
        Color color = AssetLoader.scoreFont.getColor();
        if (gameWorld.isRestart()) {
            AssetLoader.scoreFont.setColor(ColorUtils.COLOR_RESTART_INACTIVE);
        } else {
            AssetLoader.scoreFont.setColor(ColorUtils.COLOR_RESTART_ACTIVE);
        }
        AssetLoader.scoreFont.draw(batch, RESTART, x, y);
        AssetLoader.scoreFont.setColor(color);
    }

}
