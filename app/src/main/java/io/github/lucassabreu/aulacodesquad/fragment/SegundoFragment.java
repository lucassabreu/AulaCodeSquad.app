package io.github.lucassabreu.aulacodesquad.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import io.github.lucassabreu.aulacodesquad.R;

/**
 * Created by lucas on 04/09/16.
 */
public class SegundoFragment extends Fragment {

    private TextView nNome;
    private TextView mIncrement;
    private int increment = 0;

    public static SegundoFragment newInstance(String nome) {

        Bundle args = new Bundle();
        args.putString("nome", nome);

        SegundoFragment fragment = new SegundoFragment();
        fragment.setArguments(args);
        return fragment;
    }
    
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_segundo, container, false);
        nNome = (TextView) view.findViewById(R.id.textView_nome);
        mIncrement = (TextView) view.findViewById(R.id.textView_increment);
        return view;
    }
    public void increment () {
        mIncrement.setText(Integer.toString(++increment));
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        nNome.setText(getArguments().getString("nome"));

        if (savedInstanceState != null) {
            increment = savedInstanceState.getInt("increment");
            mIncrement.setText(Integer.toString(increment));
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("increment", increment);
    }

}
