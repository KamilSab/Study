package ru.itis.homework3.adapters

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class QuestionsPagerAdapter(
    manager: FragmentManager,
    lifecycle: Lifecycle,
    private var list: List<Fragment>
) : FragmentStateAdapter(manager, lifecycle) {

    override fun getItemCount(): Int = list.size

    override fun createFragment(position: Int): Fragment {
        Log.d("TAG", "fdsf")
        return list[position]
    }
}