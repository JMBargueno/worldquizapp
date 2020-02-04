package com.salesianostriana.worldquizapp.ui.country;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.salesianostriana.worldquizapp.R;
import com.salesianostriana.worldquizapp.ui.country.CountryFragment.OnListFragmentInteractionListener;
import com.salesianostriana.worldquizapp.model.Country;

import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

public class MyCountryRecyclerViewAdapter extends RecyclerView.Adapter<MyCountryRecyclerViewAdapter.ViewHolder> {

    private final List<Country> mValues;
    private final OnListFragmentInteractionListener mListener;
    String flagUrl;
    Context ctx;

    public MyCountryRecyclerViewAdapter(List<Country> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_country, parent, false);

        ctx = parent.getContext();

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);

        holder.txtName.setText(holder.mItem.getName());
        holder.txtCurrency.setText(holder.mItem.getCurrencies().get(0).getCode());
        holder.txtCapital.setText(holder.mItem.getCapital());
        holder.txtLanguage.setText(holder.mItem.getLanguages().get(0).getName());

        flagUrl = holder.mItem.getFlag();

        Glide
                .with(ctx)
                .load(holder.mItem.getFlag())
                .into(holder.imageCountry);

        //Icons country

        Glide
                .with(ctx)
                .load(R.drawable.ic_coins)
                .into(holder.imageCurrency);

        Glide
                .with(ctx)
                .load(R.drawable.ic_capital)
                .into(holder.imageCapital);

        Glide
                .with(ctx)
                .load(R.drawable.ic_language)
                .into(holder.imageLanguage);




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
        public final ImageView imageCountry;
        public final ImageView imageLanguage;
        public final ImageView imageCapital;
        public final ImageView imageCurrency;
        public final TextView txtName;
        public final TextView txtCurrency;
        public final TextView txtCapital;
        public final TextView txtLanguage;
        public Country mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            imageCountry = view.findViewById(R.id.imageViewCountry);
            imageLanguage = view.findViewById(R.id.imageViewLanguage);
            imageCapital = view.findViewById(R.id.imageViewCapital);
            imageCurrency = view.findViewById(R.id.imageViewCurrency);
            txtName = view.findViewById(R.id.textViewCountryName);
            txtCurrency = view.findViewById(R.id.textViewCurrency);
            txtCapital = view.findViewById(R.id.textViewCapital);
            txtLanguage = view.findViewById(R.id.textViewLanguage);
        }

    }
}
