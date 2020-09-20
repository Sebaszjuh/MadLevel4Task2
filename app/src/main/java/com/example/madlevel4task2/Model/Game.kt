package com.example.madlevel4task2.Model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.madlevel4task2.R
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "gameStatistic")
data class Game(

    @ColumnInfo
    var chosenAction: Int,

    @ColumnInfo
    var computerAction: Int,

    @ColumnInfo
    var playDate: Date,

    @ColumnInfo
    var gameResult: Int,

    @ColumnInfo
    var win: Int? = 0,

    @ColumnInfo
    var loss: Int? = 0,

    @ColumnInfo
    var tie: Int? = 0,


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    var id: Long? = null,

    ) : Parcelable {
    companion object {
        val possibleMoves = arrayListOf(
            R.drawable.rock,
            R.drawable.paper,
            R.drawable.scissors
        )
    }
}

