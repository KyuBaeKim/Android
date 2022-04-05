package com.example.electframe

import android.media.Image
import android.provider.MediaStore
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import java.util.ArrayList

class MediaImage(val ctx: AppCompatActivity) {

    fun getALlPhotos(): ArrayList<String>{
        // 모든 사진 정보 가져오기
        val cursor = ctx.contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI, // 대상 테이블
            null, // select 문
            null, // where
            null, // where 인수
            MediaStore.Images.ImageColumns.DATE_TAKEN +" DESC" //orderby절
        )

        val imageUris = ArrayList<String>()

        cursor?.use{
            while(it.moveToNext()){
                // 사진 경로 Uri 가지고 오기
                val uri = it.getString(it.getColumnIndexOrThrow(
                                        MediaStore.Images.Media.DATA))
                Log.d("MediaImage", uri)
                imageUris.add(uri)
            }
        }
        return imageUris
    }
}