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
public class TextButton extends AbstractButton{
    private final GameWorld gameWorld;
    private final String text;

    public TextButton(float x, float y, GameWorld gameWorld, String text) {
        super(x, y, new Rectangle(x - 20, y - 20, AssetLoader.scoreFont.getBounds(text).width + 40, 70));
        this.gameWorld = gameWorld;
        this.text = text;
    }

    public void draw(SpriteBatch batch) {
        if (!gameWorld.isRestart() && !gameWorld.isExit())
            return;
        Color color = AssetLoader.scoreFont.getColor();
        AssetLoader.scoreFont.setColor(ColorUtils.COLOR_TEXT_BUTTON);
        if (isPressed())
            AssetLoader.scoreFont.getColor().a = .8f;
        AssetLoader.scoreFont.draw(batch, text, x, y);
        AssetLoader.scoreFont.setColor(color);
    }
}
