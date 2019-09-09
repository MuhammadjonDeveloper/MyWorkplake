package com.e.myworkplace.activitys

import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.e.myworkplace.R
import com.e.myworkplace.adapters.CategoryAdapter
import com.e.myworkplace.networks.ApiService
import com.e.myworkplace.networks.CategoryViewModel
import com.e.myworkplace.networks.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var apiService: ApiService
    private lateinit var categoryViewModel: CategoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(main_toolbar)
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = ContextCompat.getColor(this, R.color.colorBlueP)
        }
        val retrofit = RetrofitClient.instaince
        apiService = retrofit.create(ApiService::class.java)
        categoryViewModel = ViewModelProviders.of(this).get(CategoryViewModel::class.java)

        //rx
        main_actvity_rv.setHasFixedSize(true)
        main_actvity_rv.layoutManager = LinearLayoutManager(this)
        displayData()
    }

    private fun displayData() {
        val adapter = CategoryAdapter(this)
        main_actvity_rv.adapter = adapter
        categoryViewModel.allTasks.observe(this, Observer { tasks ->
            tasks?.let { adapter.setList(tasks) }
        })
    }
}
