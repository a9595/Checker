package pl.wooddy.checkvr;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class SplashScreenActivity extends AppCompatActivity {

  private static final long DELAY_MILIS = 3500;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash_screen);
    MyApplication.sIsOpenedSplashScreen = true;

    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    new Handler().postDelayed(new Runnable() {
      @Override public void run() {
        startMainActivity();
      }
    }, DELAY_MILIS);

    initFAB();
  }

  private void startMainActivity() {
    final Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
    SplashScreenActivity.this.startActivity(intent);
    SplashScreenActivity.this.finish();
  }

  private void initFAB() {
    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            .setAction("Action", null)
            .show();
      }
    });
  }
}
