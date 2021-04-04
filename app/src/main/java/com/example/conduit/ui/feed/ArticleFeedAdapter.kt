package com.example.conduit.ui.feed

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.conduit.R
//import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.conduit.Utils.loadImage
import com.example.conduit.Utils.timeStamp

//import io.realworld.android.R
//import io.realworld.android.databinding.ListItemArticleBinding
import com.example.conduit.api.models.Entities.Article
import com.example.conduit.databinding.ListItemArticleBinding



// https://developer.android.com/codelabs/kotlin-android-training-diffutil-databinding#4

class ArticleFeedAdapter(val onArticleClicked: (slug: String) -> Unit)  : ListAdapter<Article, ArticleFeedAdapter.ArticleViewHolder>(
                object : DiffUtil.ItemCallback<Article>() {
                    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                        return oldItem == newItem
                    }
                    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                        return oldItem.toString() == newItem.toString()
                    }
                }
        )
{

    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
                parent.context.getSystemService(LayoutInflater::class.java).inflate(
                        R.layout.list_item_article,
                        parent,
                        false
                )
        )
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        ListItemArticleBinding.bind(holder.itemView).apply {
            val article = getItem(position)

            authorTextView.text = article.author.username
            titleTextView.text = article.title
            bodySnippetTextView.text = article.body
            dateTextView.timeStamp = article.createdAt
            avatarImageView.loadImage(article.author.image, true)

           // root.setOnClickListener { onArticleClicked(article.slug) }
            //dateTextView.text="24 April"
            //avatarImageView.background=ColorDrawable(Color.GRAY)

            root.setOnClickListener { onArticleClicked(article.slug) }

        }

    }


}