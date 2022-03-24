package com.rumeysaozer.matematikzekaoyunlari

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.rumeysaozer.matematikzekaoyunlari.databinding.FragmentFirstBinding


class FirstFragment : Fragment() {
    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!
    private var mInterstitialAd: InterstitialAd? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadInterAdd()

        binding.oyna.setOnClickListener {
            if(binding.startNameEdit.text.toString().isEmpty()){
                Toast.makeText(requireContext(), "Lütfen isminizi giriniz...", Toast.LENGTH_LONG).show()
            }
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

                    val action = FirstFragmentDirections.actionFirstFragmentToCategoryFragment2(binding.startNameEdit.text.toString())
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
            val action = FirstFragmentDirections.actionFirstFragmentToCategoryFragment2(binding.startNameEdit.text.toString())
            findNavController().navigate(action)

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}