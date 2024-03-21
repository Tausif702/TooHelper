package com.nsztta.toohelper.ui.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.storage.FirebaseStorage
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

class HomeViewModel : ViewModel() {

    //date
    @RequiresApi(Build.VERSION_CODES.O)
    val getDate: String =
        LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.getDefault()))

    //month
    @RequiresApi(Build.VERSION_CODES.O)
    val getMonth: String = LocalDate.now().month.getDisplayName(TextStyle.FULL, Locale.getDefault())


}