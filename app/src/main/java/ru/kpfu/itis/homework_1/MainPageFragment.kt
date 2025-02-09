package ru.kpfu.itis.homework_1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.kpfu.itis.R
import ru.kpfu.itis.databinding.FragmentMainPageBinding

class MainPageFragment: Fragment() {
    private var binding: FragmentMainPageBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainPageBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainPageBinding.bind(view)

        binding!!.mainPageFirstButton.setOnClickListener {
            val text = binding!!.editText.text.toString()
            val secondScreenFragment = SecondScreenFragment.newInstance(text)
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, secondScreenFragment)
                .addToBackStack(null)
                .commit()
        }

        binding!!.mainPageSecondButton.setOnClickListener {
            val text = binding!!.editText.text.toString()
            val thirdScreenFragment = ThirdScreenFragment.newInstance(text)
            val secondScreenFragment = SecondScreenFragment.newInstance(text)

            parentFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, secondScreenFragment)
                .addToBackStack(null)
                .commit()

            parentFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, thirdScreenFragment)
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}