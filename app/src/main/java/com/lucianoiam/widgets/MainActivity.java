package com.lucianoiam.widgets;

import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    private FakeOrientationLayout mRotatable;
    private boolean mRotated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mRotatable = findViewById(R.id.rotatable);

        findViewById(R.id.push_me).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRotated = !mRotated;
                mRotatable.setRotated(mRotated);
            }
        });
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        final Window window = getWindow();
        final WindowManager.LayoutParams wmlp = window.getAttributes();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            wmlp.rotationAnimation = WindowManager.LayoutParams.ROTATION_ANIMATION_SEAMLESS;
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            wmlp.rotationAnimation = WindowManager.LayoutParams.ROTATION_ANIMATION_JUMPCUT;
        }
        window.setAttributes(wmlp);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        final boolean rotated = newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE;
        mRotatable.setRotated(rotated);
    }

}
