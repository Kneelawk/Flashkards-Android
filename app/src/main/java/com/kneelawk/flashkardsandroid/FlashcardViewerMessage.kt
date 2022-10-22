package com.kneelawk.flashkardsandroid

import android.os.Parcel
import android.os.Parcelable
import java.io.IOException

data class FlashcardViewerMessage(val title: String, val cards: List<FlashcardData>, val shuffle: Boolean, val defaultFront: Boolean) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: throw IOException("Unable to read card list title"),
        parcel.createTypedArrayList(FlashcardData) ?: throw IOException("Unable to read cards list"),
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeTypedList(cards)
        parcel.writeByte(if (shuffle) 1 else 0)
        parcel.writeByte(if (defaultFront) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FlashcardViewerMessage> {
        override fun createFromParcel(parcel: Parcel): FlashcardViewerMessage {
            return FlashcardViewerMessage(parcel)
        }

        override fun newArray(size: Int): Array<FlashcardViewerMessage?> {
            return arrayOfNulls(size)
        }
    }
}
