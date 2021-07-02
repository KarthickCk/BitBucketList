package com.rakuten.myapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rakuten.myapplication.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListActivity : AppCompatActivity(), View.OnClickListener {

    private val bitBucketViewModel by viewModel<BitBucketViewModel>()
    private lateinit var listAdapter: ListAdapter
    private lateinit var progressBar: ProgressBar
    private lateinit var nextPage: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setViews()
        observeData()
        bitBucketViewModel.getRepos()
    }

    private fun setViews() {
        progressBar = findViewById(R.id.progress)
        nextPage = findViewById(R.id.next_page)
        nextPage.setOnClickListener(this)
        listAdapter = ListAdapter(bitBucketViewModel)
        val list = findViewById<RecyclerView>(R.id.list)
        list.layoutManager = LinearLayoutManager(this)
        list.adapter = listAdapter
    }

    private fun observeData() {
        bitBucketViewModel.listItem.observe(this, Observer {
            if (it is BitBucketViewModel.UIState.BitBucketList) {
                listAdapter.notifyAdapter(it.list)
            } else if (it is BitBucketViewModel.UIState.Loading) {
                if (it.isLoading)
                    progressBar.visibility = View.VISIBLE
                else
                    progressBar.visibility = View.GONE
            } else if (it is BitBucketViewModel.UIState.HideNextButton) {
                if (it.hide)
                    nextPage.visibility = View.GONE
                else
                    nextPage.visibility = View.VISIBLE
            }
        })
    }

    override fun onClick(v: View?) {
        bitBucketViewModel.getRepos()
    }
}