package com.nsztta.toohelper.ui.screen

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.nsztta.toohelper.adapter.PdfAdapter
import com.nsztta.toohelper.databinding.ActivityNewsScreenBinding
import com.nsztta.toohelper.model_classes.PdfModel

class News_Screen_Activity : AppCompatActivity(), PdfAdapter.PdfClickListner {

    private lateinit var binding: ActivityNewsScreenBinding
    private lateinit var databaseReference: DatabaseReference
    private lateinit var adapter: PdfAdapter
    private lateinit var news: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNewsScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        news = intent.getStringExtra("news").toString()

        binding.headerTitle.text = news
        binding.fbName.text = "Upload $news"
        binding.fb.setOnClickListener {
            val intent = Intent(this@News_Screen_Activity, UploadActivity::class.java)
            intent.putExtra("fileUpload", news)
            startActivity(intent)
        }

        databaseReference = FirebaseDatabase.getInstance().reference.child(news)
        initRecyclerView()
        getAllData()


    }

    private fun initRecyclerView() {
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager =
            LinearLayoutManager(this@News_Screen_Activity, LinearLayoutManager.VERTICAL, false)
        adapter = PdfAdapter(this)
        binding.recyclerView.adapter = adapter

    }

    private fun getAllData() {
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val tempList = mutableListOf<PdfModel>()

                snapshot.children.forEach {
                    val pdfFile = it.getValue(PdfModel::class.java)
                    if (pdfFile != null) {
                        tempList.add(pdfFile)
                    }
                }
                if (tempList.isEmpty()) {
                    Toast.makeText(this@News_Screen_Activity, "Data Not Found", Toast.LENGTH_SHORT)
                        .show()
                }
                adapter.submitList(tempList)

                binding.progressBar.visibility = View.GONE

            }


            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(
                    this@News_Screen_Activity,
                    "Error ${error.message}",
                    Toast.LENGTH_SHORT
                ).show()
                binding.progressBar.visibility = View.GONE
            }

        })
    }

    override fun onPdfClicked(pdfModel: PdfModel) {
        val intent = Intent(this@News_Screen_Activity,PdfViewerActivity::class.java)
        intent.putExtra("pdfName",pdfModel.pdfName)
        intent.putExtra("pdfUrl",pdfModel.pdfUrl)
        startActivity(intent)
    }


}