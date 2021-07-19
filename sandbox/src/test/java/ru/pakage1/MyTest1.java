package ru.pakage1;

import org.testng.Assert;
import org.testng.annotations.Test;


public class MyTest1 {
    @Test
    public void testPoint() {
        Point p1 = new Point(4.0, 4.0); //Задаем координаты двух точек
        Point p2 = new Point(2.0, 2.0);
        Assert.assertEquals(p1.distance(p2),2.8284271247461903);
    }
    @Test
    public void testPoint2() {
        Point p1 = new Point(4.0, 4.0); //Задаем координаты двух точек
        Point p2 = new Point(10.5, 10.5);
        Assert.assertEquals(p1.distance(p2),9.192388155425117);
    }
    @Test
    public void testPoint3() {
        Point p1 = new Point(0, 0); //Задаем координаты двух точек
        Point p2 = new Point(10.5, 10.5);
        Assert.assertEquals(p1.distance(p2),14.849242404917497);
    }
    @Test
    public void testPoint4() {
        Point p1 = new Point(0, 0); //Задаем координаты двух точек
        Point p2 = new Point(0, 0);
        Assert.assertEquals(p1.distance(p2),0.0);
    }
}
