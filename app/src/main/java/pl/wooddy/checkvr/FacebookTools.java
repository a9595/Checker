package pl.wooddy.checkvr;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;

/**
 * Created by tieorange on 22/12/2016.
 */

public class FacebookTools {
  public static Intent getFacebookPageURLIntent(Context context) {
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
}
