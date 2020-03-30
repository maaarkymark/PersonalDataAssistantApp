package com.pjboud.personaldataassistant;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class TodoFragment extends Fragment{
    Communicator comm;
    String selectedProject;
    private Button newProjectBtn;
    private Button newTaskBtn;
    private Button mainMenu;
    Fragment myFragment;
    Spinner spinner;
    ArrayAdapter dataAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        return inflater.inflate(R.layout.fragment_todo, container, false);
    }
    @Override
    public void onStart() {
        super.onStart();

        // get Spinner reference
        Spinner spinner = getView().findViewById(R.id.spinner_todo);

        // Create array adapter for spinner
        ArrayAdapter dataAdapter = new ArrayAdapter(
                this.getActivity(),
                android.R.layout.simple_spinner_item,
                Data.projects);

        // Drop down style will be listview with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);

        // Attach data adapter to spinner
        spinner.setAdapter(dataAdapter);

        // Set Listener
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (!parent.getItemAtPosition(position).toString().equals("select project")) {
                    comm.respond(parent.getItemAtPosition(position).toString());
                }

                //getFragmentManager().beginTransaction().replace(R.id.fragment_bottom,
                //        new TaskListFragment(), "TaskListFragment").commit();

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        selectedProject = spinner.getSelectedItem().toString();

        // Set Button variables
        newProjectBtn = getView().findViewById(R.id.new_project_btn);
        newTaskBtn = getView().findViewById(R.id.new_task_btn);
        mainMenu = getView().findViewById(R.id.main_menu_btn);

        //newTaskBtn.setEnabled(true);

        // Set Button listeners
        newProjectBtn.setOnClickListener(selectActivity);
        newTaskBtn.setOnClickListener(selectActivity);
        mainMenu.setOnClickListener(selectActivity);
    }

    private View.OnClickListener selectActivity = new View.OnClickListener() {
        @Override
        public void onClick(View view ) {
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            hideKeyboard(view.getContext());

        switch(view.getId()) {
            case(R.id.new_project_btn):
                //newTaskBtn.setEnabled(false);
                ft.replace(R.id.fragment_top, new TodoFragment());
                myFragment = new ProjectCreateFragment();
                ft.replace(R.id.fragment_bottom, myFragment, "ProjectCreateFragment");
                ft.commit();
                //selectedProject = "select project";
                break;
            case(R.id.new_task_btn):
                if(getActivity().findViewById(R.id.et_task_name).toString() != "")
                    comm.respond(selectedProject);
                ft.replace(R.id.fragment_bottom, new TaskListFragment(), "TaskListFragment");
                ft.commit();
                getView().invalidate();
                break;
            case(R.id.main_menu_btn):
                comm.respond("mainMenu");
                break;
            }
        }
    };

    public void hideKeyboard(Context mContext) {
        InputMethodManager inputManager = (InputMethodManager) mContext
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        // check if no view has focus:
        View v = ((Activity) mContext).getCurrentFocus();
        if (v == null)
            return;
        inputManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    public void onAttach(Activity a) {
        super.onAttach(a);
        comm = (Communicator) a;
    }

    // Container Activity must implement this interface
    public interface Communicator {
        public void respond(String project);
    }
}
