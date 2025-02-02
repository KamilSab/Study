package ru.itis.homework2.ui.holders

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import ru.itis.homework2.databinding.GameItemBinding
import ru.itis.homework2.model.ListGameItemModel

class GameViewHolder(
    private val binding: GameItemBinding,
    private val requestManager: RequestManager,
    private val onItemClick: (ListGameItemModel) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bindItem(itemData: ListGameItemModel) {
        binding.nameTv.text = itemData.gameName

        requestManager.load(itemData.imageUrl)
            .into(binding.pictureIv)

        binding.root.setOnClickListener {
            onItemClick.invoke(itemData)
        }
    }
}