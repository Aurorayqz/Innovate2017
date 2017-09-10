package innovate.topcoder.packagecom.innovate2017;

import android.app.Application;
import android.content.Context;

/**
 * Created by Aurorayqz on 2017/9/10.
 */

public class MyApplication extends Application {
    private static MyApplication instance;

    public static MyApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        instance = this;
    }
}
