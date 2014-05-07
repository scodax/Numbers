package by.scodax.bird.helpers;

import by.scodax.bird.GameWorld;
import by.scodax.bird.control.Direction;
import by.scodax.bird.model.Numbers;
import by.scodax.bird.ui.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by patrick on 24.04.14.
 */
public class InputHandler implements GestureDetector.GestureListener, InputProcessor {
    private GameWorld myWorld;

    private List<Button> menuButtons;

    private Button restartButton;
    private Button yesButton;
    private Button noButton;
    private Button menuButton;

    private float scaleFactorX;
    private float scaleFactorY;
    private Numbers numbers;

    public InputHandler(GameWorld myWorld, float scaleFactorX,
                        float scaleFactorY, Numbers numbers) {
        this.myWorld = myWorld;

        this.scaleFactorX = scaleFactorX;
        this.scaleFactorY = scaleFactorY;
        this.numbers = numbers;

        menuButtons = new ArrayList<Button>();
        restartButton = new RestartButton(40, 260, myWorld);
        yesButton = new YesButton(100, 550, myWorld);
        noButton = new NoButton(300, 550, myWorld);
        menuButton = new MenuButton(400, 20, myWorld);

        menuButtons.add(restartButton);
        menuButtons.add(yesButton);
        menuButtons.add(noButton);
        menuButtons.add(menuButton);
    }


    private int scaleX(int screenX) {
        return (int) (screenX / scaleFactorX);
    }

    private int scaleY(int screenY) {
        return (int) (screenY / scaleFactorY);
    }

    @Override
    public boolean touchDown(float v, float v2, int i, int i2) {
        float x = v / scaleFactorX;
        float y = v2 / scaleFactorY;
        if (myWorld.isRunning() && menuButton.isClicked(x, y)) {
            menuButton.setPressed(true);
        } else if ((myWorld.isRestart() || myWorld.isExit()) && yesButton.isClicked(x, y)) {
            yesButton.setPressed(true);
        } else if ((myWorld.isRestart() || myWorld.isExit()) && noButton.isClicked(x, y)) {
            noButton.setPressed(true);
        } else if (myWorld.isRunning() && restartButton.isClicked(x, y)) {
            restartButton.setPressed(true);
        }
        return false;
    }

    @Override
    public boolean tap(float v, float v2, int i, int i2) {
        float x = v / scaleFactorX;
        float y = v2 / scaleFactorY;
        if (restartButton.isClicked(x, y) && myWorld.isRunning()) {
            myWorld.setState(GameWorld.GameState.IS_RESTART);
        } else if (myWorld.isRestart() && yesButton.isClicked(x, y)) {
            myWorld.restart();
        } else if (myWorld.isRestart() && noButton.isClicked(x, y)) {
            myWorld.setState(GameWorld.GameState.RUNNING);
        } else if (myWorld.isExit() && yesButton.isClicked(x, y)) {
            Gdx.app.exit();
        } else if (myWorld.isExit() && noButton.isClicked(x, y)) {
            myWorld.setState(GameWorld.GameState.RUNNING);
        }
        resetPressed();
        return false;
    }

    @Override
    public boolean longPress(float v, float v2) {
        resetPressed();
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        if (!myWorld.isRunning()) {
            resetPressed();
            return true;
        }
        Boolean gameOver = null;
        if (Math.abs(velocityX) > Math.abs(velocityY)) {
            if (velocityX > 0) {
                gameOver = numbers.swipe(Direction.Right);
            } else if (velocityX < 0) {
                gameOver = numbers.swipe(Direction.Left);
            } else {
                // Do nothing.
            }
        } else {
            if (velocityY > 0) {
                gameOver = numbers.swipe(Direction.Down);
            } else if (velocityY < 0) {
                gameOver = numbers.swipe(Direction.Up);
            } else {
                // Do nothing.
            }
        }
        if (gameOver != null && gameOver.equals(Boolean.FALSE)) {
            myWorld.setState(GameWorld.GameState.GAMEOVER);
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

    public List<Button> getMenuButtons() {
        return menuButtons;
    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.BACK){
            if (myWorld.isRestart()) {
                myWorld.setState(GameWorld.GameState.RUNNING);
            } else if (myWorld.isRunning()) {
                myWorld.setState(GameWorld.GameState.EXIT);
            } else if (myWorld.isExit()) {
                myWorld.setState(GameWorld.GameState.RUNNING);
            } else if (myWorld.isGameOver()) {
                myWorld.setState(GameWorld.GameState.RUNNING);
            }
        }
        return false;
    }

    @Override
    public boolean keyUp(int i) {
        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchDown(int i, int i2, int i3, int i4) {
        System.out.println("" + i + " : " + i2 + " : " + i3 + " : " + i4);
        return false;
    }

    @Override
    public boolean touchUp(int i, int i2, int i3, int i4) {
        resetPressed();
        return false;
    }

    private void resetPressed() {
        menuButton.setPressed(false);
        yesButton.setPressed(false);
        noButton.setPressed(false);
        restartButton.setPressed(false);
    }

    @Override
    public boolean touchDragged(int i, int i2, int i3) {
        return false;
    }

    @Override
    public boolean mouseMoved(int i, int i2) {
        return false;
    }

    @Override
    public boolean scrolled(int i) {
        return false;
    }
}