package pl.wooddy.checkvr;

import android.app.Application;
import com.google.firebase.analytics.FirebaseAnalytics;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by tieorange on 22/12/2016.
 */

public class MyApplication extends Application {
  public static FirebaseAnalytics sFirebaseAnalytics;
  public static boolean sIsOpenedSplashScreen = false;

  @Override public void onCreate() {
    super.onCreate();

    sFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

    CalligraphyConfig.initDefault(
        new CalligraphyConfig.Builder()
            //.setDefaultFontPath("fonts/9204.otf")
            .setFontAttrId(R.attr.fontPath)
            .build());
  }
}
