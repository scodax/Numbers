package com.example.FlappyBird;

import android.os.Bundle;
import by.scodax.bird.NumbersGame;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.backends.android.surfaceview.RatioResolutionStrategy;

public class MyActivity extends AndroidApplication {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration config = new
                AndroidApplicationConfiguration();
        config.useGLSurfaceView20API18 = true;
        config.resolutionStrategy = new RatioResolutionStrategy(480, 800);

        initialize(new NumbersGame(), config);
    }
}
