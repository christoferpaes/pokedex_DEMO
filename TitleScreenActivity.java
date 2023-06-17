package mavericks.PokeDexDemo;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import mavericks.PokeDexDemo.R;

public class TitleScreenActivity extends AppCompatActivity {

    private Button openPokedexButton;
    private LinearLayout buttonLayout;
    private ImageView backgroundImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.titlescreenactivity);

        openPokedexButton = findViewById(R.id.openPokedexButton);
        buttonLayout = findViewById(R.id.buttonLayout);
        backgroundImage = findViewById(R.id.backgroundImage);

        openPokedexButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateFadeOut();
            }
        });
    }

    private void animateFadeOut() {
        Animation fadeOut = new AlphaAnimation(1.0f, 0.0f);
        fadeOut.setDuration(5000); // 5 seconds
        fadeOut.setFillAfter(true);
        buttonLayout.startAnimation(fadeOut);
        fadeOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                // Animation started
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // Animation ended
                openMainActivity();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // Animation repeated
            }
        });
    }

    private void openMainActivity() {
        Intent intent = new Intent(TitleScreenActivity.this, MainActivity.class);
        startActivity(intent);
        finish(); // Optional, if you want to finish the title screen activity
    }
}
