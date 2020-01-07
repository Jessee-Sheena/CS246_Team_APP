package com.team10.lifeorientedlist;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PointsOverviewFragment extends Fragment {
    String name;
    String currentPoints;
    String lifetimePoints;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.point_overview, container, false);

        User user = new ManageData(getActivity()).getUser();

        name = user.getName();
        currentPoints = Integer.toString(user.getCurrentPoints());
        lifetimePoints = Integer.toString(user.getLifetimePoints());

        if (!name.isEmpty())
            ((TextView)view.findViewById(R.id.VARIABLE_point_overview_name)).setText(name);
        ((TextView)view.findViewById(R.id.VARIABLE_point_overview_currentPoints)).setText(currentPoints);
        ((TextView)view.findViewById(R.id.VARIABLE_point_overview_lifetimePoints)).setText(lifetimePoints);

        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Points Overview");
    }
}
