package com.hakan.urunapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.contentValuesOf
import androidx.fragment.app.ListFragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hakan.urunapp.data.User
import com.hakan.urunapp.databinding.FragmentFirstBinding
import com.hakan.urunapp.databinding.UserItemBinding
import com.hakan.urunapp.fragment.FirstFragment

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    class MyViewHolder(val binding: UserItemBinding) : RecyclerView.ViewHolder(binding.root) {}

    var userList = emptyList<User>()


    @SuppressLint("NotifyDataSetChanged")
    fun deleteItem(user: User) {
        (userList as MutableList).remove(user)
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): RecyclerViewAdapter.MyViewHolder {
        return MyViewHolder(
            UserItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.MyViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.binding.apply {
            txtName.text = currentItem.name
            txtMarca.text = currentItem.marca
            txtPrice.text = currentItem.price.toString()
            root.setOnClickListener{
                onClickListener?.let { clickListener->
                    clickListener(holder.absoluteAdapterPosition, currentItem)
                }
            }
        }
    }


    override fun getItemCount(): Int {
        return userList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(userList: List<User>) {
        this.userList = userList
        notifyDataSetChanged()
    }

    private var onClickListener: ((position: Int, user: User) -> Unit)? = null
    fun setOnClickListener(f: (position: Int, user: User) -> Unit) {
        onClickListener = f
    }

}