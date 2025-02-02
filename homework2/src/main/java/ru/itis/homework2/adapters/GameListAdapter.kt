package ru.itis.homework2.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import ru.itis.homework2.R
import ru.itis.homework2.databinding.ButtonsItemBinding
import ru.itis.homework2.databinding.GameItemBinding
import ru.itis.homework2.model.ButtonsItemModel
import ru.itis.homework2.ui.holders.GameViewHolder
import ru.itis.homework2.model.ListGameItemModel
import ru.itis.homework2.repo.GameListRepo
import ru.itis.homework2.ui.holders.ButtonViewHolder
import kotlin.random.Random

class GameListAdapter (
    items: List<ListGameItemModel>,
    private val requestManager: RequestManager,
    private val onItemClick: (ListGameItemModel) -> Unit
) : RecyclerView.Adapter<GameViewHolder>() {

    private val dataList = mutableListOf<ListGameItemModel>()

    init {
        dataList.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val binding = GameItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GameViewHolder(binding, requestManager, onItemClick)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        holder.bindItem(itemData = dataList[position])
    }

    override fun getItemCount(): Int = dataList.size

    fun removeItem(position: Int) {
        dataList.removeAt(position)
        notifyItemRemoved(position)
    }

    fun addMultipleItems(count: Int) {
        repeat(count) {
            dataList.add(generateItem())
        }
        notifyItemRangeInserted(dataList.size - count, count)
    }
    fun addSingleItem(){
        dataList.add(generateItem())
        notifyItemInserted(dataList.size -1)
    }

    fun removeMultipleItems(count: Int) {
        val itemsToRemove = minOf(count, dataList.size)
        repeat(itemsToRemove) {
            val randomIndex = Random.nextInt(dataList.size)
            dataList.removeAt(randomIndex)
        }
        notifyDataSetChanged()
    }


    fun removeSingleItem() {
        if (dataList.isNotEmpty()) {
            val randomIndex = Random.nextInt(dataList.size)
            dataList.removeAt(randomIndex)
            notifyItemRemoved(randomIndex)
        }
    }

    private fun generateItem(): ListGameItemModel {
        return ListGameItemModel(
            id = dataList.size + 1,
            gameName = "Name",
            description = "Some Description",
            imageUrl = "https://cdn-icons-png.flaticon.com/512/6150/6150822.png"
        )
    }
}