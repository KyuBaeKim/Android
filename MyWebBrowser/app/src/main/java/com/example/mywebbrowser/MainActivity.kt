package com.example.mywebbrowser

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.ContextMenu
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.EditorInfo
import android.webkit.WebViewClient
import android.widget.Toast
import com.example.mywebbrowser.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding  // 뷰 바인딩

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        // 웹뷰 기본 설정
        binding.content.webView.apply { // this는 webView
            settings.javaScriptEnabled = true
            webViewClient = WebViewClient()

        // 컨텍스트 메뉴 등록
        registerForContextMenu(binding.content.webView)

        }

        binding.content.webView.loadUrl("http://www.google.com")
        binding.content.urlEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                var url = binding.content.urlEditText.text.toString()
                if (!url.startsWith("http://") && !url.startsWith("https://")) {
                    url = "https://$url"
                    binding.content.urlEditText.setText(url)
                }
                binding.content.webView.loadUrl(binding.content.urlEditText.text.toString())
                true
            } else {
                false
            }
        }
    }

    override fun onBackPressed() {
        if (binding.content.webView.canGoBack()) {
            binding.content.webView.goBack()
        } else {
            super.onBackPressed() // activity 닫힘
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.menu_context, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item?.itemId){
            R.id.action_share -> {
                Toast.makeText(this, "공유", Toast.LENGTH_LONG).show()
            }
            R.id.action_browser -> {
                Toast.makeText(this, "브라우저", Toast.LENGTH_LONG).show()
            }
        }
        return super.onContextItemSelected(item)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item?.itemId) {
            R.id.action_google, R.id.action_home -> {
                binding.content.webView.loadUrl("http://www.google.com")
                return true
            }
            R.id.action_naver -> {
                binding.content.webView.loadUrl("http://www.naver.com")
                return true
            }
            R.id.action_daum -> {
                binding.content.webView.loadUrl("http://www.daum.net")
                return true
            }
            R.id.action_call -> {
                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse("tel:031-123-4567")
                if (intent.resolveActivity(packageManager) != null) {
                    startActivity(intent)
                }
                return true
            }
            R.id.action_send_text -> {
                Toast.makeText(this, "문자 보내기", Toast.LENGTH_LONG).show()
                true
            }
            R.id.action_email -> {
                Toast.makeText(this, "email qhsorl", Toast.LENGTH_SHORT).show()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }


}