package com.salesianostriana.worldquizapp;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.salesianostriana.worldquizapp.model.UserEntity;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link UserEntity} and makes a call to the
 * specified {@link IRankingListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyUserEntityRecyclerViewAdapter extends RecyclerView.Adapter<MyUserEntityRecyclerViewAdapter.ViewHolder> {

    private final List<UserEntity> mValues;
    private final IRankingListener mListener;
    private Context context;

    public MyUserEntityRecyclerViewAdapter(List<UserEntity> items, IRankingListener listener,Context context) {
        this.mValues = items;
        this.mListener = listener;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_userentity, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);

        holder.nombreUsuario.setText(holder.mItem.getEmail());
        holder.puntos.setText(String.valueOf(holder.mItem.getTotalPoints()));
        holder.efectividad.setText(String.valueOf(holder.mItem.getAverageScore()));

        Glide.with(context).load(holder.mItem.getUrlFoto()).apply(RequestOptions.centerCropTransform()).into(holder.fotoPerfil);


        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onclickRanking(holder.mItem);
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
        public final TextView nombreUsuario;
        public final TextView puntos;
        public final TextView efectividad;
        public final ImageView fotoPerfil;

        public UserEntity mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;

            nombreUsuario = view.findViewById(R.id.nombreUsuario);
            puntos = view.findViewById(R.id.puntos);
            efectividad = view.findViewById(R.id.efectividad);
            fotoPerfil = view.findViewById(R.id.fotoUsuario);


        }


    }
}
