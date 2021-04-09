package ru.stqa.pft.sandbox;

public class MyFirstProgram {

  public static void main(String[] args) {
    hello("world");
    hello("user");
    hello("Sasha");

    Square s = new Square(5);
    System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());

    Rectangle r = new Rectangle(4, 6);
    System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area());

    Point p1 = new Point(10, 10);
    Point p2 = new Point(13, 14);
    System.out.println("Расстояние между точкой с координатами "+ p1.x + "," + p1.y + " и второй точкой с координатами "
            + p2.x + "," + p2.y + " на двумерной плоскости равно " + p1.distance(p2));
  }

  public static void hello(String somebody) {
    System.out.println("Hello, "+ somebody + "!");
  }

}