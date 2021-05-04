package io.github.codejunk1e.maythefourth.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize data class User (val name: String, val gender: String, val height: String ) : Parcelable