package com.rakuten.myapplication.domain

import com.rakuten.myapplication.R

class NextPageItem(val api: String): IListItem {
    override fun getLayoutID() = R.layout.layout_next_page
}