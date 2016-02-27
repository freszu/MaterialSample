package pl.naniewicz.wrocloveplaces.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.DrawableRes;

/**
 * Copyright (C) 2016  Rafał Naniewicz and Szymon Kozak
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

public class Place implements Parcelable {

    private String mPlaceName;
    private String mDescription;
    private String mReview;
    private
    @DrawableRes int mDrawableRes;

    public Place(String placeName, String description, String review, int drawableRes) {
        mPlaceName = placeName;
        mDescription = description;
        mReview = review;
        mDrawableRes = drawableRes;
    }

    public String getPlaceName() {
        return mPlaceName;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getReview() {
        return mReview;
    }

    public int getDrawableRes() {
        return mDrawableRes;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mPlaceName);
        dest.writeString(mDescription);
        dest.writeString(mReview);
        dest.writeInt(mDrawableRes);
    }

    protected Place(Parcel in) {
        mPlaceName = in.readString();
        mDescription = in.readString();
        mReview = in.readString();
        mDrawableRes = in.readInt();
    }

    public static final Parcelable.Creator<Place> CREATOR = new Parcelable.Creator<Place>() {
        public Place createFromParcel(Parcel source) {
            return new Place(source);
        }

        public Place[] newArray(int size) {
            return new Place[size];
        }
    };
}
