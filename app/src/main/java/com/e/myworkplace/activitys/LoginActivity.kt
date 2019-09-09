package com.e.myworkplace.activitys

import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.e.myworkplace.App
import com.e.myworkplace.R
import com.e.myworkplace.networks.ApiService
import com.e.myworkplace.networks.RetrofitClient
import com.e.myworkplace.networks.req.LoginRequest
import com.e.myworkplace.networks.res.MyLoginResponse
import com.e.myworkplace.utils.isOnline
import com.e.myworkplace.watcher.IWatcher
import com.e.myworkplace.watcher.SimpleTextWatcher
import com.google.firebase.messaging.FirebaseMessaging
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), IWatcher {
    private var login: String? = ""
    private var passwort: String? = ""
    private lateinit var service: ApiService
    //    private lateinit var taskViewModel: CategoryViewModel
    private var app: App? = null

//    eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJhcHAiLCJzY29wZSI6WyJvcGVuaWQiXSwiZXhwIjoxNTY0MTE4NTY1LCJpYXQiOjE1NjM5NzQ1NjUsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiJdLCJqdGkiOiIyMTc4NThmYS1hYzIwLTQ3YjMtYjlhMy1iZjllMDQ2MDRkZjEiLCJjbGllbnRfaWQiOiJ3ZWJfYXBwIn0.bnX2d8SUhQh98a4QFwGZ08-zArx8RUYStEyZencfpt6D1Ia2JgMeTzn_cEpABneeYZabQDnt1hljOg5uotcOXjm5xW-Od3qu5m8nZmfsIuZLu5Nrx3e_GnZPNH2S9PocL9fbG1dQ9IhvxmMTcCuqc9rCmC1K2v-81X24Vi_EHUJQZEyUTyF7VTFT00j1SY8moNXcavWiulSHwQgcny8ZEzdpSG3n-joBwMnO40YDTsRUVPeoteiuSMHw5lVQxbQFkEv-jMUlXNGSg_OgzaSEKnf1T7tAnnrqZOkmhCcx_-cdcXspUmXgVKJJr2bN7sriH0G6o4w6Nh6MGllBW6nRnA

    override fun onWatcher(text: String) {
        login = login_activity_login.text!!.toString()
        passwort = login_activity_passwort!!.text!!.toString()
        isvalidate()
    }

    fun isvalidate() {
        if (!login!!.isEmpty() && !passwort!!.isEmpty()
//            && login_activity_reapcha.isChecked
        ) {
            btn_circle.isEnabled = true
            btn_circle!!.setTextColor(ContextCompat.getColor(applicationContext, R.color.colorWhile))
        } else {
            btn_circle.isEnabled = false
            btn_circle!!.setTextColor(ContextCompat.getColor(applicationContext, R.color.divider_color))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        app = application as App
//        taskViewModel = ViewModelProviders.of(this@LoginActivity).get(CategoryViewModel::class.java)
        RetrofitClient.instaince
        service = RetrofitClient.getApiService()!!

        FirebaseMessaging.getInstance().subscribeToTopic(R.string.topic.toString())
        login_activity_login.addTextChangedListener(SimpleTextWatcher(this))
        login_activity_passwort.addTextChangedListener(SimpleTextWatcher(this))
        /*       login_activity_reapcha.isEnabled = false
               login_activity_re_btn.setOnClickListener {
                   SafetyNet.getClient(this)
                       .verifyWithRecaptcha(resources.getString(R.string.recaptcha_site_key))
                       .addOnSuccessListener { token ->
                           validationRecaptchaFrom(token.tokenResult)
                       }
                       .addOnFailureListener { it->

                           Log.d("TAGTOKEN","message error :$it")


               }*/

        btn_circle.setOnClickListener {
            if (login!!.isEmpty()) {
                login_activity_login.error = "login kiriting"
            }
            if (passwort!!.isEmpty()) {
                login_activity_passwort.error = "passwort kiriting"
            }
            btn_circle.startAnimation()
            Log.d("TAGLOG","login :"+login+"  Password : "+passwort)
            if (!login!!.isEmpty() && !passwort!!.isEmpty()) {
                if (isOnline()) {
                    val loginRequest = LoginRequest(login!!, passwort!!)
                    initCirclebutton("done", "Okay")
//                    service.getLogin(loginRequest)
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(object : SingleObserver<MyLoginResponse> {
//                            override fun onError(e: Throwable?) {
//                                initCirclebutton(e?.message.toString(), "Error")
//                            }
//
//                            override fun onSubscribe(d: Disposable?) {
//
//                            }
//
//                            override fun onSuccess(value: MyLoginResponse?) {
//                                if (value?.id_token!=null) {
//                                    initCirclebutton("done", value.id_token.toString())
//                                }else{
//                                    initCirclebutton("Login yoki password notug'ri","")
//                                }
//                            }
//                        })

                } else {
                    initCirclebutton("Siz internetga ulanmagansiz ! ", str = "")
                }
            }
        }
    }

    private fun validationRecaptchaFrom(token: String) {
//        progress_recapcha.visibility=View.GONE
//        login_activity_reapcha.isChecked = true
        isvalidate()
        Log.d("TAGTOKEN", "token :$token")
    }

    private fun initCirclebutton(s: String, str: String) {
        if (s.equals("done")) {
            app?.setUpdating(false)
            Toast.makeText(this@LoginActivity, "Okey", Toast.LENGTH_SHORT).show()
            btn_circle.doneLoadingAnimation(
                Color.parseColor("#13FF00"),
                BitmapFactory.decodeResource(resources, R.drawable.ic_done_white_48dp)
            )
            Handler().postDelayed({
                btn_circle.revertAnimation()
                btn_circle.setBackgroundResource(R.drawable.register_btn)
                val intent = Intent(this@LoginActivity, PinCodeActivity::class.java)
                startActivity(intent)
            }, 1000)
        } else {
            btn_circle.doneLoadingAnimation(
                Color.parseColor("#D5D51010"),
                BitmapFactory.decodeResource(resources, R.drawable.factorials)
            )
            Handler().postDelayed({
                btn_circle.revertAnimation()
                btn_circle.setBackgroundResource(R.drawable.register_btn)
            }, 1000)
            Toast.makeText(this@LoginActivity, s, Toast.LENGTH_SHORT).show()

        }
    }
}
