package com.example.guest.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.guest.viewmodel.GuestFormViewModel
import com.example.guest.R

class GuestFormActivity : AppCompatActivity(), View.OnClickListener {

    val btnSave by lazy { findViewById<Button>(R.id.btn_save) }
    val edtName by lazy { findViewById<EditText>(R.id.edt_name) }
    val rbPresence by lazy { findViewById<RadioButton>(R.id.rb_presence) }

    private lateinit var mViewModel: GuestFormViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guest_form)

        mViewModel = ViewModelProvider(this).get(GuestFormViewModel::class.java)

        setListeners()
        observe()
    }

    override fun onClick(v: View?) {
        val id = v?.id
        if (id == R.id.btn_save) {

            val name = edtName.text.toString()
            val presence = rbPresence.isChecked

            mViewModel.save(name, presence)
        }
    }

    private fun setListeners() {
        btnSave.setOnClickListener(this)
    }

    private fun observe() {
        mViewModel.saveGuest.observe(this, {
            if (it) {
                Toast.makeText(applicationContext, "Sucesso", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(applicationContext, "Falha", Toast.LENGTH_SHORT).show()
            }
            finish()
        })
    }


}