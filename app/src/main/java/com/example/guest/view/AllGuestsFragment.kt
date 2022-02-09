package com.example.guest.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.guest.R
import com.example.guest.service.constants.GuestConstants
import com.example.guest.view.adapter.GuestAdapter
import com.example.guest.view.listener.GuestListener
import com.example.guest.viewmodel.AllGuestsViewModel

class AllGuestsFragment : Fragment() {

    private lateinit var allGuestViewModel: AllGuestsViewModel
    private val mAdapter: GuestAdapter = GuestAdapter()
    private lateinit var mListener: GuestListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        allGuestViewModel =
            ViewModelProvider(this).get(AllGuestsViewModel::class.java)

        val root: View = inflater.inflate(R.layout.fragment_all, container, false)
        val rvAllGuests = root.findViewById<RecyclerView>(R.id.rv_all_guests)

        rvAllGuests.layoutManager = LinearLayoutManager(context)
        rvAllGuests.adapter = mAdapter

        mListener = object : GuestListener {
            override fun onCLick(id: Int) {
                val intenet = Intent(context, GuestFormActivity::class.java)

                val bundle = Bundle()
                bundle.putInt(GuestConstants.GUEST_ID, id)

                intenet.putExtras(bundle)
                startActivity(intenet)
            }

        }

        mAdapter.attachListener(mListener)

        observer()

        return root
    }

    override fun onResume() {
        super.onResume()
        allGuestViewModel.load()
    }

    private fun observer() {
        allGuestViewModel.guestList.observe(viewLifecycleOwner, {
            mAdapter.updateGuests(it)
        })
    }
}