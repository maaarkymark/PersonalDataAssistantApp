package com.pjboud.personaldataassistant;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class TaskListFragment extends Fragment {
    String selectedProject;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        // Create Adapter
        final ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<String>(
                        getActivity(),
                        R.layout.fragment_todo_task_list_item,
                        R.id.chkStatus,
                        Data.tasks);

        // Inflate the View
        View rootView = inflater.inflate(R.layout.fragment_todo_task_list, container, false);
        rootView.setClickable(false);
        rootView.setOnClickListener(null);

        // Get a reference to the ListView, and attach this adapter to it.
        ListView listView = (ListView) rootView.findViewById(R.id.lv_tasks);
        listView.setAdapter(itemsAdapter);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        selectedProject = "select project";

        Button createTask = getActivity().findViewById(R.id.new_task_btn);

        createTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            EditText taskName = getActivity().findViewById(R.id.et_task_name);
            String taskNameValue = taskName.getText().toString();

            getFragmentManager().beginTransaction().replace(R.id.fragment_top, new TodoFragment());

            if (selectedProject == "select project") {
                Toast.makeText(getActivity(), "You Must Select a Project",
                        Toast.LENGTH_LONG).show();
             /*} else if((TaskListFragment) getFragmentManager().
                        findFragmentById(R.id.fragment_bottom) == null) {
                Toast.makeText(getActivity(), "Wrong Frag", Toast.LENGTH_LONG).show();

                getFragmentManager().beginTransaction().replace(R.id.fragment_bottom,
                        new TodoFragment(), "TaskListFragment");
                Data.tasks.add(taskNameValue);
                taskName.setText("");
                ((TodoActivity)getActivity()).refreshFragment();
                */

            } else if(taskNameValue.matches("")) {
                Toast.makeText(getActivity(), "Enter a Task Name", Toast.LENGTH_LONG).show();
            } else if(Data.tasks.contains(taskNameValue)) {
                Toast.makeText(getActivity(), taskNameValue + " already exists.", Toast.LENGTH_LONG).show();
                taskName.setText("");
            } else {
                Data.tasks.add(taskNameValue);
                taskName.setText("");
                ((TodoActivity)getActivity()).refreshTaskListFragment();
            }
            }
        });


    }

    public void setSelectedProject(String project) {
        selectedProject = project;
    }
}