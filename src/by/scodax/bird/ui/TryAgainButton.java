package by.scodax.bird.ui;

import by.scodax.bird.GameWorld;
import by.scodax.bird.helpers.AssetLoader;
import by.scodax.bird.helpers.ColorUtils;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * patrick 04.05.14.
 */
public class TryAgainButton extends AbstractButton {
    public static final String RESTART = "TRY AGAIN";
    private final GameWorld gameWorld;

    public TryAgainButton(float x, float y, GameWorld gameWorld) {
        super(x, y, new Rectangle(x - 20, y - 20, AssetLoader.scoreFont.getBounds(RESTART).width + 40, 70));
        this.gameWorld = gameWorld;
    }

    public void draw(SpriteBatch batch) {
        if (gameWorld.isExit()) {
            Color color = AssetLoader.scoreFont.getColor();
            AssetLoader.scoreFont.setColor(ColorUtils.COLOR_RESTART_ACTIVE);
            AssetLoader.scoreFont.draw(batch, RESTART, x, y);
            AssetLoader.scoreFont.setColor(color);
        }
    }

}
