package com.pjboud.personaldataassistant;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;

public class TodoActivity extends MainActivity implements TodoFragment.Communicator {
    Fragment taskListFragment;
    TODO myDb;
    Project myDb2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        myDb = new TODO(this);
        myDb2 = new Project(this);
    }

    @Override
    public void onStart() {
        super.onStart();

        Fragment todoFragment = new TodoFragment();
        Fragment taskListFragment = new TaskListFragment();

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_top, todoFragment, "TodoFragment");
        //ft.replace(R.id.fragment_bottom, taskListFragment, "TaskListFragment");
        ft.commit();
    }

    public void respond(String project) {
        if(project == "mainMenu") {
            Intent intent = new Intent(TodoActivity.this, MainMenuActivity.class);
            startActivity(intent);
        } else {

            Fragment currentFragment = getFragmentManager().findFragmentById(R.id.fragment_bottom);
            if (currentFragment instanceof TaskListFragment) {
                ((TaskListFragment) getFragmentManager().
                        findFragmentById(R.id.fragment_bottom)).setSelectedProject(project);
                //refreshTaskListFragment();
            }
        }
    }

    // Refresh TaskListFragment
    public void refreshTaskListFragment() {
        // Assign Fragment with "TaskListFragment" TAG to variable
        Fragment myFragment = new TaskListFragment();
        //
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_bottom, myFragment, "TaskListFragment");
        fragmentTransaction.commit();
    }

    // Refresh TodoFragment
    public void refreshTodoFragment() {
        // Assign Fragment with "TaskListFragment" TAG to variable
        Fragment myFragment = new TodoFragment();
        //
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_top, myFragment, "TodoFragment");
        fragmentTransaction.commit();
    }
}

