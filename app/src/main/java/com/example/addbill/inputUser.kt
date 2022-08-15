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
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.addbill.Viewmodel.UserViewModel
import com.example.addbill.model.User_list

class inputUser : Fragment() {



    private var IUri: Uri? = null
     private val sharedviewmodel by activityViewModels<UserViewModel>()





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_input_user, container, false)
        (activity as AppCompatActivity).supportActionBar?.title = "UserInfo"


         val callback = object :OnBackPressedCallback(true){
             override fun handleOnBackPressed() {
                 findNavController().navigate(R.id.action_inputUser_to_user_list)


             }

         }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,callback)
        val  userimg = view.findViewById<ImageView>(R.id.userImg)
        val username = view.findViewById<EditText>(R.id.username)
        val usernum = view.findViewById<EditText>(R.id.phonenumber)
        val submit  =view.findViewById<Button>(R.id.submit)

        val getImg = registerForActivityResult(
            ActivityResultContracts.GetContent(),
            ActivityResultCallback {

                IUri =it
                userimg.setImageURI(it)

            }
        )


        userimg.setOnClickListener {

                    getImg.launch("image/*")


        }
        submit.setOnClickListener {

            sharedviewmodel.insertUser(User_list(null,IUri.toString(),username.text.toString(),usernum.text.toString().toLong()))

            Navigation.findNavController(view).navigate(R.id.action_inputUser_to_user_list)





        }








        return view
    }




}