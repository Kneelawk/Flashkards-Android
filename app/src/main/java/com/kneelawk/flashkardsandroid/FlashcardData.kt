package com.kneelawk.flashkardsandroid

import android.os.Parcel
import android.os.Parcelable
import androidx.compose.ui.text.AnnotatedString

data class FlashcardData(val front: AnnotatedString, val back: AnnotatedString) : Parcelable {
    constructor(parcel: Parcel) : this(
        ParcelHelper.readAnnotatedString(parcel),
        ParcelHelper.readAnnotatedString(parcel)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        ParcelHelper.writeAnnotatedString(parcel, front)
        ParcelHelper.writeAnnotatedString(parcel, back)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FlashcardData> {
        override fun createFromParcel(parcel: Parcel): FlashcardData {
            return FlashcardData(parcel)
        }

        override fun newArray(size: Int): Array<FlashcardData?> {
            return arrayOfNulls(size)
        }
    }
}
