package wuguanglei.exam.com.wuguanglei.playService;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import wuguanglei.exam.com.wuguanglei.R;

/**
 * Created by admin on 2016/6/25.
 */
public class PlayService extends Service {
    private MediaPlayer player;
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        player=MediaPlayer.create(this, R.raw.ringstone);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String msg = intent.getStringExtra("msg");
        if (msg!=null){
            player.start();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
