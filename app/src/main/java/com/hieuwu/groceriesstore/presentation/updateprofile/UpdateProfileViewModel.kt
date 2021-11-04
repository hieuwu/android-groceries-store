package com.hieuwu.groceriesstore.presentation.updateprofile

import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.hieuwu.groceriesstore.BR
import com.hieuwu.groceriesstore.domain.models.UserModel
import com.hieuwu.groceriesstore.domain.repository.UserRepository
import com.hieuwu.groceriesstore.presentation.utils.ObservableViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class UpdateProfileViewModel @Inject constructor(private val userRepository: UserRepository) :
    ObservableViewModel() {
    private val _user =
        userRepository.getCurrentUser() as MutableLiveData<UserModel>
    val user: LiveData<UserModel?>
        get() = _user
    private var _name: String? = null
    var name: String?
        @Bindable
        get() {
            return _name
        }
        set(value) {
            _name = value
            notifyPropertyChanged(BR.name)
        }

    private var _email: String? = null
    var email: String?
        @Bindable
        get() {
            return _email
        }
        set(value) {
            _email = value
            notifyPropertyChanged(BR.email)
        }

    private var _phoneNumber: String? = null
    var phoneNumber: String?
        @Bindable
        get() {
            return _phoneNumber
        }
        set(value) {
            _phoneNumber = value
            notifyPropertyChanged(BR.phoneNumber)
        }

    private val _isDoneUpdate = MutableLiveData<Boolean>(null)
    val isDoneUpdate: LiveData<Boolean?>
        get() = _isDoneUpdate

    fun updateUserProfile() {
        var id = _user.value!!.id
        try {
            viewModelScope.launch {
                userRepository.updateUserProfile(id, _name!!, email!!, email!!)
            }
            _isDoneUpdate.value = true
        } catch (e: Exception) {
            _isDoneUpdate.value = false
        }
    }

}