package com.example.addbill

import android.net.Uri
import android.os.Bundle
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
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.addbill.Viewmodel.UserViewModel
import com.example.addbill.model.Info

class createBill : Fragment() {

    private val sharedviewmodel by activityViewModels<UserViewModel>()

    private val args :createBillArgs by navArgs()
    private  var IUri:Uri?=null




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_create_bill, container, false)
        (activity as AppCompatActivity).supportActionBar?.title = "ExpenseInfo"

        val add = view.findViewById<Button>(R.id.addbill)
        val itemImg =  view.findViewById<ImageView>(R.id.ProductItem)

        val getImg = registerForActivityResult(
            ActivityResultContracts.GetContent(),
            ActivityResultCallback {

                IUri =it
                itemImg.setImageURI(it)

            }
        )


        itemImg.setOnClickListener {

            getImg.launch("image/*")


        }
         val num = args.idnum
        add.setOnClickListener {
            insertBill(view,num)




        }

        val callback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {

                val action = createBillDirections.actionCreateBillToBillByUser(num)
                findNavController().navigate(action)




            }

        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,callback)

        return view
    }

    private fun insertBill(view: View, num: String) {

        val itemname = requireView().findViewById<EditText>(R.id.enteritemname)
        val itemamount  =requireView().findViewById<EditText>(R.id.enteritemamount)




        val name = itemname.text.toString()
        val amount = itemamount.text.toString()
        val username = num

        sharedviewmodel.insertuserBill(Info(null,IUri.toString(),username,name,amount.toInt()))


        val action = createBillDirections.actionCreateBillToBillByUser(num)
        Navigation.findNavController(view).navigate(action)

        Toast.makeText(requireContext(),"Bill added!",Toast.LENGTH_SHORT).show()











    }


}