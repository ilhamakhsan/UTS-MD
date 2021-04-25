package com.ilhamakhsani.Tokoilham.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "Toko")
//Parcelable annotation to make parcelable object
@Parcelize
data class Toko (
             //PrimaryKey annotation to declare primary key with auto increment value
            //ColumnInfo annotation to specify the column's name
            @PrimaryKey(autoGenerate = true)@ColumnInfo(name = "id") var id: Int = 0,
    @ColumnInfo(name = "title") var title: String = "",
    @ColumnInfo(name = "body") var body: String = ""
): Parcelable