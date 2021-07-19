package ru.pakage1;

public class Point {

    public double x;
    public double y;

    public Point(double x,double y){
        this.x = x;
        this.y = y;
    }

    private double distance(Point p2){
        return Math.sqrt(Math.pow((p2.x - this.x), 2) + Math.pow((p2.y - this.y), 2));
    }
    public void write(Point p2){
        System.out.println("4.Задание. Расстояние между точками А(" + this.x + ";" + this.y + ") и В(" + p2.x + ";" + p2.y + ") равно " + distance(p2));
    }
}

