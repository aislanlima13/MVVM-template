package com.example.guest.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import com.example.guest.viewmodel.GuestFormViewModel
import com.example.guest.R
import com.example.guest.service.constants.GuestConstants

class GuestFormActivity : AppCompatActivity(), View.OnClickListener {

    val btnSave by lazy { findViewById<Button>(R.id.btn_save) }
    val edtName by lazy { findViewById<EditText>(R.id.edt_name) }
    val rbPresence by lazy { findViewById<RadioButton>(R.id.rb_presence) }
    val rbAbsent by lazy { findViewById<RadioButton>(R.id.rb_absent) }

    private lateinit var mViewModel: GuestFormViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guest_form)

        mViewModel = ViewModelProvider(this).get(GuestFormViewModel::class.java)

        setListeners()
        observe()
        loadData()

        rbPresence.isChecked = true
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

    private fun loadData() {
        val bundle = intent.extras
        if (bundle != null) {
            val id = bundle.getInt(GuestConstants.GUEST_ID)
            mViewModel.load(id)
        }
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

        mViewModel.guest.observe(this, {
            edtName.setText(it.name)

            if (it.presence) {
                rbPresence.isChecked = true
            } else {
                rbAbsent.isChecked = true
            }
        })
    }
}
