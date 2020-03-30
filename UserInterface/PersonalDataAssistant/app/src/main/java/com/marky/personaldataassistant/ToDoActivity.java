package com.marky.personaldataassistant;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Marky on 2017-11-14.
 */

public class ToDoActivity extends OverflowMenu {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
    }

    public void goBack(View view) {
        Intent showMain = new Intent(ToDoActivity.this, MainActivity.class);
        startActivity(showMain);
        finish();
    }

    public void NewProjectOnClick(View view) {
        Fragment myfragment1;
        myfragment1 = new ToDo_BottomFragment_Projects();

        FragmentManager fm1 = getFragmentManager();
        FragmentTransaction fragmentTransaction1 = fm1.beginTransaction();
        fragmentTransaction1.replace(R.id.fragment2, myfragment1);
        fragmentTransaction1.commit();

    }
    public void NewTaskOnClick(View view) {
        Fragment myfragment2;
        myfragment2 = new ToDo_BottomFragment_Tasks();

        FragmentManager fm2 = getFragmentManager();
        FragmentTransaction fragmentTransaction2 = fm2.beginTransaction();
        fragmentTransaction2.replace(R.id.fragment2, myfragment2);
        fragmentTransaction2.commit();

    }

    @Override
    protected void onStart () {
        super.onStart();
        overridePendingTransition(R.anim.todo_in, R.anim.main_out2);
    }

}
