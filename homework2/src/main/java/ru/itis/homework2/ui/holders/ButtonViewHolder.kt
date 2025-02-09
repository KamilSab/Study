package ru.itis.homework2.ui.holders

import androidx.recyclerview.widget.RecyclerView
import ru.itis.homework2.databinding.ButtonsItemBinding
import ru.itis.homework2.model.ButtonsItemModel

class ButtonViewHolder(
    val binding: ButtonsItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bindItem(itemData: ButtonsItemModel) {
        binding.button1.text = itemData.firstButtonText
        binding.button2.text = itemData.secondButtonText
        binding.button3.text = itemData.thirdButtonText
    }
}