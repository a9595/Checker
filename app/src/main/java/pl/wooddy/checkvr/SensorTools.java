package pl.wooddy.checkvr;

import android.content.Context;
import android.content.pm.PackageManager;

/**
 * Created by tieorange on 22/12/2016.
 */

public class SensorTools {
  public static boolean hasSensorGyroscope(Context context) {
    PackageManager PM = context.getPackageManager();
    //boolean acc = PM.hasSystemFeature(PackageManager.FEATURE_SENSOR_ACCELEROMETER);
    boolean result = PM.hasSystemFeature(PackageManager.FEATURE_SENSOR_GYROSCOPE);
    return result;
  }

  public static boolean hasSensorAccelerometer(Context context) {
    PackageManager manager = context.getPackageManager();
    final boolean result = manager.hasSystemFeature(PackageManager.FEATURE_SENSOR_ACCELEROMETER);
    return result;
  }
}
