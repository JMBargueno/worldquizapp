package com.salesianostriana.worldquizapp.ui.maps;

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
        String[] info = m.getTitle().split("#");

        tvTitle = v.findViewById(R.id.textViewTitle);
        tvCapital = v.findViewById(R.id.textViewName);
        tvLang = v.findViewById(R.id.textViewLang);
        tvHabs = v.findViewById(R.id.textViewHab);
        ivFlag = v.findViewById(R.id.imageViewFlag);

        Glide.with(v.getContext())
             .load(m.getSnippet())
                .placeholder(R.drawable.ic_flag)
             .into(ivFlag);

        tvTitle.setText(info[0]);
        tvCapital.setText(info[1]);
        tvHabs.setText(info[2]+" M");
        tvLang.setText(info[3]);

        return v;
    }

    @Override
    public View getInfoWindow(Marker m) {
        return null;
    }
}
