package wuguanglei.exam.com.wuguanglei.utils;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import wuguanglei.exam.com.wuguanglei.bean.ExamEntity;

/**
 * Created by admin on 2016/6/25.
 */
public class JsonUtil {
    public static List<ExamEntity> parseJson(String result){
        List<ExamEntity> listExam=new ArrayList<>();
        Log.d("1608", "parseJson: "+result);
        try {
                JSONObject jsonObject=new JSONObject(result);
                JSONObject dataObject=jsonObject.getJSONObject("data");
                JSONArray jsonArray=dataObject.getJSONArray("data");
                for (int i=0;i<jsonArray.length();i++){
                    JSONObject data=jsonArray.getJSONObject(i);
                    String id=data.getString("id");
                    String title=data.getString("title");
                    String pic_min=data.getString("pic_min");
                    JSONObject user=data.getJSONObject("usr");
                    String nickname=user.getString("nickname");
                    ExamEntity exam=new ExamEntity(id,title,pic_min,nickname);
                    listExam.add(exam);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  listExam;
    }
    public  static List<ExamEntity> getEntity(String json){
        List<ExamEntity>dataList=new ArrayList<>();
        try {
            JSONObject object=new JSONObject(json);
            JSONObject object1=object.getJSONObject("data");
            JSONArray array=object1.getJSONArray("data");
            for (int i=0;i<array.length();i++){
                JSONObject object2=array.getJSONObject(i);
                String id=object2.getString("id");
                String title=object2.getString("title");
                String pic_min=object2.getString("pic_min");
                JSONObject object3=object2.getJSONObject("usr");
                String nickname=object3.getString("nickname");
                ExamEntity exam=new ExamEntity(id,title,pic_min,nickname);
                dataList.add(exam);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return dataList ;
    }

}
