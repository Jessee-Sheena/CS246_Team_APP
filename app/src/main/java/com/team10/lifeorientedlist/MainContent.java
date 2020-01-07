package com.team10.lifeorientedlist;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Main Content View
 * <p>Contains the view for the main page content</p>
 * @author Sheena Jessee, Michael Hegerhorst
 * @version 1.0
 */
public class MainContent extends Fragment {
      ManageData manageData;
    /**
     * Sets up the View after it's been initialized.
     *
     * @author Sheena Jessee, Michael Hegerhorst
     *
     * @param inflater The LayoutInflater
     * @param container The ViewGroup
     * @param savedInstanceState The bundle
     * 
     * @return Returns the created view
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_content, container, false);
        manageData = new ManageData(getContext());
        if(manageData.getPrimaryColor() != 0) {
            view.findViewById(R.id.hr_rule).setBackground(new ColorDrawable(getResources().getColor(manageData.getPrimaryColor())));
        }
        //Text setup
        //Load in the user
        User user = new ManageData(getActivity()).getUser();
        //Get TextViews
        TextView totalGoals = view.findViewById(R.id.view_goals);
        TextView totalTodo = view.findViewById(R.id.view_todo);
        TextView totalPoints = view.findViewById(R.id.view_points);
        if(manageData.getSecondaryColor() != 0) {
            totalPoints.setBackground(new ColorDrawable(getResources().getColor(manageData.getSecondaryColor())));
            totalGoals.setBackground(new ColorDrawable(getResources().getColor(manageData.getSecondaryColor())));
            totalTodo.setBackground(new ColorDrawable(getResources().getColor(manageData.getSecondaryColor())));
        }

        //Set strings
        String totalStart = getResources().getString(R.string.totalStart);
        String totalEnd = getResources().getString(R.string.totalEnd);
        String pointsEnd = getResources().getString(R.string.pointsTotalEnd);

        String goals = getResources().getString(R.string.goals);
        String todos = getResources().getString(R.string.todos);

        String goalText = totalStart + " " + user.getGoalList().size() + " " + goals + " " + totalEnd;
        String todoText = totalStart + " " + user.getToDoList().size() + " " + todos + " " + totalEnd;
        String pointsText = totalStart + " " + user.getCurrentPoints() + " " + pointsEnd;

        totalGoals.setText(goalText);
        totalTodo.setText(todoText);
        totalPoints.setText(pointsText);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Main Content");

        TextView goals = view.findViewById(R.id.view_goals);
        goals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.fragment = new GoalView();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.content_main, MainActivity.fragment);
                ft.commit();
            }
        });



        TextView todos = view.findViewById(R.id.view_todo);
        todos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.fragment = new ToDoView();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.content_main, MainActivity.fragment);
                ft.commit();
            }
        });



        TextView rewards = view.findViewById(R.id.view_points);
        rewards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.fragment = new RewardView();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.content_main, MainActivity.fragment);
                ft.commit();
            }
        });
    }
}
