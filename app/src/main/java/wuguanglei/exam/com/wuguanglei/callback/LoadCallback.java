package wuguanglei.exam.com.wuguanglei.callback;

import java.util.List;

import wuguanglei.exam.com.wuguanglei.bean.ExamEntity;

/**
 * Created by admin on 2016/6/25.
 */
public interface LoadCallback {
    public void getData(List<ExamEntity> examEntityList);
}
