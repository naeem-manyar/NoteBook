package com.naeem.notebook.room

import androidx.room.*
import com.naeem.notebook.model.NoteBook

@Dao
interface NoteBookDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(noteBook:NoteBook)

    @Delete
    suspend fun delete(noteBook: NoteBook)

    @Query("SELECT * FROM NoteBook")
    suspend fun getAllNotebook():List<NoteBook>
}