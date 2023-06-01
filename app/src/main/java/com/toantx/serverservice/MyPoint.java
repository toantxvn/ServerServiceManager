package com.toantx.serverservice;

import android.os.Parcel;
import android.os.Parcelable;

public class MyPoint implements Parcelable {
    final int x;
    final int y;

    public MyPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    protected MyPoint(Parcel in) {
        x = in.readInt();
        y = in.readInt();
    }

    public static final Creator<MyPoint> CREATOR = new Creator<MyPoint>() {
        @Override
        public MyPoint createFromParcel(Parcel in) {
            return new MyPoint(in);
        }

        @Override
        public MyPoint[] newArray(int size) {
            return new MyPoint[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(x);
        dest.writeInt(y);
    }

    @Override
    public String toString() {
        return "MyPoint{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
