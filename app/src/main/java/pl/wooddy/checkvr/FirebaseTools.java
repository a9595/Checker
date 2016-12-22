package pl.wooddy.checkvr;

import android.os.Bundle;
import com.google.firebase.analytics.FirebaseAnalytics;

/**
 * Created by tieorange on 22/12/2016.
 */

class FirebaseTools {
  public static void logFunPageClick() {
    Bundle bundle = new Bundle();
    bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "fun page click");
    MyApplication.sFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
  }
}
