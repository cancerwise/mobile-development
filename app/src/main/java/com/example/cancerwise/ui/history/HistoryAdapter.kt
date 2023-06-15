package com.example.cancerwise.ui.history

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cancerwise.ui.DetailResultActivity
import com.example.cancerwise.R
import com.example.cancerwise.model.History

class HistoryAdapter(private val dataSet: ArrayList<History>) :
    RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView
        val date: TextView
        val score: TextView
        val id: TextView

        init {
            // Define click listener for the ViewHolder's View
            title = view.findViewById(R.id.tv_history_tittle)
            date = view.findViewById(R.id.tv_history_date)
            id = view.findViewById(R.id.tv_history_id)
            score = view.findViewById(R.id.tv_history_score)

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
        fun placeHolder(title: String, data: String): String {
            return viewHolder.itemView.context.getString(R.string.data_show, title, data)
        }

        viewHolder.title.text = placeHolder("Title", dataSet[position].quizTitle)
        viewHolder.date.text = placeHolder("Date", dataSet[position].date)
        viewHolder.score.text = placeHolder("Score", dataSet[position].score.toString())
        viewHolder.id.text = placeHolder("Id", dataSet[position].id.toString())

        viewHolder.itemView.setOnClickListener {
            val intent = Intent(viewHolder.itemView.context, DetailResultActivity::class.java)

            viewHolder.itemView.context.startActivity(intent)
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}