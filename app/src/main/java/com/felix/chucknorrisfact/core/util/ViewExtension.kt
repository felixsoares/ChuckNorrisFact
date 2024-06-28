package com.felix.chucknorrisfact.core.util

import android.content.Context
import android.content.Intent

fun Context.shareFact(fact: String){
    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, fact)
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(sendIntent, null)
    this.startActivity(shareIntent)
}