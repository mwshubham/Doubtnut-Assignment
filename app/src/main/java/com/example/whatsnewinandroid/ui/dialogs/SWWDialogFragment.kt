package com.example.whatsnewinandroid.ui.dialogs

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.whatsnewinandroid.utilities.view.swwDialog

/**
 * Something Went Wrong Dialog Fragment
 */
class SWWDialogFragment : DialogFragment() {

    override fun onCreateDialog(
        savedInstanceState: Bundle?
    ): Dialog {
        return swwDialog()
    }
}