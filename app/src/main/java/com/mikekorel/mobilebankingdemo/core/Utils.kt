package com.mikekorel.mobilebankingdemo.core

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

fun String.capitalizeFirstChar(locale: Locale = Locale.getDefault()) =
    replaceFirstChar { if (it.isLowerCase()) it.titlecase(locale) else it.toString() }

enum class DatePattern(val pattern: String) {
    DAY_MONTH_YEAR("d MMMM yyyy"),
    MONTH_YEAR("MMMM yyyy")
}
fun getDateString(time: String, datePattern: DatePattern): String =
    LocalDate
        .parse(time.substringBefore('T'))
        .format(DateTimeFormatter.ofPattern(datePattern.pattern))
