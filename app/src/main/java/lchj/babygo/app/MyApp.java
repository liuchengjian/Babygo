package lchj.babygo.app;

import android.app.Application;

import com.joanzapata.iconify.fonts.FontAwesomeModule;

import lchj.babygo.R;
import lchj.babygo.net.interceptors.DebugInterceptor;


/**
 * Created by XiMiTwo on 2017/11/8.
 */

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withIcon(new FontAwesomeModule())
               // .withIcon(new FontEcModule())
                .withLoaderDelayed(1000)
                .withApiHost("http://192.168.58.134/")
                .withInterceptor(new DebugInterceptor("test", R.raw.test))
                .withJavascriptInterface("latte")
                .configure();

    }
}
