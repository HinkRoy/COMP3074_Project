package com.studen.bestfood;

public class CafeteriaInfo {
    private String name;
    private String rating;
    private String comment;
    private int imageResId;

    public CafeteriaInfo(String name, String rating, String comment, int imageResId) {
        this.name = name;
        this.rating = rating;
        this.comment = comment;
        this.imageResId = imageResId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }
}
