package by.scodax.bird;

import by.scodax.bird.model.Numbers;
import by.scodax.bird.ui.RestartButton;

/**
 * User: Administrator
 * Date: 24.04.14
 * Time: 17:50
 */
public class GameWorld {

    private GameRenderer renderer;

    private GameState currentState;
    private Numbers numbers;

    public void setState(GameState gameState) {
        currentState = gameState;
    }

    public boolean isRunning() {
        return currentState == GameState.RUNNING;
    }

    public void restart() {
        numbers.reset();
        currentState = GameState.RUNNING;
    }

    public enum GameState {
        RUNNING, IS_RESTART, EXIT
    }

    public GameWorld(Numbers numbers) {
        this.numbers = numbers;
        this.currentState = GameState.RUNNING;
    }

    public void update(float delta) {

        switch (currentState) {
            case IS_RESTART:
            case EXIT:
//                updateReady(delta);
                break;

            case RUNNING:
                updateRunning(delta);
                break;
            default:
                break;
        }
//        updateRunning(delta);

    }

    private void updateReady(float delta) {
    }

    public void updateRunning(float delta) {
        if (delta > .15f) {
            delta = .15f;
        }
        numbers.update(delta);
    }

    public boolean isRestart() {
        return currentState == GameState.IS_RESTART;
    }

    public boolean isExit() {
        return currentState == GameState.EXIT;
    }

    public void setRenderer(GameRenderer renderer) {
        this.renderer = renderer;
    }



}