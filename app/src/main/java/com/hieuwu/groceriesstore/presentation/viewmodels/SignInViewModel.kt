package com.hieuwu.groceriesstore.presentation.viewmodels

import androidx.databinding.Bindable
import com.hieuwu.groceriesstore.BR
import com.hieuwu.groceriesstore.presentation.utils.ObservableViewModel


class SignInViewModel : ObservableViewModel() {

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

    private var _password: String? = null
    var password: String?
        @Bindable
        get() {
            return _password
        }
        set(value) {
            _password = value
            notifyPropertyChanged(BR.password)
        }
}