package com.salesianostriana.worldquizapp;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.salesianostriana.worldquizapp.model.UserEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link IRankingListener}
 * interface.
 */
public class UserEntityRankingFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private IRankingListener mListener;
    Context context;
    RecyclerView recyclerView;
    List<UserEntity> listaDummyUsuarios;
    MyUserEntityRecyclerViewAdapter adapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public UserEntityRankingFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static UserEntityRankingFragment newInstance(int columnCount) {
        UserEntityRankingFragment fragment = new UserEntityRankingFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_userentity_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
             context = view.getContext();
             recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

        }

        listaDummyUsuarios = new ArrayList<>();
        listaDummyUsuarios.add(new UserEntity("Pablo","Rodriguez Roldan","sulfuro","pablo@gmail.com","https://image.freepik.com/vector-gratis/perfil-avatar-hombre-icono-redondo_24640-14044.jpg",20,10));
        listaDummyUsuarios.add(new UserEntity("Pablo","Rodriguez Roldan","sulfuro","pablo@gmail.com","https://image.freepik.com/vector-gratis/perfil-avatar-hombre-icono-redondo_24640-14044.jpg",25,5));
        listaDummyUsuarios.add(new UserEntity("Pablo","Rodriguez Roldan","sulfuro","pablo@gmail.com","https://image.freepik.com/vector-gratis/perfil-avatar-hombre-icono-redondo_24640-14044.jpg",2,2));
        listaDummyUsuarios.add(new UserEntity("Pablo","Rodriguez Roldan","sulfuro","pablo@gmail.com","https://image.freepik.com/vector-gratis/perfil-avatar-hombre-icono-redondo_24640-14044.jpg",0,4));
        listaDummyUsuarios.add(new UserEntity("Pablo","Rodriguez Roldan","sulfuro","pablo@gmail.com","https://image.freepik.com/vector-gratis/perfil-avatar-hombre-icono-redondo_24640-14044.jpg",2,2));


        adapter = new MyUserEntityRecyclerViewAdapter(listaDummyUsuarios,mListener,context);
        recyclerView.setAdapter(adapter);
        Collections.sort(listaDummyUsuarios, new comparadorPuntos());

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof IRankingListener) {
            mListener = (IRankingListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

}

class comparadorPuntos implements Comparator<UserEntity> {
    public int compare(UserEntity a, UserEntity b) {
        return (String.valueOf(b.getTotalPoints())).compareTo(String.valueOf(a.getTotalPoints()));
    }
}