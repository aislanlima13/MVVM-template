package com.example.guest.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.guest.R
import com.example.guest.viewmodel.PresentViewModel

class PresentFragment : Fragment() {

    private lateinit var presentViewModel: PresentViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        presentViewModel =
            ViewModelProvider(this).get(PresentViewModel::class.java)

        val root: View = inflater.inflate(R.layout.fragment_present, container, false)

        val textView: TextView = root.findViewById(R.id.text_gallery)
        presentViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}