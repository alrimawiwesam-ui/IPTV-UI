package com.novatv.player

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.WindowManager
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.app.Activity
/**
 * NOVA TV — Android TV shell.
 * Loads the full app UI from assets/www/index.html in a fullscreen WebView.
 *
 * What this shell provides that a browser cannot:
 *  - Plays http:// (cleartext) IPTV streams          -> usesCleartextTraffic in manifest
 *  - No CORS blocking of the Xtream API              -> allowUniversalAccessFromFileURLs
 *  - Splash video autoplays WITH SOUND               -> mediaPlaybackRequiresUserGesture = false
 *  - TV remote BACK button navigates inside the app  -> dispatchKeyEvent below
 *  - Screen stays awake while watching               -> FLAG_KEEP_SCREEN_ON
 */
class MainActivity : Activity() {

    private lateinit var web: WebView

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        web = WebView(this)
        setContentView(web)
        hideSystemUi()

        web.setBackgroundColor(0xFF04060D.toInt())
        with(web.settings) {
            javaScriptEnabled = true
            domStorageEnabled = true                      // localStorage: saved login + favorites
            mediaPlaybackRequiresUserGesture = false      // splash video sound + autoplay
            allowFileAccess = true
            allowContentAccess = true
            @Suppress("DEPRECATION")
            allowUniversalAccessFromFileURLs = true       // lets the app call the Xtream API (no CORS)
            mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
            cacheMode = WebSettings.LOAD_DEFAULT
            mediaPlaybackRequiresUserGesture = false
            setSupportZoom(false)
            useWideViewPort = true
            loadWithOverviewMode = true
        }

        web.webViewClient = WebViewClient()
        web.webChromeClient = WebChromeClient()

        web.loadUrl("file:///android_asset/www/index.html")
    }

    /** TV remote / phone BACK: let the web app navigate back first; exit only from the home screen. */
    override fun dispatchKeyEvent(event: KeyEvent): Boolean {
        if (event.keyCode == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_UP) {
            web.evaluateJavascript("window.appBack ? window.appBack() : false") { handled ->
                if (handled != "true") finish()
            }
            return true
        }
        return super.dispatchKeyEvent(event)
    }

    private fun hideSystemUi() {
        @Suppress("DEPRECATION")
        window.decorView.systemUiVisibility = (
            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            )
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) hideSystemUi()
    }

    override fun onPause() { super.onPause(); web.onPause() }
    override fun onResume() { super.onResume(); web.onResume() }
    override fun onDestroy() { web.destroy(); super.onDestroy() }
}
