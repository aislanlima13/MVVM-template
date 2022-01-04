package com.example.guest.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import com.example.guest.viewmodel.GuestFormViewModel
import com.example.guest.R

class GuestFormActivity : AppCompatActivity(), View.OnClickListener {

    val btnSave by lazy { findViewById<Button>(R.id.btn_save) }

    private lateinit var mViewModel: GuestFormViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guest_form)

        mViewModel = ViewModelProvider(this).get(GuestFormViewModel::class.java)

        setListeners()
    }

    private fun setListeners() {
        btnSave.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val id = v?.id
        if (id == R.id.btn_save) {

        }
    }
}