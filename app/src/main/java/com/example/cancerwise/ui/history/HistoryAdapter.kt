package com.example.cancerwise.ui.history

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cancerwise.DetailResultActivity
import com.example.cancerwise.R
import com.example.cancerwise.model.History
import java.time.format.DateTimeFormatter

class HistoryAdapter(private val dataSet: ArrayList<History>) :
    RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView
        val date: TextView
        val img: ImageView
        val condition: TextView

        init {
            // Define click listener for the ViewHolder's View
            title = view.findViewById(R.id.title)
            date = view.findViewById(R.id.time)
            img = view.findViewById(R.id.img_view)
            condition = view.findViewById(R.id.condition)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.history_card, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.title.text = dataSet[position].quizTitle

        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
        val formatted = dataSet[position].date.format(formatter)

        viewHolder.date.text = formatted
        viewHolder.condition.text = dataSet[position].score.toString()

        viewHolder.img.setImageResource(dataSet[position].img)

        viewHolder.itemView.setOnClickListener {
            val intent = Intent(viewHolder.itemView.context, DetailResultActivity::class.java)

            viewHolder.itemView.context.startActivity(intent)

        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}