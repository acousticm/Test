package com.example.myapplication.presentation.utils

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.Window
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.myapplication.R

class ProgressUtil {


    companion object {

        private lateinit var dialogBuilder: AlertDialog.Builder
        private lateinit var alertDialog: AlertDialog
        private lateinit var tvMsg: TextView

        fun showLoading(context: Context, msg: String? = "Loading") {
            val inflator =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val view = inflator.inflate(R.layout.dialog_custom_progress, null, false)
            // instantiating the lateint objects
            dialogBuilder = AlertDialog.Builder(context)

            //findViewById
            tvMsg = view.findViewById(R.id.tv_msg)

            // setText
            tvMsg.text = msg

            // setting up the dialog
            dialogBuilder.setCancelable(false)
            dialogBuilder.setView(view)
            alertDialog = dialogBuilder.create()

            // magic of transparent background goes here
            alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            // setting the alertDialog's BackgroundDrawable as the color resource of any color with 1% opacity
            alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#00141414")))

            // finally displaying the Alertdialog containging the ProgressBar
            alertDialog.show()
        }

        fun hideLoading() {
            try {
                if (alertDialog.isShowing) {
                    alertDialog.dismiss()
                }
            } catch (e: UninitializedPropertyAccessException) {
//            Log.e(,e.printStackTrace())
            }
        }
    }

}