package com.salesianostriana.worldquizapp.ui.dashboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.salesianostriana.worldquizapp.R;

public class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

    private static final String TAG = "CustomInfoWindowAdapter";
    private LayoutInflater inflater;
    ImageView ivFlag;
    TextView tvTitle,tvLang,tvCapital,tvHabs;

    public CustomInfoWindowAdapter(LayoutInflater inflater){
        this.inflater = inflater;
    }

    @Override
    public View getInfoContents(final Marker m) {
        View v = inflater.inflate(R.layout.infowindow_layout, null);

        tvTitle = v.findViewById(R.id.textViewTitle);
        tvCapital = v.findViewById(R.id.textViewName);
        tvLang = v.findViewById(R.id.textViewLang);
        tvHabs = v.findViewById(R.id.textViewHab);

        tvTitle.setText(m.getTitle());
        tvCapital.setText(m.getSnippet());
        tvLang.setText("Uno mu raro");
        tvHabs.setText("25.000 o más");

        ivFlag = v.findViewById(R.id.imageViewFlag);
        Glide
                .with(inflater.getContext())
                .load(R.drawable.ic_flag)
                .into(ivFlag);
        return v;
    }

    @Override
    public View getInfoWindow(Marker m) {
        return null;
    }
}
