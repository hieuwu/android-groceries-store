package com.hieuwu.groceriesstore.presentation.viewmodels

import androidx.lifecycle.ViewModel


class SignInViewModel : ViewModel() {


    private var _email: String? = null
    public val Email: String?
        get() {
            return _email
        }

    private var _password: String? = null
    public val Password: String?
        get() {
            return _password
        }

}