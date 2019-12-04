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
        ComplexFunction cf=new ComplexFunction("plus(x^2+5,div(19x,7x+1))");
        int i=0;
        cf.printInOrder(cf.getBt().getRoot());
    }

    @Test
    public void copy() {
    }
}