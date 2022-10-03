package com.source.mymvvm.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.previewlibrary.loader.IZoomMediaLoader;
import com.previewlibrary.loader.MySimpleTarget;

public class PreImageLoader implements IZoomMediaLoader {
    @Override
    public void displayImage(@NonNull Fragment fragment, @NonNull String s, ImageView imageView, @NonNull MySimpleTarget mySimpleTarget) {
        Glide.with(fragment)
                .asBitmap()
                .load(s)
                .into(new SimpleTarget<Bitmap>() {
                      @Override
                      public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                          mySimpleTarget.onResourceReady();
                          imageView.setImageBitmap(resource);
                      }
                });
    }

    @Override
    public void displayGifImage(@NonNull Fragment fragment, @NonNull String s, ImageView imageView, @NonNull MySimpleTarget mySimpleTarget) {

    }

    @Override
    public void onStop(@NonNull Fragment fragment) {
        Glide.with(fragment).onStop();
    }

    @Override
    public void clearMemory(@NonNull Context c) {
        Glide.get(c).clearMemory();
    }
}
