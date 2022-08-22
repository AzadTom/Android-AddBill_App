package com.example.addbill

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.addbill.UserAdapter.ByUserBill
import com.example.addbill.UserAdapter.deleteUserbill
import com.example.addbill.Viewmodel.UserViewModel
import com.example.addbill.model.Info
import com.google.android.material.floatingactionbutton.FloatingActionButton

class BillByUser : Fragment(),deleteUserbill {

    private val sharedviewmodel by activityViewModels<UserViewModel>()

    private lateinit var adapter :ByUserBill
    private val args :BillByUserArgs by navArgs()
    private lateinit var data: ArrayList<Info>
    private lateinit var billrec: RecyclerView





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_bill_by_user, container, false)
        (activity as AppCompatActivity).supportActionBar?.title= args.id.uppercase()
        val callback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.action_billByUser_to_user_list)


            }

        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,callback)
         billrec = view.findViewById(R.id.recyclerView2)
        val addBill = view.findViewById<FloatingActionButton>(R.id.floatingActionButton2)
        val totalbill = view.findViewById<TextView>(R.id.totalBill)









        sharedviewmodel.billdataByuser(args.id).observe(viewLifecycleOwner,{ list ->
            list?.let {



                     data = list as ArrayList<Info>
                   adapter = ByUserBill(requireContext(),list,this)
                 billrec.adapter = adapter
                  billrec.layoutManager = LinearLayoutManager(requireContext())
                billrec.setHasFixedSize(true)
                totalbillUser(totalbill)

            }






        })






        addBill.setOnClickListener {

            create(view,args.id)
        }











        return view

    }

     fun totalbillUser(totalbill: TextView) {

        var msg =0;
        for (i in this.data)
        {
            msg += i.Amount
        }

       totalbill.text =  "  Total \n  $msg₹"





    }

    private fun create(view: View, numbers: String) {


        val action = BillByUserDirections.actionBillByUserToCreateBill(numbers)

        Navigation.findNavController(view).navigate(action)




    }

    override fun deleteBill(info: Info, view: View) {

        val list = info
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.billdelete_alert)


         val comfirm = dialog.findViewById<Button>(R.id.comfirm)
         val cancel = dialog.findViewById<Button>(R.id.discard)
        cancel.setOnClickListener {


            dialog.dismiss()

        }

        comfirm.setOnClickListener {


            sharedviewmodel.deleteuserbill(list)
            dialog.dismiss()

        }

        dialog.setCancelable(false)

        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)



        dialog.show()






    }




    override fun updateBill(info: Info, view: View) {

        val action = BillByUserDirections.actionBillByUserToEditBill(info)
        Navigation.findNavController(view).navigate(action)





    }


}