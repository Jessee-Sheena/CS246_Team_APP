package com.team10.lifeorientedlist;

import android.content.Intent;
import android.location.SettingInjectorService;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Settings controls the changes of color themes and any other changes user could make
 */

public class Settings extends Fragment implements View.OnClickListener {
    Button spring;
    Button summer;
    Button fall;
    Button winter;
    View view;
    ManageData manageData;

    /**
     * On Create over ride creates the view and holds buttons that change the theme
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        manageData = new ManageData(getContext());
        view = inflater.inflate(R.layout.settings, container, false);
        spring = (Button) view.findViewById(R.id.spring);
        spring.setOnClickListener(this);
        summer= (Button) view.findViewById(R.id.summer);
        summer.setOnClickListener(this);
        fall = (Button) view.findViewById(R.id.fall);
        fall.setOnClickListener(this);
        winter = (Button) view.findViewById(R.id.winter );
        winter .setOnClickListener(this);
        return view;

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Changes Settings");

    }

    /**
     * onclick listener for the buttons that will set the different color themes
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.spring:
                manageData.setUserColors(R.color.spring1,R.color.spring2, R.color.spring3);
                startActivity(new Intent(getContext(), MainActivity.class));
                break;
            case R.id.summer:
                manageData.setUserColors(R.color.summer1,R.color.summer2, R.color.summer3);
                startActivity(new Intent(getContext(), MainActivity.class));
                break;
            case R.id.fall:
                manageData.setUserColors(R.color.fall1,R.color.fall2, R.color.fall3);
                startActivity(new Intent(getContext(), MainActivity.class));
                break;
            case R.id.winter:
                manageData.setUserColors(R.color.winter1,R.color.winter2, R.color.winter3);
                startActivity(new Intent(getContext(), MainActivity.class));
                break;


        }

    }
}
