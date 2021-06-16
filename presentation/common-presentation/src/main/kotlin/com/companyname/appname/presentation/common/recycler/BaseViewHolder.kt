package com.companyname.appname.presentation.common.recycler

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.companyname.appname.presentation.common.Action
import io.reactivex.subjects.PublishSubject

abstract class BaseViewHolder<in T : RecyclerState>(view: View) : RecyclerView.ViewHolder(view) {
    val actionStream = PublishSubject.create<Action>()

    abstract fun bindTo(state: T)
}
