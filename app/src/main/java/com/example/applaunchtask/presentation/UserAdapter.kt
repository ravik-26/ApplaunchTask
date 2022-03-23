package com.example.applaunchtask.presentation

import android.R.attr
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.applaunchtask.data.local.entities.UserEntity
import com.example.applaunchtask.databinding.UserItemBinding
import com.example.applaunchtask.domain.model.User
import android.R.attr.data
import androidx.navigation.findNavController
import com.example.applaunchtask.R


class UserAdapter: ListAdapter<UserEntity, UserAdapter.UserViewHolder>(DiffCallback) {
    class UserViewHolder(private val binding: UserItemBinding)
        : RecyclerView.ViewHolder(binding.root) {
            fun bind(user: User) {
                binding.userItem.text = user.toString()

                binding.apply {
                    root.setOnClickListener {
                        root.findNavController().navigate(R.id.action_userListFragment_to_weatherFragment)
                    }
                }
            }
        }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<UserEntity>() {
            override fun areItemsTheSame(oldItem: UserEntity, newItem: UserEntity): Boolean {
                return oldItem.email == newItem.email
            }

            override fun areContentsTheSame(oldItem: UserEntity, newItem: UserEntity): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it.toUser())
        }
    }

    fun getUserByPosition(position: Int) = getItem(position).toUser()
}