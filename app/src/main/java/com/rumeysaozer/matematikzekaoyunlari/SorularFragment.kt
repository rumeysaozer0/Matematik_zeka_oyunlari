package com.rumeysaozer.matematikzekaoyunlari

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.rumeysaozer.matematikzekaoyunlari.databinding.FragmentSorularBinding


class SorularFragment : Fragment(), View.OnClickListener{
    private var _binding: FragmentSorularBinding? = null
    private val binding get() = _binding!!
    private var name = "Muhammed"
    private var mid: Int = 0

    private var mCorrectAnswers: Int = 0
    private var mCurrentPosition: Int = 1
    private var mQuestionList: ArrayList<Soru>? = null
    private var mSelectedPosition: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSorularBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            name = SorularFragmentArgs.fromBundle(it).name
            mid = SorularFragmentArgs.fromBundle(it).mid
        }
        if(mid == 1){
            mQuestionList = Constants.getToplama()
        }
        if(mid == 2){
            mQuestionList = Constants.getCikarma()
        }
        if(mid == 3){
            mQuestionList = Constants.getCarpma()
        }
        if(mid == 4){
            mQuestionList = Constants.getBolme()
        }
        if(mid == 5){
            mQuestionList = Constants.getKarisik()
        }
        if(mid == 6){
            mQuestionList = Constants.getSembol()
        }
        setQuestion()
        binding.option1.setOnClickListener(this)
        binding.option2.setOnClickListener(this)
        binding.option3.setOnClickListener(this)
        binding.option4.setOnClickListener(this)
        binding.sentQuestion.setOnClickListener(this)
    }

    private fun setQuestion(){
        val question = mQuestionList!![mCurrentPosition -1]
        defaultOptionsView()
        if(mCurrentPosition == mQuestionList!!.size){
            binding.sentQuestion.text = "Bitir"
        }else{
            binding.sentQuestion.text = "Gönder"
        }
        binding.progress.progress = mCurrentPosition
        binding.progressText.text = "$mCurrentPosition" + "/" + binding.progress.max
        binding.word.text = question!!.question
        binding.option1.text = question.optionOne
        binding.option2.text = question.optionTwo
        binding.option3.text = question.optionThree
        binding.option4.text = question.optionFour
    }
    private fun defaultOptionsView(){
        val options = ArrayList<TextView>()
        options.add(0, binding.option1)
        options.add(1, binding.option2)
        options.add(2, binding.option3)
        options.add(3, binding.option4)
        for (option in options){
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(requireContext(), R.drawable.option_border)
        }
    }
    private fun selectedOptionView(textView: TextView, selectedOptionNumber: Int){
        defaultOptionsView()
        mSelectedPosition = selectedOptionNumber
        textView.setTextColor(Color.parseColor("#363A43"))
        textView.setTypeface(textView.typeface, Typeface.BOLD)
        textView.background = ContextCompat.getDrawable(requireContext(), R.drawable.selected_option_border)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.option1 ->{
                selectedOptionView(binding.option1,1)
            }
            R.id.option2 ->{
                selectedOptionView(binding.option2,2)
            }
            R.id.option3 ->{
                selectedOptionView(binding.option3,3)
            }
            R.id.option4 ->{
                selectedOptionView(binding.option4,4)
            }
            R.id.sentQuestion ->{
                if(mSelectedPosition == 0){
                    mCurrentPosition++
                    when{
                        mCurrentPosition <= mQuestionList!!.size ->{
                            setQuestion()
                        }else ->{
                        val action = SorularFragmentDirections.actionSorularFragmentToLastFragment(name,mCorrectAnswers,mQuestionList!!.size)
                        findNavController().navigate(action)
                    }
                    }
                    }else{
                    val question = mQuestionList?.get(mCurrentPosition -1)
                    if(question!!.correctAnswer != mSelectedPosition){
                        answerView(mSelectedPosition, R.drawable.wrong_option)
                    }else{
                        mCorrectAnswers++
                    }
                    answerView(question.correctAnswer, R.drawable.correct_option)

                    if(mCurrentPosition == mQuestionList!!.size){
                        binding.sentQuestion.text = "Bitir"
                    }else{
                        binding.sentQuestion.text = "Sonraki"
                    }
                    mSelectedPosition = 0
                }
            }
        }
    }

    private fun answerView(answer: Int, drawableView: Int){
        when(answer){
            1->{
                binding.option1.background = ContextCompat.getDrawable(requireContext(), drawableView)
            }
            2->{
                binding.option2.background = ContextCompat.getDrawable(requireContext(), drawableView)
            }
            3->{
                binding.option3.background = ContextCompat.getDrawable(requireContext(), drawableView)
            }
            4->{
                binding.option4.background = ContextCompat.getDrawable(requireContext(), drawableView)
            }
        }
    }


}