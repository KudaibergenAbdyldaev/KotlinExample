package com.example.brandstore.Fragments.HomeFragment


import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.brandstore.Model.ProductData
import com.example.brandstore.R
import com.example.brandstore.databinding.FragmentHomeBinding
import com.google.firebase.database.*

class HomeFragment : Fragment() {

    var firedatabase : FirebaseDatabase? = null
    var productDataList : ArrayList<ProductData> ? = null
    var ref : DatabaseReference? = null
    var mRecyclerView : RecyclerView? =null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                             savedInstanceState: Bundle?): View? {
        val binding:FragmentHomeBinding = DataBindingUtil.inflate(
            inflater,R.layout.fragment_home, container,false)

        firedatabase = FirebaseDatabase.getInstance()
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = LinearLayoutManager(context)

        productDataList = arrayListOf<ProductData>()
        ref = FirebaseDatabase.getInstance().getReference("Uploads").child("All")

        ref?.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
               Log.e("HomeFragment", "onSuccess")
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                productDataList!!.clear()
                Log.e("HomeFragment", "Success")
                if(dataSnapshot.exists()){
                    for (h in dataSnapshot.children){
                        val bal = h.getValue(ProductData::class.java)
                        productDataList?.add(bal!!)
                    }
                    val adapter = AdapterHomeJava(context!!, productDataList!!)
                    mRecyclerView?.adapter = adapter
                }

            }
        })

        setHasOptionsMenu(true)
        return binding.root
    }



    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.basket_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
            return NavigationUI.onNavDestinationSelected(item!!,
            view!!.findNavController())
                || super.onOptionsItemSelected(item)
    }
}
