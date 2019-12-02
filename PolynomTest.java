package myMath;

<<<<<<< HEAD
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
=======
import java.util.Collections;
import java.util.Iterator;

public class PolynomTest {
	public static void main(String[] args) {
	//	test1();
	//	test2();
	//test3();
		//test4();
	//	test5(); //manually test
		test6();
	}
	public static void test4()
	{

		//Polynom p = new Polynom("x^5+x^6+x^5+3+x^7+x^2");
		Polynom p = new Polynom("-x^5+5x^2+3x+4");
		Polynom t = new Polynom("-x^5+5x^2+3x+4");
		Polynom k = new Polynom("x^2+1");
		//t.add(Monom.MINUS1);
		System.out.println(p.equals(t));
		p.add(k);
		System.out.println(p.derivative().toString());
		p.multiply(k);
		System.out.println(p.toString());
		System.out.println(p.area(-0.4,2.1,0.0001));
		System.out.println(p.root(1.5,4,0.001));
	}
	public static void test3(){
		Polynom p1 = new Polynom();
		Polynom p2 = new Polynom();
		String t = "+888x";
		String s = "3.2x^3+1";
		String s1 = "1.5x^3-1";
		String s2 = "4.8000000000001x^6-3.2x^3";
		p1 = new Polynom(t);
		p2 = new Polynom(s1);
		Polynom p3 = new Polynom(s2);
		Monom m1 = new Monom (-0.0000001,1);
		p3.iteretor();
		p1.multiply(p2);
		p1.substract(p2);
		System.out.println(p3.toString());
		System.out.println(p1.equals(p3));

		System.out.println(p1.toString());

		System.out.println(p1.derivative().toString());

	}
	public static void test1() {
		Polynom p1 = new Polynom();
		String[] monoms = {"1","x","x^2", "0.5x^2"};
		//for(int i=0;i<monoms.length;i++) {
		Monom m = new Monom(monoms[1]);
		p1.add(m);
		double aa = p1.area(0, 1, 0.0001);
		p1.substract(p1);
		System.out.println(p1);
	}
	public static void test2() {
		Polynom p1 = new Polynom(), p2 =  new Polynom();
		String[] monoms1 = {"2", "-x","-3.2x^2","4","-1.5x^2"};
		String[] monoms2 = {"5", "1.70x","3.20x^2","-3","-1.50x^2"};
		for(int i=0;i<monoms1.length;i++) {
			Monom m = new Monom(monoms1[i]);
			p1.add(m);
		}
		for(int i=0;i<monoms2.length;i++) {
			Monom m = new Monom(monoms2[i]);
			p2.add(m);
		}
		System.out.println("p1: "+p1);
		System.out.println("p2: "+p2.toString());
		p1.add(p2);
		System.out.println("p1+p2: "+p1);
		p1.multiply(p2);
		System.out.println("(p1+p2)*p2: "+p1);
		String s1 = p1.toString();

	}
	public static void test5(){
		int count=0;
		System.out.println("***********test 5- with exceptions *************");
		Polynom p=new Polynom("2x^2+5x+3");
		System.out.println("catching exception number 1(root): ");
		try {
			System.out.println( p.root(1, 2, 0.01));
		}
		catch (Exception e)
		{
			count++;
			System.out.println(e.toString());
		}
		System.out.println("catching exception number 2(root): ");
		try {
			System.out.println(p.root(-4, -6, 0.01));
		}
		catch (Exception e){
			count++;
			System.out.println(e.toString());
		}
		System.out.println("catching exception number 3(area): ");
		try {
			System.out.println(p.area(1, -1, 0.01));
		}
		catch(Exception e){
			count++;
			System.out.println(e.toString());
		}
		System.out.println("catching exception number 4(area): ");
		try {
			System.out.println(p.root(1, 1, 0.01));
		}
		catch(Exception e){
			count++;
			System.out.println(e.toString());
		}
		System.out.println("catching exception number 5(string polynom): ");
		try {
			System.out.println(new Polynom("x4x5x^2").area(1, 2, 0.01));
		}
		catch(Exception e){
			count++;
			System.out.println(e.toString());
		}
		System.out.println("catching exception number 6(string polynom): ");
		try {
			System.out.println(new Polynom("2x^2.5").area(1, 2, 0.01));
		}
		catch(Exception e){
			count++;
			System.out.println(e.toString());
		}
		System.out.println("Number of exceptions should be 6 and it is: " + count);


	}
	public static void test6(){
		Polynom p1=new Polynom("5x^2-3x");
		p1.substract(p1);
		System.out.println(p1.toString());
	}

}
>>>>>>> a9a3ba933bc12195c2ea3445205678d9ef1a5728
