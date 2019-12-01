
package myMath;

import java.util.Comparator;

/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and a is an integer (summed a none negative),
 * see: https://en.wikipedia.org/wiki/Monomial
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply.
 *
 * @author Boaz
 */
public class Monom implements function {
    public static final Monom ZERO = new Monom(0, 0);
    public static final Monom MINUS1 = new Monom(-1, 0);
    public static final double EPSILON = 0.0000001;
    public static final Comparator<Monom> _Comp = new Monom_Comperator();

    public static Comparator<Monom> getComp() {
        return _Comp;
    }

    public Monom(double a, int b) {
        this.set_coefficient(a);
        if (compareDouble(a,0.0)) this.set_power(0);
        		else this.set_power(b);

    }

    public Monom(Monom ot) {
        this(ot.get_coefficient(), ot.get_power());
    }

    public double get_coefficient() {
        return this._coefficient;
    }

    public int get_power() {
        return this._power;
    }

    /**
     * this method returns the derivative monom of this.
     *
     * @return
     */
    public Monom derivative() {
        if (this.get_power() == 0) {
            return getNewZeroMonom();
        }
        return new Monom(this.get_coefficient() * this.get_power(), this.get_power() - 1);
    }

    public double f(double x) {
        double ans = 0;
        double p = this.get_power();
        ans = this.get_coefficient() * Math.pow(x, p);
        return ans;
    }

    public boolean isZero() {
        return compareDouble(this.get_coefficient(),0.0);
    }

    /**
     * this constructor initiates a new monom from a valid string input
     * @param s valid string in the form of "ax^b"
     * @throws RuntimeException if the string is invalid monom.
     * @return
     */
    public Monom(String s) {
        if (s == null || s.isEmpty())
            throw new RuntimeException("Invalid empty Input");
        int xIndex = s.indexOf('x');    //find first appearance of 'x' and '^'
        int powIndex = s.indexOf('^');

        try {
          	stringToCoefficient(s,xIndex);
        } catch (Exception e) {
            throw new RuntimeException("Invalid string Input");
        }
        try{
            stringToPower(s,powIndex,xIndex);
        }
        catch (Exception e){
            throw new RuntimeException ("Invalid string input");
        }

    }


    public void add(Monom m) {
    	if (this.get_power() == m.get_power()) {
            this.set_coefficient(this.get_coefficient() + m.get_coefficient());
            if (isZero())
            {
                this.set_power(0);
                this.set_coefficient(0);
            }
        }
    }

    public void multiply(Monom d) {
        this.set_coefficient(this.get_coefficient()*d.get_coefficient());
        if (isZero())
        {
            this.set_coefficient(0);
            this.set_power(0);
        }
        else this.set_power(this.get_power()+d.get_power());
    }
    /**
     * this method compares two coefficient numbers to remove numerical conflicts
     * @param num1,num2 real numbers
     * @return true if difference is less than epsilon
     */
    private boolean compareDouble(Double num1,Double num2){
		if (Math.abs(num1-num2)<= EPSILON) return true;
    		else return false;

	}
    /**
     * this method is called by the constructor to convert a sub string to coefficient
     * and set the coefficient of the monom
     * @param s coefficient string
     * @param index index of 'x' char in string
     * @return
     */
	private void stringToCoefficient(String s, int index){
    	switch (index){	//-1: no x in string , 0: coefficient is 1, 1: coefficient is possibly -1
			case -1:
				this.set_coefficient(Double.parseDouble(s));
				break;
			case 1:
				if (s.charAt(0) == '-')
					this.set_coefficient(-1);
				else
					this.set_coefficient(Double.parseDouble(s.substring(0,index)));
				break;
			case 0:
				this.set_coefficient(1);
				break;
			default:
				this.set_coefficient(Double.parseDouble(s.substring(0, index)));

		}
	}
    /**
     * this method is called by the constructor to convert a sub string to power
     * and set the power of the monom
     * @param s coefficient string
     * @param index index of '^' char in string
     * @param xIndex index of 'x' char in string
     * @throws RuntimeException if there is invalid string input
     * @return
     */
	private void stringToPower(String s,int index,int xIndex){
    	String pow;

    	if (index == -1)
    	    if (xIndex > -1) {
    	        pow = s.substring(xIndex + 1, s.length());
                if (pow.isEmpty()) set_power(1);
                 else throw new RuntimeException("Invalid string input");}
             else set_power(0);
         else
             set_power(Integer.parseInt(s.substring(xIndex+2,s.length())));
		}
    /**
     * this method converts the double and integer values of the monom to a string
     * by gradually adding the coefficient then the power to an empty string
     * @return monom string in the format of : "ax^b"
     */
    public String toString() {
        String ans = "";
        if (isZero())  {
        	ans=ans.concat("0");
        	return ans;
        }
        else
        	ans=ans.concat(Double.toString(this.get_coefficient()));
        if (this.get_power()==0)
        	return ans;
        	else ans=ans.concat("x");
        	if (this.get_power()>1){
        		ans=ans.concat("^");
        		ans=ans.concat(Integer.toString(this.get_power()));}
        	return ans;


    }
    /**
     * this method compares the value and power of this monom with another monom
     * @return true if both equal
     */
	@Override
	public boolean equals(Object obj) {    //took code casting from here: https://dev.to/havarem/override-equals-method-in-java-2b3j
        if (obj != null && obj instanceof Monom) {
            Monom o1 = (Monom) obj;
            if (isZero() && o1.isZero()) return true;
            else if (this.get_power() == o1.get_power() && compareDouble(this.get_coefficient(), o1.get_coefficient()))
            return true;
        }
        return false;
    }





	// you may (always) add other methods.

    //****************** Private Methods and Data *****************


    private void set_coefficient(double a) {
        this._coefficient = a;
    }

    private void set_power(int p) {
        if (p < 0) {
            throw new RuntimeException("ERR the power of Monom should not be negative, got: " + p);
        }
        this._power = p;
    }

    private static Monom getNewZeroMonom() {
        return new Monom(ZERO);
    }

    private double _coefficient;
    private int _power;


}
