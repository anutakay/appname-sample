package com.companyname.appname.presentation.common.recycler

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

abstract class BaseAdapter(diff: DiffUtil.ItemCallback<RecyclerState>) :
    ListAdapter<RecyclerState, BaseViewHolder<*>>(diff) {

    override fun getItemViewType(position: Int): Int = getItem(position)?.getType() ?: 0
}
