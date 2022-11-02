package com.mikekorel.mobilebankingdemo.core

import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

fun String.capitalizeFirstChar(locale: Locale = Locale.getDefault()) =
    replaceFirstChar { if (it.isLowerCase()) it.titlecase(locale) else it.toString() }

fun getDateString(time: String): String =
    LocalDate
        .parse(time.substringBefore('T'))
        .format(DateTimeFormatter.ofPattern("d MMMM yyyy"))



