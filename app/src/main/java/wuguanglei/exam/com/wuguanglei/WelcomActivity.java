package wuguanglei.exam.com.wuguanglei;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class WelcomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcom);
    }
    //跳转主界面
    public void startMain(View view) {
        Intent intent = new Intent(this, newActivity.class);
        startActivity(intent);
        finish();
    }
}
