package com.example.cancerwise.ui.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.cancerwise.R
import com.example.cancerwise.model.Quizioner
import com.example.cancerwise.ui.quizioner.QuizionerActivity
import com.example.cancerwise.ui.quizioner.QuizionerActivity.Companion.EXTRA_ID
import com.example.cancerwise.utils.Utils

class ProductAdapter(private val quizioner: ArrayList<Quizioner>, private val viewPager2: ViewPager2) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img: ImageView = itemView.findViewById<ImageView>(R.id.product_img_main)

        fun bind(item: Quizioner) {
            img.setImageResource(item.img)
        }

        fun openQuiz(item: Quizioner) {

            Utils.DialogController.showDialogConfirmation(
                itemView.context,
                "Reminder",
                "This test is use for first aid purpose, there's no doctor patient relationship being made. Our model calculation is only for next step consideration and not for medical reference"
            ) {
                val intent = Intent(itemView.context, QuizionerActivity::class.java)
                    .putExtra(EXTRA_ID, item.id)

                itemView.context.startActivity(intent)
            }
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
            holder.openQuiz(data)
        }

    }

    override fun getItemCount(): Int {
        return quizioner.size
    }

}
