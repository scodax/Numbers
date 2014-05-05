package by.scodax.bird;

import by.scodax.bird.helpers.AssetLoader;
import by.scodax.bird.helpers.InputHandler;
import by.scodax.bird.model.Fishka;
import by.scodax.bird.model.Numbers;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.input.GestureDetector;

public class GameScreen implements Screen {

    private GameWorld world;
    private GameRenderer renderer;
    private float runTime;
    private final Numbers numbers;

    // This is the constructor, not the class declaration
    public GameScreen() {

        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        float gameWidth = 480;
        float gameHeight = screenHeight / (screenWidth / gameWidth);

        numbers = new Numbers();
        world = new GameWorld(numbers);
        InputHandler inputHandler = new InputHandler(world, screenWidth / gameWidth, screenHeight / gameHeight, numbers);
        Gdx.input.setInputProcessor(new InputMultiplexer(new GestureDetector(20, 0.5f, 2, 0.15f, inputHandler), inputHandler));
        Gdx.input.setCatchBackKey(true);

        renderer = new GameRenderer(world, (int) gameHeight, numbers, inputHandler);
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
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!resize");
    }

    @Override
    public void show() {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!show");
        if (AssetLoader.prefs.contains("resume")) {
            for (int i = 0; i < numbers.getFishki().length; i++) {
                Fishka[] row = numbers.getFishki()[i];
                for (int j = 0; j < row.length; j++) {
                    Fishka fishka = row[j];
                    int value = AssetLoader.prefs.getInteger("f:" + i + ":" + j);
                    fishka.setValue(value != 0 ? value : null);
                    fishka.setDisplayedValue(value != 0 ? value : null);
                }
            }
            numbers.setScore(AssetLoader.prefs.getInteger("score", 0));
        }  else {
            numbers.init();
        }
    }

    @Override
    public void hide() {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!hide");
    }

    @Override
    public void pause() {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!pause");
        for (int i = 0; i < numbers.getFishki().length; i++) {
            Fishka[] row = numbers.getFishki()[i];
            for (int j = 0; j < row.length; j++) {
                Fishka fishka = row[j];
                AssetLoader.prefs.putInteger("f:" + i + ":" + j, fishka.getValue() != null ? fishka.getValue() : 0);
            }
        }
        AssetLoader.prefs.putInteger("score", numbers.getScore());
        AssetLoader.prefs.putBoolean("resume", true);
        AssetLoader.prefs.flush();
    }

    @Override
    public void resume() {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!resume");
    }

    @Override
    public void dispose() {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!dispose");
    }

}