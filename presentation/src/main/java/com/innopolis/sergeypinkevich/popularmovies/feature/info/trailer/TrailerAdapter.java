package com.innopolis.sergeypinkevich.popularmovies.feature.info.trailer;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.innopolis.sergeypinkevich.popularmovies.R;
import com.innopolis.sergeypinkevich.popularmovies.model.Trailer;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Sergey Pinkevich
 */

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.TrailerViewHolder> {

    private TrailerAdapterClickListener clickListener;
    private List<Trailer> trailers;

    public TrailerAdapter(TrailerAdapterClickListener listener) {
        clickListener = listener;
    }

    public void updateData(List<Trailer> trailerList) {
        trailers = trailerList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TrailerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trailer_item, parent, false);
        return new TrailerViewHolder(view, clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull TrailerViewHolder holder, int position) {
        holder.bind(trailers.get(position));
    }

    @Override
    public int getItemCount() {
        return trailers.size();
    }

    class TrailerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.trailer_number)
        TextView trailerNumber;

        View view;

        public TrailerViewHolder(View itemView, TrailerAdapterClickListener listener) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
            clickListener = listener;
            view.setOnClickListener(this);
        }

        public void bind(Trailer trailer) {
            trailerNumber.setText(trailer.getName());
        }

        @Override
        public void onClick(View view) {
            clickListener.onClick(view, getAdapterPosition());
        }
    }
}
