package by.scodax.bird;

import by.scodax.bird.helpers.InputHandler;
import by.scodax.bird.model.Numbers;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.input.GestureDetector;

public class GameScreen implements Screen {

    private GameWorld world;
    private GameRenderer renderer;
    private float runTime;

    // This is the constructor, not the class declaration
    public GameScreen() {

        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        float gameWidth = 480;
        float gameHeight = screenHeight / (screenWidth / gameWidth);
        int midPointY = (int) (gameHeight / 2);

        Numbers numbers = new Numbers();
        numbers.init();
        world = new GameWorld(midPointY,numbers);
        Gdx.input.setInputProcessor(new GestureDetector(20, 0.5f, 2, 0.15f, new InputHandler(world, screenWidth / gameWidth, screenHeight / gameHeight, numbers)));

        renderer = new GameRenderer(world, (int) gameHeight, midPointY, numbers);
        world.setRenderer(renderer);
    }

    @Override
    public void render(float delta) {
        runTime += delta;
        world.update(delta);
        renderer.render(delta, runTime);
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }

}