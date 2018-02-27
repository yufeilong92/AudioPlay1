package test.llx.com.audioplay_kedademo;

import android.app.Application;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class DemoApplication extends Application {

    private static final String TAG = "GetuiSdkDemo";

    private static DemoHandler handler;
    public static MainActivity demoActivity;

    /**
     * 应用未启动, 个推 service已经被唤醒,保存在该时间段内离线消息(此时 MainActivity.mEtContent == null)
     */
    public static StringBuilder payloadData = new StringBuilder();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "DemoApplication onCreate");

        if (handler == null) {
            handler = new DemoHandler();
        }
    }

    public static void sendMessage(Message msg) {
        handler.sendMessage(msg);
    }

    public static class DemoHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    if (demoActivity != null) {
                        payloadData.append((String) msg.obj);
                        payloadData.append("\n");
                        if (MainActivity.mEtContent != null) {
                            MainActivity.mEtContent.append(msg.obj + "\n");
                        }
                    }
                    break;

               /* case 1:
                    if (demoActivity != null) {
                        if (MainActivity.mEtContent != null) {
                            MainActivity.tView.setText((String) msg.obj);
                        }
                    }
                    break;*/
            }
        }
    }
}
