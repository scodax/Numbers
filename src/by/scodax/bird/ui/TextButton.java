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
public class TextButton implements Button {
    private final float x;
    private final float y;
    private final GameWorld gameWorld;
    private final Rectangle bounds;
    private final String text;

    public TextButton(float x, float y, GameWorld gameWorld, String text) {
        this.x = x;
        this.y = y;
        this.gameWorld = gameWorld;

        this.text = text;
        bounds = new Rectangle(x - 20, y - 20, AssetLoader.scoreFont.getBounds(text).width + 40, 70);
    }

    public boolean isClicked(float x, float y) {
        return bounds.contains(x, y);
    }

    public void draw(SpriteBatch batch) {
        if (!gameWorld.isRestart() && !gameWorld.isExit())
            return;
        Color color = AssetLoader.scoreFont.getColor();
        AssetLoader.scoreFont.setColor(ColorUtils.COLOR_TEXT_BUTTON);
        AssetLoader.scoreFont.draw(batch, text, x, y);
        AssetLoader.scoreFont.setColor(color);
    }

}
