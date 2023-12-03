package com.juanparedes.forecastapp

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale

fun ImageView.loadImage(url: String) {
    Glide.with(context)
        .load(url)
        .centerCrop()
        .apply {
            RequestOptions()
                //.placeholder(R.drawable.placeholder_image) // Placeholder image
                .error(R.drawable.ic_launcher_background)
        }
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(this)
}

fun String.convertLocalTimeToPrettyFormat(): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd H:mm", Locale.getDefault())

    val calendar = Calendar.getInstance()
    calendar.time = inputFormat.parse(this)

    val year = calendar.get(Calendar.YEAR)
    val month = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())
    val dayOfWeek = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault())
    val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
    val hour = calendar.get(Calendar.HOUR_OF_DAY)

    return "$dayOfWeek, $dayOfMonth of $month, $year. Time is $hour"
}