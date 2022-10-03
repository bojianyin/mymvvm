package com.source.mymvvm.bean;

import android.graphics.Rect
import android.os.Parcel
import android.os.Parcelable
import com.previewlibrary.enitity.IThumbViewInfo


class PreImageBean @JvmOverloads constructor(var curl:String, var cmBounds:Rect?=null,var cvideoUrl:String? = null) : IThumbViewInfo {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readParcelable(Rect::class.java.classLoader),
        parcel.readString()
    ) {
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        p0?.writeString(curl)
        p0?.writeParcelable(cmBounds, p1)
        p0?.writeString(cvideoUrl)
    }

    override fun getUrl(): String {
        return curl
    }

    override fun getBounds(): Rect? {
        return cmBounds
    }

    override fun getVideoUrl(): String? {
        return cvideoUrl
    }

    companion object CREATOR : Parcelable.Creator<PreImageBean> {
        override fun createFromParcel(parcel: Parcel): PreImageBean {
            return PreImageBean(parcel)
        }

        override fun newArray(size: Int): Array<PreImageBean?> {
            return arrayOfNulls(size)
        }
    }


}