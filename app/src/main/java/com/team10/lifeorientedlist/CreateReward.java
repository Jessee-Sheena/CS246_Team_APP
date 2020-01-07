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
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class CreateReward extends Fragment {
    final String TAG = "CreateReward";

    ManageData manageData;

    EditText name;
    EditText numPoints;

    User currentUser;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.create_reward, container, false);
        manageData = new ManageData(getContext());
        if(manageData.getPrimaryColor() != 0) {
            view.findViewById(R.id.hr_rule_create).setBackground(new ColorDrawable(getResources().getColor(manageData.getPrimaryColor())));
        }
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Create Reward");


        name = view.findViewById(R.id.task_name);
        numPoints = view.findViewById(R.id.points);

        currentUser = manageData.getUser();

        View createButton = view.findViewById(R.id.submit_button);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createTask(view);
            }
        });

        View cancelButton = view.findViewById(R.id.cancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.fragment = new MainContent();

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.content_main, MainActivity.fragment);
                ft.commit();
            }
        });
    }

    /**
     * Creates a Reward and saves it to the user information.
     *
     * @author Michael Hegerhorst
     * @version 1.0
     * @since 3/20/2019
     *
     * @param view The view where the buttons and such are.
     *
     * @see Reward
     */
    private void createTask(View view) {
        //Get name
        String rewardName= name.getText().toString();
        if (rewardName.isEmpty()) {
            Toast.makeText(getActivity(), "Please enter a name!", Toast.LENGTH_SHORT).show();
            return;
        }

        //Get points
        String value = numPoints.getText().toString();
        int points = 0;
        if (!value.isEmpty())
            points = Integer.parseInt(value);

        //Get creation time
        Calendar calendar = Calendar.getInstance();
        String day = Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
        String month = Integer.toString(calendar.get(Calendar.MONTH) + 1);
        String year = Integer.toString(calendar.get(Calendar.YEAR));

        String createdTime = month + "/" + day + "/" + year;

        //Create Reward
        currentUser.setReward(rewardName, points, createdTime);

        Toast.makeText(getActivity(), "Reward created!", Toast.LENGTH_SHORT).show();
        name.setText("");
        numPoints.setText("");
        //Save
        manageData.storeData(currentUser);
    }
}
