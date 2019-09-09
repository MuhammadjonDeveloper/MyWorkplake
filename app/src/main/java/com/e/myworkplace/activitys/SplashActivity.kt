package com.e.myworkplace.activitys

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.preference.PreferenceManager
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.e.myworkplace.App
import com.e.myworkplace.R
import com.e.myworkplace.db.Task
import com.e.myworkplace.networks.ApiService
import com.e.myworkplace.networks.CategoryViewModel
import com.e.myworkplace.networks.RetrofitClient
import com.e.myworkplace.utils.isOnline
import com.google.firebase.messaging.FirebaseMessaging
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class SplashActivity : AppCompatActivity() {
    private lateinit var categoryViewModel: CategoryViewModel
    private var service: ApiService? = null


    private var preferences: SharedPreferences? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)

        val topic = resources.getString(R.string.topic)
        FirebaseMessaging.getInstance().subscribeToTopic(topic)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        categoryViewModel = ViewModelProviders.of(this).get(CategoryViewModel::class.java)
        preferences = PreferenceManager.getDefaultSharedPreferences(this)


    }

    override fun onStart() {
        super.onStart()
        RetrofitClient.instaince
        service = RetrofitClient.getApiService()
        if (isOnline()) {
            service!!.posts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<List<Task>> {
                    override fun onError(e: Throwable?) {
                    }

                    override fun onSubscribe(d: Disposable?) {
                    }

                    override fun onSuccess(value: List<Task>?) {
                        if (value != null) {
                            initNextActivity()
                            categoryViewModel.insert(value)
                        }
                    }
                })
        } else {
            Toast.makeText(this, "Siz internetga ulanmagansiz", Toast.LENGTH_SHORT).show()
        }

    }

    private fun initNextActivity() {
        Handler().postDelayed({
            val app = application as App
            if (app.getUpdating()) {
                startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
                finish()
            } else {
                startActivity(Intent(this@SplashActivity, PinCodeActivity::class.java))
                finish()
            }
        }, 2000)

    }
}