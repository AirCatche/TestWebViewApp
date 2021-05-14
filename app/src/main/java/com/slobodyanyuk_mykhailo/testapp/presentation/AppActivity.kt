package com.slobodyanyuk_mykhailo.testapp.presentation

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.slobodyanyuk_mykhailo.testapp.viewmodel.AppViewModel
import com.slobodyanyuk_mykhailo.testapp.viewmodel.AppViewModelFactory
import com.slobodyanyuk_mykhailo.testapp.viewmodel.OnLoadingListener
import com.slobodyanyuk_mykhailo.testapp.R
import com.slobodyanyuk_mykhailo.testapp.databinding.ActivityApplicationBinding
import com.slobodyanyuk_mykhailo.testapp.model.network.ConnectionInterceptor
import com.slobodyanyuk_mykhailo.testapp.model.responses.LinksResponse
import com.slobodyanyuk_mykhailo.testapp.utils.Constants

class AppActivity : AppCompatActivity(), OnLoadingListener {

    private lateinit var binding: ActivityApplicationBinding
    private lateinit var fragmentManager: FragmentManager
    private lateinit var appViewModel: AppViewModel
    private lateinit var prefs: SharedPreferences
    private var firstStart: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prefs = getSharedPreferences("prefs", MODE_PRIVATE)
        firstStart = prefs.getBoolean("firstRun", true)
        binding = ActivityApplicationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val viewModelFactory = AppViewModelFactory(ConnectionInterceptor(this))
        appViewModel = ViewModelProvider(this, viewModelFactory).get(AppViewModel::class.java)
        appViewModel.loadingListener = this
        fragmentManager = supportFragmentManager

        val fragment: Fragment = LoadingFragment.newInstance()
        fragmentManager.beginTransaction().replace(R.id.main_fragment_container, fragment)
            .commitNow()
    }

    override fun onStart() {
        super.onStart()
        if (firstStart) {
            appViewModel.fetchData()
        } else {
            val fragment = BrowserFragment.newInstance()
            fragmentManager.beginTransaction()
                .setCustomAnimations(
                    R.anim.transaction_animation_enter,
                    R.anim.transaction_animation_exit,
                    R.anim.transaction_animation_enter,
                    R.anim.transaction_animation_exit
                )
                .add(R.id.main_fragment_container, fragment, Constants.BROWSER_FRAGMENT)
                .commit()
        }
    }

    override fun onLoadingComplete(links: LinksResponse) {
        prefs.edit().also {
            it.putString(Constants.KEY_LINK, links.link)
            it.putString(Constants.KEY_HOME, links.home)
        }.apply()
        val fragment = BrowserFragment.newInstance()
        fragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.transaction_animation_enter,
                R.anim.transaction_animation_exit,
                R.anim.transaction_animation_enter,
                R.anim.transaction_animation_exit
            )
            .add(R.id.main_fragment_container, fragment, Constants.BROWSER_FRAGMENT)
            .commit()
    }

    override fun onLoadingFailure(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
    override fun onBackPressed() {
        val webView = supportFragmentManager.findFragmentByTag(Constants.BROWSER_FRAGMENT)
        if (webView is BrowserFragment) {
            val isPreviousPageExist: Boolean = (webView as BrowserFragment?)!!.canGoBack()
            if (!isPreviousPageExist) {
                super.onBackPressed()
            }
        }
    }
}
