package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

  @Test
  public void testPointPositiveCoord() {
    Point p1 = new Point(10, 10);
    Point p2 = new Point(13, 14);
    Assert.assertEquals(p1.distance(p2), 5.0);
  }

  @Test
  public void testPointNegativeCoord() {
    Point p1 = new Point(-5, -2);
    Point p2 = new Point(-8, -2);
    Assert.assertEquals(p1.distance(p2), 3.0);
  }
}
