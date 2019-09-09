package com.e.myworkplace.db
import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "word_item")
 class WordExample() :Parcelable
{
   @PrimaryKey
   var mId:Int?=0
   @ColumnInfo(name = "id")
   var id: String? = null
   @ColumnInfo(name = "date")
   var date: String? = null
   @ColumnInfo(name = "status")
   var status: String? = null
   @ColumnInfo(name = "group")
   var group: String? = null
   @ColumnInfo(name = "type")
   var type: String? = null
   @ColumnInfo(name = "details")
   var details: String? = null
   @ColumnInfo(name = "parentId")
   var parentId: String? = null
   @ColumnInfo(name = "level")
   var level: Long? = 0
   @ColumnInfo(name = "admId")
   var admId: String? = null
   @ColumnInfo(name = "admAdmId")
   var admAdmId: String? = null
   constructor(parcel: Parcel) : this() {
      status = parcel.readString()
      group = parcel.readString()
      type = parcel.readString()
      details = parcel.readString()
      parentId = parcel.readString()
      level = parcel.readValue(kotlin.Long::class.java.classLoader) as? Long
      admId = parcel.readString()
      admAdmId = parcel.readString()
   }

   override fun writeToParcel(parcel: Parcel, flags: Int) {
      parcel.writeString(status)
      parcel.writeString(group)
      parcel.writeString(type)
      parcel.writeString(details)
      parcel.writeString(parentId)
      parcel.writeValue(level)
      parcel.writeString(admId)
      parcel.writeString(admAdmId)
   }

   override fun describeContents(): Int {
      return 0
   }

   companion object CREATOR : Parcelable.Creator<WordExample> {
      override fun createFromParcel(parcel: Parcel): WordExample {
         return WordExample(parcel)
      }

      override fun newArray(size: Int): Array<WordExample?> {
         return arrayOfNulls(size)
      }
   }
}
//        "id": "5d396d751849f02c444276e4",
//        "date": "2019-07-24T19:00:00Z",
//        "status": "OK",
//        "group": "SECURITY",
//        "type": "Тип 123",
//        "details": "Детали 123",
//        "parentId": "Идентификатор",
//        "level": 1,
//        "admId": "5cdd5649c74dd528a03c0b3a",
//        "admAdmId": "1"
//


