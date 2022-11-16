package com.example.universityApp.ui;

import android.app.Application;
import android.content.Context;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UniversityApp extends Application {
    private static final int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();

    private static final ExecutorService executor = Executors.newFixedThreadPool(NUMBER_OF_CORES);

    public static ExecutorService getAppExecutor() {
        return executor;
    }
}
