package com.example.karakiamobileapp.util

import androidx.appcompat.widget.SearchView

//inline = efficiency, crossinline = necessary
inline fun SearchView.onQueryTextChanged(crossinline listener: (String) -> Unit) {
    this.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            return true
        }

        //queries when text changes
        override fun onQueryTextChange(newText: String?): Boolean {
            listener(newText.orEmpty()) //returns empty string if search is null
            return true
        }
    })
}