package myMath;

import org.junit.Test;

public class ComplexFunctionTest {

    @Test
    public void plus() {
       ComplexFunction cf=new ComplexFunction("plus",new Polynom("x"),new Polynom("2"));
       cf.plus(new Polynom("x"));
       ComplexFunction cf2=new ComplexFunction(cf);
       int i=0;
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
}