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
    public static final Color COLOR_BACKGROUND = Color.valueOf("faf8ef");
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


    private void drawScore() {
        int length = ("" + myWorld.getScore()).length();
        AssetLoader.shadow.draw(batcher, "" + myWorld.getScore(),
                68 - (3 * length), midPointY - 82);
        AssetLoader.font.draw(batcher, "" + myWorld.getScore(),
                68 - (3 * length), midPointY - 83);
    }

    public void render(float delta, float runTime) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

//        Draw Background color
        shapeRenderer.setColor(COLOR_BACKGROUND);
        shapeRenderer.rect(0, 0, 480, 800);
//
//        // Draw Grass
//        shapeRenderer.setColor(111 / 255.0f, 186 / 255.0f, 45 / 255.0f, 1);
//        shapeRenderer.rect(0, midPointY + 66, 136, 11);
//
//        // Draw Dirt
////        shapeRenderer.setColor(147 / 255.0f, 80 / 255.0f, 27 / 255.0f, 1);
//        shapeRenderer.rect(0, midPointY + 77, 136, 52);

        shapeRenderer.end();

        batcher.begin();
        batcher.disableBlending();

        batcher.draw(field, 40, 300, 400, 400);
//        batcher.draw(bg, 0, midPointY + 23, 136, 43);
        batcher.end();


//        batcher.enableBlending();

//        Color color = batcher.getColor();
//        float oldAlpha = color.a;

        for (int i = 0; i < numbers.getFishki().length; i++) {
            Fishka[] column = numbers.getFishki()[i];
            for (int j = 0; j < column.length; j++) {
                Fishka fishka = column[j];
                if (fishka.getDisplayedValue() == null)
                    continue;
                renderFishka(i, j, fishka);
//                batcher.setColor(color.r, color.g, color.b, fishka.getAlpha());
//                float zoomDelta = (fishka.getZoom() * CELL_SIZE - CELL_SIZE) / 2;
//                batcher.draw(getTextureRegion(fishka.getDisplayedValue()), x + fishka.getShiftLeft() * 100 - fishka.getShiftRight() * 100 - zoomDelta, y + fishka.getShiftUp() * 100 - fishka.getShiftDown() * 100 - zoomDelta, CELL_SIZE * fishka.getZoom(), CELL_SIZE * fishka.getZoom());
            }
        }

//        batcher.setColor(color.r, color.g, color.b, oldAlpha);

//
//        if (myWorld.isRunning()) {
//            drawScore();
//        } else if (myWorld.isReady()) {
//        } else if (myWorld.isMenu()) {
//            drawMenuUI();
//        } else if (myWorld.isGameOver()) {
//        } else if (myWorld.isHighScore()) {
//        }


//        batcher.end();
//        drawTransition(delta);

    }

    private void renderFishka(int i, int j, Fishka fishka) {
        float zoomDelta = (fishka.getZoom() * CELL_SIZE - CELL_SIZE) / 2;
        float x = 48 + i * (CELL_SIZE + 8) + fishka.getShiftLeft() * (CELL_SIZE + 8) - fishka.getShiftRight() * (CELL_SIZE + 8) - zoomDelta;
        float y = 308 + j * (CELL_SIZE + 8) + fishka.getShiftUp() * (CELL_SIZE + 8) - fishka.getShiftDown() * (CELL_SIZE + 8) - zoomDelta;
        float size = CELL_SIZE * fishka.getZoom();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(getFishkaColor(fishka.getDisplayedValue()));

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
            font = AssetLoader.font;
        else
            font = AssetLoader.whiteFont;
        font.draw(batcher, s, x + 50 - FONT_SIZE * s.length() / 2, y + 50 - FONT_SIZE);

        batcher.end();
    }

    private Color getFishkaColor(Integer displayedValue) {
        Color color = null;
        switch (displayedValue) {
            case 2:
                color = Color.valueOf("eee4da");
                break;
            case 4:
                color = Color.valueOf("ede0c8");
                break;
            case 8:
                color = Color.valueOf("f2b179");
                break;
            case 16:
                color = new Color(245 / 255, 149 / 255, 99 / 255, 1);
                break;
            case 32:
                color = new Color(246 / 255, 124 / 255, 95 / 255, 1);
                break;
            case 64:
                color = new Color(246 / 255, 94 / 255, 59 / 255, 1);
                break;
            case 128:
                color = new Color(237 / 255, 107 / 255, 114 / 255, 1);
                break;
            case 256:
                color = new Color(237 / 255, 204 / 255, 97 / 255, 1);
                break;
            case 512:
                color = new Color(255 / 255, 255 / 255, 102 / 255, 1);
                break;
            case 1024:
                color = new Color(255 / 255, 255 / 255, 0, 1);
                break;
            case 2048:
                color = new Color(237 / 255, 194 / 255, 46 / 255, 1);
                break;
        }
        return color;
    }

    private TextureRegion getTextureRegion(Integer displayedValue) {
        TextureRegion texture = null;
        switch (displayedValue) {
            case 2:
                texture = AssetLoader.f2;
                break;
            case 4:
                texture = AssetLoader.f4;
                break;
            case 8:
                texture = AssetLoader.f8;
                break;
            case 16:
                texture = AssetLoader.f16;
                break;
            case 32:
                texture = AssetLoader.f32;
                break;
            case 64:
                texture = AssetLoader.f64;
                break;
            case 128:
                texture = AssetLoader.f128;
                break;
            case 256:
                texture = AssetLoader.f256;
                break;
            case 512:
                texture = AssetLoader.f512;
                break;
            case 1024:
                texture = AssetLoader.f1024;
                break;
            case 2048:
                texture = AssetLoader.f2048;
                break;
        }
        return texture;
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
