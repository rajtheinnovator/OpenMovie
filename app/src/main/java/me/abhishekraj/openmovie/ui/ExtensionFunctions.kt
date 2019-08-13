package me.abhishekraj.openmovie.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import me.abhishekraj.openmovie.R

/**
 * Created by Abhishek Raj on 8/12/2019.
 */

//Example to show extension functions use cases
fun EditText.setError(message: String) {
    error = message
    setTextColor(resources.getColor(R.color.error))
}

fun Context.showToast(text: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, text, duration).show()
}

fun ViewGroup.inflate(layoutRes: Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}