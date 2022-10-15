package com.example.karakiamobileapp.data
import android.net.Uri
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Entity(tableName = "karakia_table")
@Parcelize
data class Karakia (
    val imageResource: Int,
    val videoResource: Int,
    var verses: Int,
    var english: Int,
    val audioResource: Int,
    var title: String,
    var shortDescription: String,
    var longDescription: String,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
) : Parcelable
