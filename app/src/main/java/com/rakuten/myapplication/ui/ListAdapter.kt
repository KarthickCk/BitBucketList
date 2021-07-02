package com.rakuten.myapplication.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.rakuten.myapplication.BR
import com.rakuten.myapplication.DataBindingViewHolder
import com.rakuten.myapplication.R
import com.rakuten.myapplication.domain.BitBucketRepo

class ListAdapter(
    private val onListItemClick: OnListItemClick
) : RecyclerView.Adapter<DataBindingViewHolder>() {

    private val repoList = mutableListOf<BitBucketRepo.Repo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataBindingViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            inflater, R.layout.layout_repo_item, parent, false
        )
        return DataBindingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataBindingViewHolder, position: Int) {
        holder.viewDataBinding.setVariable(BR.data, repoList[position])
        holder.viewDataBinding.root.setOnClickListener {
            onListItemClick.onItemClick(repoList[position])
        }
    }

    override fun getItemCount() = repoList.size

    fun notifyAdapter(list: List<BitBucketRepo.Repo>) {
        val prevPosition = repoList.size
        repoList.addAll(list)
        notifyItemInserted(prevPosition)
    }

    interface OnListItemClick {
        fun onItemClick(bitBucketRepo: BitBucketRepo.Repo)
    }
}