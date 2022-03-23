package com.example.applaunchtask.presentation.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.applaunchtask.R
import com.example.applaunchtask.databinding.FragmentLoginBinding
import com.example.applaunchtask.databinding.FragmentUserListBinding
import com.example.applaunchtask.domain.model.User
import com.example.applaunchtask.presentation.MainViewModel
import com.example.applaunchtask.presentation.UserAdapter
import com.example.applaunchtask.util.SessionManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class UserListFragment : Fragment() {

    private var _binding: FragmentUserListBinding? = null
    private val binding get() = _binding!! // Helper Property

    private val viewModel: MainViewModel by viewModels()
    private val userAdapter by lazy { UserAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentUserListBinding.inflate(layoutInflater, container, false)
        binding.userListToolbar.inflateMenu(R.menu.user_list_menu)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.userListToolbar.setOnMenuItemClickListener {
            when(it.itemId) {
                R.id.addUserMenu -> {
                    findNavController().navigate(R.id.action_userListFragment_to_userFormFragment)
                    true
                } else -> false
            }
        }

        binding.userRecyclerview.apply {
            adapter = userAdapter
            layoutManager = LinearLayoutManager(context)
        }


        lifecycleScope.launch {

            viewModel.getUser().collect{
                userAdapter.submitList(it)
            }
        }

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val user = userAdapter.getUserByPosition(viewHolder.adapterPosition)
                viewModel.deleteUser(user)
            }

        }).attachToRecyclerView(binding.userRecyclerview)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}