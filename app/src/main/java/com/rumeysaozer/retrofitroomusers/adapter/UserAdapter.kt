package com.rumeysaozer.retrofitroomusers.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.rumeysaozer.retrofitroomusers.R
import com.rumeysaozer.retrofitroomusers.model.UserItem
import com.rumeysaozer.retrofitroomusers.view.UserFragmentDirections

class UserAdapter (val userList : ArrayList<UserItem>):RecyclerView.Adapter<UserAdapter.UserViewHolder>(){
    class UserViewHolder(var view: View): RecyclerView.ViewHolder(view){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_row, parent,false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.view.findViewById<TextView>(R.id.name).text = userList[position].name
        holder.view.findViewById<TextView>(R.id.id).text = userList[position].id.toString()
        holder.view.setOnClickListener {
            val action = UserFragmentDirections.actionUserFragmentToUserDetailFragment(userList[position].uuid)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
      return  userList.size
    }
    fun updateUserList(newUserList: List<UserItem>){
        userList.clear()
        userList.addAll(newUserList)
        notifyDataSetChanged()
    }
}