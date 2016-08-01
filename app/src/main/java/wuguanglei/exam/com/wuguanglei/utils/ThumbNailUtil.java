package wuguanglei.exam.com.wuguanglei.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

/**
 * 二次采样
 * Created by admin on 2016/6/23.
 */
public class ThumbNailUtil {
    public  static Bitmap getThumbnailBitmap(byte[]datas,int simpleSize){
        //第一次采样，获取图片的宽高
        BitmapFactory.Options options=new BitmapFactory.Options();
        options.inJustDecodeBounds=true;
        BitmapFactory.decodeByteArray(datas,0,datas.length,options);
        //设置压缩比例
        options.inSampleSize=simpleSize;
        options.inJustDecodeBounds=false;
        Bitmap thumbNailBitmap = BitmapFactory.decodeByteArray(datas, 0, datas.length, options);
        return  thumbNailBitmap;
    }
    public static  Bitmap getThumbNailBitmap(Bitmap bitmap,int simpleSize){
        ByteArrayOutputStream bos=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,bos);
        byte[]datas=bos.toByteArray();

        BitmapFactory.Options options=new BitmapFactory.Options();
        options.inJustDecodeBounds=true;
        BitmapFactory.decodeByteArray(datas,0,datas.length,options);
        //设置压缩比例
        options.inSampleSize=simpleSize;
        options.inJustDecodeBounds=false;
        Bitmap thumbNailBitmap = BitmapFactory.decodeByteArray(datas, 0, datas.length, options);
        return  thumbNailBitmap;

    }
    //对本地图片进行二次采样
    public  static Bitmap getThumbNailBitmap(String pathName,int simpleSize){
        BitmapFactory.Options options=new BitmapFactory.Options();
        options.inJustDecodeBounds=true;
        BitmapFactory.decodeFile(pathName,options);

        options.inSampleSize=simpleSize;
        options.inJustDecodeBounds=false;
        Bitmap thumbNailBitmap = BitmapFactory.decodeFile(pathName, options);
        return thumbNailBitmap;
    }
}
