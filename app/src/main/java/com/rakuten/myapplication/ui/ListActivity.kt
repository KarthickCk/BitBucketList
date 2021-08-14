package com.rakuten.myapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.rakuten.myapplication.BitBucketApplication
import com.rakuten.myapplication.R
import com.rakuten.myapplication.data.BitBucketRepositor
import com.rakuten.myapplication.domain.BitBucketRepo
import java.util.concurrent.ExecutorService

class ListActivity : AppCompatActivity(), View.OnClickListener, ListAdapter.OnListItemClick {

    private val listAdapter = ListAdapter(this)
    private lateinit var progressBar: ProgressBar
    private lateinit var nextPage: Button
    private lateinit var executorService: ExecutorService
    private lateinit var bitBucketRepositor: BitBucketRepositor
    private lateinit var bitBucketsViewModel: BitBucketsViewModel
    private val gson: Gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        executorService = (application as BitBucketApplication).getExecutor()
        bitBucketRepositor = BitBucketRepositor(executorService, gson)
        bitBucketsViewModel = ViewModelProvider(this,
            BitBucketsViewModel.MyBitBucketViewModel(bitBucketRepositor)).get(BitBucketsViewModel::class.java)
        setViews()
        observeData()
        bitBucketsViewModel.getRepoList()
    }

    private fun setViews() {
        progressBar = findViewById(R.id.progress)
        nextPage = findViewById(R.id.next_page)
        nextPage.setOnClickListener(this)

        val list = findViewById<RecyclerView>(R.id.list)
        list.layoutManager = LinearLayoutManager(this)
        list.adapter = listAdapter
    }

    private fun observeData() {
        bitBucketsViewModel.repositoryList.observe(this, Observer { list ->
            listAdapter.notifyAdapter(list)
        })
    }

    override fun onClick(v: View?) {
    }

    override fun onItemClick(bitBucketRepo: BitBucketRepo.Repo) {
    }
}