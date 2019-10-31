package com.example.kotlincourse


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import kotlinx.android.synthetic.main.f_web.view.*

class WebFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val vu: View = inflater.inflate(R.layout.f_web, container, false)

        val wCount = CountManager(this.requireContext()).webCount
        CountManager(this.requireContext()).webCount = wCount?.plus(1)
        Toast.makeText(this.context, wCount.toString(), Toast.LENGTH_LONG).show()

        val url: String? = arguments!!.getString("url")

        vu.webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url)
                return true
            }
        }
        vu.webView.loadUrl(url)
        return vu
    }
}
