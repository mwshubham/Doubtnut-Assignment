package com.example.whatsnewinandroid.ui.dialogs

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.whatsnewinandroid.utilities.view.internetUnavailableDialog

/**
 * Internet Unavailable Dialog Fragment
 */
class InternetUnavailableDialogFragment : DialogFragment() {

    override fun onCreateDialog(
        savedInstanceState: Bundle?
    ): Dialog {
        return internetUnavailableDialog()
    }
}