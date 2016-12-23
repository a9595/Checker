package pl.wooddy.checkvr;

import android.app.Application;
import com.google.firebase.analytics.FirebaseAnalytics;

/**
 * Created by tieorange on 22/12/2016.
 */

public class MyApplication extends Application {
  public static FirebaseAnalytics sFirebaseAnalytics;
  public static boolean sIsOpenedSplashScreen = false;

  @Override public void onCreate() {
    super.onCreate();

    sFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
  }
}
