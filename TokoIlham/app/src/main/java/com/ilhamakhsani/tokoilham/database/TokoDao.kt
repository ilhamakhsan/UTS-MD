package com.ilhamakhsani.Tokoilham.database

import androidx.room.*
import com.ilhamakhsani.Tokoilham.model.Toko

@Dao
interface TokoDao {
    @Insert
    fun insert(contact: Toko)

    @Update
    fun update(contact: Toko)

    @Delete
    fun delete(contact: Toko)

    @Query("SELECT * FROM Toko")
    fun getAll() : List<Toko>

    @Query("SELECT * FROM Toko WHERE id = :id")
    fun getById(id: Int) : List<Toko>
}