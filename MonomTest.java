package myMath;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * This class represents a simple (naive) tester for the Monom class, 
 * Note: <br>
 * (i) The class is NOT a JUNIT - (i.e., educational reasons) - should be changed to a proper JUnit in Ex1. <br>
 * (ii) This tester should be extend in order to test ALL the methods and functionality of the Monom class.  <br>
 * (iii) Expected output:  <br>
 * *****  Test1:  *****  <br>
0) 2.0    	isZero: false	 f(0) = 2.0  <br>
1) -1.0x    	isZero: false	 f(1) = -1.0  <br>
2) -3.2x^2    	isZero: false	 f(2) = -12.8  <br>
3) 0    	isZero: true	 f(3) = 0.0  <br>
*****  Test2:  *****  <br>
0) 0    	isZero: true  	eq: true  <br>
1) -1.0    	isZero: false  	eq: true  <br>
2) -1.3x    	isZero: false  	eq: true  <br>
3) -2.2x^2    	isZero: false  	eq: true  <br>
 *****  Test 3: *******
 * 3x^4+3x^4=5x^4
 * 5x^4*3x^3=15x^7
 * (15x^7)'=105x^6
 */
public class MonomTest {
	public static void main(String[] args) {
		//test3();
	//test1();
		//test2();
	}
	private static void test1() {
		System.out.println("*****  Test1:  *****");
		String[] monoms = {"2", "-x","10x^2","x"};
		for(int i=0;i<monoms.length;i++) {
			Monom m = new Monom(monoms[i]);
			String s = m.toString();
		//	System.out.println(s);
			m = new Monom(monoms[i]);
			double fx = m.f(i);
			int x=1;
			System.out.println(i+") "+m +"    \tisZero: "+m.isZero()+"\t f("+i+") = "+fx);
		}
	//	monoms[1].mult
	}



	private static void test2() {
		System.out.println("*****  Test2:  *****");
		ArrayList<Monom> monoms = new ArrayList<Monom>();
		monoms.add(new Monom(0.000001,5));
		monoms.add(new Monom(-1,0));
		monoms.add(new Monom(-1.3,1));
		monoms.add(new Monom(-2.2,2));
		Collections.sort(monoms, new Monom_Comperator());
		System.out.println(Collections.binarySearch(monoms, new Monom(1,6), new Monom_Comperator()));
		System.out.println(monoms.get(2).toString());
		for(int i=0;i<monoms.size();i++) {
			Monom m = new Monom(monoms.get(i));
			String s = m.toString();
			Monom m1 = new Monom(s);
			boolean e = m.equals(m1);
			System.out.println(i+") "+m +"    \tisZero: "+m.isZero()+"  \teq: "+e);
		}
		Monom k = new Monom(Monom.ZERO);
		System.out.println(monoms.get(0).equals(k));
	}
	public static void test3(){
		System.out.println("********Test 3*********");
		Monom m= new Monom("3x^4");
		m.add(new Monom("2x^4"));
		System.out.println("3x^4+3x^4= "+ m.toString());
		m.multiply(new Monom("3x^3"));
		System.out.println("5x^4 * 3x^3=  "+m.toString() );
		System.out.println("monom is : " + m.toString());
		System.out.println("Derivative is : " +  m.derivative().toString());
	}

}
