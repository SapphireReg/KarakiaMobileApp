package com.example.karakiamobileapp.ui

import androidx.appcompat.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.karakiamobileapp.R
import java.lang.IllegalStateException

class FirstOpenDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setMessage(R.string.disclaimer)
                .setPositiveButton(R.string.accept,
                    DialogInterface.OnClickListener { dialog, id ->
                    dialog.dismiss()
                })
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}