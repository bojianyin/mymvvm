package com.source.mymvvm.view.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.source.mymvvm.R

class LoadingDialog(context: Context) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onStart() {
        super.onStart()
        window?.run {
            val attributes = this.attributes
            attributes.dimAmount = 0.3f
            this.attributes = attributes
        }


    }

    override fun show(){
        var imageView = ImageView(context)
        imageView?.setImageResource(R.drawable.ic_action_loading)

        var loadAnimation = AnimationUtils.loadAnimation(context, R.anim.loading_ani)
        setContentView(imageView!!)
        imageView?.startAnimation(loadAnimation)

        super.show()
    }

    override fun dismiss() {
        super.dismiss()

        Log.e("LoadingDialog", "dismiss: ")
    }


}