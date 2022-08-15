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
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.addbill.Viewmodel.UserViewModel
import com.example.addbill.model.Info


class EditBill : Fragment() {


    private val sharedviewmodel by activityViewModels<UserViewModel>()
    private var IUri: Uri? = null
    private val args: EditBillArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_edit_bill, container, false)

        (activity as AppCompatActivity).supportActionBar?.title = "UpdateExpenseInfo"
        val itemname = view.findViewById<EditText>(R.id.enteritemname)
        val itemamount = view.findViewById<EditText>(R.id.enteritemamount)
        val update = view.findViewById<Button>(R.id.userBillupdate)
         val itemImg = view.findViewById<ImageView>(R.id.editItemImg)
        val list = args.billlist

        itemname.setText(list.Itemname)
        itemamount.setText(list.Amount.toString())
        Glide.with(requireContext()).load(list.ItemImg.toUri()).into(itemImg)

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


        update.setOnClickListener {

            if (IUri == null)
            {
                IUri = list.ItemImg.toUri()
            }
            sharedviewmodel.updateBill(
                Info(
                    list.Id,
                    IUri.toString(),
                    list.username, itemname.text.toString(), itemamount.text.toString().toInt()
                )
            )


            val action = EditBillDirections.actionEditBillToBillByUser(list.username)
            Navigation.findNavController(view).navigate(action)
            Toast.makeText(requireContext(),"UpdateBill successfully!", Toast.LENGTH_SHORT).show()


        }





        return view
    }


}