package com.yascode.todolist.splash;

/**
 * Created by caksono21 on 25/11/17.
 */

public interface SplashContract {

    interface view {
        void hideLoader();

        void showLoader();

        void gotoIntent();
    }

    interface presenter {
        void retreiveTodoList();
    }
}
