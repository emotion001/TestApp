package wuguanglei.exam.com.wuguanglei.fragment;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import wuguanglei.exam.com.wuguanglei.R;
import wuguanglei.exam.com.wuguanglei.adapter.MyFragmentAdapter;
import wuguanglei.exam.com.wuguanglei.bean.ExamEntity;
import wuguanglei.exam.com.wuguanglei.callback.LoadCallback;
import wuguanglei.exam.com.wuguanglei.helper.DatabaseHelper;
import wuguanglei.exam.com.wuguanglei.task.MyLoadTask;
import wuguanglei.exam.com.wuguanglei.uri.Uri;

/**
 * Created by admin on 2016/6/25.
 */
public class MyFragment extends Fragment implements AbsListView.OnScrollListener,AdapterView.OnItemLongClickListener {
    private List<ExamEntity> list;
    private MyFragmentAdapter adapter;
    private boolean isLastItem;
    private int pageNumber=1;
    private ListView listView;
    private TextView textView;
    private DatabaseHelper helper;
    private SQLiteDatabase db;

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list=new ArrayList<>();
        adapter = new MyFragmentAdapter(list,getActivity());
        helper=new DatabaseHelper(getActivity());
        db = helper.getReadableDatabase();
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View convertView=inflater.inflate(R.layout.fragment_layout,container,false);

        listView= (ListView) convertView.findViewById(R.id.listViewId);
        textView= (TextView) convertView.findViewById(R.id.textViewId);
        listView.setEmptyView(textView);
        //设置空适配器
        listView.setAdapter(adapter);
        //得到数据
        getData();
        listView.setOnScrollListener(this);

        listView.setOnItemLongClickListener(this);
        return convertView;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (isLastItem=true  && scrollState==SCROLL_STATE_IDLE){
            pageNumber++;
            getData();
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (firstVisibleItem+visibleItemCount==totalItemCount){
            isLastItem=true;
        }else {
            isLastItem=false;
        }
    }

    public void getData() {
        new MyLoadTask(new LoadCallback() {
            @Override
            public void getData(List<ExamEntity> examEntityList) {
                list.addAll(examEntityList);
                adapter.notifyDataSetChanged();
            }
        }).execute(Uri.PATH_URL+pageNumber);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

        ExamEntity examEntity= list.get(position);
        String title=examEntity.getTitle();
        String nickname=examEntity.getNickname();
        ContentValues values=new ContentValues();
        values.put("title",title);
        values.put("nickname",nickname);
        long i= db.insert(helper.TABLE_NAME,null,values);
        if (i>0){
            Toast.makeText(getActivity(),"插入成功",Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}
