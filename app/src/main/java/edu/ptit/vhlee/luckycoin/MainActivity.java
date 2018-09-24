package edu.ptit.vhlee.luckycoin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ImageView mCoinWhite;
    private Button mLucky;
    private int lucky = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
    }

    private void initUI() {
        mCoinWhite = findViewById(R.id.image_coin_white);
        mLucky = findViewById(R.id.button_lucky);
        mLucky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getCoin(view);
            }
        });
    }

    private void getCoin(View view) {
        Random random = new Random();
        lucky = random.nextInt(2);
        flipCoin(view);
    }

    public void flipCoin(View v) {
        if (lucky == 0) {

            boolean stayTheSame = (lucky == R.drawable.tails);
            long timeOfAnimation = animateCoin(stayTheSame);
            lucky = R.drawable.tails;
        } else {  // We have Heads

            boolean stayTheSame = (lucky == R.drawable.heads);
            long timeOfAnimation = animateCoin(stayTheSame);
            lucky = R.drawable.heads;
        }

    }

    private long animateCoin(boolean stayTheSame) {

        Flip3dAnimation animation;

        if (lucky == R.drawable.heads) {
            animation = new Flip3dAnimation(mCoinWhite, R.drawable.heads, R.drawable.tails,
                    0, 180, 0, 0, 0, 0);
        } else {
            animation = new Flip3dAnimation(mCoinWhite, R.drawable.tails, R.drawable.heads,
                    0, 180, 0, 0, 0, 0);
        }
        if (stayTheSame) {
            animation.setRepeatCount(5); // must be odd (5+1 = 6 flips so the side will stay the same)
        } else {
            animation.setRepeatCount(6); // must be even (6+1 = 7 flips so the side will not stay the same)
        }

        animation.setDuration(200);
        animation.setInterpolator(new LinearInterpolator());


        mCoinWhite.startAnimation(animation);

        return animation.getDuration() * (animation.getRepeatCount() + 1);
    }

}
