package com.kneelawk.flashkardsandroid

import android.os.Parcel
import android.os.Parcelable
import java.io.IOException

data class FlashcardConfigureMessage(val name: String) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: throw IOException("Unable to read flashcard set selector name")
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FlashcardConfigureMessage> {
        override fun createFromParcel(parcel: Parcel): FlashcardConfigureMessage {
            return FlashcardConfigureMessage(parcel)
        }

        override fun newArray(size: Int): Array<FlashcardConfigureMessage?> {
            return arrayOfNulls(size)
        }
    }
}
