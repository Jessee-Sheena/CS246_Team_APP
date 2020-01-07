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

public class RewardOverviewFragment extends Fragment {
    String rewardName;
    String timeCreated;
    String points;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reward_overview, container, false);

        Bundle variables = getArguments();

        String never = getResources().getString(R.string.never);

        rewardName = variables.getString("name", "ERROR");
        timeCreated = variables.getString("timeCreated", never);
        points = variables.getString("points", "0");

        ((TextView)view.findViewById(R.id.VARIABLE_fragment_reward_overview_rewardName)).setText(rewardName);
        ((TextView)view.findViewById(R.id.VARIABLE_fragment_reward_overview_rewardDateCreated)).setText(timeCreated);
        ((TextView)view.findViewById(R.id.VARIABLE_fragment_reward_overview_rewardPoints)).setText(points);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Task Overview");

        Button deleteButton = view.findViewById(R.id.BUTTON_fragment_reward_overview_Delete);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Deletes the Task from the User
             *
             * @param v The view we're in
             */
            @Override
            public void onClick(View v) {
                ManageData manageData = new ManageData(v.getContext());

                Reward reward = new Reward(rewardName, Integer.valueOf(points), timeCreated);

                User user = manageData.getUser();

                user.getRewardList().remove(reward);

                /*boolean deleted = false;
                int indexOf = user.indexOfTask(reward);
                if (indexOf != -1) {
                    user.getRewardList().remove(indexOf);
                    deleted = true;*/

                //Save and notify deletion
                if (true/*delete*/) {
                    //Change fragment
                    MainActivity.fragment = new MainContent();
                    FragmentTransaction ft = ((FragmentActivity) v.getContext()).getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.content_main, MainActivity.fragment);
                    ft.commit();
                    //Save
                    manageData.storeData(user);
                    //Notify
                    Toast.makeText(v.getContext(), "Reward deleted!", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(v.getContext(), "Task could not be deleted!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
