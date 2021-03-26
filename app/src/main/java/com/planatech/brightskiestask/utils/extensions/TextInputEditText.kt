package com.planatech.brightskiestask.utils.extensions

import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

fun TextInputEditText.isValid(error: Int, parent: TextInputLayout?): Boolean {
    return if (this.text.toString().isValidUserNamePassword()) {
        parent?.error = null
        true
    } else {
        parent?.error = context.getString(error)
        this.requestFocus()
        false
    }
}