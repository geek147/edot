package com.example.edot.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.edot.R
import com.example.edot.databinding.FragmentHomeBinding
import com.example.edot.ui.base.BaseFragment
import com.example.edot.ui.home.adapter.CarouselAdapter
import com.example.edot.ui.home.adapter.MovieAdapter
import com.example.edot.utils.HorizontalMarginItemDecoration
import com.example.edot.utils.Intent
import com.example.edot.utils.State
import com.example.edot.utils.ViewState

class HomeFragment :  BaseFragment<Intent,
        State>(){

    private var _binding: FragmentHomeBinding? = null
    private val queryWords = "Avengers Endgame"
    private val binding get() = _binding!!
    private lateinit var adapter: MovieAdapter
    private lateinit var carouselAdapter: CarouselAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.state.observe(viewLifecycleOwner) {
            invalidate(it)
        }

        setupRecyclerView()
        viewModel.onIntentReceived(Intent.searchMovie(queryWords))
    }

    override val layoutResourceId: Int
        get() = R.layout.fragment_home


    private fun setupRecyclerView() {
        with(binding) {
            recyclerView.setHasFixedSize(true)
            val linearLayoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            recyclerView.layoutManager = linearLayoutManager
            recyclerView.itemAnimator = null
            adapter = MovieAdapter(this@HomeFragment)
            adapter.setHasStableIds(true)
            recyclerView.adapter = adapter
        }
    }

    override fun invalidate(state: State) {
        with(binding) {
            pgProgressList.visibility = if (state.showLoading) View.VISIBLE else View.GONE
        }

        when (state.viewState) {
            ViewState.EmptyListSearch -> {
                with(binding) {
                    errorView.visibility = View.VISIBLE
                    errorView.run {
                        setUpErrorView(
                            title = resources.getString(R.string.empty_state_title),
                            message = resources.getString(R.string.empty_state_message)
                        )
//                        binding.buttonRetry.setOnClickListener {
//                            homeViewModel.onIntentReceived(Intent.searchMovie(queryWords))
//                        }
                    }
                    adapter.setData(emptyList())
                    recyclerView.visibility = View.GONE
                }
            }
            ViewState.Error -> {
                with(binding) {
                    errorView.visibility = View.VISIBLE
                    errorView.run {
                        setUpErrorView()
//                        binding.buttonRetry.setOnClickListener {
//                            homeViewModel.onIntentReceived(Intent.searchMovie(queryWords))
//                        }
                    }
                    adapter.setData(emptyList())
                    recyclerView.visibility = View.GONE
                }
            }

            ViewState.Idle -> {}
            ViewState.SuccessSearch -> {
                with(binding) {
                    //carousel
                    carouselAdapter = CarouselAdapter(this@HomeFragment)
                    carouselAdapter.setData(state.listSearchResult)
                    viewPager.adapter = carouselAdapter
                    setupCarousel(viewPager)

                    carouselAdapter = CarouselAdapter(this@HomeFragment)
                    carouselAdapter.setData(state.listSearchResult)
                    viewPager2.adapter = carouselAdapter
                    setupCarousel(viewPager2)

                    //common recycler
                    recyclerView.visibility = View.VISIBLE
                    adapter.setData(state.listSearchResult)
                    errorView.visibility = View.GONE
                }
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupCarousel(viewPager : ViewPager2){

        viewPager.offscreenPageLimit = 1

        val nextItemVisiblePx = resources.getDimension(R.dimen.viewpager_next_item_visible)
        val currentItemHorizontalMarginPx = resources.getDimension(R.dimen.viewpager_current_item_horizontal_margin)
        val pageTranslationX = nextItemVisiblePx + currentItemHorizontalMarginPx
        val pageTransformer = ViewPager2.PageTransformer { page: View, position: Float ->
            page.translationX = -pageTranslationX * position
            page.scaleY = 1 - (0.25f * kotlin.math.abs(position))
            page.alpha = 0.25f + (1 - kotlin.math.abs(position))
        }
        viewPager.setPageTransformer(pageTransformer)
        val itemDecoration = this@HomeFragment.context?.let {
            HorizontalMarginItemDecoration(
                it,
                R.dimen.viewpager_current_item_horizontal_margin
            )
        }
        if (itemDecoration != null) {
            viewPager.addItemDecoration(itemDecoration)
        }
    }
}