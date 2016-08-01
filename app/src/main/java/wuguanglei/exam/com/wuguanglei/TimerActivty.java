package wuguanglei.exam.com.wuguanglei;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class TimerActivty extends AppCompatActivity {
    private int count=10;
    private TextView textView;
    private Timer timer;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int  count = (int) msg.obj;
            textView.setText("修改成功"+"/n"+count+"秒后关闭界面");
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_activty);
        textView = ((TextView) findViewById(R.id.textId));
        new MyThread().start();
    }

    private class MyThread  extends Thread{
        @Override
            public void run() {
                super.run();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        count--;
                        Message message=handler.obtainMessage();
                        message.arg1=count;
                        if (count>0) {
                            handler.sendMessage(message);
                        }
                    }
                },null,1000);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }
}
