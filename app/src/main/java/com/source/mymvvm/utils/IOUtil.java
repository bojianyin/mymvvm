package com.source.mymvvm.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class IOUtil {

    public static Bitmap base64ToBMP(String baseString){
        byte[] bytes = Base64.decode(baseString, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes,0,bytes.length);
    }

    public static String bmpToBase64(Bitmap bmp){
        ByteArrayOutputStream outputStream;
        outputStream = new ByteArrayOutputStream();

        bmp.compress(Bitmap.CompressFormat.JPEG,100,outputStream);

        byte[] bytes = outputStream.toByteArray();

        try {
            outputStream.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        return Base64.encodeToString(bytes,Base64.DEFAULT);

    }



}
