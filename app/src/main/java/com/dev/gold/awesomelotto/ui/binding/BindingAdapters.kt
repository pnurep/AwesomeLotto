package com.dev.gold.awesomelotto.ui.binding

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.BindingAdapter
import com.dev.gold.awesomelotto.utils.getActivity


@BindingAdapter("toolBarTitle")
fun Toolbar.setToolbarTitle(
    title: String?
) {
    title?.let(::setTitle)
}

@BindingAdapter("appBarController")
fun Toolbar.setAppBarController(
    drawable: Drawable?
) {
    (getActivity() as? AppCompatActivity)?.let { activity ->
        activity.setSupportActionBar(this)
        activity.supportActionBar?.run {
            setDisplayShowTitleEnabled(false)
            setDisplayHomeAsUpEnabled(drawable != null)
            drawable?.let(::setHomeAsUpIndicator)
        }
    }
}
