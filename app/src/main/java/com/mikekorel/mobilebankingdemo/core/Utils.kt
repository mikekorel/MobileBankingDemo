package com.mikekorel.mobilebankingdemo.core

import java.util.*

fun String.capitalizeFirstChar(locale: Locale = Locale.getDefault()) =
    replaceFirstChar { if (it.isLowerCase()) it.titlecase(locale) else it.toString() }

