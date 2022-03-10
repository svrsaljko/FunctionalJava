package com.stipe.practice;

public class Square {

    private int area;

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return "Square{" +
                "area=" + area +
                '}';
    }
}
