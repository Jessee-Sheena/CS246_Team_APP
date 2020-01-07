package com.team10.lifeorientedlist;


import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.Calendar;

/**
 * Create Task Controls the view for creating tasks and sets the data.
 * @author Sheena Jessee, Michael Hegerhorst
 * @version 1.0
 */
public class CreateTask extends Fragment {
    final String TAG = "CreateTask";

    ManageData manageData;
    EditText name;
    RadioGroup typeProject;
    EditText numPoints;
    EditText due;

    User currentUser;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.create_task, container, false);
        manageData = new ManageData(getContext());
        if(manageData.getPrimaryColor() != 0) {
           view.findViewById(R.id.hr_rule_create).setBackground(new ColorDrawable(getResources().getColor(manageData.getPrimaryColor())));
        }
      return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Create Task");
       // manageData = new ManageData(getContext());

        name = view.findViewById(R.id.task_name);

        typeProject = view.findViewById(R.id.radio);
        numPoints = view.findViewById(R.id.points);
        due = view.findViewById(R.id.due);

        currentUser = manageData.getUser();
        typeProject.check(R.id.goalButton);

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
     * Takes a string and parses it to be a valid date.
     * {@link #validDate(String) See CreateTask.validDate(String)}
     *
     * @author Michael Hegerhorst
     * @version 1.0
     * @since 3/21/2019
     *
     * @param date The date string to parse
     * @return Returns a date string like so: 1/1/2019
     */
    private String parseDate(String date) {
        String month = "";
        String day = "";
        String year = "";

        //Get month
        int i = 0;
        char letter = date.charAt(i++);
        while (letter != '/') {
            month += letter;
            letter = date.charAt(i++);
        }
        //Get day
        letter = date.charAt(i++);
        while (letter != '/') {
            day += letter;
            letter = date.charAt(i++);
        }
        //Get year
        letter = date.charAt(i);
        while (true) {
            year += letter;
            if ((++i) < date.length())
                letter = date.charAt(i);
            else
                break;
        }

        //Fix month
        if (month.length() > 1 && month.charAt(0) == '0')
            month = new String() + month.charAt(1);
        //Fix day
        if (day.length() > 1 && day.charAt(0) == '0')
            day = new String() + day.charAt(1);
        //Fix year
        if (year.length() < 3) {
            String temp = Integer.toString(Calendar.getInstance().get(Calendar.YEAR));
            temp = temp.substring(0, 2);
            year = temp + year;
        }

        return month + "/" + day + "/" + year;
    }

    /**
     * Test if the received date is valid.
     *
     * @author Michael Hegerhorst
     * @version 1.0
     * @since 3/21/2019
     *
     * @param date The string being passed as a date
     * @return Returns a boolean as to whether or not the date is valid.
     */
    public static boolean validDate(String date) {
        String temp = Integer.toString(Calendar.getInstance().get(Calendar.YEAR));
        String millennium = "[" + temp.charAt(0) + "-9]";
        String century = "[" + temp.charAt(1) + "-9]";

        String regex = "^[0-1]?[1-9]/[0-3]?[0-9]/(" + millennium + century + ")?((19)|[2-9][0-9])$";

        if (!date.matches(regex))
            return false;

        //if (date)

        return true;
    }

    /**
     * Creates either a Goal or a Todo and saves it to the user information.
     *
     * @author Michael Hegerhorst
     * @version 1.0
     * @since 3/20/2019
     *
     * @param view The view where the buttons and such are.
     *
     * @see Goal
     * @see Todo
     */
    private void createTask(View view) {
        //Get name
        String goalName = name.getText().toString();
        if (goalName.isEmpty()) {
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

        //Get due date
        String dueDate = due.getText().toString();
        if (dueDate.isEmpty())
            dueDate = getResources().getString(R.string.never);
        else if(validDate(dueDate)) {
            dueDate = parseDate(dueDate);
        } else
            Toast.makeText(getActivity(), "Please enter a valid due date!", Toast.LENGTH_SHORT).show();

        //Create Task
        if (typeProject.getCheckedRadioButtonId() == R.id.goalButton) {
            Log.d(TAG, "goalButton != null");
            currentUser.setGoal(goalName, points, createdTime, dueDate, false);
        }
        else if (typeProject.getCheckedRadioButtonId() == R.id.todoButton) {
            Log.d(TAG, "todoButton != null");
            currentUser.setToDo(goalName, points, createdTime, dueDate, false);
        }
        else
        {
            Log.d(TAG, "both == null");
            Toast.makeText(getActivity(), "Please select Goal or To Do!", Toast.LENGTH_SHORT).show();
            return;
        }


        Toast.makeText(getActivity(), "Task created!", Toast.LENGTH_SHORT).show();
        name.setText("");
        due.setText("");
        numPoints.setText("");
        //Save
        manageData.storeData(currentUser);
        Gson gson = new Gson();
        String jsonG = gson.toJson(currentUser.getGoalList());
        String jsonT = gson.toJson(currentUser.getToDoList());
        String jsonR = gson.toJson(currentUser.getRewardList());
        UpdateDatabase update = new UpdateDatabase();
        update.insertGoal(jsonG, jsonT, jsonR);
    }




}
