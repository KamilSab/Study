package ru.itis.homework3.screens

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import ru.itis.homework3.R
import ru.itis.homework3.adapters.AnswersAdapter
import ru.itis.homework3.databinding.FragmentViewPagerBinding
import ru.itis.homework3.model.Question
import ru.itis.homework3.repo.QuestionsRepo

const val QUESTION_ID = "QUESTION_ID"

class ViewPagerFragment : Fragment(R.layout.fragment_view_pager) {

    private var binding : FragmentViewPagerBinding? = null
    private var question : Question? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            question = QuestionsRepo.getById(it.getInt(QUESTION_ID))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentViewPagerBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d("TAG", binding.toString())
        binding.apply {
            question?.let {
                this!!.questionTv.text = it.questionText
                answersRv.adapter = AnswersAdapter(
                    question = it
                )
                Log.d("TAG", "MSG")
                answersRv.layoutManager = LinearLayoutManager(requireContext())

            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    companion object{
        fun newInstance(id: Int) =
            ViewPagerFragment().apply {
                arguments = Bundle().apply {
                    putInt(QUESTION_ID, id)
                }
            }
    }
}