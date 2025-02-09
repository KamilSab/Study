package ru.itis.homework2.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import ru.itis.homework2.databinding.FragmentDatailedGameInfoBinding

class DatailedGameInfoFragment : Fragment() {

    private var binding: FragmentDatailedGameInfoBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDatailedGameInfoBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDatailedGameInfoBinding.bind(view)
        val glide = Glide.with(requireContext())

        glide.load(arguments?.getString("URL"))
            .into(binding!!.pictureIv)
        binding!!.nameTv.text = arguments?.getString("NAME")
        binding!!.descriptionTv.text = arguments?.getString("DESC")
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    companion object {
        fun getInstance(url: String, name: String, desc: String): DatailedGameInfoFragment {
            val args = Bundle()
            args.putString("URL", url)
            args.putString("NAME", name)
            args.putString("DESC", desc)
            return DatailedGameInfoFragment().apply {
                arguments = args
            }
        }
    }
}