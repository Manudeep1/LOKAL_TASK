package com.example.lokaltask.di

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * This is the main class that has to all our dependency class object, and we have to mention this class
 * in our MANIFEST file
 */
@HiltAndroidApp
class MyApplication: Application() {
}