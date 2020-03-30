package com.pjboud.personaldataassistant;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ProjectCreateFragment extends Fragment {
    TodoFragment.Communicator comm;
    private Button createProject;
    private EditText projectName;
    private String projectNameValue = null;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceStae) {
        super.onCreateView(inflater, container, savedInstanceStae);

        return inflater.inflate(R.layout.fragment_todo_create_project, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        createProject = getView().findViewById(R.id.create_project_btn);

        createProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard(view.getContext());
                projectName = getActivity().findViewById(R.id.et_project_name);
                projectNameValue = projectName.getText().toString();

                if(projectNameValue.matches("")) {
                    Toast.makeText(getActivity(), "Enter a Project Name", Toast.LENGTH_LONG).show();
                //} elseif(projectNameValue.exists()) {
                    //Toast.makeText(getActivity(), "Project Name Already Exists", Toast.LENGTH_LONG).show();
                //}
                } else if(Data.projects.contains(projectNameValue)) {
                    Toast.makeText(getActivity(), projectNameValue + " already exists.", Toast.LENGTH_LONG).show();
                    projectName.setText("");
                } else {
                    Data.projects.add(projectNameValue);
                    // switch to this project's task list fragment

                    ((TodoActivity)getActivity()).refreshTaskListFragment();
                    ((TodoActivity)getActivity()).refreshTodoFragment();
                }
            }
        });
    }

    public void hideKeyboard(Context mContext) {
        InputMethodManager inputManager = (InputMethodManager) mContext
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        // check if no view has focus:
        View v = ((Activity) mContext).getCurrentFocus();
        if (v == null)
            return;
        inputManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

}
