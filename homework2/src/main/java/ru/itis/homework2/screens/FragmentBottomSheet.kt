package ru.itis.homework2.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.itis.homework2.adapters.GameListAdapter
import ru.itis.homework2.databinding.BottomSheetDialogFragmentBinding

class FragmentBottomSheet(
    private val adapter: GameListAdapter
) : BottomSheetDialogFragment() {

    private var binding : BottomSheetDialogFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BottomSheetDialogFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding!!.buttonAddMultiple.setOnClickListener { addMultipleItems() }
        binding!!.buttonRemoveMultiple.setOnClickListener { removeMultipleItems() }
        binding!!.buttonAddSingle.setOnClickListener { addSingleItem() }
        binding!!.buttonRemoveSingle.setOnClickListener { removeSingleItem() }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun addMultipleItems() {
        val count = binding!!.editTextCount.text.toString().toIntOrNull() ?: 0
        adapter.addMultipleItems(count)
        dismiss()
    }

    private fun removeMultipleItems() {
        val count = binding!!.editTextCount.text.toString().toIntOrNull() ?: 0
        adapter.removeMultipleItems(count)
        dismiss()
    }

    private fun addSingleItem() {
        adapter.addSingleItem()
        dismiss()
    }

    private fun removeSingleItem() {
        adapter.removeSingleItem()
        dismiss()
    }
}