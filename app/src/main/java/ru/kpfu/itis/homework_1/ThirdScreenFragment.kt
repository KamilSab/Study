package ru.kpfu.itis.homework_1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.kpfu.itis.R
import ru.kpfu.itis.databinding.FragmentThirdScreenBinding

class ThirdScreenFragment: Fragment(R.layout.fragment_third_screen) {

    private var binding: FragmentThirdScreenBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentThirdScreenBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentThirdScreenBinding.bind(view)

        val text = if (arguments?.getString("TEXT").isNullOrEmpty()) {
            "Default text"
        } else {
            arguments?.getString("TEXT")
        }
        binding!!.textViewThirdPage.text = text
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    companion object {
        fun newInstance(text: String) : ThirdScreenFragment {
            val args = Bundle()
            args.putString("TEXT", text)
            return ThirdScreenFragment().apply {
                arguments = args
            }
        }
    }
}