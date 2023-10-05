package com.example.edot.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.edot.R
import com.example.edot.databinding.FragmentDashboardBinding
import com.example.edot.ui.base.BaseFragment
import com.example.edot.utils.Intent
import com.example.edot.utils.State

class DashboardFragment : BaseFragment<Intent,
        State>()  {

    private var _binding: FragmentDashboardBinding? = null


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun invalidate(state: State) {

    }

    override val layoutResourceId: Int
        get() = R.layout.fragment_dashboard

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}