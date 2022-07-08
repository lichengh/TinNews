package com.laioffer.tinnews.ui.search;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.laioffer.tinnews.R;
import com.laioffer.tinnews.databinding.SearchNewsItemBinding;
import com.laioffer.tinnews.model.Article;
import com.laioffer.tinnews.ui.save.SavedNewsAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SearchNewsAdapter extends RecyclerView.Adapter<SearchNewsAdapter.SearchNewsViewHolder>{

    // callback for jumping from search page to detail page
    interface ItemCallback {

        void onOpenDetails(Article article);
    }

    // 1. Supporting data:
    // TODO
    private static final String TAG = "SearchNewsAdapter";
    private List<Article> articles = new ArrayList<>();
    private ItemCallback itemCallback;


    public void setArticles(List<Article> newsList) {
        articles.clear();
        articles.addAll(newsList);
        notifyDataSetChanged();
    }

    public void setItemCallback(ItemCallback itemCallback) {
        this.itemCallback = itemCallback;
    }


    // 2. SearchNewsViewHolder:
    // TODO
    @NonNull
    @Override
    public SearchNewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.search_news_item, parent, false);
        SearchNewsViewHolder viewHolder = new SearchNewsViewHolder(itemView);
        Log.d(TAG, viewHolder.toString() + "onCreateViewHolder");
        return new SearchNewsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchNewsViewHolder holder, int position) {
        Log.d(TAG, holder.toString() + "onBindViewHolder");
        Article article = articles.get(position);
        holder.itemTitleTextView.setText(article.title);
        Picasso.get().load(article.urlToImage).
                resize(200,200).into(holder.itemImageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemCallback.onOpenDetails(article);
            }
        });
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }


    // 3. Adapter overrides:
    // TODO
    // ViewHolder的作用：容器。
    public static class SearchNewsViewHolder extends RecyclerView.ViewHolder {
        ImageView itemImageView;
        TextView itemTitleTextView;

        public SearchNewsViewHolder(@NonNull View itemView) {
            super(itemView);
            SearchNewsItemBinding binding = SearchNewsItemBinding.bind(itemView);
            itemImageView = binding.searchItemImage;
            itemTitleTextView = binding.searchItemTitle;
        }
    }
}
