package com.rumeysaozer.retrofitroomusers.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.rumeysaozer.retrofitroomusers.R
import com.rumeysaozer.retrofitroomusers.databinding.FragmentUserBinding
import com.rumeysaozer.retrofitroomusers.databinding.FragmentUserDetailBinding
import com.rumeysaozer.retrofitroomusers.viewmodel.UserDetailViewModel


class UserDetailFragment : Fragment() {
    private lateinit var viewModel : UserDetailViewModel
    private var _binding: FragmentUserDetailBinding? = null
    private val binding get() = _binding!!
    private var userUuid = 0
            override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let{
            userUuid = UserDetailFragmentArgs.fromBundle(it).uuid
        }
        viewModel = ViewModelProvider(this).get(UserDetailViewModel::class.java)
        viewModel.getDataFromRoom(userUuid)
        observeLiveData()
    }
    fun observeLiveData(){
        viewModel.userLiveData.observe(viewLifecycleOwner, Observer { user->
            user?.let{
                binding.name.text = user.name
                binding.email.text = user.email
                binding.tel.text = user.phone
                binding.website.text = user.website
            }

        })
    }

}