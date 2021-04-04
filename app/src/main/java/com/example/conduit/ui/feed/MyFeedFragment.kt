package com.example.conduit.ui.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.conduit.R
import com.example.conduit.databinding.FragmentFeedBinding
import com.example.conduit.ui.article.ArticleFragment
import com.example.conduit.ui.article.ArticleViewModel

class MyFeedFragment: Fragment() {

    private var _binding: FragmentFeedBinding?=null
    private lateinit var viewModel: FeedViewModel
    private lateinit var feedAdapter: ArticleFeedAdapter


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentFeedBinding.inflate(inflater, container, false)
        viewModel= ViewModelProvider(this).get(FeedViewModel::class.java)


        feedAdapter = ArticleFeedAdapter { openArticle(it) }


        _binding?.feedRecyclerView?.layoutManager= LinearLayoutManager(context)
        _binding?.feedRecyclerView?.adapter=feedAdapter

        viewModel.fetchMyFeed()
        viewModel.myfeed.observe({ lifecycle }) {
            feedAdapter.submitList(it)
        }

        return _binding?.root
    }
    fun openArticle(articleId: String) {
        findNavController().navigate(
                R.id.action_myFeed_openArticle,
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