package com.source.mymvvm.bean

data class AreaItemBean(
    val cnName: String,
    val dialingCode: String,
    val enName: String,
    val numberCode: String,
    val showName: String,

){
    var pCode:Char? = null
    var isHead:Boolean = false
}