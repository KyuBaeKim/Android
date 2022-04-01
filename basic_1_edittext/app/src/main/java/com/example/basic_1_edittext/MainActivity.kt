package com.example.basic_1_edittext

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*


// R.mipmap.penguin --> 데이터 타입: Int

class MainActivity : AppCompatActivity() {

    val TAG = "edittext::MainActivity"
//    val images = listOf<Int>(R.mipmap.hun,R.mipmap.zzang,R.mipmap.zzanga) // 읽기 전용 리스트

    // image의 Url을 element로 가지면됨.
    val imageUrls = listOf<String>(
        "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMTEwMDRfMjQ1%2FMDAxNjMzMzU4Mjg2NDU5.wHz3NGNC5Hlnv0e0q4FRxCHovzyx1C8AcQ-wFTY_bMog.vavjeHad3FFKDKv0CtWsPkKmBIq-aI5R9xyJqHBJj1gg.JPEG.alsgml7640%2FIMG_9905.jpg&type=sc960_832",
        "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMjAyMTlfMjA2%2FMDAxNjQ1MjU3MTYxNTA1.GSs_w0jdFI5XxfErsEqau8gCl2DKUGejkaksESknirkg.QuooBR45E3PHh4ZXq2VdvQRl7IYlQHlF8DRTf5U0fTgg.JPEG.gogucute-jnhnsa%2FScreenshot%25A3%25DF20220106%25A3%25AD113833%25A3%25DFTVING.jpg&type=sc960_832",
        "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMTA4MjJfMjM5%2FMDAxNjI5NTY0MDcyODc0.Rc3jaS6zZduQYGtqGo9ep6f-NDMql8FEcIwXSiRRQNAg.y_3RyGmlv-2aPrmFfQECxchL1cluwAAGtJwF-ZRbY4og.JPEG.alsgml7640%2FIMG_8063.jpg&type=sc960_832"
    )
    var current_image = 0

    companion object{ // 어떤 호출인지 상수로 정의
        val REQUEST = 0
        val ID = "ID"
        val PASSWD = "PASSWD"
        val RESULT = "RESULT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // ImageView의 이미지 리소스 변경: setImageResource(리소스 ID)
//        imageView.setImageResource(images[current_image])


        loadImage(imageUrls[current_image])

        btn_prev.setOnClickListener{
            --current_image
            if(current_image<0){
                current_image = imageUrls.size-1
            }
            Log.i(TAG, "이전버튼 : $current_image, ${imageUrls[current_image]}")
            loadImage(imageUrls[current_image])
        }

        btn_next.setOnClickListener{
            current_image = (current_image + 1) % imageUrls.size
            loadImage(imageUrls[current_image])

        }

        editName.setOnFocusChangeListener() { v, hasFocus ->
            val edt = v as EditText
            val color = if (hasFocus) {
                Color.TRANSPARENT
            } else {
                Color.LTGRAY
            }
            edt.setBackgroundColor(color)
        }


        editPassWD.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                textViewPasswd.text = s
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })

        btnLogin.setOnClickListener {
            val i = Intent(this, ResultActivity::class.java)
            i.putExtra(ID, editName.text.toString())
            i.putExtra(PASSWD, editPassWD.text.toString())

            startActivityForResult(i, REQUEST)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            Log.i("MainActivity", "onActivityResult $data")
            if(requestCode != REQUEST) return

            data?.getStringExtra(RESULT).let{
                textMessage.text = it
            }
            super.onActivityResult(requestCode,resultCode, data)
        }


    fun loadImage(url: String) = Glide.with(this)
                                    .load(imageUrls[current_image])
                                    .into(imageView)
}
