package com.studen.bestfood;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;
@Entity
public class CafeteriaInfo implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    public int id;
    private String name;
    private String phone;
    private String tags;
    private String rating;
    private String description;
    private String imagePath;
    private String location;
    private double latitude;
    private double longitude;

    // Parcelable
    protected CafeteriaInfo(Parcel in) {
        name = in.readString();
        phone = in.readString();
        tags = in.readString();
        rating = in.readString();
        description = in.readString();
        imagePath = in.readString();
        location = in.readString();
        latitude = in.readDouble();
        longitude = in.readDouble();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(phone);
        dest.writeString(tags);
        dest.writeString(rating);
        dest.writeString(description);
        dest.writeString(imagePath);
        dest.writeString(location);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CafeteriaInfo> CREATOR = new Creator<CafeteriaInfo>() {
        @Override
        public CafeteriaInfo createFromParcel(Parcel in) {
            return new CafeteriaInfo(in);
        }

        @Override
        public CafeteriaInfo[] newArray(int size) {
            return new CafeteriaInfo[size];
        }
    };

    public CafeteriaInfo(String name, String phone, String tags, String rating,
                         String description, String imagePath, String location, double latitude, double longitude) {
        this.name = name;
        this.phone = phone;
        this.tags = tags;
        this.rating = rating;
        this.description = description;
        this.imagePath = imagePath;
        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}

