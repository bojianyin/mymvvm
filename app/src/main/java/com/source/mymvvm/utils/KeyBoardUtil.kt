package com.source.mymvvm.utils

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager

object KeyBoardUtil {
    /**
     * 隐藏键盘的方法
     * @param activity
     */
    fun close(activity: Activity) {
        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        // 隐藏软键盘
        imm.hideSoftInputFromWindow(activity.window.decorView.windowToken, 0);
    }

}