package wuguanglei.exam.com.wuguanglei;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import wuguanglei.exam.com.wuguanglei.adapter.MyCursorAdapte;
import wuguanglei.exam.com.wuguanglei.helper.DatabaseHelper;

public class SecondActivity extends AppCompatActivity {

    private ListView listView;
    private static DatabaseHelper helper;
    private static SQLiteDatabase db;
    private static Cursor cursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);
        listView = ((ListView) findViewById(R.id.listView_Id));

        //得到数据
        helper=new DatabaseHelper(this);
        db = helper.getReadableDatabase();
        cursor = db.query("table_person",null,null,null,null,null,null);

        MyCursorAdapte adapte=new MyCursorAdapte(this,cursor);
        listView.setAdapter(adapte);
    }
    public void backBt(View view) {
        finish();
    }
}
