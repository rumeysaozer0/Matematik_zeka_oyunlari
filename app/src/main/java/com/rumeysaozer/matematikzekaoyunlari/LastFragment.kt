package com.rumeysaozer.matematikzekaoyunlari

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.rumeysaozer.matematikzekaoyunlari.databinding.FragmentLastBinding

class LastFragment : Fragment() {

    private var _binding: FragmentLastBinding? = null
    private val binding get() = _binding!!
    private var correct=1
    private var question = 1
    private var name = "Ayşe"
    private var mInterstitialAd: InterstitialAd? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLastBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadInterAdd()
        arguments?.let {
            name = LastFragmentArgs.fromBundle(it).name
            correct = LastFragmentArgs.fromBundle(it).correct
            question= LastFragmentArgs.fromBundle(it).question

        }
        binding.userName.text = name
        binding.score.text = "Skorunuz ${question} soruda ${correct} doğru  "
        binding.lastFinish.setOnClickListener {
            showInterAdd()

        }
    }

    private fun loadInterAdd(){
        var adRequest = AdRequest.Builder().build()

        InterstitialAd.load(requireContext(),"ca-app-pub-9610090819395446/8402979872", adRequest, object : InterstitialAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {

                mInterstitialAd = null
            }

            override fun onAdLoaded(interstitialAd: InterstitialAd) {

                mInterstitialAd = interstitialAd
            }
        })
    }


    private fun showInterAdd(){
        if(mInterstitialAd != null){

            mInterstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback(){
                override fun onAdClicked() {
                    super.onAdClicked()
                }

                override fun onAdDismissedFullScreenContent() {
                    super.onAdDismissedFullScreenContent()

                    val action = LastFragmentDirections.actionLastFragmentToFirstFragment()
                    findNavController().navigate(action)

                }

                override fun onAdFailedToShowFullScreenContent(p0: AdError) {
                    super.onAdFailedToShowFullScreenContent(p0)
                }

                override fun onAdImpression() {
                    super.onAdImpression()
                }

                override fun onAdShowedFullScreenContent() {
                    super.onAdShowedFullScreenContent()
                }

            }
            mInterstitialAd?.show(requireActivity())

        }else{
            val action = LastFragmentDirections.actionLastFragmentToFirstFragment()
            findNavController().navigate(action)

        }

    }
}