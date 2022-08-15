package com.example.addbill

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.addbill.UserAdapter.ByUserBill
import com.example.addbill.UserAdapter.UserAdapter
import com.example.addbill.Viewmodel.UserViewModel
import com.example.addbill.model.User_list

class Username_Edit : Fragment() {


    private var IUri: Uri? = null
    private val sharedviewmodel by activityViewModels<UserViewModel>()
    private val  usernameEditargs :Username_EditArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_username__edit, container, false)

        (activity as AppCompatActivity).supportActionBar?.title = "UpdateUserInfo"


        val callback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.action_username_Edit_to_user_list)


            }

        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,callback)
        val  upadteImg= view.findViewById<ImageView>(R.id.updateImg)
        val  updateUsername = view.findViewById<EditText>(R.id.updateusername)
        val  updatenumber = view.findViewById<EditText>(R.id.updatephone)
        val  edit  =view.findViewById<Button>(R.id.edit)

        val list = usernameEditargs.list

        Glide.with(requireContext()).load(list.userImg?.toUri()).into(upadteImg)
        updateUsername.setText(list.username.toString())
        updatenumber.setText(list.phoneNumber.toString())



        val getImg = registerForActivityResult(
            ActivityResultContracts.GetContent(),
            ActivityResultCallback {

                IUri =it
                upadteImg.setImageURI(it)

            }
        )


        upadteImg.setOnClickListener {

            getImg.launch("image/*")



        }


        edit.setOnClickListener {

              if (IUri == null)
              {
                  IUri =list.userImg?.toUri()
              }





            sharedviewmodel.updateUser(User_list(list.id!!,IUri.toString(),list.username,updatenumber.text.toString().toLong()))

            Navigation.findNavController(view).navigate(R.id.action_username_Edit_to_user_list)
            Toast.makeText(requireContext(),"UpdateName!",Toast.LENGTH_SHORT).show()








        }

        return view

    }


}