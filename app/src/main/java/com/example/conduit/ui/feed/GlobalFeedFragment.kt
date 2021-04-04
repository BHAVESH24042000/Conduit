package com.example.conduit.ui.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.conduit.R
import com.example.conduit.databinding.FragmentFeedBinding


//https://developer.android.com/codelabs/kotlin-android-training-view-model#4
class GlobalFeedFragment : Fragment() {

    private var _binding: FragmentFeedBinding?=null
    private lateinit var viewModel: FeedViewModel
    private lateinit var feedAdapter: ArticleFeedAdapter


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentFeedBinding.inflate(inflater, container, false)
        viewModel=ViewModelProvider(this).get(FeedViewModel::class.java)

        feedAdapter = ArticleFeedAdapter { openArticle(it) }


        _binding?.feedRecyclerView?.layoutManager=LinearLayoutManager(context)
        _binding?.feedRecyclerView?.adapter=feedAdapter

        viewModel.fetchGlobalFeed()
        viewModel.feed.observe({ lifecycle }) {
            feedAdapter.submitList(it)
        }

        return _binding?.root
    }

    fun openArticle(articleId: String) {
        findNavController().navigate(
                R.id.action_globalFeed_openArticle,
                bundleOf(
                        resources.getString(R.string.arg_article_id) to articleId
                )
        )
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}