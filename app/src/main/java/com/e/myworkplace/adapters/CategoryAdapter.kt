package com.e.myworkplace.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.e.myworkplace.R
import com.e.myworkplace.activitys.MoreActivity
import com.e.myworkplace.db.Task
import com.e.myworkplace.db.WordExample
import kotlinx.android.synthetic.main.category_row.view.*


class CategoryAdapter internal constructor(context: Context) :
    RecyclerView.Adapter<CategoryAdapter.CategoryVH>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var dataset = emptyList<Task>()
    private val context = context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryVH {
        val view: View = inflater.inflate(R.layout.category_row, parent, false)
        return CategoryVH(view)
    }

    override fun getItemCount() = if (dataset != null) dataset.size else 0

    override fun onBindViewHolder(holder: CategoryVH, position: Int) {
        holder.itemView.setOnClickListener {
            val intent = Intent(context, MoreActivity::class.java)
            intent.putExtra("KEY", dataset[position])
            context.startActivity(intent)
        }
        if (position % 3 == 0)
            holder.itemView.category_done_row.isChecked = false
        holder.onBind(dataset[position])
    }

    inner class CategoryVH(v: View) : RecyclerView.ViewHolder(v) {
        fun onBind(wordExaple: Task) {

            itemView.category_title.text = wordExaple.title
            itemView.category_text.text = wordExaple.body!!.substring(0,50)
        }
    }

    internal fun setList(words: List<Task>) {
        this.dataset = words
        notifyDataSetChanged()
    }
}