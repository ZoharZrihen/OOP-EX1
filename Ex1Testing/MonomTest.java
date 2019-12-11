package Ex1Testing;


import Ex1.Monom;
import Ex1.function;
import org.junit.Test;

import static org.junit.Assert.*;

public class MonomTest {

    @Test
    public void derivative() {
        Monom mon1 = new Monom("3x^2");
        mon1 = mon1.derivative();
        Monom mon2 = new Monom("6x");
        assertEquals(mon1,mon2);
    }

    @Test
    public void f() {
        Monom mon = new Monom("-x^2");
        assertEquals(-1,mon.f(1),Monom.EPSILON);
        assertEquals(-0.25, mon.f(0.5),Monom.EPSILON);
    }

    @Test
    public void isZero() {
        Monom mon1 = new Monom("5x^6");
        Monom mon2 = new Monom("-5x^6");
        mon1.add(mon2);
        assertFalse(!mon1.isZero());
    }

    @Test
    public void add() {
        Monom mon1 = new Monom("3x");
        Monom mon2 = new Monom("-x");
        Monom mon3 = new Monom("2x");
        mon1.add(mon2);
        assertEquals(mon1,mon3);

    }

    @Test
    public void multiply() {
        Monom mon1 = new Monom("8.25x^6");
        Monom mon2 = new Monom("-x");
        Monom mon3 = new Monom("-8.25x^7");
        mon1.multiply(mon2);
        assertEquals(mon1,mon3);
        mon1.multiply(Monom.ZERO);
        assertFalse(!mon1.isZero());

    }

    @Test
    public void testToString() {
        Monom mon1 = new Monom("-15.054x^734");
        assertEquals(mon1.toString(),"-15.054x^734");
    }

    @Test
    public void initFromString() {
        Monom mon1 = new Monom("7x^3");
        function mon2 = mon1.initFromString(mon1.toString());
        assertEquals(mon1.toString(),mon2.toString());
    }

    @Test
    public void copy() {
        Monom mon1 = new Monom("5x");
        function mon2 = mon1.copy();
        assertEquals(mon1,mon2);
        mon1.add(new Monom("x"));
        assertNotEquals(mon1,mon2);
    }

    @Test
    public void testEquals() {
        Monom mon1 = new Monom("18.25x^2");
        Monom mon2 = new Monom("18.26x^2");
        assertNotEquals(mon1,mon2);
        mon1.add(new Monom("0.01x^2"));
        assertEquals(mon2,mon1);
    }
}

