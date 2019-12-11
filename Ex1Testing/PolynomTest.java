package Ex1Testing;

import Ex1.Monom;
import Ex1.Polynom;
import Ex1.function;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class PolynomTest {

    @Test
    public void f() {
        Polynom p=new Polynom("2x^2+1");
        Polynom p1=new Polynom("4x+1");
        double px=p.f(2);
        double p1x=p1.f(2);
        assertEquals(9,px,p1x);
    }

    @Test
    public void add() {
        Polynom p=new Polynom("4x^2+1");
        p.add(new Polynom("x^2"));
        assertEquals(new Polynom("5x^2+1"),p);
    }

    @Test
    public void substract() {
        Polynom p=new Polynom("5x^2+2x+1");
        p.substract(new Polynom("x^2+2x"));
        assertEquals(new Polynom("4x^2+1"),p);
    }

    @Test
    public void multiply() {
        Polynom p=new Polynom("4x+1");
        p.multiply(new Polynom("x"));
        assertEquals(new Polynom("4x^2+x"),p);
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
        Polynom p=new Polynom();
        Polynom p1=new Polynom("2x^5+1");
        p1.substract(new Polynom("2x^5+1"));
        if(!p1.isZero()&&!p.isZero()){
            fail();
        }

    }

    @Test
    public void root() {
    Polynom p=new Polynom("-3x^3+4x^2+1");
        assertEquals(1.484527587890625,p.root(-15,15,0.001),new Polynom("-3x^3+4x^2+1").root(-15,15,0.001));
    }

    @Test
    public void derivative() {
        Polynom p=new Polynom("2x^5+3x^2+4x+1");
        assertEquals(new Polynom("10x^4+6x+4"),p.derivative());
    }

    @Test
    public void area() {
        Polynom p=new Polynom("4x^2+3x+2");
        assertEquals(319.4399999999997,p.area(2,6,0.2),319.4399999999997);
    }

    @Test
    public void testToString() {
        Polynom p=new Polynom("5x^3+2x^2+4x");
        String t=p.toString();
        String s="5.0x^3+2.0x^2+4.0x";
        assertEquals(t,s);
    }

    @Test
    public void copy() {
        function p=new Polynom("3x^3+5.3x+4.8");
        function p1=p.copy();
        assertEquals(p,p1);
    }
}
