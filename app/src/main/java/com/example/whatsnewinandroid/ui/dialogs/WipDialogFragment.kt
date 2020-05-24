package com.example.whatsnewinandroid.ui.dialogs

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.whatsnewinandroid.utilities.view.createWipDialog

/**
 * Work In Progress Dialog Fragment
 */
class WipDialogFragment : DialogFragment() {

    override fun onCreateDialog(
        savedInstanceState: Bundle?
    ): Dialog {
        return createWipDialog()
    }
}