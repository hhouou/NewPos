package com.achpay.wallet.component;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;


public class ImageLoader {


    public static void load(Context context, int url, ImageView iv) {
        Glide.with(context)
                .load(url)
                .into(iv);
    }

    public static void load(Context context, Bitmap url, ImageView iv) {
        Glide.with(context)
                .load(url)
                .into(iv);
    }

    public static void load(Context context, String url, ImageView iv) {
        Glide.with(context)
                .load(url)
                .into(iv);
    }

    public static void load(Context context, String url, ImageView iv,int place) {
        Glide.with(context)
                .load(url)
                .placeholder(place)
                .into(iv);
    }

//
//    public static void load(Context context, String url, ImageView iv) {    //使用Glide加载圆形ImageView(如头像)时，不要使用占位图
//            Glide.with(context).load(url).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(iv);
//    }
//
//    public static void load(Activity activity, String url, ImageView iv) {    //使用Glide加载圆形ImageView(如头像)时，不要使用占位图
//        if(!activity.isDestroyed()) {
//            Glide.with(activity).load(url).crossFade().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(iv);
//        }
//    }
//
//    public static void loadAll(Context context, String url, ImageView iv) {    //不缓存，全部从网络加载
//        Glide.with(context).load(url).crossFade().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(iv);
//    }
//    public static void loadResource(Context context, int resourceId, ImageView targetView) {    //加载本地图片
//        Glide.with(context)
//                .load(resourceId)
//                .asGif()
//                .diskCacheStrategy(DiskCacheStrategy.NONE)
//                .skipMemoryCache(true)
//                .into(targetView);
//
//    }
//    public static void load(Context context, int resourceId, ImageView targetView) {    //加载本地图片
//        Glide.with(context).load(resourceId)
//                .diskCacheStrategy(DiskCacheStrategy.NONE)
//                .skipMemoryCache(true)
//                .into(targetView);
//
//    }
//
//    public static void loadAll(Activity activity, String url, ImageView iv) {    //不缓存，全部从网络加载
//        if(!activity.isDestroyed()) {
//            Glide.with(activity).load(url).crossFade().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(iv);
//        }
//    }
//
//    public static  void downloadImg(final Context mContext, String imageUrl) {
//        Glide.with(mContext).load(imageUrl).asBitmap().toBytes().into(new SimpleTarget<byte[]>() {
//            @Override
//            public void onResourceReady(byte[] bytes, GlideAnimation<? super byte[]> glideAnimation) {
//                try {
//                    FileUtils.savaBytesToSD(mContext,bytes);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }
//    public static  Bitmap getBitmap(final Context mContext, final String imageUrl) {
//
//        new Runnable(){
//            @Override
//            public void run() {
//                try {
//
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                } catch (ExecutionException e) {
//                    e.printStackTrace();
//                }
//            }
//        }.run();
//
//        return myBitmap;
//    }
}
