package com.example.madlevel4task2.Model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "gameStatistic")
data class Game(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null,

    @ColumnInfo(name = "chosenAction")
    var chosenAction: String,

    @ColumnInfo(name = "computerAction")
    var computerAction: String,

    @ColumnInfo(name = "amount")
    var amount: Int

    ) : Parcelable