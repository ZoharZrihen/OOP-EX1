package myMath;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

public class ComplexFunctionTest {
   public ComplexFunction cf = new ComplexFunction("plus(2x^2,+5)");
   public ComplexFunction cf1 = new ComplexFunction("mul(3x^3,4");
    public ComplexFunction cf2= new ComplexFunction("div(3x^2,x");
    @BeforeEach
    public void before(){
         ComplexFunction cf = new ComplexFunction("plus(2x^2,+5)");
         ComplexFunction cf1 = new ComplexFunction("mul(3x^3,4");
         ComplexFunction cf2= new ComplexFunction("div(3x^2,x");
    }

    @Test
    public void plus() {



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
        ComplexFunction cf=new ComplexFunction("plus(5x+2,4x)");
        Polynom p=new Polynom("5x+2");
        ComplexFunction cf2=new ComplexFunction("plus(4x,5x+2)");
        System.out.println(cf.equals(cf2));
        ComplexFunction cf3=new ComplexFunction("mul(2x,x)");
        ComplexFunction cf4=new ComplexFunction("div(4x^2,2)");
        System.out.println(cf3.equals(cf4));
        ComplexFunction cf5=new ComplexFunction("div(2x,x)");
        ComplexFunction cf6=new ComplexFunction("plus(1,1)");
        System.out.println(cf5.equals(cf6));
    }
}