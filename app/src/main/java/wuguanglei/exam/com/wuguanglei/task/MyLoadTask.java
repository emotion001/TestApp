package wuguanglei.exam.com.wuguanglei.task;

import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import wuguanglei.exam.com.wuguanglei.bean.ExamEntity;
import wuguanglei.exam.com.wuguanglei.callback.LoadCallback;
import wuguanglei.exam.com.wuguanglei.utils.JsonUtil;
import wuguanglei.exam.com.wuguanglei.utils.MyHttpUtils;

/**
 * Created by admin on 2016/6/25.
 */
public class MyLoadTask extends AsyncTask<String ,Void,List<ExamEntity>> {
    private LoadCallback callback;

    public MyLoadTask(LoadCallback callback) {
        this.callback = callback;
    }

    @Override
    protected List<ExamEntity> doInBackground(String... params) {
        String jsonFromUrl = MyHttpUtils.getTextFromUrl(params[0]);
        Log.d("1608", "doInBackground: "+jsonFromUrl);
        List<ExamEntity> entityList = JsonUtil.getEntity(jsonFromUrl);
        return entityList;
    }

    @Override
    protected void onPostExecute(List<ExamEntity> examEntities) {
        super.onPostExecute(examEntities);
        callback.getData(examEntities);
    }
}
