package com.juanparedes.forecastapp

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale

fun ImageView.loadImage(url: String) {
    val formattedUrl = context.getString(R.string.image_prefix, url)
    Glide.with(context)
        .load(formattedUrl)
        .centerCrop()
        .apply(
            RequestOptions()
                .placeholder(R.drawable.cloudy)
                .error(R.drawable.cloudy)
        )
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(this)
}

fun String.convertLocalTimeToPrettyFormat(): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd H:mm", Locale.getDefault())

    val calendar = Calendar.getInstance()
    try {
        calendar.time = inputFormat.parse(this)
    } catch (e: ParseException) {
        return EMPTY_STRING
    }

    val year = calendar.get(Calendar.YEAR)
    val month = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())
    val dayOfWeek =
        calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault())
    val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
    val hour = calendar.get(Calendar.HOUR_OF_DAY)
    val minutes = calendar.get(Calendar.MINUTE)

    return "$dayOfWeek, $month $dayOfMonth, $year.\nTime is $hour:$minutes"
}

fun String.convertLocalTimeToPrettyFormatShort(): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val calendar = Calendar.getInstance()
    try {
        calendar.time = inputFormat.parse(this)
    } catch (e: ParseException) {
        return EMPTY_STRING
    }
    val dayOfWeek =
        calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault())

    return dayOfWeek
}