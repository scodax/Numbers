package by.scodax.bird.helpers;

import by.scodax.bird.GameWorld;
import by.scodax.bird.control.Direction;
import by.scodax.bird.model.Numbers;
import by.scodax.bird.ui.SimpleButton;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by patrick on 24.04.14.
 */
public class InputHandler implements GestureDetector.GestureListener {
    private GameWorld myWorld;

    private List<SimpleButton> menuButtons;

    private SimpleButton playButton;

    private float scaleFactorX;
    private float scaleFactorY;
    private Numbers numbers;

    public InputHandler(GameWorld myWorld, float scaleFactorX,
                        float scaleFactorY, Numbers numbers) {
        this.myWorld = myWorld;

        this.scaleFactorX = scaleFactorX;
        this.scaleFactorY = scaleFactorY;
        this.numbers = numbers;

        menuButtons = new ArrayList<SimpleButton>();
        playButton = new SimpleButton(
                136 / 2 - (AssetLoader.playButtonUp.getRegionWidth() / 2),
                500, 29, 16, AssetLoader.playButtonUp,
                AssetLoader.playButtonDown);
        menuButtons.add(playButton);
    }


    private int scaleX(int screenX) {
        return (int) (screenX / scaleFactorX);
    }

    private int scaleY(int screenY) {
        return (int) (screenY / scaleFactorY);
    }

    public List<SimpleButton> getMenuButtons() {
        return menuButtons;
    }

    @Override
    public boolean touchDown(float v, float v2, int i, int i2) {
        return false;
    }

    @Override
    public boolean tap(float v, float v2, int i, int i2) {
        return false;
    }

    @Override
    public boolean longPress(float v, float v2) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        if (Math.abs(velocityX) > Math.abs(velocityY)) {
            if (velocityX > 0) {
                System.out.println("right");
                numbers.swipe(Direction.Right);
            } else if (velocityX < 0) {
                System.out.println("left");
                numbers.swipe(Direction.Left);
            } else {
                // Do nothing.
            }
        } else {
            if (velocityY > 0) {
                System.out.println("down");
                numbers.swipe(Direction.Down);
            } else if (velocityY < 0) {
                System.out.println("up");
                numbers.swipe(Direction.Up);
            } else {
                // Do nothing.
            }
        }
        return true;
    }

    @Override
    public boolean pan(float v, float v2, float v3, float v4) {
        return false;
    }

    @Override
    public boolean panStop(float v, float v2, int i, int i2) {
        return false;
    }

    @Override
    public boolean zoom(float v, float v2) {
        return false;
    }

    @Override
    public boolean pinch(Vector2 vector2, Vector2 vector22, Vector2 vector23, Vector2 vector24) {
        return false;
    }
}