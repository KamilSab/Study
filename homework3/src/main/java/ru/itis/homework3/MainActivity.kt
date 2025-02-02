package ru.itis.homework3

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import ru.itis.homework3.adapters.QuestionsPagerAdapter
import ru.itis.homework3.databinding.ActivityMainBinding
import ru.itis.homework3.repo.QuestionsRepo
import ru.itis.homework3.screens.ViewPagerFragment

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding?.apply {
            questionVp.adapter = QuestionsPagerAdapter(supportFragmentManager, lifecycle, QuestionsRepo.questions.map {
                ViewPagerFragment.newInstance(it.id)
            })

            questionVp.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
                override fun onPageSelected(position: Int) {
                    numberTv.text = "${position + 1} / ${QuestionsRepo.questions.size}"
                    updateButton()
                }
            })

            nextQuestionBtn.setOnClickListener {
                if (questionVp.currentItem < (questionVp.adapter?.itemCount?.minus(1) ?: 0)) {
                    questionVp.currentItem += 1
                } else {
                    if(QuestionsRepo.questions.filter { it.selectedAnswer!! > 0 }.isNotEmpty()){
                        Snackbar.make(questionVp,
                            getString(R.string.ended_text), Snackbar.LENGTH_SHORT)
                            .show()
                    } else {
                        Snackbar.make(questionVp,
                            getString(R.string.not_all_answers), Snackbar.LENGTH_SHORT)
                            .show()
                    }

                }
            }

            pastQuestionBtn.setOnClickListener {
                if (questionVp.currentItem > 0) {
                    questionVp.currentItem -= 1
                }
            }

        }
    }

    private fun updateButton() {
        binding?.apply {
            pastQuestionBtn.isEnabled = questionVp.currentItem > 0

            if(questionVp.currentItem < (questionVp.adapter?.itemCount?.minus(1) ?: 0)) {
                nextQuestionBtn.setBackgroundColor(ContextCompat.getColor(applicationContext, R.color.primary))
                nextQuestionBtn.text = resources.getString(R.string.next)
            } else {
                nextQuestionBtn.setBackgroundColor(ContextCompat.getColor(applicationContext, R.color.light_green))
                nextQuestionBtn.text = resources.getString(R.string.finish)
            }
        }
    }
}