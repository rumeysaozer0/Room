package com.rumeysaozer.retrofitroomusers.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.rumeysaozer.retrofitroomusers.adapter.UserAdapter
import com.rumeysaozer.retrofitroomusers.databinding.FragmentUserBinding
import com.rumeysaozer.retrofitroomusers.viewmodel.UserViewModel


class UserFragment : Fragment() {
    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel : UserViewModel
    private val adapter = UserAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = androidx.lifecycle.ViewModelProvider(this).get(UserViewModel::class.java)
        viewModel.refrefData()
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter
        observeLiveData()
    }

    fun observeLiveData(){
        viewModel.users.observe(viewLifecycleOwner, Observer { user->
            user?.let {
                adapter.updateUserList(it)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}