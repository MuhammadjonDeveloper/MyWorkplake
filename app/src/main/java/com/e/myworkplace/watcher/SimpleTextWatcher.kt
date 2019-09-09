package com.e.myworkplace.watcher

import android.text.Editable
import android.text.TextWatcher

class SimpleTextWatcher (private val listener: IWatcher):TextWatcher{
    override fun afterTextChanged(s: Editable?) {
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        listener.onWatcher(s.toString())
    }

}