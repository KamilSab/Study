package ru.itis.homework2.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import ru.itis.homework2.ItemCallBackHelper
import ru.itis.homework2.R
import ru.itis.homework2.adapters.GameListAdapter
import ru.itis.homework2.databinding.FragmentGameListBinding
import ru.itis.homework2.model.ListGameItemModel
import ru.itis.homework2.repo.GameListRepo
import ru.itis.homework2.ui.decorators.SimpleOffsetDecoration

class GameListFragment : Fragment(R.layout.fragment_game_list) {

    private var binding: FragmentGameListBinding? = null

    private var rvAdapter: GameListAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGameListBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentGameListBinding.bind(view)
        val glide = Glide.with(requireContext())


        rvAdapter = GameListAdapter(
            items = GameListRepo.items,
            requestManager = glide,
            ::onItemClick
        )


        binding!!.mainRecycler.adapter = rvAdapter


        with(binding!!) {

            firstButton.setOnClickListener {
                binding!!.mainRecycler.layoutManager = LinearLayoutManager(context)
            }

            secondButton.setOnClickListener {
                binding!!.mainRecycler.layoutManager = GridLayoutManager(context, 3)
            }

            binding!!.mainRecycler.addItemDecoration(SimpleOffsetDecoration(12))

            binding!!.fab.setOnClickListener {
                val bottomSheet = FragmentBottomSheet(rvAdapter!!)
                bottomSheet.show(childFragmentManager, "AddRemoveBottomSheetDialogFragment")
            }
        }

        val itemCallBackHelper = ItemCallBackHelper(rvAdapter!!)
        val itemTouchHelper = ItemTouchHelper(itemCallBackHelper)
        itemTouchHelper.attachToRecyclerView(binding!!.mainRecycler)
    }

    private fun onItemClick(item: ListGameItemModel) {
        val url = item.imageUrl
        val name = item.gameName
        val desc = item.description
        val dgiFragment = DatailedGameInfoFragment.getInstance(url, name, desc)
        parentFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, dgiFragment)
            .addToBackStack(null)
            .commit()
    }
}