package com.example.addbill

import android.app.Dialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.addbill.UserAdapter.UserAdapter
import com.example.addbill.UserAdapter.UserAdapterfuntion
import com.example.addbill.Viewmodel.UserViewModel
import com.example.addbill.model.User_list
import com.google.android.material.floatingactionbutton.FloatingActionButton

class User_list : Fragment(), UserAdapterfuntion {


    private val sharedviewmodel by activityViewModels<UserViewModel>()
    private lateinit var adapter: UserAdapter
    private lateinit var data: List<User_list>

    private lateinit var views: View


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_user_list, container, false)
        views = view

        (activity as AppCompatActivity).supportActionBar?.title = "Billing & Expense".uppercase()

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                activity?.finish()


            }

        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
        val adduser = view.findViewById<FloatingActionButton>(R.id.floatingActionButton)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        val seacrh = view.findViewById<EditText>(R.id.editText)



        seacrh.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {

                filter(s.toString())

            }


        })



        sharedviewmodel.getAllUser().observe(viewLifecycleOwner, { list ->

            list?.let {
                data = list
                adapter = UserAdapter(requireContext().applicationContext, data, this)
                recyclerView.adapter = adapter
                recyclerView.layoutManager = LinearLayoutManager(requireContext())
                recyclerView.setHasFixedSize(true)

            }


        })







        adduser.setOnClickListener {
            inputFragment(view)

        }




















        return view

    }

    private fun filter(text: String) {

        val temp: ArrayList<User_list> = ArrayList()

        for (i in data as ArrayList<User_list>) {

            if (i.username!!.lowercase().contains(text.lowercase())) {
                temp.add(i)


            }

        }

        adapter.updateList(temp)


    }


    private fun inputFragment(view: View) {

        Navigation.findNavController(view).navigate(R.id.action_user_list_to_inputUser)


    }

    override fun onDeleteUser(userList: User_list) {

        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.billdelete_alert)


        val comfirm = dialog.findViewById<Button>(R.id.comfirm)
        val cancel= dialog.findViewById<Button>(R.id.discard)
        cancel.setOnClickListener {


            dialog.dismiss()

        }

        comfirm.setOnClickListener {


            sharedviewmodel.deleteuser(userList)
            sharedviewmodel.delteuserByname(userList.username.toString())
            dialog.dismiss()

        }

        dialog.setCancelable(false)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)



        dialog.show()

    }


    override fun onItemUpadte(userList: User_list, view: View) {


        val action = User_listDirections.actionUserListToUsernameEdit(userList)
       Navigation.findNavController(view).navigate(action)



    }

    override fun onbilluser(userList: User_list, view: View) {


        val action = User_listDirections.actionUserListToBillByUser(userList.username!!)
        Navigation.findNavController(view).navigate(action)


    }


}