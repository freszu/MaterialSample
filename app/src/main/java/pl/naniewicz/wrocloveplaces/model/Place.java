package pl.naniewicz.wrocloveplaces.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.DrawableRes;

public class Place implements Parcelable {

    private String mPlaceName;
    private String mDescription;
    private String mReview;
    
    @DrawableRes
    private int mDrawableRes;

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
