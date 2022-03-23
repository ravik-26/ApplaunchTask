package com.example.applaunchtask.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.applaunchtask.R
import com.example.applaunchtask.databinding.FragmentUserListBinding
import com.example.applaunchtask.databinding.FragmentWeatherBinding
import com.example.applaunchtask.presentation.MainViewModel
import com.example.applaunchtask.util.SessionManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class WeatherFragment : Fragment() {

    private var _binding: FragmentWeatherBinding? = null
    private val binding get() = _binding!! // Helper Property

    @Inject
    lateinit var sessionManager: SessionManager

    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentWeatherBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.logoutButton.setOnClickListener {
            sessionManager.clearSession()
            findNavController().navigate(R.id.action_weatherFragment_to_onboardingFragment)
        }

        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        lifecycleScope.launchWhenCreated {
            viewModel.getWeatherData().collectLatest {
                binding.weatherTextview.text = it.current.toString()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}