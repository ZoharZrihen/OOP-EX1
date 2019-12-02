package myMath;

import org.junit.Test;

import static org.junit.Assert.*;

public class PolynomTest {

    @Test
    public void f() {
    }

    @Test
    public void add() {
    }

    @Test
    public void testAdd() {
    }

    @Test
    public void substract() {
    }

    @Test
    public void multiply() {
    }

    @Test
    public void testEquals() {
        Polynom p1 = new Polynom("x^2+5");
        Polynom p2 = new Polynom("5+x^2");
        assertEquals(p1,p2);
        p1.substract(new Polynom("5"));
        Monom mon1 = new Monom("x^2");
        assertEquals(p1,mon1);
        p1.substract(new Polynom("x^2"));
        p1.equals(Monom.ZERO);
    }

    @Test
    public void isZero() {
    }

    @Test
    public void root() {
    }

    @Test
    public void derivative() {
    }

    @Test
    public void area() {
    }

    @Test
    public void testMultiply() {
    }

    @Test
    public void testToString() {
    }

    @Test
    public void initFromString() {
    }

    @Test
    public void copy() {
    }
}