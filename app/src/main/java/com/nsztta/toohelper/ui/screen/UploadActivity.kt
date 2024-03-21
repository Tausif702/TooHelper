package com.nsztta.toohelper.ui.screen

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.documentfile.provider.DocumentFile
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.nsztta.toohelper.R
import com.nsztta.toohelper.databinding.ActivityUploadBinding
import com.nsztta.toohelper.model_classes.PdfModel

class UploadActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUploadBinding
    private lateinit var databaseReference: DatabaseReference
    private lateinit var storageReference: StorageReference
    private lateinit var fileUpload:String
    private var fileUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityUploadBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        fileUpload = intent.getStringExtra("fileUpload").toString()
        databaseReference = FirebaseDatabase.getInstance().reference.child(fileUpload)
        storageReference = FirebaseStorage.getInstance().reference.child(fileUpload)


        binding.fb.setOnClickListener {
            launcher.launch("*/*")
        }
        binding.uploadBtn.setOnClickListener {
            if (fileUri != null) {
                uploadFile()
            } else {
                Toast.makeText(this@UploadActivity, "please select the file", Toast.LENGTH_SHORT)
                    .show()
            }
        }


    }

    private fun uploadFile() {
        val fileName = binding.dName.text.toString()
        val mStoreRef = storageReference.child("${System.currentTimeMillis()}/$fileName")
        fileUri?.let { uri ->
            mStoreRef.putFile(uri).addOnSuccessListener {
                val pdfFile = PdfModel(fileName, fileUri.toString())
                databaseReference.push().key?.let { pushKey ->
                    databaseReference.child(pushKey).setValue(pdfFile)
                        .addOnSuccessListener {
                            fileUri = null
                            binding.dName.text = resources.getString(R.string.filename)
                            if (binding.progress.isShown) {
                                binding.progress.visibility = View.GONE
                            }
                            Toast.makeText(
                                this@UploadActivity,
                                "Uploaded Successfully",
                                Toast.LENGTH_SHORT
                            ).show()

                        }
                        .addOnFailureListener {

                            if (binding.progress.isShown) {
                                binding.progress.visibility = View.GONE
                            }

                        }
                }

            }.addOnProgressListener {
                val uploadPer = it.bytesTransferred * 100 / it.totalByteCount
                binding.progress.progress = uploadPer.toInt()
                if (!binding.progress.isShown) {
                    binding.progress.visibility = View.VISIBLE
                }

            }.addOnFailureListener {
                if (binding.progress.isShown) {
                    binding.progress.visibility = View.GONE
                }
                Toast.makeText(
                    this@UploadActivity,
                    "Uploaded Failed",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    }

    private val launcher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        fileUri = uri
        binding.dName.text =
            uri?.let { DocumentFile.fromSingleUri(this@UploadActivity, it)?.name.toString() }

    }
}