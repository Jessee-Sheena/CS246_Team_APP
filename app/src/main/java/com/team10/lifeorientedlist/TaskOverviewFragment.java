package com.team10.lifeorientedlist;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class TaskOverviewFragment extends Fragment {

    String taskName;
    String timeCreated;
    String timeDue;
    String points;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_goal_overview, container, false);

        Bundle variables = getArguments();

        String never = getResources().getString(R.string.never);

        taskName = variables.getString("name", "ERROR");
        timeCreated = variables.getString("timeCreated", never);
        timeDue = variables.getString("timeDue", never);
        points = variables.getString("points", "0");

        ((TextView)view.findViewById(R.id.VARIABLE_fragment_goal_overview_goalName)).setText(taskName);
        ((TextView)view.findViewById(R.id.VARIABLE_fragment_goal_overview_goalDateCreated)).setText(timeCreated);
        ((TextView)view.findViewById(R.id.VARIABLE_fragment_goal_overview_goalDateDue)).setText(timeDue);
        ((TextView)view.findViewById(R.id.VARIABLE_fragment_goal_overview_goalPoints)).setText(points);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Task Overview");

        Button deleteButton = view.findViewById(R.id.BUTTON_fragment_goal_overview_Delete);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Deletes the Task from the User
             *
             * @param v The view we're in
             */
            @Override
            public void onClick(View v) {
                ManageData manageData = new ManageData(v.getContext());

                Task task = new Goal(timeCreated, timeDue, taskName, Integer.valueOf(points) );

                User user = manageData.getUser();

                boolean deleted = false;
                int indexOf = user.indexOfTask(task);
                if (indexOf != -1) {
                    user.getGoalList().remove(indexOf);
                    deleted = true;
                }
                else {
                    //Change this when an individual fragment is created for Todo
                    task = new Todo(timeCreated, timeDue, taskName, Integer.valueOf(points) );
                    indexOf = user.indexOfTask(task);
                    if (indexOf != -1) {
                        user.getToDoList().remove(indexOf);
                        deleted = true;
                    }
                }

                //Save and notify deletion
                if (deleted) {
                    //Change fragment
                    MainActivity.fragment = new MainContent();
                    FragmentTransaction ft = ((FragmentActivity) v.getContext()).getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.content_main, MainActivity.fragment);
                    ft.commit();
                    //Save
                    manageData.storeData(user);
                    //Notify
                    Toast.makeText(v.getContext(), "Task deleted!", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(v.getContext(), "Task could not be deleted!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

