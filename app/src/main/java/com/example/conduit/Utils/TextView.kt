package com.example.conduit.Utils

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.widget.TextView
import androidx.annotation.RequiresApi
import java.util.*

//import androidx.compose.ui.text.intl.Locale


@RequiresApi(Build.VERSION_CODES.N)
@SuppressLint("ConstantLocale")
val isoDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())

@RequiresApi(Build.VERSION_CODES.N)
@SuppressLint("ConstantLocale")
val appDateFormat = SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault())

var TextView.timeStamp: String
    @RequiresApi(Build.VERSION_CODES.N)
    set(value) {
        val date = isoDateFormat.parse(value)
        text = appDateFormat.format(date)
    }
    @RequiresApi(Build.VERSION_CODES.N)
    get() {
        val date = appDateFormat.parse(text.toString())
        return isoDateFormat.format(date)
    }