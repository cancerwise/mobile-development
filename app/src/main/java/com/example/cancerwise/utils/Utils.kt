package com.example.cancerwise.utils

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class Utils {
    object DialogController {
        private lateinit var dialogAlert: AlertDialog
        private lateinit var dialogConfirmation: AlertDialog

        private fun cantShowDialog(): Boolean =
            this::dialogAlert.isInitialized && dialogAlert.isShowing ||
                    this::dialogConfirmation.isInitialized && dialogConfirmation.isShowing

        fun showDialogAlert(context: Context, title: String, msg: String) {
            if (cantShowDialog()) {
                return
            }

            dialogAlert = MaterialAlertDialogBuilder(context)
                .setTitle(title)
                .setMessage(msg)
                .setPositiveButton("Close", null)
                .setCancelable(false)
                .create()
                .apply {
                    setCanceledOnTouchOutside(false)
                    show()
                }
        }

        fun showDialogConfirmation(
            context: Context,
            title: String,
            msg: String,
            callback: () -> Unit
        ) {
            if (cantShowDialog()) {
                return
            }

            dialogConfirmation = MaterialAlertDialogBuilder(context)
                .setTitle(title)
                .setMessage(msg)
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Confirm") { _, _ -> callback() }
                .setCancelable(false)
                .create()
                .apply {
                    setCanceledOnTouchOutside(false)
                    show()
                }
        }
    }
}