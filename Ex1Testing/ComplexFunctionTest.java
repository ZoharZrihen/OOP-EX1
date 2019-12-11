package Ex1Testing;

import Ex1.ComplexFunction;
import Ex1.Monom;
import Ex1.Operation;
import Ex1.Polynom;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class ComplexFunctionTest {
    private ComplexFunction cf;
    private ComplexFunction cf1;
    private ComplexFunction cf2;
    @Before
    public void before(){
          cf = new ComplexFunction("plus(2x^2,+5)");
          cf1 = new ComplexFunction("mul(3x^3,4)");
          cf2= new ComplexFunction("div(3x^2,8x)");
    }

    @Test
    public void plus() {
        ComplexFunction p1 = new ComplexFunction(new ComplexFunction("plus",new Polynom("2x^2"),new Polynom("5")));
        ComplexFunction p2 = new ComplexFunction(new ComplexFunction("mul",new Polynom("3x^3"),new Monom("4")));
        cf.plus(cf1);
        ComplexFunction res = new ComplexFunction("plus",p1,p2);
        res.equals(cf);
        assertFalse(!res.equals(cf));
        cf.plus(cf2);
        res.plus(cf2);
        assertFalse(!res.equals(cf));
    }

    @Test
    public void mul() {
        cf.mul(cf1);
        ComplexFunction cf4=new ComplexFunction("mul(plus(2x^2,+5),mul(3x^3,4))");
        assertEquals(cf,cf4);
    }

    @Test
    public void div() {
        cf.div(cf1);
        ComplexFunction cf4=new ComplexFunction("div(plus(2x^2,+5),mul(3x^3,4))");
        assertEquals(cf,cf4);
    }

    @Test
    public void max() {
        cf.max(cf1);
        ComplexFunction cf4=new ComplexFunction("max(plus(2x^2,+5),mul(3x^3,4))");
        assertEquals(cf,cf4);
    }

    @Test
    public void min() {
        cf.min(cf1);
        ComplexFunction cf4=new ComplexFunction("min(plus(2x^2,+5),mul(3x^3,4))");
        assertEquals(cf,cf4);
    }

    @Test
    public void comp() {
        cf.comp(cf1);
        ComplexFunction cf4=new ComplexFunction("comp(plus(2x^2,+5),mul(3x^3,4))");
        assertEquals(cf,cf4);
    }

    @Test
    public void left() {
        Polynom p=new Polynom("2x^2");
        Polynom p1 =new Polynom("3x^3");
        Polynom p2=new Polynom("3x^2");
        assertEquals(cf.left(),p);
        assertEquals(cf1.left(),p1);
        assertEquals(cf2.left(),p2);

    }

    @Test
    public void right() {
        Polynom p=new Polynom("5");
        Polynom p1 =new Polynom("4");
        Polynom p2=new Polynom("8x");
        assertEquals(cf.right(),p);
        assertEquals(cf1.right(),p1);
        assertEquals(cf2.right(),p2);
    }

    @Test
    public void getOp() {
        Operation op=cf.getOp();
        Operation op1=cf1.getOp();
        Operation op2=cf2.getOp();
        assertFalse(op!=Operation.Plus);
        assertFalse(op1!=Operation.Times);
        assertFalse(op2!=Operation.Divid);
    }

    @Test
    public void f() {
        double output=cf.f(2);
        assertEquals(output,13,13);
        output=cf1.f(2);
        assertEquals(96,output,96);
    }

    @Test
    public void initFromString() {
        ComplexFunction cf=new ComplexFunction("plus(div(7x+1,5),mul(4x^5,2))");
        ComplexFunction cf2=new ComplexFunction(cf.initFromString("plus(div(7x+1,5),mul(4x^5,2))"));
        ComplexFunction cf3=new ComplexFunction(cf2.initFromString("plus(mul(4x^5,2),div(7x+1,5))"));
        assertEquals(cf,cf2);
       assertFalse( !cf.equals(cf3));
    }

    @Test
    public void copy() {
         cf2= (ComplexFunction) cf.copy();
         assertFalse(!cf2.equals(cf));
         cf.mul(new Polynom("2"));
         assertFalse(cf2.equals(cf));
    }
    @Test
    public void equals(){
        ComplexFunction t1 = new ComplexFunction("plus",new Polynom("1"),new Polynom("1"));
        ComplexFunction t2 = new ComplexFunction("div",new Polynom("2x-20"),new Polynom("x-10"));
        assertFalse(!t1.equals(t2));
    }
}