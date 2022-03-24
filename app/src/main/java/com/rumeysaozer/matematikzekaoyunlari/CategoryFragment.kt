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
import com.rumeysaozer.matematikzekaoyunlari.databinding.FragmentCategoryBinding

class CategoryFragment : Fragment() {
    private var _binding: FragmentCategoryBinding? = null
    private val binding get() = _binding!!
    private var name = "Muhammed"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            name = CategoryFragmentArgs.fromBundle(it).name
        }

        binding.toplama.setOnClickListener {

        val action = CategoryFragmentDirections.actionCategoryFragment2ToSorularFragment(name, 1)
            findNavController().navigate(action)
        }

        binding.cikarma.setOnClickListener {
            val action = CategoryFragmentDirections.actionCategoryFragment2ToSorularFragment(name, 2)
            findNavController().navigate(action)
        }
        binding.carpma.setOnClickListener {
            val action = CategoryFragmentDirections.actionCategoryFragment2ToSorularFragment(name, 3)
            findNavController().navigate(action)
        }

        binding.bolme.setOnClickListener {
            val action = CategoryFragmentDirections.actionCategoryFragment2ToSorularFragment(name, 4)
            findNavController().navigate(action)
        }
        binding.karisik.setOnClickListener {
            val action = CategoryFragmentDirections.actionCategoryFragment2ToSorularFragment(name, 5)
            findNavController().navigate(action)
        }

        binding.sembol.setOnClickListener {
            val action = CategoryFragmentDirections.actionCategoryFragment2ToSorularFragment(name, 6)
            findNavController().navigate(action)
        }
    }






    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}