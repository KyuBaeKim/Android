package com.example.basic_3_recyclerview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main_detail.*
import kotlinx.android.synthetic.main.item_main.*
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {
//    var items: MutableList<MainData> = mutableListOf(
//        MainData("Title1","Content1", "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.akamai.com%2Fko%2Fproducts%2Fimage-and-video-manager&psig=AOvVaw31SBddgs3QyhgKVmvdEuDl&ust=1649125185272000&source=images&cd=vfe&ved=0CAgQjRxqFwoTCNiq4Zms-fYCFQAAAAAdAAAAABAD"),
//        MainData("Title2","Content2", "https://www.google.com/url?sa=i&url=http%3A%2F%2Fwww.w3bai.com%2Fko%2Fcss%2Fcss_image_gallery.html&psig=AOvVaw31SBddgs3QyhgKVmvdEuDl&ust=1649125185272000&source=images&cd=vfe&ved=0CAgQjRxqFwoTCNiq4Zms-fYCFQAAAAAdAAAAABAJ"),
//        MainData("Title3","Content3", "https://www.google.com/url?sa=i&url=http%3A%2F%2Fwww.w3bai.com%2Fko%2Fcss%2Fcss_image_gallery.html&psig=AOvVaw31SBddgs3QyhgKVmvdEuDl&ust=1649125185272000&source=images&cd=vfe&ved=0CAgQjRxqFwoTCNiq4Zms-fYCFQAAAAAdAAAAABAP"),
//    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv_main_list.adapter = MainAdapter(MainDatas.items, ::onItemClick)
        rv_main_list.layoutManager = LinearLayoutManager(this)

        btnAdd.setOnClickListener  {
            val title = edtTitle.text.toString() // 문자열 끄낼때 toString
            val content = edtContent.text.toString()
            val item = MainData(title, content, "")
            MainDatas.items += item

            rv_main_list.adapter?.notifyDataSetChanged()

            edtTitle.setText("") // 문자열 쓸때 setText
            edtContent.setText("")

          timer(period = 3000) {
                runOnUiThread{
                    viewPager.currentItem =
                        (viewPager.currentItem + 1) % MainDatas.items.size
                }
            }
        }
    }

    // R. V. 아이템을 클릭했을 때 호출할 메서드
    fun onItemClick(position: Int){  // Int->unit
//        val data = items[position]

//        Toast.makeText(this, "${data.title}, ${data.content}", Toast.LENGTH_LONG).show()

        val i = Intent(this, MainDetailActivity::class.java)
//        i.putExtra("MAIN_DATA", data)
        i.putExtra("POSITION", position)
        startActivity(i)
    }
}