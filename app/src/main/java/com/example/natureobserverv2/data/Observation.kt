package com.example.natureobserverv2.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "observation_table")
data class Observation (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var title: String,
    var date: String,
    var location: String,
    var notes: String
    )