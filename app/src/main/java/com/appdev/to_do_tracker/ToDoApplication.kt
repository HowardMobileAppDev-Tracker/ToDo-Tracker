package com.appdev.to_do_tracker

import android.app.Application

class ToDoApplication : Application() {
    val db by lazy { AppDatabase.getInstance(this) }
}