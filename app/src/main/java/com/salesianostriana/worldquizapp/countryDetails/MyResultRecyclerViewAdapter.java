package com.salesianostriana.worldquizapp.countryDetails;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.salesianostriana.worldquizapp.R;
import com.salesianostriana.worldquizapp.countryDetails.ResultFragment.OnListFragmentInteractionListener;

import com.salesianostriana.worldquizapp.model.unsplash.Result;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.List;

public class MyResultRecyclerViewAdapter extends RecyclerView.Adapter<MyResultRecyclerViewAdapter.ViewHolder> {

    private final List<Result> mValues;
    private final OnListFragmentInteractionListener mListener;
    Context ctx;

    public MyResultRecyclerViewAdapter(List<Result> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_result, parent, false);

        ctx = parent.getContext();

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);

        holder.sliderView.setIndicatorAnimation(IndicatorAnimations.FILL);
        holder.sliderView.setScrollTimeInSec(1);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final SliderView sliderView;

        public Result mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;

            sliderView = view.findViewById(R.id.imageSlider);

        }

    }
}
