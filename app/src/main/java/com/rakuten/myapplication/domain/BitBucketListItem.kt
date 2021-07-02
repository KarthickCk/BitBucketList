package com.rakuten.myapplication.domain

import com.rakuten.myapplication.R

class BitBucketListItem(
    val repo: BitBucketRepo.Repo
) : IListItem {
    override fun getLayoutID() = R.layout.layout_repo_item
}