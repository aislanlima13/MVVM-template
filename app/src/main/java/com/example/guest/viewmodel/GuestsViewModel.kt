package com.example.guest.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.guest.service.constants.GuestConstants
import com.example.guest.service.model.GuestModel
import com.example.guest.service.repository.GuestRepository

class GuestsViewModel(application: Application) : AndroidViewModel(application) {

    private val mGuestRepository = GuestRepository(application.applicationContext)

    private val mGuestList = MutableLiveData<List<GuestModel>>()
    val guestList: LiveData<List<GuestModel>> = mGuestList

    fun load(filter: Int) {
        when (filter) {
            GuestConstants.FILTER.EMPTY -> mGuestList.value = mGuestRepository.getAll()
            GuestConstants.FILTER.PRESENT -> mGuestList.value = mGuestRepository.getPresent()
            GuestConstants.FILTER.ABSENT -> mGuestList.value = mGuestRepository.getAbsent()
        }
    }

    fun delete(id: Int) {
        val guest = mGuestRepository.get(id)
        mGuestRepository.delete(guest)
    }
}