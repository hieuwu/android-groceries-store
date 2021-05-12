package com.hieuwu.groceriesstore.viewmodels

import androidx.lifecycle.ViewModel


class SignUpViewModel : ViewModel() {



    private var _password: String? = null
    public val Password: String?
        get() {
            return _password
        }

    private var _name: String? = null
    public val Name: String?
        get() {
            return _name
        }

    private var _email: String? = null
    public val Email: String?
        get() {
            return _email
        }


}