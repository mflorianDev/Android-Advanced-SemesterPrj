package com.example.natureobserverv2.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

// Parcelize makes it possible to pass object in safeArgs
@Parcelize
@Entity(tableName = "observation_table")
data class Observation (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var title: String,
    var date: String,
    var location: String,
    var notes: String
    ): Parcelable