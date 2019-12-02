package myMath;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.function.Predicate;

/**
 * This class represents a Polynom with add, multiply functionality, it also should support the following:
 * 1. Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral
 * 2. Finding a numerical value between two values (currently support root only f(x)=0).
 * 3. Derivative
 *
 * @author Boaz
 *
 */
public class Polynom implements Polynom_able {

	private ArrayList<Monom> _monoms = new ArrayList<Monom>();

	/**
	 * Zero (empty polynom)
	 */
	public Polynom() {

	}

	/**
	 * init a Polynom from a String such as:
	 * {"x", "3+1.4X^3-34x"}
	 * @param s: is a string represents a Polynom
	 * @throws RuntimeException if the string is empty.
	 */
	public Polynom(String s) {    // splitting string taken from: https://stackoverflow.com/questions/27594938/splitting-on-multiple-delimiters-but-keep-the-delimiters-on-the-same-string

		if (s.isEmpty())
			throw new RuntimeException("Illegal empty polynom string");
		String[] st = s.split("(?=-|\\+)");
		for (String monoString : st) {
			if (monoString.charAt(0) == '+')
				add(new Monom(monoString.substring(1, monoString.length())));
			else
				add(new Monom(monoString));
		}

	}

	public ArrayList<Monom> getMonoms() {
		return _monoms;
	}
	/**
	 * return the value of the polynom at x.
	 * @param x the value of x to calculate.
	 * @return ans
	 */

	@Override
	public double f(double x) {
		Iterator<Monom> iter = iteretor();
		double ans = 0;
		if (isZero()) return ans;
		while(iter.hasNext())
			ans+=iter.next().f(x);
		return ans;
	}

	/**
	 * this method adds each monom of the new polynom to this polynom.
	 * @param p1 new polynom.
	 * @return
	 */
	@Override
	public void add(Polynom_able p1) {
		Iterator<Monom> iter= p1.iteretor();
		if (p1.isZero()) return;
		while (iter.hasNext())
			add(new Monom (iter.next()));
	}
	/**
	 * constructor to create an Array list to store all the monoms.
	 * @return
	 */
	@Override
	public void add(Monom m1) {    //Complexity total is binary search+insertion at index O(logn)+O(n)=O(n)
		if (m1.isZero()) return;
		int  i = Collections.binarySearch(getMonoms(),m1,new Monom_Comperator());	//binary search sorted array by power, returns index of monom power
		if (i<0)																	//if monom doesn't exsist return "-1-correct index"
			getMonoms().add(-i-1,m1);
		else
		{
			getMonoms().get(i).add(m1);
			if (getMonoms().get(i).isZero()) getMonoms().remove(i);
		}

	}

	@Override
	public void substract(Polynom_able p1) {
		if(p1.isZero()){
			return;
		}
			Polynom_able p2 = new Polynom(p1.toString());
			p2.multiply(Monom.MINUS1);
			add(p2);

	}

	@Override
	public void multiply(Polynom_able p1) {
		Polynom_able temp = new Polynom(this.toString());
		Polynom_able p2 = new Polynom(p1.toString());
		Iterator<Monom> iter1 = temp.iteretor();
		Iterator<Monom> iter2;
		getMonoms().clear();
		if (p2.isZero() || temp.isZero()) return;
		while (iter1.hasNext())
		{
			Monom mon1 = new Monom(iter1.next());
			iter2=p2.iteretor();
			while(iter2.hasNext())
			{
				Monom mon2 = new Monom(mon1);
				mon2.multiply(new Monom(iter2.next()));
				add(mon2);
			}
		}

	}

