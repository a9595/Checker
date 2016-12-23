package pl.wooddy.checkvr;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity {

  private static final String TAG = MainActivity.class.getSimpleName();
  @BindView(R.id.toolbar) Toolbar mToolbar;
  @BindView(R.id.gyroscope) TextView mGyroscope;
  @BindView(R.id.accelerometer) TextView mAccelerometer;
  @BindView(R.id.isGyro) ImageView mIsGyro;
  @BindView(R.id.isAccelerometer) ImageView mIsAccelerometer;
  @BindView(R.id.content_main) ConstraintLayout mContentMain;
  @BindView(R.id.fab) FloatingActionButton mFab;
  @BindView(R.id.gyroscopeImage) ImageView mGyroscopeImage;
  @BindView(R.id.accelerometerImage) ImageView mAccelerometerImage;
  @BindView(R.id.constraintLayout) ConstraintLayout mConstraintLayout;
  @BindView(R.id.logo) ImageView mLogo;
  @BindView(R.id.customizableVrHeadset) TextView mCustomizableVrHeadset;
  @BindView(R.id.learnMoreButton) ImageView mLearnMoreButton;
  @BindView(R.id.smartphoneIsReady) TextView mSmartphoneIsReady;
  @BindView(R.id.congrats) TextView mCongrats;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    startSplashScreen();

    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
    setSupportActionBar(mToolbar);
    initFAB();

    initViews();
  }

  private void startSplashScreen() {
    if (!MyApplication.sIsOpenedSplashScreen) {
      final Intent intent = new Intent(this, SplashScreenActivity.class);
      startActivity(intent);
    }
  }

  private void initViews() {
    // Gyro:
    final int ic_true = R.drawable.ic_check_circle;
    final int ic_false = R.drawable.ic_cancel;
    final boolean hasSensorGyroscope = SensorTools.hasSensorGyroscope(MainActivity.this);
    if (hasSensorGyroscope) {
      mIsGyro.setImageResource(ic_true);
    } else {
      mIsGyro.setImageResource(ic_false);
    }

    // Accelerometer:
    final boolean hasSensorAccelerometer = SensorTools.hasSensorAccelerometer(MainActivity.this);
    if (hasSensorAccelerometer) {
      mIsAccelerometer.setImageResource(ic_true);
    } else {
      mIsAccelerometer.setImageResource(ic_false);
    }

    if (!hasSensorAccelerometer || !hasSensorGyroscope) {
      mSmartphoneIsReady.setVisibility(View.GONE);
      mCongrats.setText(R.string.doesnt_support);
    }
  }

  @OnClick(R.id.learnMoreButton) public void onClickFacebook() {
    FirebaseTools.logFunPageClick();

    final Intent intent = FacebookTools.getFacebookPageURLIntent(MainActivity.this);
    startActivity(intent);
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  @Override protected void attachBaseContext(Context newBase) {
    super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
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
