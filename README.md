# About this project 
This project represent a way to calculate and draw functions like Polynom, and Complex Functions.

# The functions: 
* Function- All kind of functions- Monom, Polynom, ComplexFunction.
* Monom- A simple monom in type of a*x^b, a,b are real numbers.
* Polynom- A list of monoms which togther representing Polynom.
* ComplexFunction- function in type of O(f1,f2), O- operations like mul,plus,divide,min,max,complex , f1 and f2 are instance of function.

# Example of functions:
* Monom- 3x^2, -5.4x^-4, 9x,-9.
* Polynom- 9,2x, 4x^2, -5x^2-4x+2.
* Complex function- plus(4x,2x^2), div(2x^5+1, mul(2x,4x+1)), comp(5x^2+2x, plus(2x,15x^2).

# Hierarchy of the objects and the implements
* The interface functions extends Collection of java.
* The class Functions_GUI implements functions.
* The interface function extends Serializable of java.
* The interface complex_function extends function.
* The class ComplexFunction implements complex_function.
* The interface cont_function extends function.
* The interface Polynom_able extends cont_function.
* The class Polynom implements Polynom_able.
* The class Monom implements function. 

# Methods
* F: to calculate f(x) for a given x.
* Derivative: to return a derivative of Polynom.
* Equals: Test if two functions are logically equals.
* Math operations: Compute operations (like plus,multiply, divide, complex,max,min) between 2 functions. 
* Area: Compute a Riman's integral from x0 to x1 in epsilon steps for a Polynom.
* Root: Compute a value x' of a Polynom (x0<=x'<=x1) for which |f(x')| < eps. assuming f(x0)*f(x1)<=0.
* Draw: to draw a function or collection of functions using StdDraw librariy. 
* Save/Load: to save/load a collection of functions to/from your pc.

# Class Monom:
*This class represent a Monom of type a*x^b where a,b are real numbers.*
* This class implements methods like derivative, multiply, add, F(x), equals and more.

# Class Polynom:
*This class represent a Polynom as Array list of Monoms*
* Polynom class implements methods like monom class and more like Root and Area.

# Class ComplexFunction:
*This class represent a ComplexFunction of type O(f1,f2).*
* O(f1,f2) means a mathematical operation between two functions f1,f2.
    * Operations are- Complex,min,max,divide,multiply,plus.

# Class Function_Gui
*This class represent a java collection of functions.*
* Speical methods are:
  * This class can read collection of functions from text file and init it into a Function_Gui object.
  * It also can save a collection of functions into a text file.
  * Function_Gui using stdDraw librariy so it can draw collections of functions.
  * User can set his own details of drawing like height, width, resolution and range.
  * User also can set the drawing details with JSON file.

  
  


# Tests
* This project contain a package of tests for each class, using JUnit4.
* Those tests are example for how to use all the classes and the methods of each class.
* The tests package also checks that everything is working well.

