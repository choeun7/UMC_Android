package com.example.onboarding;

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator

class SplashFragment : Fragment() {

private var _binding: SplashFragmentBinding? = null
private val binding get() = _binding!!

        override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
        ): View {
        _binding = SplashFragmentBinding.inflate(inflater, container, false)
        return binding.root
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //1
        Handler(Looper.getMainLooper()).postDelayed({
        if (isOnBoardingFinished()) {
        findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
        } else {
        findNavController().navigate(R.id.action_splashFragment_to_onBoardingFragment)
        }
        }, 1500)
        }

//2
private fun isOnBoardingFinished(): Boolean {
        val prefs = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        return prefs.getBoolean("finished", false)
        }

        override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        }

        }