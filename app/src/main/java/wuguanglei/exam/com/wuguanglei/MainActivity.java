package wuguanglei.exam.com.wuguanglei;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import wuguanglei.exam.com.wuguanglei.fragment.MyFragment;

public class MainActivity extends AppCompatActivity {
    private FragmentManager manager;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化sharedPreference对象。
        sharedPreferences=getSharedPreferences("app_config", Context.MODE_PRIVATE);
        //判断是否是第一打开app;
        isFirstOpen();
        //将fragment加载布局管理器中；
        manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fragmentId,new MyFragment());
        transaction.commit();
    }

    public void isFirstOpen(){
        boolean isFirstLogin=sharedPreferences.getBoolean("isFirstLogin",true);
        if (isFirstLogin){
            Intent intent=new Intent(this,WelcomActivity.class);
            startActivity(intent);
            Toast.makeText(this,"欢迎第一次登陆app",Toast.LENGTH_SHORT).show();
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putBoolean("isFirstLogin",false);
            editor.commit();
        }else {
            Toast.makeText(this,"不是第一次登陆app",Toast.LENGTH_SHORT).show();
        }
    }

    public void changeActivity(View view) {
        Intent intent=new Intent(this,SecondActivity.class);
        startActivity(intent);
    }
}
