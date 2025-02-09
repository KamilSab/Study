package ru.itis.homework3.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.itis.homework3.databinding.ItemAnswerBinding
import ru.itis.homework3.holders.AnswerVH
import ru.itis.homework3.model.Question

class AnswersAdapter(
    private val question: Question
) : RecyclerView.Adapter<AnswerVH>() {

    private var selectedPosition = question.selectedAnswer

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswerVH {
        return AnswerVH(
            binding = ItemAnswerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = question.answers.size

    override fun onBindViewHolder(holder: AnswerVH, position: Int) {
        holder.itemBind(
            question.answers[position],
            position == selectedPosition
        ) {
            if (selectedPosition != position) {
                selectedPosition?.let { notifyItemChanged(it) }
                selectedPosition = holder.adapterPosition
                question.selectedAnswer = holder.adapterPosition
                selectedPosition?.let { notifyItemChanged(it) }
            }
        }
    }
}