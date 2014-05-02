package by.scodax.bird;

import by.scodax.bird.model.Numbers;

/**
 * User: Administrator
 * Date: 24.04.14
 * Time: 17:50
 */
public class GameWorld {

    private int score = 0;
    private float runTime = 0;
    private int midPointY;
    private GameRenderer renderer;

    private GameState currentState;
    private Numbers numbers;

    public enum GameState {
        MENU, READY, RUNNING, GAMEOVER, HIGHSCORE
    }

    public GameWorld(int midPointY, Numbers numbers) {
        this.numbers = numbers;
        currentState = GameState.MENU;
        this.midPointY = midPointY;
    }

    public void update(float delta) {
        runTime += delta;
//
//        switch (currentState) {
//            case READY:
//            case MENU:
//                updateReady(delta);
//                break;
//
//            case RUNNING:
//                updateRunning(delta);
//                break;
//            default:
//                break;
//        }
        updateRunning(delta);

    }

    private void updateReady(float delta) {
    }

    public void updateRunning(float delta) {
        if (delta > .15f) {
            delta = .15f;
        }
        numbers.update(delta);
    }


    public int getScore() {
        return score;
    }

    public void addScore(int increment) {
        score += increment;
    }

    public void start() {
        currentState = GameState.RUNNING;
    }

    public void ready() {
        currentState = GameState.READY;
        renderer.prepareTransition(0, 0, 0, 1f);
    }

    public void restart() {
        score = 0;
        ready();
    }

    public boolean isReady() {
        return currentState == GameState.READY;
    }

    public boolean isGameOver() {
        return currentState == GameState.GAMEOVER;
    }

    public boolean isHighScore() {
        return currentState == GameState.HIGHSCORE;
    }

    public boolean isMenu() {
        return currentState == GameState.MENU;
    }

    public boolean isRunning() {
        return currentState == GameState.RUNNING;
    }

    public void setRenderer(GameRenderer renderer) {
        this.renderer = renderer;
    }

}