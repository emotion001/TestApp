package wuguanglei.exam.com.wuguanglei;

import android.app.NotificationManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import wuguanglei.exam.com.wuguanglei.adapter.MyCursorAdapte;
import wuguanglei.exam.com.wuguanglei.helper.DatabaseHelper;
import wuguanglei.exam.com.wuguanglei.playService.PlayService;

public class newActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private ListView listView;
    private static DatabaseHelper helper;
    private static SQLiteDatabase db;
    private static Cursor cursor;
    private  LoaderManager manager;
    private final int LOADER_ID=1;
    private MyCursorAdapte adapter;
    private TextView textView;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        listView = ((ListView) findViewById(R.id.listViewId));
        textView = ((TextView) findViewById(R.id.text_id));
        listView.setEmptyView(textView);

        helper=new DatabaseHelper(this);
        db = helper.getReadableDatabase();

        manager=getSupportLoaderManager();
        manager.initLoader(LOADER_ID,null,this);

        cursor=db.query("table_person",null,null,null,null,null,null);
        adapter=new MyCursorAdapte(this,cursor);
        listView.setAdapter(adapter);
        registerForContextMenu(listView);

    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.main, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        position = menuInfo.position;

        cursor.moveToPosition(position);
        String id = cursor.getString(cursor.getColumnIndex("_id"));

        switch (item.getItemId()) {
            case R.id.deleteId:
                deleteData(id);
                break;
            case R.id.updateId:
                showUpdateDialog(id);
                break;
        }
        return super.onContextItemSelected(item);
    }

    // 删除数据
    private void deleteData(String id) {
        int count = db.delete("table_person", "_id = ?", new String[]{id});
        // 刷新界面
        manager.restartLoader(LOADER_ID, null, this);
        if (count > 0) {
            Toast.makeText(newActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(newActivity.this, "删除失败", Toast.LENGTH_SHORT).show();
        }
    }

    // 修改数据
    private void updateData(String id, String name, String age) {
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("age", age);
        int count = db.update("table_person", values, "_id = ?", new String[]{id});
        manager.restartLoader(LOADER_ID, null, this);
        if (count > 0) {
            Toast.makeText(newActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
            //弹出消息提示
            creatNotify();
            //跳转倒计时页
            goToTimerActivity();
            //播放音乐
            startPlayMusic();
        } else {
            Toast.makeText(newActivity.this, "修改失败", Toast.LENGTH_SHORT).show();
        }
    }

    private void goToTimerActivity() {
        Intent intent=new Intent(this,TimerActivty.class);
        startActivity(intent);
    }

    private void startPlayMusic() {
        Intent intent=new Intent(this, PlayService.class);
        intent.putExtra("msg","开播");
        startService(intent);
    }

    private void creatNotify() {
        NotificationCompat.Builder builder=new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.drawable.noti_love);
        builder.setContentTitle("消息提醒");
        builder.setContentText("修改成功");

        NotificationManager manager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(LOADER_ID,builder.build());
    }

    // 弹出修改对话框
    private void showUpdateDialog(final String id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("修改信息");

        View rootView = getLayoutInflater().inflate(R.layout.layout_update_dialog, null);
        final EditText nameEditText = (EditText) rootView.findViewById(R.id.up_title);
        final EditText ageEditText = (EditText) rootView.findViewById(R.id.up_nickname);

        builder.setView(rootView);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name = nameEditText.getText().toString();
                String age = ageEditText.getText().toString();
                if (!"".equals(name) && !"".equals(age)) {
                    updateData(id, name, age);
                }
            }
        });
        builder.show();

    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {

        return new MyAsyncTaskLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCursor(null);
    }

    static class MyAsyncTaskLoader extends AsyncTaskLoader<Cursor> {
        private Cursor cursor;

        public MyAsyncTaskLoader(newActivity activity) {
            super(activity);
        }

        @Override
        protected void onStartLoading() {
            super.onStartLoading();
            forceLoad();
        }


        @Override
        public Cursor loadInBackground() {
            cursor = db.query("table_person",null,null,null,null,null,null);
            return cursor;
        }

        @Override
        public void deliverResult(Cursor data) {
            super.deliverResult(data);
        }
    }
}

