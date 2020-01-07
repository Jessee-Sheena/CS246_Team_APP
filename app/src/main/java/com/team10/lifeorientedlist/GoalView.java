package com.team10.lifeorientedlist;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Sets the recycler view for the list of goals
 * @author  Sheena Jessee
 * @version 1.0
 */

public class GoalView extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.goal_view, container, false);

        RecyclerView goalRecycler = (RecyclerView) view.findViewById(R.id.goal_view_id);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

        goalRecycler.setLayoutManager(layoutManager);

        User user = new ManageData(this.getContext()).getUser();

        TaskRecyclerAdapter goalAdapter = new TaskRecyclerAdapter(user.getGoalList());
        goalRecycler.setAdapter(goalAdapter);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Goal View");
    }
}
