package com.example.electframe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.util.jar.Manifest
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {

    lateinit var  permissionChecker: PermissionChecker // 초기화를 뒤로 미룸

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val permissions = arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE)
        permissionChecker = PermissionChecker(this, permissions)
        if(permissionChecker.check()){
            // 초기화
            init()
        }
    }

    private fun init() {
        val mediaImage = MediaImage(this)

        val adapter = PhotoPagerAdapter(this, mediaImage.getALlPhotos())

        viewPager.adapter = adapter

        // 3초마다 자동으로 슬라이드
        timer(period = 3000) {
            runOnUiThread {
                viewPager.currentItem =
                    (viewPager.currentItem + 1) % adapter.itemCount
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(
            requestCode, permissions, grantResults)
            if (permissionChecker.checkGranted(
                    requestCode, permissions, grantResults
                )
            ) {
                //모든 권한 획득 성공
                //초기화
                init()

            } else {
                // 권환획득 실패
                finish()
            }
        }
    }



