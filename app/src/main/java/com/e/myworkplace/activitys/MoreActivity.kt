package com.e.myworkplace.activitys

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.e.myworkplace.R
import com.e.myworkplace.db.Task
import kotlinx.android.synthetic.main.activity_more.*

class MoreActivity:AppCompatActivity(){
   private lateinit var wordExaple : Task
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_more)
        if (more_toolbar != null) {
            setSupportActionBar(more_toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setHomeButtonEnabled(true)
        }


        more_toolbar.setNavigationOnClickListener {
            finish()
        }
        val bundle:Bundle=intent.extras
        if (bundle!=null) {
            wordExaple=bundle.getParcelable("KEY")
        }
        more_title.text=wordExaple.title
        more_body.text=wordExaple.body
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.home) {
            finish()
            return true
        }
        return true
    }
}