package com.slobodyanyuk_mykhailo.testapp.presentation;

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.slobodyanyuk_mykhailo.testapp.databinding.FragmentBrowserBinding
import com.slobodyanyuk_mykhailo.testapp.utils.Constants


class BrowserFragment: Fragment(){
    private var _binding: FragmentBrowserBinding? = null
    private lateinit var webView: WebView
    private var isFirstRun: Boolean = true
    // Use between OnCreateView & OnDestroyView
    private val binding get() = _binding!!
    private var url: String? = null
    companion object {
        private const val TAG = "BrowserFragment"
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentBrowserBinding.inflate(inflater,container,false)
        val prefs = requireActivity().getSharedPreferences("prefs", 0)
        isFirstRun = prefs.getBoolean("firstRun", true)
        webView = binding.browserWebView.also {
            it.clearCache(true)
            it.clearHistory()
            it.settings.javaScriptEnabled = true
            it.webViewClient = WebViewClient()
            it.settings.domStorageEnabled = true
            it.settings.builtInZoomControls = true
            it.settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
        }
        url = if (isFirstRun) {
            val editor = prefs.edit()
            editor.putBoolean("firstRun", false).apply()
            prefs.getString(Constants.KEY_LINK, "")
        } else {
            prefs.getString(Constants.KEY_HOME, "")
        }
        if (savedInstanceState == null) {
            url?.let {
                binding.browserWebView.loadUrl(it)
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(TAG, "onViewCreated: starts")
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        webView.saveState(outState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        savedInstanceState?.let { webView.restoreState(it) }
    }

    fun canGoBack() : Boolean {
        return if (binding.browserWebView.canGoBack()) {
            binding.browserWebView.goBack()
            true
        } else {
            false
        }
    }
}