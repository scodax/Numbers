package by.scodax.bird.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by patrick on 24.04.14.
 */
public class AssetLoader {

    public static Texture texture, logoTexture, birdTexture, bgTexture, fieldTexture, fishkiTexture;
    public static TextureRegion logo, zbLogo, bg, grass, bird, birdDown,
            birdUp, skullUp, skullDown, bar, playButtonUp, playButtonDown,
            ready, gameOver, highScore, scoreboard, star, noStar, retry, tetrad, field,
            f2, f4, f8, f16, f32, f64, f128, f256, f512, f1024, f2048;
    public static Animation birdAnimation;
    public static Sound dead, flap, coin, fall;
    public static BitmapFont font, shadow, whiteFont;
    private static Preferences prefs;

    public static void load() {

        logoTexture = new Texture(Gdx.files.internal("data/logo.png"));
        logoTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        logo = new TextureRegion(logoTexture, 0, 0, 512, 114);

        texture = new Texture(Gdx.files.internal("data/texture.png"));
        texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        birdTexture = new Texture(Gdx.files.internal("data/angrybird.png"));
        birdTexture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        bgTexture = new Texture(Gdx.files.internal("data/tetrad.png"));
        bgTexture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        fieldTexture = new Texture(Gdx.files.internal("data/field.png"));
        fieldTexture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        fishkiTexture = new Texture(Gdx.files.internal("data/fishki.png"));
        fishkiTexture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        playButtonUp = new TextureRegion(texture, 0, 83, 29, 16);
        playButtonDown = new TextureRegion(texture, 29, 83, 29, 16);
        playButtonUp.flip(false, true);
        playButtonDown.flip(false, true);

        ready = new TextureRegion(texture, 59, 83, 34, 7);
        ready.flip(false, true);

        retry = new TextureRegion(texture, 59, 110, 33, 7);
        retry.flip(false, true);

        gameOver = new TextureRegion(texture, 59, 92, 46, 7);
        gameOver.flip(false, true);

        scoreboard = new TextureRegion(texture, 111, 83, 97, 37);
        scoreboard.flip(false, true);

        star = new TextureRegion(texture, 152, 70, 10, 10);
        noStar = new TextureRegion(texture, 165, 70, 10, 10);

        star.flip(false, true);
        noStar.flip(false, true);

        highScore = new TextureRegion(texture, 59, 101, 48, 7);
        highScore.flip(false, true);

        zbLogo = new TextureRegion(texture, 0, 55, 135, 24);
        zbLogo.flip(false, true);

        bg = new TextureRegion(texture, 0, 0, 136, 43);
        bg.flip(false, true);

        tetrad = new TextureRegion(bgTexture, 0, 0, 136, 170);
        tetrad.flip(false, true);

        grass = new TextureRegion(texture, 0, 43, 143, 11);
        grass.flip(false, true);

        field = new TextureRegion(fieldTexture, 0, 0, 400, 400);
        field.flip(false, true);

        birdDown = new TextureRegion(birdTexture, 0, 0, 34, 24);
        birdDown.flip(false, true);

        bird = new TextureRegion(birdTexture, 34, 0, 34, 24);
        bird.flip(false, true);

        birdUp = new TextureRegion(birdTexture, 68, 0, 34, 24);
        birdUp.flip(false, true);

        TextureRegion[] birds = {birdDown, bird, birdUp};
        birdAnimation = new Animation(0.06f, birds);
        birdAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

        f2 = new TextureRegion(fishkiTexture, 0, 0, 48, 48);
        f2.flip(false, true);

        f4 = new TextureRegion(fishkiTexture, 48, 0, 48, 48);
        f4.flip(false, true);

        f8 = new TextureRegion(fishkiTexture, 96, 0, 48, 48);
        f8.flip(false, true);

        f16 = new TextureRegion(fishkiTexture, 144, 0, 48, 48);
        f16.flip(false, true);

        f32 = new TextureRegion(fishkiTexture, 0, 48, 48, 48);
        f32.flip(false, true);

        f64 = new TextureRegion(fishkiTexture, 48, 48, 48, 48);
        f64.flip(false, true);

        f128 = new TextureRegion(fishkiTexture, 96, 48, 48, 48);
        f128.flip(false, true);

        f256 = new TextureRegion(fishkiTexture, 144, 48, 48, 48);
        f256.flip(false, true);

        f512 = new TextureRegion(fishkiTexture, 0, 96, 48, 48);
        f512.flip(false, true);

        f1024 = new TextureRegion(fishkiTexture, 48, 96, 48, 48);
        f1024.flip(false, true);

        f2048 = new TextureRegion(fishkiTexture, 96, 96, 48, 48);
        f2048.flip(false, true);

        skullUp = new TextureRegion(texture, 192, 0, 24, 14);
        // Create by flipping existing skullUp
        skullDown = new TextureRegion(skullUp);
        skullDown.flip(false, true);

        bar = new TextureRegion(texture, 136, 16, 22, 3);
        bar.flip(false, true);

        dead = Gdx.audio.newSound(Gdx.files.internal("data/dead.wav"));
        flap = Gdx.audio.newSound(Gdx.files.internal("data/flap.wav"));
        coin = Gdx.audio.newSound(Gdx.files.internal("data/coin.wav"));
        fall = Gdx.audio.newSound(Gdx.files.internal("data/fall.wav"));

        font = new BitmapFont(Gdx.files.internal("data/text.fnt"));
        font.setScale(.25f, -.25f);

        whiteFont = new BitmapFont(Gdx.files.internal("data/whitetext.fnt"));
        whiteFont.setScale(.1f, -.1f);

        shadow = new BitmapFont(Gdx.files.internal("data/shadow.fnt"));
        shadow.setScale(.25f, -.25f);

        // Create (or retrieve existing) preferences file
        prefs = Gdx.app.getPreferences("ZombieBird");

        if (!prefs.contains("highScore")) {
            prefs.putInteger("highScore", 0);
        }
    }

    public static void setHighScore(int val) {
        prefs.putInteger("highScore", val);
        prefs.flush();
    }

    public static int getHighScore() {
        return prefs.getInteger("highScore");
    }

    public static void dispose() {
        // We must dispose of the texture when we are finished.
        texture.dispose();

        // Dispose sounds
        dead.dispose();
        flap.dispose();
        coin.dispose();

        font.dispose();
        shadow.dispose();
    }

}