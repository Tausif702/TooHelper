package com.nsztta.toohelper.ui.slideshow

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.nsztta.toohelper.model_classes.PdfModel


class SlideshowViewModel : ViewModel() {
    private val databaseReference = FirebaseDatabase.getInstance().reference.child("News")
    private val storageReference=FirebaseStorage.getInstance().reference.child("News")



    private val _pdfList = MutableLiveData<List<PdfModel>>()
    val pdfList: LiveData<List<PdfModel>> get() = _pdfList


    fun fetchData() {
        val pdfList = mutableListOf<PdfModel>()

        // Assuming you have a list of items in the "Quantums" directory
        storageReference.listAll()
            .addOnSuccessListener { listResult ->
                listResult.items.forEach { item ->
                    val pdfName = item.name
                    val pdfUrl = "Your base URL here" + item.path // You need to construct the URL

                    val pdfModel = PdfModel(pdfName, pdfUrl)
                    pdfList.add(pdfModel)
                }

                _pdfList.value = pdfList
            }
            .addOnFailureListener {
                // Handle failure
                Log.e("FetchData", "Error fetching data: ${it.message}")
            }
    }
}