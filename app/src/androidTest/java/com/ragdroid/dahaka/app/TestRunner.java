package com.ragdroid.dahaka.app;

import android.app.Application;
import android.content.Context;
import androidx.test.runner.AndroidJUnitRunner;

import com.ragdroid.dahaka.DahakaTestApplication;


public class TestRunner extends AndroidJUnitRunner {
  @Override
  public Application newApplication(ClassLoader cl, String className, Context context)
      throws InstantiationException, IllegalAccessException, ClassNotFoundException {
    return super.newApplication(cl, DahakaTestApplication.class.getName(), context);
  }
}