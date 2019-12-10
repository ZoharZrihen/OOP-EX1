package myMath;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

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
        String s="abcde";
        System.out.println(s.substring(0,2).toString());
    }

    @Test
    public void div() {
    }

    @Test
    public void max() {
    }

    @Test
    public void min() {
    }

    @Test
    public void comp() {
    }

    @Test
    public void left() {
    }

    @Test
    public void right() {
    }

    @Test
    public void getOp() {
    }

    @Test
    public void f() {
    }

    @Test
    public void initFromString() {
        ComplexFunction cf=new ComplexFunction("plus(div(7x+1,5),mul(4x^5,2))");
        ComplexFunction cf2=new ComplexFunction("plus(x^2+5,4x+1)");
       // cf.div(new ComplexFunction());
        System.out.println(cf.toString());
        cf2 = new ComplexFunction(cf.toString());
        System.out.println(cf2.toString());
        System.out.println(cf.equals(cf2));
        int i=0;
      //  System.out.println(1/0.0000000000000000001);

    }

    @Test
    public void copy() {
    }
    @Test
    public void equals(){
        ComplexFunction t1 = new ComplexFunction("plus",new Polynom("1"),new Polynom("1"));
        ComplexFunction t2 = new ComplexFunction("div",new Polynom("2x-20"),new Polynom("x-10"));
        assertFalse(!t1.equals(t2));
     /**   ComplexFunction cf=new ComplexFunction("plus(5x+2,4x)");
        Polynom p=new Polynom("5x+2");
        ComplexFunction cf2=new ComplexFunction("plus(4x,5x+2)");
        System.out.println(cf.equals(cf2));
        ComplexFunction cf3=new ComplexFunction("mul(2x,x)");
        ComplexFunction cf4=new ComplexFunction("div(4x^2,2)");
        System.out.println(cf3.equals(cf4));
        ComplexFunction cf5=new ComplexFunction("div(2x,x)");
        ComplexFunction cf6=new ComplexFunction("plus(1,1)");
        System.out.println(cf5.equals(cf6));**/
    }
}