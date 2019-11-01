package com.gbpec.collify;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsAdapter extends RecyclerView.Adapter <NewsAdapter.NewsViewHolder> {


    Context context;

    public interface OnCardClickListener{
        void onCardClicked(int pos);
    }


    OnCardClickListener listener;

    public NewsAdapter(Context context) {
        this.context = context;
        listener = (OnCardClickListener) context;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_list_view,parent,false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        Glide.with(context)
                .load("https://cdn.grabon.in/gograbon/images/web-images/uploads/1563948052223/Friendship-day-offers.jpg")
                .into(holder.mImg);

        holder.mTitle.setText("This college gonna rock "+position);
        holder.mCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onCardClicked(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return 8;
    }

    class NewsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image)
        ImageView mImg;

        @BindView(R.id.news_card)
        CardView mCard;

        @BindView(R.id.news_title)
        TextView mTitle;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

        }
    }
}
