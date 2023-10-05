package com.example.edot.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.edot.R
import com.example.edot.databinding.FragmentNotificationsBinding
import com.example.edot.ui.base.BaseFragment
import com.example.edot.utils.Intent
import com.example.edot.utils.State

class NotificationsFragment :  BaseFragment<Intent,
        State>() {

    private var _binding: FragmentNotificationsBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun invalidate(state: State) {

    }

    override val layoutResourceId: Int
        get() = R.layout.fragment_notifications

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}