package wuguanglei.exam.com.wuguanglei.utils;

import android.graphics.Bitmap;
import android.os.Handler;
import android.util.LruCache;
import android.widget.ImageView;

/**
 * Created by admin on 2016/6/24.
 */
public class LruCacheUtil {
    private int maxSize= (int) (Runtime.getRuntime().maxMemory()/8);
    private LruCache<String,Bitmap>lruCache=new LruCache<String,Bitmap>(maxSize){
        @Override
        protected int sizeOf(String key, Bitmap value) {
            return value.getByteCount();
        }
    };
    private Handler handler=new Handler();
    //展示图片:下载、缓存
    public void showImageView(ImageView iv,String  imageUrl){
        if (lruCache.get("imageUrl")==null){
            //下载
            downloadImage(iv,imageUrl);
        }else{
            Bitmap bitmap = lruCache.get("imageUrl");
            iv.setImageBitmap(bitmap);
        }
    }
    //下载图片
    public void downloadImage(final ImageView iv, final String imgUrl){
        iv.setTag(imgUrl);
        new Thread(new Runnable() {
            @Override
            public void run() {
                final Bitmap bitmap=MyHttpUtils.getBitmapFromUrl(imgUrl);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        String tag= (String) iv.getTag();
                        if (tag!=null&& tag.equals(imgUrl)){
                            iv.setImageBitmap(bitmap);

                        }
                        lruCache.put(imgUrl, bitmap);
                    }
                });
            }
        }).start();
    }
}