	public boolean equals(Object obj){
		if ((obj instanceof Polynom) || (obj instanceof Polynom_able)) {
			Polynom p1 = (Polynom) obj;
			return equal(iteretor(), p1.iteretor());
		}
		else if (obj instanceof Monom)
		{
		    Monom mon1 = (Monom)obj;
		    if (isZero() && mon1.isZero()) return true;

			if (this.getMonoms().size()>1) return false;
				else {
				    return this.getMonoms().get(0).equals(obj);
            }
		}
		else return false;
	}
	/**public boolean equals(Polynom_able p1) {

		return equal(iteretor(),p1.iteretor());
	}**/
	private boolean equal(Iterator<Monom> iter1,Iterator<Monom> iter2) {//took code from here: https://www.hackerrank.com/challenges/compare-two-linked-lists/forum
		if (!iter1.hasNext() && !iter2.hasNext()) return true;
		else if(!iter1.hasNext() || !iter2.hasNext()) return false;
		if (iter1.next().equals(iter2.next())) return equal(iter1,iter2);
		else return false;
	}



	@Override
	public boolean isZero() {
		if (getMonoms().isEmpty()) return true;
		return false;
	}

	@Override
	public double root(double x0, double x1, double eps) {
		if (eps < 0 || f(x0)*f(x1)>0) { //checking if the there are valid values.
			throw new RuntimeException("Illegal input");
		}
		if (compareDouble(f(x0), 0.0)) //checking if x0/x1 are root.
			return x0;
		if (compareDouble(f(x1),0.0))
			return x1;
		return root_find(x0, x1, eps);//if not, find the root.
	}

	private double root_find ( double x0, double x1, double eps) {
		double posX, negX;
		double x2 = (x0 + x1) / 2;// is the middle of x0,x1 is the root?
		if (Math.abs(f(x2)) < eps)
			return x2;
		if (f(x0) < f(x1)) { // finding which point is positive and which is negative point.
			posX = x1;
			negX = x0;
		} else {
			posX = x0;
			negX = x1;
		}
		if (f(x2) < 0) // if the new middle point is negative then advance the negative point.
			negX = x2;
		else
			posX = x2; //else advance the positive point.
		return root_find(negX, posX, eps); //recursive call for keep searching for the root.
	}

	@Override
	public Polynom_able derivative() {
		Iterator<Monom> iter = iteretor();
		Polynom_able p = new Polynom();
		if (isZero()) return p;
		while(iter.hasNext())
			p.add(iter.next().derivative());
		return p;
	}

	@Override
	public double area(double x0, double x1, double eps) {
		if (x1 < x0) {
			throw new RuntimeException("x1 can't be smaller than x0");
		}
		if (eps <= 0) {
			throw new RuntimeException("Illegal eps value");
		}
		double stepper = x0;
		double sumOfArea = 0;
		while (stepper + eps <= x1) {//going to make all the rectangles, and calculate them
			sumOfArea += Math.max(f(stepper) * eps,0.0);
			stepper += eps;
		}
		if (stepper != x1) {
			sumOfArea += Math.max(f(stepper) * (x1 - stepper),0.0);// calculate the last rectangle.
		}
		return sumOfArea;
	}

	@Override
	public Iterator<Monom> iteretor() {
		return getMonoms().iterator();
	}
	@Override
	public void multiply(Monom m1) {
		Iterator<Monom> iter = iteretor();
		if (m1.isZero())
			getMonoms().clear();
		else
			while (iter.hasNext())
				iter.next().multiply(m1);
	}
	@Override
	public String toString(){
		String ans="";
		if (isZero()) return "0";
		ans=ans.concat(getMonoms().get(0).toString());
		for (int i=1;i<getMonoms().size();i++){
			if (getMonoms().get(i).get_coefficient()-Monom.EPSILON >= 0)
				ans=ans.concat("+");
			ans=ans.concat(getMonoms().get(i).toString());}
		return ans;
	}

	@Override
	public function initFromString(String s) {
		return null;
	}

	@Override
	public function copy() {
		function p = new Polynom(this.toString());
		return p;
	}

	private boolean compareDouble(Double num1,Double num2) {
		if (Math.abs(num1 - num2) <= Monom.EPSILON) return true;
		else return false;
	}

}