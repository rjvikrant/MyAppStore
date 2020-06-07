package com.example.myappstore.database

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
@Json(name = "entry")
data class DbEntry(
    @PrimaryKey(autoGenerate = true)
    val entryid: Int = 0,
    @Json(name = "im:name") @Embedded(prefix = "mName_") var entryName: Name,
    @Json(name = "rights") @Embedded(prefix = "mRights_") var entryRights: Rights,
    // @Json(name= "im:artist")@Embedded(prefix = "mArtist_") val entryArtist: Artist,


    @Embedded(prefix = "mPrice_") @Json(name = "im:price") var entryPrice: Price,
    @Json(name = "im:image") var entryImages: List<Images>,
    @Embedded(prefix = "mTitle_") var title: Title,
    @Embedded(prefix = "mELink_") var link: ELink,
    @Embedded(prefix = "mELink_") var id: LId,
    @Embedded(prefix = "mLId_") @Json(name = "im:contentType") var entrycontentType: ContentType,
    @Embedded(prefix = "mCategory_") var category: Category,
    @Embedded(prefix = "mReleaseDate_") @Json(name = "im:releaseDate") var releaseDate: ReleaseDate


) : Parcelable

@Parcelize
data class Name(
    var label: String

) : Parcelable

@Parcelize
data class Rights(
    var label: String

) : Parcelable


@Parcelize
data class Artist(
    var label: String,
    @Embedded(prefix = "mAAttribuits_") var attributes: AAttribuits

) : Parcelable


@Parcelize
data class AAttribuits(
    var href: String = ""

) : Parcelable


@Parcelize
@Json(name = "im:price")
data class Price(
    var label: String,
    @Embedded(prefix = "mPAttribuits_") var attributes: PAttribuits
) : Parcelable


@Parcelize
data class PAttribuits(

    var amount: Int,
    var currency: String
) : Parcelable


@Parcelize
data class Images(
    var label: String = "",
    @Embedded(prefix = "mIAttribuits_") var attributes: IAttribuits
) : Parcelable


@Parcelize
data class IAttribuits(
    var height: String
) : Parcelable

//

/*   @Parcelize
   data class Artist(
       var label: String,
       @Embedded(prefix = "mAAttribuits_") var attributes: AAttribuits

   ) : Parcelable


   @Parcelize
   data class AAttribuits(
       var href: String


   ) : Parcelable
*/

@Parcelize
data class LId(
    var label: String,
    @Embedded(prefix = "mLAttribuits_") var attributes: LAttribuits

) : Parcelable


@Parcelize
data class LAttribuits(
    @Json(name = "im:id") var id: String,
    @Json(name = "im:bundleId") var bid: String

) : Parcelable


@Parcelize
data class ContentType(
    @Embedded(prefix = "mContentAttribuits_") var attributes: ContentAttribuits

) : Parcelable

//
@Parcelize
data class ContentAttribuits(
    var term: String,
    var label: String

) : Parcelable


@Parcelize
data class Category(
    @Embedded(prefix = "mCAttribuits_") var attributes: CAttribuits

) : Parcelable


@Parcelize
data class CAttribuits(
    @Json(name = "im:id") var id: String,
    var term: String,
    var scheme: String,
    var label: String

) : Parcelable


@Parcelize
data class ReleaseDate(
    var label: String,
    @Embedded(prefix = "mRAttribuits_") var attributes: RAttribuits

) : Parcelable


@Parcelize
data class RAttribuits(
    var label: String
) : Parcelable


@Parcelize
data class ELink(
    @Embedded(prefix = "mLinkAttribuits_") var attributes: LinkAttribuits
) : Parcelable


@Parcelize
data class LinkAttribuits(
    var rel: String,
    var type: String,
    var href: String

) : Parcelable


@Parcelize
data class Title(
    var label: String
) : Parcelable

