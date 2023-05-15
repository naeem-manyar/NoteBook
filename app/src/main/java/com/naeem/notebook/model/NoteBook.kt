package com.naeem.notebook.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
class NoteBook(
    var title: String,
    var desc: String,
):Parcelable{
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}