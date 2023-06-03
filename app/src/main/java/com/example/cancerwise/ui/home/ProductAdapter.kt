package com.example.cancerwise.ui.home

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.cancerwise.ui.quizioner.PreQuizActivity
import com.example.cancerwise.ui.quizioner.PreQuizActivity.Companion.EXTRA_DES
import com.example.cancerwise.ui.quizioner.PreQuizActivity.Companion.EXTRA_ID
import com.example.cancerwise.ui.quizioner.PreQuizActivity.Companion.EXTRA_IMAGE
import com.example.cancerwise.ui.quizioner.PreQuizActivity.Companion.EXTRA_NAME
import com.example.cancerwise.R
import com.example.cancerwise.model.Quizioner

class ProductAdapter(private val quizioner: ArrayList<Quizioner>, private val viewPager2: ViewPager2) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById<TextView>(R.id.title)
//        val desc: TextView = itemView.findViewById<TextView>(R.id.desc)
        val img: ImageView = itemView.findViewById<ImageView>(R.id.img)

        fun bind(item: Quizioner) {
            title.text = item.name
            img.setImageResource(item.img)
        }

        fun openDetail(item: Quizioner) {

            val optionsCompat: ActivityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                itemView.context as Activity,
                Pair(img, "image"))

            val intent = Intent(itemView.context, PreQuizActivity::class.java)
                .putExtra(EXTRA_ID, item.id)
                .putExtra(EXTRA_NAME, item.name)
                .putExtra(EXTRA_DES, item.desc)
                .putExtra(EXTRA_IMAGE, item.img)

            itemView.context.startActivity(intent, optionsCompat.toBundle())
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.product_items, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val data = quizioner[position]

        holder.bind(data)
        holder.itemView.setOnClickListener {
            holder.openDetail(data)
        }

        if (position == quizioner.size - 1) {
            viewPager2.post(runnable)
        }
    }

    override fun getItemCount(): Int {
        return quizioner.size
    }

    private val runnable = Runnable {
        quizioner.addAll(quizioner)
        notifyDataSetChanged()
    }
}
