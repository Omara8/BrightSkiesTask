package com.planatech.brightskiestask.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.planatech.brightskiestask.R
import com.planatech.brightskiestask.utils.extensions.isValid
import kotlinx.android.synthetic.main.login_bottom_sheet.*

class LoginFragment(private val loginCallback: () -> Unit) : BottomSheetDialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.dialogStyle)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        login_button?.setOnClickListener {
            if (user_name_text?.isValid(R.string.error, user_name) == true
                && password_text?.isValid(R.string.error, password) == true
            ) {
                loginCallback()
                dismiss()
            }
        }
    }

}