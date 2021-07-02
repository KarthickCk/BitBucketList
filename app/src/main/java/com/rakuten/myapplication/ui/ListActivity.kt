package com.rakuten.myapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.rakuten.myapplication.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListActivity : AppCompatActivity() {

    private val bitBucketViewModel by viewModel<BitBucketViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bitBucketViewModel.getRepos()
    }

    private fun observeData() {
        bitBucketViewModel.listItem.observe(this, Observer {

        })
    }
}