package com.source.mymvvm.adapter;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.widget.ImageView;

import androidx.core.content.ContextCompat;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.source.mymvvm.MyApplication;

public class ImageBindingAdapter {
    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView view,String url){
        Context context = MyApplication.getContext();
        Glide.with(context)
                .load(url)
                .placeholder(new ColorDrawable(ContextCompat.getColor(context,android.R.color.darker_gray)))
                .into(view);
    }

}