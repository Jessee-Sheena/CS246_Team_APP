package com.team10.lifeorientedlist;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Used to put the data from a Task into a Recycler
 *
 * @author Michael Hegerhorst
 * @since 3/18/2019
 * @version 1.0
 *
 * @see Task
 * @see android.support.v7.widget.RecyclerView.Recycler
 */
public class TaskRecyclerAdapter extends RecyclerView.Adapter<TaskRecyclerAdapter.RecyclerViewHolder> {
    private List<Task> _dataset;

    public TaskRecyclerAdapter (List<Task> _dataset) {
        this._dataset = _dataset;
    }


    @Override
    public TaskRecyclerAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_item, parent, false);

        RecyclerViewHolder viewHolder = new RecyclerViewHolder(view);
        viewHolder.setTask(view, _dataset.get(i));

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder recyclerViewHolder, int i) {
        Task task = _dataset.get(i);

        recyclerViewHolder._taskName.setText(task.getName());
        recyclerViewHolder._timeCreated.setText(task.getTimeCreated());
        recyclerViewHolder._timeDue.setText(task.getTimeDue());
        recyclerViewHolder._points.setText(Integer.toString(task.getPoints()));
    }

    @Override
    public int getItemCount() {
        return _dataset.size();
    }


    public void clear() {
        int size = _dataset.size();
        _dataset.clear();
        notifyItemRangeRemoved(0, size);
    }





    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView _taskName;
        public TextView _timeCreated;
        public TextView _timeDue;
        public TextView _points;
        private Task task;

        public RecyclerViewHolder(View v) {
            super(v);

            _taskName = v.findViewById(R.id.recyclerTaskName);
            _timeCreated = v.findViewById(R.id.recyclerTimeCreated);
            _timeDue = v.findViewById(R.id.recyclerTimeDue);
            _points = v.findViewById(R.id.recyclerPoints);

            final View doneButton = v.findViewById(R.id.BUTTON_recyclerDone);
            doneButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    task.setDone(!(task.isDone()));
                    if (task.isDone()) {
                        ManageData mData = new ManageData(v.getContext());
                        User user = mData.getUser();
                        user.addCurrentPoints(task.getPoints());
                        mData.storeData(user);
                    }
                    setCheckmarkColors(v, task.isDone());
                }
            });

            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            MainActivity.fragment = new TaskOverviewFragment();

            Bundle variables = new Bundle();
            variables.putString("name", (String)_taskName.getText());
            variables.putString("timeCreated", (String)_timeCreated.getText());
            variables.putString("timeDue", (String)_timeDue.getText());
            variables.putString("points", (String)_points.getText());

            MainActivity.fragment.setArguments(variables);

            FragmentTransaction ft = ((FragmentActivity) v.getContext()).getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_main, MainActivity.fragment);
            ft.commit();
        }

        private void setTask(View view, Task task) {
            this.task = task;
            setCheckmarkColors(view.findViewById(R.id.BUTTON_recyclerDone), task.isDone());
        }

        /**
         * Switches a View's background between red_checkmark and green_checkmark.
         *
         * @param v The View with the checkmark
         */
        private void setCheckmarkColors(View v, boolean done) {
            if (done)
                v.setBackground(v.getResources().getDrawable(R.drawable.green_checkmark));
            else
                v.setBackground(v.getResources().getDrawable(R.drawable.red_checkmark));
        }
    }


}
