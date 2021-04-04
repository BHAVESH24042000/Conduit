package com.example.conduit.ui.article

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.conduit.R
import com.example.conduit.Utils.loadImage
import com.example.conduit.Utils.timeStamp
import com.example.conduit.databinding.FragmentArticleBinding

class ArticleFragment:Fragment() {

    private var _binding: FragmentArticleBinding? = null
    lateinit var articleViewModel: ArticleViewModel
    var articleId: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        articleViewModel = ViewModelProvider(this).get(ArticleViewModel::class.java)
        _binding = FragmentArticleBinding.inflate(inflater, container, false)

        arguments?.let {
            articleId = it.getString(resources.getString(R.string.arg_article_id))
        }

        articleId?.let { articleViewModel.fetchArticle(it) }

        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        articleViewModel.article.observe({ lifecycle }) {
            _binding?.apply {
                titleTextView.text = it.title
                bodyTextView.text = it.body
                authorTextView.text = it.author.username
               dateTextView.timeStamp = it.createdAt
                avatarImageView.loadImage(it.author.image, true)
            }
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}