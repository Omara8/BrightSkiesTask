package com.planatech.brightskiestask.utils.extensions

import com.planatech.brightskiestask.utils.USERNAME_PASSWORD_VALUE

fun String.isValidUserNamePassword(): Boolean {
    return this.decapitalize() == USERNAME_PASSWORD_VALUE
}