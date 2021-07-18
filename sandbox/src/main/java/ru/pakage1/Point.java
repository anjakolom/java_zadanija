package ru.pakage1;

public class Point {

    public double x;
    public double y;

    public Point(double x,double y){
        this.x = x;
        this.y = y;
    }

    private double distance(double x2, double y2){
        return Math.sqrt(Math.pow((x2 - this.x), 2) + Math.pow((y2 - this.y), 2));
    }
    public void write(Point p2){
        System.out.println("4.Задание. Расстояние между точками А(" + this.x + ";" + this.y + ") и В(" + p2.x + ";" + p2.y + ") равно " + distance(p2.x,p2.y));
    }
}

