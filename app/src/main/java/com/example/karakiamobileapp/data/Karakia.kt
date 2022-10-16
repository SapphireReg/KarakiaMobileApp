package com.example.karakiamobileapp.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.nio.file.Path


@Entity(tableName = "karakia_table")
@Parcelize
data class Karakia(
    val imageResource: Int,
    val videoResource: Int,
    val audioResource: Int,
    val versesFileName: String,
    val englishFileName: String,
    val title: String,
    val shortDescription: String,
    val longDescription: String,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
) : Parcelable

