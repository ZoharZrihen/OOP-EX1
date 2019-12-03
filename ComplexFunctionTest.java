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
    }

    @Test
    public void copy() {
    }
}