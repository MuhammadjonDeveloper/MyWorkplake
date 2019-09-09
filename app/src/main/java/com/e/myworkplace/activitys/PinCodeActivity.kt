package com.e.myworkplace.activitys

import android.annotation.TargetApi
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.preference.PreferenceManager
import android.util.Log
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.e.myworkplace.App
import com.e.myworkplace.R
import com.e.myworkplace.components.PinKeyboard
import com.e.myworkplace.networks.ApiService
import com.e.myworkplace.networks.RetrofitClient
import com.google.firebase.messaging.FirebaseMessaging
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_pinkode.*

class PinCodeActivity : AppCompatActivity() {

    private var animation: Animation? = null
    private var animation1: Animation? = null
    private var enterPincode: String? = null
    private var preferences: SharedPreferences? = null
    private var pinCode = ""
    private var createPinCode: Boolean? = null
    private var pass: String? = null
    private var app: App? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pinkode)
        app = application as App
        val arg = intent.extras
        if (arg != null) {
            pass = arg.getString("key")
        }
        preferences = PreferenceManager.getDefaultSharedPreferences(this)
        createPinCode = preferences!!.getBoolean("is_counter", true)

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = ContextCompat.getColor(this, R.color.colorBlueP)
        }

        animation = AnimationUtils.loadAnimation(this, R.anim.shake)
        animation1 = AnimationUtils.loadAnimation(this, R.anim.scale_up)
        pin_keyboard!!.setOnTabListener(object : PinKeyboard.TabListener {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            override fun onNumberTab(number: String) {
                setPinCode(pinCode + number)
            }

            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            override fun onEraseTab() {
                if (pinCode.length > 0) {
                    setPinCode(pinCode.substring(0, pinCode.length - 1))
                }
            }
        })
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private fun setPinCode(pinCode: String) {
        if (pinCode.length <= 4) {
            this.pinCode = pinCode
        }
        pin_circles!!.fillCircles(this.pinCode.length)
        if (this.pinCode.length == 4) {
            Handler().postDelayed({
                if (this.pinCode.length == 4)
                    checkPin()

            }, 200)
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private fun checkPin() {
        if (createPinCode!!) {
            if (enterPincode.equals(pinCode)) {
                preferences!!.edit().putBoolean("is_counter", false).apply()
                app?.setName(pinCode)
                startActivity(Intent(this@PinCodeActivity, MainActivity::class.java))
                finish()
            } else {
                if (enterPincode == null)
                    enterPincode = pinCode
                setPinCode("")
                pin_circles!!.startAnimation(animation1)
            }
        } else {
            if (pinCode == app?.getName()) {
                FirebaseMessaging.getInstance().subscribeToTopic(R.string.topic.toString())
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                pin_info_text!!.text = "Pin code error"
                pin_circles!!.startAnimation(animation)
                setPinCode("")
            }
        }

    }
}
