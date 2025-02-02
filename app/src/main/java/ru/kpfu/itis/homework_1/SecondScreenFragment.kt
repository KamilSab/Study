package ru.kpfu.itis.homework_1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.kpfu.itis.R
import ru.kpfu.itis.databinding.FragmentSecondScreenBinding

class SecondScreenFragment : Fragment(R.layout.fragment_second_screen) {

    private var binding: FragmentSecondScreenBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondScreenBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSecondScreenBinding.bind(view)



        val text = if (arguments?.getString("TEXT").isNullOrEmpty()) {
                "Default text"
            } else {
                arguments?.getString("TEXT")
            }
        binding!!.textViewSecondScreen.text = text

        val thirdScreenFragment = ThirdScreenFragment.newInstance(text.toString())

        binding!!.secondScreenButton.setOnClickListener {
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

    companion object {
        fun newInstance(text: String) : SecondScreenFragment {
            val args = Bundle()
            args.putString("TEXT", text)
            return SecondScreenFragment().apply {
                arguments = args
            }
        }
    }
}