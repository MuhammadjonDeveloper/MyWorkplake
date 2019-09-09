package com.e.myworkplace.db

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Task() :Parcelable{
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
    var usetId: Int? = null
    var title: String? = null
    var body: String? = null


    constructor(parcel: Parcel) : this() {
        usetId = parcel.readValue(Int::class.java.classLoader) as? Int
        title = parcel.readString()
        body = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(usetId)
        parcel.writeString(title)
        parcel.writeString(body)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Task> {
        override fun createFromParcel(parcel: Parcel): Task {
            return Task(parcel)
        }

        override fun newArray(size: Int): Array<Task?> {
            return arrayOfNulls(size)
        }
    }
}