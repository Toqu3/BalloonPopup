package it.beppi.balloonpopup;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;

import java.util.Random;

import it.beppi.balloonpopuplibrary.BalloonPopup;
import it.beppi.balloonpopuplibrary.BalloonPopup.BalloonGravity;

import static it.beppi.balloonpopuplibrary.BalloonPopup.BalloonAnimation.fade_and_pop;
import static it.beppi.balloonpopuplibrary.BalloonPopup.BalloonGravity.center;
import static it.beppi.balloonpopuplibrary.BalloonPopup.BalloonShape.rounded_square;

public class SampleActivity extends AppCompatActivity {
    PopupWindow pw;
    BalloonGravity bg = center;
    BalloonPopup bp;
    Random rnd = new Random();
    BTimer bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        ((Button)findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bp == null || !bp.isShowing())
                    bp = BalloonPopup.Builder(getApplicationContext(), findViewById(R.id.button))
                        .text("text")
                        .timeToLive(2000)
                        .animation(fade_and_pop)
                        .shape(rounded_square)
                        .fgColor(Color.RED)
                        .gravity(bg)
                        .textSize(6)
                        .show();
                else
                    bp.restartLifeTime();

            }
        });

        final Button button_g = (Button)findViewById(R.id.button_gravity);
        button_g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bg = BalloonGravity.values()[rnd.nextInt(25)];
                button_g.setText("Gravity: " + bg.toString());
                if (bp != null && bp.isShowing()) {
                    bp.updateGravity(bg, true);
                    bp.updateTextSize(new Random().nextInt(5) + 10, true);

                }
            }
        });


    }
}
