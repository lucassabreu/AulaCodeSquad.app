package io.github.lucassabreu.aulacodesquad.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import io.github.lucassabreu.aulacodesquad.R;

/**
 * Created by lucas on 03/09/16.
 */
public class PrimeiroFragment extends Fragment
    implements CompoundButton.OnCheckedChangeListener {

    private CheckBox mCheck;

    /*
     * onCreateView é chamado na criação do fragment, segue o mesmo conceito
     * do onCreate da Activity, sendo chamado também em mudanças maiores de
     * interface, como orientação
     *
     * onViewCreated será chamado após a view ter sido criado e eh chamado
     * apenas uma vez após o onCreateView ser chamado
     */

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /*
         * the thrid parameter is a boolean, and indicates if the view must be add to the container
         */
        View view = inflater.inflate(R.layout.fragment_primeiro, container, true);
        mCheck = (CheckBox) view.findViewById(R.id.checkbox_fragment_primeiro);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mCheck.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

    }
}
