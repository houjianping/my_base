package com.scwang.refreshlayout.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.request.RequestOptions;
import com.scwang.refreshlayout.R;

import java.security.MessageDigest;

public class ImageLoaderUtils {

    public static void show(ImageView imageView, String url) {
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }

    public static void displayRound(Context context, ImageView imageView, String url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.toux2)
                .error(R.mipmap.ic_launcher)
                .priority(Priority.HIGH)
                .transform(new GlideRoundTransformUtil(context))
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(context).load(url)
                .apply(options).into(imageView);
    }

    public static void displayRound(Context context, ImageView imageView, int resId) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.toux2)
                .error(R.mipmap.ic_launcher)
                .priority(Priority.HIGH)
                .transform(new GlideRoundTransformUtil(context))
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(context).load(resId)
                .apply(options).into(imageView);
    }

    public static class GlideRoundTransformUtil extends BitmapTransformation {
        public GlideRoundTransformUtil(Context context) {
            super(context);
        }

        @Override
        protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
            return circleCrop(pool, toTransform);
        }

        private Bitmap circleCrop(BitmapPool pool, Bitmap source) {
            if (source == null) return null;

            int size = Math.min(source.getWidth(), source.getHeight());
            int x = (source.getWidth() - size) / 2;
            int y = (source.getHeight() - size) / 2;
            Bitmap squared = Bitmap.createBitmap(source, x, y, size, size);

            Bitmap result = pool.get(size, size, Bitmap.Config.ARGB_8888);
            if (result == null) {
                result = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
            }

            Canvas canvas = new Canvas(result);
            Paint paint = new Paint();
            paint.setShader(new BitmapShader(squared, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
            paint.setAntiAlias(true);
            float r = size / 2f;
            canvas.drawCircle(r, r, r, paint);
            return result;
        }

        @Override
        public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {

        }
    }
}
