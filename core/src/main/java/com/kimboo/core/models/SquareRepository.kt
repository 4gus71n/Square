package com.kimboo.core.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SquareRepository(
    val name: String,
    val stars: Int
) : Parcelable