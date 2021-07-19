package ru.pakage1;

import static java.lang.Math.sqrt;

public class  MyFirstProgram {

	public static void main(String[] args) {

		Point p1 = new Point(0, 0); //Задаем координаты двух точек
		Point p2 = new Point(0, 0);

		System.out.println("3.Задание. Расстояние между точками А(" + p1.x + ";" + p1.y + ") и В(" + p2.x + ";" + p2.y + ") равно " + distance(p1, p2));

		//В качестве параметров метода Point.write необходимо передать объект Point - вторую точку.
		p1.write(p2);
		p2.write(p1);
		p1.write(p1);
		p2.write(p2);
	}

	public static double distance(Point p1, Point p2) {
		return Math.sqrt(Math.pow((p2.x - p1.x), 2) + Math.pow((p2.y - p1.y), 2));
	}


}