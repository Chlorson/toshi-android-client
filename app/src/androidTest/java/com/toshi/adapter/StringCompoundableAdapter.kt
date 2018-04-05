/*
 * 	Copyright (c) 2018. Toshi Inc
 *
 * 	This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.toshi.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.TextView
import com.toshi.view.adapter.BaseCompoundableAdapter

class StringViewHolder(val view: ViewGroup) : RecyclerView.ViewHolder(view) {

    val textView: TextView by lazy {
        val textView = TextView(view.context)
        view.addView(textView)
        textView
    }

    fun displayValue(value: String) {
        textView.text = value
    }
}

class StringCompoundableAdapter(strings: List<String>) : BaseCompoundableAdapter<StringViewHolder, String>() {

    init {
        setItemList(strings)
    }

    override fun compoundableBindViewHolder(viewHolder: RecyclerView.ViewHolder, adapterIndex: Int) {
        val typedHolder = viewHolder as? StringViewHolder ?: return
        onBindViewHolder(typedHolder, adapterIndex)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StringViewHolder {
        return StringViewHolder(parent)
    }

    override fun onBindViewHolder(holder: StringViewHolder, position: Int) {
        val string = itemAt(position)
        holder.displayValue(string)
    }
}