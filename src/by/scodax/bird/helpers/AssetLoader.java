package by.scodax.bird.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by patrick on 24.04.14.
 */
public class AssetLoader {

    public static Texture texture, logoTexture, fieldTexture, scoreCurrentTexture, scoreHighTexture, patrickTexture, menuTexture, yesNoButtonsTexture, gameover;
    public static TextureRegion logo, zbLogo, bg, skullUp, skullDown, bar, playButtonUp, playButtonDown,
            ready, highScore, scoreboard, star, noStar, retry, field, scoreCurrent, scoreHigh, patrick, menu, menuPressed,
            yes, yesPressed, no, noPressed;
    public static Sound dead, flap, coin, fall;
    public static BitmapFont blackFont, whiteFont, scoreFont, scoreFontBig;
    public static Preferences prefs;

    public static void load() {

        logoTexture = new Texture(Gdx.files.internal("data/logo.png"));
        logoTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        logo = new TextureRegion(logoTexture, 0, 0, 512, 114);

        texture = new Texture(Gdx.files.internal("data/texture.png"));
        texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        scoreCurrentTexture = new Texture(Gdx.files.internal("data/score.png"));
        scoreCurrentTexture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        scoreHighTexture = new Texture(Gdx.files.internal("data/best.png"));
        scoreHighTexture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        fieldTexture = new Texture(Gdx.files.internal("data/field.png"));
        fieldTexture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        patrickTexture = new Texture(Gdx.files.internal("data/patrick.png"));
        patrickTexture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        menuTexture = new Texture(Gdx.files.internal("data/menu_button.png"));
        menuTexture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        yesNoButtonsTexture = new Texture(Gdx.files.internal("data/yes_no_buttons.png"));
        yesNoButtonsTexture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        gameover = new Texture(Gdx.files.internal("data/gameover.png"));
        gameover.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        playButtonUp = new TextureRegion(texture, 0, 83, 29, 16);
        playButtonDown = new TextureRegion(texture, 29, 83, 29, 16);
        playButtonUp.flip(false, true);
        playButtonDown.flip(false, true);

        ready = new TextureRegion(texture, 59, 83, 34, 7);
        ready.flip(false, true);

        retry = new TextureRegion(texture, 59, 110, 33, 7);
        retry.flip(false, true);

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

        field = new TextureRegion(fieldTexture, 0, 0, 400, 400);
        field.flip(false, true);

        scoreCurrent = new TextureRegion(scoreCurrentTexture, 120, 60);
        scoreCurrent.flip(false, true);

        scoreHigh = new TextureRegion(scoreHighTexture, 120, 60);
        scoreHigh.flip(false, true);

        patrick = new TextureRegion(patrickTexture, 140, 105);
        patrick.flip(false, true);

        skullUp = new TextureRegion(texture, 192, 0, 24, 14);
        // Create by flipping existing skullUp
        skullDown = new TextureRegion(skullUp);
        skullDown.flip(false, true);

        bar = new TextureRegion(texture, 136, 16, 22, 3);
        bar.flip(false, true);

        menu = new TextureRegion(menuTexture, 0, 0, 40, 32);
        menu.flip(false, true);

        menuPressed = new TextureRegion(menuTexture, 40, 0, 40, 32);
        menuPressed.flip(false, true);

        yes = new TextureRegion(yesNoButtonsTexture, 0, 0, 100, 60);
        yes.flip(false, true);

        yesPressed = new TextureRegion(yesNoButtonsTexture, 0, 60, 100, 60);
        yesPressed.flip(false, true);

        no = new TextureRegion(yesNoButtonsTexture, 100, 0, 100, 60);
        no.flip(false, true);

        noPressed = new TextureRegion(yesNoButtonsTexture, 100, 60, 100, 60);
        noPressed.flip(false, true);

        dead = Gdx.audio.newSound(Gdx.files.internal("data/dead.wav"));
        flap = Gdx.audio.newSound(Gdx.files.internal("data/flap.wav"));
        coin = Gdx.audio.newSound(Gdx.files.internal("data/coin.wav"));
        fall = Gdx.audio.newSound(Gdx.files.internal("data/fall.wav"));

        blackFont = new BitmapFont(Gdx.files.internal("data/mecha.fnt"));
        blackFont.setScale(.35f, -.35f);
        blackFont.setColor(Color.valueOf("736d6b"));

        scoreFont  = new BitmapFont(Gdx.files.internal("data/mecha.fnt"));
        scoreFont.setScale(.3f, -.3f);
        scoreFont.setColor(Color.valueOf("f7f3ef"));

        scoreFontBig  = new BitmapFont(Gdx.files.internal("data/mecha.fnt"));
        scoreFontBig.setScale(.5f, -.5f);

        whiteFont  = new BitmapFont(Gdx.files.internal("data/mecha.fnt"));
        whiteFont.setScale(.35f, -.35f);
        whiteFont.setColor(Color.valueOf("f7f3ef"));

        // Create (or retrieve existing) preferences file
        prefs = Gdx.app.getPreferences("Numbers2048");

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

        blackFont.dispose();
    }

}
