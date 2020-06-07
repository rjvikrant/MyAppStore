package com.example.myappstore.network


import android.os.Parcelable
import com.example.myappstore.database.DbEntry
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AppData(

    val feed: Feed

) : Parcelable

@Parcelize
data class Feed(
    val author: Author,
    val entry: List<DbEntry>,
    val updated: Updated,
    val rights: Rights,
    val title: Title,
    val icon: Icon,
    //  @Json(name = "link") val elink : Link,
    val id: Id
) : Parcelable


@Parcelize
data class Author(
    val name: Name,
    val uri: Uri

) : Parcelable


@Parcelize
data class Name(
    val label: String
) : Parcelable

@Parcelize
data class Uri(
    val label: String
) : Parcelable


@Parcelize
data class Updated(
    val label: String
) : Parcelable

@Parcelize
data class Rights(
    val label: String
) : Parcelable

@Parcelize
data class Title(
    val label: String
) : Parcelable

@Parcelize
data class Icon(
    val label: String
) : Parcelable


@Parcelize
data class Id(
    val label: String
) : Parcelable