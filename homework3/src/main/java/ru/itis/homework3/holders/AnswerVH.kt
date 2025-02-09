package ru.itis.homework3.holders

import android.R
import android.content.res.ColorStateList
import android.graphics.Color
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import ru.itis.homework3.databinding.ItemAnswerBinding

class AnswerVH(
    private val binding: ItemAnswerBinding,
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.apply {

            answerRb.buttonTintList = ColorStateList(
                arrayOf(
                    intArrayOf(-android.R.attr.state_checked),
                    intArrayOf(android.R.attr.state_checked)
                ),
                intArrayOf(
                    Color.GRAY,
                    Color.GREEN
                )
            )

            answerRb.setOnCheckedChangeListener { buttonView, isChecked ->
                card.setCardBackgroundColor(ContextCompat.getColor(buttonView.context, if(isChecked) R.color.holo_green_light else R.color.white))
            }
        }
    }

    fun itemBind(item : String, isChecked : Boolean, onClick: () -> Unit){
        binding.apply {
            answerTv.text = item
            answerRb.isChecked = isChecked

            answerRb.setOnClickListener {
                onClick()
            }

            root.setOnClickListener {
                onClick()
            }
        }
    }
}