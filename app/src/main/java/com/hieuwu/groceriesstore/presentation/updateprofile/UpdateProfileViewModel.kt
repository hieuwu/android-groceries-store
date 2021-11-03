package com.hieuwu.groceriesstore.presentation.updateprofile

import androidx.databinding.Bindable
import androidx.lifecycle.MutableLiveData
import com.hieuwu.groceriesstore.BR
import com.hieuwu.groceriesstore.domain.models.UserModel
import com.hieuwu.groceriesstore.domain.repository.UserRepository
import com.hieuwu.groceriesstore.presentation.utils.ObservableViewModel
import javax.inject.Inject

class UpdateProfileViewModel @Inject constructor(userRepository: UserRepository) :
    ObservableViewModel() {

    var user: MutableLiveData<UserModel> =
        userRepository.getCurrentUser() as MutableLiveData<UserModel>

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

    init {
        name = user.value?.name
        email = user.value?.email
    }


}