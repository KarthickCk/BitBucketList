package com.rakuten.myapplication.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.rakuten.myapplication.BR
import com.rakuten.myapplication.DataBindingViewHolder
import com.rakuten.myapplication.domain.IListItem

class ListAdapter(
    private val bitBucketViewModel: BitBucketViewModel
) : RecyclerView.Adapter<DataBindingViewHolder>() {

    private val repoList = mutableListOf<IListItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataBindingViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            inflater, viewType, parent, false
        )
        return DataBindingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataBindingViewHolder, position: Int) {
        holder.viewDataBinding.setVariable(BR.data, repoList[position])
        holder.viewDataBinding.setVariable(BR.vm, bitBucketViewModel)
    }

    override fun getItemViewType(position: Int): Int {
        return repoList[position].getLayoutID()
    }

    override fun getItemCount() = repoList.size
}