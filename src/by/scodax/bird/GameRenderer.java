package by.scodax.bird;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;
import by.scodax.bird.helpers.*;
import by.scodax.bird.model.Fishka;
import by.scodax.bird.model.Numbers;
import by.scodax.bird.ui.SimpleButton;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.List;

/**
 * User: Administrator
 * Date: 24.04.14
 * Time: 17:50
 */
public class GameRenderer {

    public static final int CELL_SIZE = 90;
    public static final int FONT_SIZE = 16;
    public static final int CORNER_RADIUS = 5;

    private GameWorld myWorld;
    private OrthographicCamera cam;
    private ShapeRenderer shapeRenderer;

    private SpriteBatch batcher;

    private int midPointY;

    // Game Assets
    private TextureRegion field;

    // Tween stuff
    private TweenManager manager;
    private Value alpha = new Value();

    // Buttons
    private List<SimpleButton> menuButtons;
    private Color transitionColor;
    private TextureRegion star;

    private Numbers numbers;

    public GameRenderer(GameWorld world, int gameHeight, int midPointY, Numbers numbers) {
        myWorld = world;

        this.midPointY = midPointY;
        this.numbers = numbers;
//        this.menuButtons = ((InputHandler) Gdx.input.getInputProcessor())
//                .getMenuButtons();

        cam = new OrthographicCamera();
        cam.setToOrtho(true, 480, gameHeight);

        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(cam.combined);
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);

        initGameObjects();
        initAssets();

        transitionColor = new Color();
        prepareTransition(255, 255, 255, .5f);

    }

    private void initGameObjects() {

    }

    private void initAssets() {
        field = AssetLoader.field;
        star = AssetLoader.star;
//        noStar = AssetLoader.noStar;
    }



    private void drawMenuUI() {

        for (SimpleButton button : menuButtons) {
            button.draw(batcher);
        }

    }

    public void render(float delta, float runTime) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

//        Draw Background color
        shapeRenderer.setColor(ColorUtils.COLOR_BACKGROUND);
        shapeRenderer.rect(0, 0, 480, 800);

        shapeRenderer.end();

        batcher.begin();
        batcher.disableBlending();

        batcher.draw(field, 40, 300, 400, 400);
        batcher.enableBlending();
        batcher.draw(AssetLoader.scoreCurrent, 192, 70, 120, 60);
        batcher.draw(AssetLoader.scoreHigh, 320, 70, 120, 60);
        String scoreS = "" + numbers.getScore();
        BitmapFont.TextBounds scoreSBounds = AssetLoader.scoreFont.getBounds(scoreS);
        AssetLoader.scoreFont.draw(batcher, scoreS, 192 + 60 - scoreSBounds.width / 2, 100);
        String highScoreS = "" + AssetLoader.getHighScore();
        BitmapFont.TextBounds highScoreSBounds = AssetLoader.scoreFont.getBounds(highScoreS);
        AssetLoader.scoreFont.draw(batcher, highScoreS, 320 + 60 - highScoreSBounds.width / 2, 100);

        batcher.draw(AssetLoader.patrick, 40, 35, 140, 105);
        batcher.end();


        for (int i = 0; i < numbers.getFishki().length; i++) {
            Fishka[] column = numbers.getFishki()[i];
            for (int j = 0; j < column.length; j++) {
                Fishka fishka = column[j];
                if (fishka.getDisplayedValue() == null)
                    continue;
                renderFishka(i, j, fishka);
            }
        }
    }

    private void renderFishka(int i, int j, Fishka fishka) {
        float zoomDelta = (fishka.getZoom() * CELL_SIZE - CELL_SIZE) / 2;
        float x = 48 + i * (CELL_SIZE + 8) + fishka.getShiftLeft() * (CELL_SIZE + 8) - fishka.getShiftRight() * (CELL_SIZE + 8) - zoomDelta;
        float y = 308 + j * (CELL_SIZE + 8) + fishka.getShiftUp() * (CELL_SIZE + 8) - fishka.getShiftDown() * (CELL_SIZE + 8) - zoomDelta;
        float size = CELL_SIZE * fishka.getZoom();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(ColorUtils.getFishkaColor(fishka.getDisplayedValue()));

        shapeRenderer.rect(x + CORNER_RADIUS, y, size - CORNER_RADIUS * 2, size);
        shapeRenderer.rect(x, y + CORNER_RADIUS, size, size - CORNER_RADIUS * 2);

        shapeRenderer.arc(x + CORNER_RADIUS, y + CORNER_RADIUS, CORNER_RADIUS, 180, 90);
        shapeRenderer.arc(x + CORNER_RADIUS, y + size - CORNER_RADIUS, CORNER_RADIUS, 90, 90);
        shapeRenderer.arc(x + size - CORNER_RADIUS, y + CORNER_RADIUS, CORNER_RADIUS, 270, 90);
        shapeRenderer.arc(x + size - CORNER_RADIUS, y + size - CORNER_RADIUS, CORNER_RADIUS, 0, 90);

        shapeRenderer.end();

        batcher.begin();
        batcher.enableBlending();

        String s = "" + fishka.getDisplayedValue();
        BitmapFont font;
        if (fishka.getDisplayedValue() <= 4)
            font = AssetLoader.blackFont;
        else
            font = AssetLoader.whiteFont;
        BitmapFont.TextBounds bounds = font.getBounds(s);
        font.draw(batcher, s, x + (CELL_SIZE - bounds.width) / 2, y + CELL_SIZE / 2 + bounds.height / 2);

        batcher.end();
    }


    public void prepareTransition(int r, int g, int b, float duration) {
        transitionColor.set(r / 255.0f, g / 255.0f, b / 255.0f, 1);
        alpha.setValue(1);
        Tween.registerAccessor(Value.class, new ValueAccessor());
        manager = new TweenManager();
        Tween.to(alpha, -1, duration).target(0)
                .ease(TweenEquations.easeOutQuad).start(manager);
    }

    private void drawTransition(float delta) {
        if (alpha.getValue() > 0) {
            manager.update(delta);
            Gdx.gl.glEnable(GL20.GL_BLEND);
            Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(transitionColor.r, transitionColor.g,
                    transitionColor.b, alpha.getValue());
            shapeRenderer.rect(0, 0, 136, 300);
            shapeRenderer.end();
            Gdx.gl.glDisable(GL20.GL_BLEND);

        }
    }

}
