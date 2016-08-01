package wuguanglei.exam.com.wuguanglei.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import wuguanglei.exam.com.wuguanglei.R;
import wuguanglei.exam.com.wuguanglei.bean.ExamEntity;
import wuguanglei.exam.com.wuguanglei.utils.LruCacheUtil;

/**
 * Created by admin on 2016/6/25.
 */
public class MyFragmentAdapter extends BaseAdapter  {
    private List<ExamEntity>entityList;
    private LayoutInflater inflater;
    private ViewHolder holder;
    private Bitmap bitmap;

    public MyFragmentAdapter(List<ExamEntity> entityList, Context context) {
        this.entityList=entityList;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return entityList.size();
    }

    @Override
    public Object getItem(int position) {
        return entityList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            convertView=inflater.inflate(R.layout.item_layout,null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        else {
            holder= (ViewHolder) convertView.getTag();
        }

        //获取数据
        //得到数据，为控件设置数据
        String id = entityList.get(position).getId();
        String title = entityList.get(position).getTitle();
        String pic_min = entityList.get(position).getPic_min();
        String nickname = entityList.get(position).getNickname();
        //这是文本数据
        holder.idTextView.setText(id);
        holder.nameTextView.setText(nickname);
        holder.titleTextView.setText(title);
        //设置图片
        LruCacheUtil lruCacheUtil=new LruCacheUtil();
        lruCacheUtil.showImageView(holder.imageView,pic_min);
        return convertView;
    }

    private class ViewHolder{
        private ImageView imageView;
        private TextView idTextView,titleTextView,nameTextView;
        public ViewHolder(View  convertView){
            imageView = (ImageView) convertView.findViewById(R.id.imageViewId);
            idTextView= (TextView) convertView.findViewById(R.id.idTextViewId);
            titleTextView = (TextView) convertView.findViewById(R.id.titleTextId);
            nameTextView = (TextView) convertView.findViewById(R.id.nameTextId);
        }
    }
//    public void addAll(List<ExamEntity> list){
//        entityList.addAll(list);
//        notifyDataSetChanged();
//    }
//
//    public void clear(){
//        entityList.clear();
//        notifyDataSetChanged();
//    }

}
