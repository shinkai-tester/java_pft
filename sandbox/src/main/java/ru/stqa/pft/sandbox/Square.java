package ru.stqa.pft.sandbox;

public class Square {

  public double l;

  // конструктор
  public Square(double l) {
    this.l = l;
  }

  public double area() {
    return this.l * this.l;
  }

}