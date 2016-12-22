package pl.wooddy.checkvr;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

  private static final String TAG = MainActivity.class.getSimpleName();
  @BindView(R.id.toolbar) Toolbar mToolbar;
  @BindView(R.id.gyroscope) TextView mGyroscope;
  @BindView(R.id.accelerometer) TextView mAccelerometer;
  @BindView(R.id.isGyro) ImageView mIsGyro;
  @BindView(R.id.isAccelerometer) ImageView mIsAccelerometer;
  @BindView(R.id.content_main) ConstraintLayout mContentMain;
  @BindView(R.id.fab) FloatingActionButton mFab;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    initFAB();

    initViews();
  }

  private void initViews() {
    // Gyro:
    if (SensorTools.hasSensorGyroscope(MainActivity.this)) {
      mIsGyro.setImageResource(R.drawable.ic_check_circle);
    } else {
      mIsGyro.setImageResource(R.drawable.ic_no);
    }

    // Accelerometer:
    if (SensorTools.hasSensorAccelerometer(MainActivity.this)) {
      mIsAccelerometer.setImageResource(R.drawable.ic_check_circle);
    } else {
      mIsAccelerometer.setImageResource(R.drawable.ic_no);
    }
  }

  @OnClick(R.id.facebookButton) public void onClickFacebook() {
    final Intent intent = getFacebookPageURLIntent(MainActivity.this);
    startActivity(intent);
  }

  public static Intent newFacebookIntent(Context context, String url) {
    PackageManager pm = context.getPackageManager();
    Uri uri = Uri.parse(url);
    try {
      ApplicationInfo applicationInfo = pm.getApplicationInfo("com.facebook.katana", 0);
      if (applicationInfo.enabled) {
        // http://stackoverflow.com/a/24547437/1048340
        uri = Uri.parse("fb://facewebmodal/f?href=" + url);
      }
    } catch (PackageManager.NameNotFoundException ignored) {
      Log.d(TAG, "newFacebookIntent() called with: context = [" + ignored);
    }
    return new Intent(Intent.ACTION_VIEW, uri);
  }

  public Intent getFacebookPageURLIntent(Context context) {
    String uri;
    PackageManager packageManager = context.getPackageManager();
    try {
      int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;

      boolean activated = packageManager.getApplicationInfo("com.facebook.katana", 0).enabled;
      if (activated) {
        if ((versionCode >= 3002850)) {
          uri = "fb://facewebmodal/f?href=" + Constants.URL_FUN_PAGE;
        } else {
          uri = "fb://page/" + Constants.FUN_PAGE_NAME;
        }
      } else {
        uri = Constants.URL_FUN_PAGE;
      }
    } catch (PackageManager.NameNotFoundException e) {
      uri = Constants.URL_FUN_PAGE;
    }

    return new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
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
