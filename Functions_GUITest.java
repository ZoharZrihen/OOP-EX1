package myMath;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class Functions_GUITest {
    Functions_GUI fgui = new Functions_GUI();
    Functions_GUI fgui1 = new Functions_GUI();
    @Before
    public void setUp() throws Exception {
        fgui = new Functions_GUI();
        String s1 = "3.1+2.4x^2-x^4";
        String s2 = "5+2x-3.3x+0.1x^5";
        String[] s3 = {"x+3","x-2", "x-4"};
        Polynom p1 = new Polynom(s1);
        Polynom p2 = new Polynom(s2);
        Polynom p3 = new Polynom(s3[0]);
        ComplexFunction cf3 = new ComplexFunction(p3);
        for(int i=1;i<s3.length;i++) {
            cf3.mul(new Polynom(s3[i]));
        }

        ComplexFunction cf = new ComplexFunction("plus", p1,p2);
        ComplexFunction cf4 = new ComplexFunction("div", new Polynom("x+1"),cf3);
        cf4.plus(new Monom("2"));
        fgui.add(cf.copy());
        fgui.add(cf4.copy());
        cf.div(p1);
        fgui.add(cf.copy());
        String s = cf.toString();
        function cf5 = cf4.initFromString(s1);
        function cf6 = cf4.initFromString(s2);
        fgui.add(cf5.copy());
        fgui.add(cf6.copy());
        ComplexFunction max = new ComplexFunction(fgui.get(0).copy());
        ComplexFunction min = new ComplexFunction(fgui.get(0).copy());
        for(int i=1;i<fgui.size();i++) {
            max.max(fgui.get(i));
            min.min(fgui.get(i));
        }
        fgui.add(max);
        fgui.add(min);

        //
        String s4 = "5x^4+3x^2+1";
        String s5 = "-5x^3-7x-9";
        String s6 = "5x^2";
        String s7 = "3x^7-6x^5";
        Polynom n1 = new Polynom(s4);
        Polynom n2 = new Polynom(s5);
        Polynom n3 = new Polynom(s6);
        Polynom n4 = new Polynom(s7);
        ComplexFunction cfn1 = new ComplexFunction("plus",n1,n2);
        ComplexFunction cfn2 = new ComplexFunction("mul",n3,n4);
        ComplexFunction cfn3 = new ComplexFunction("div",cfn1,cfn2);
        fgui1.add(cfn1);
        fgui1.add(cfn2);
        fgui1.add(cfn3);
    }

    @Test
    public void initFromFile() throws IOException {
        Functions_GUI fifi1 = new Functions_GUI();
        fifi1.initFromFile("aaa.txt");
        if (fgui.size() != fifi1.size())
            fail("collections are of diffrent size");
        else {
            for (int i = 0; i < fgui.size(); i++)
                if (!(fgui.get(i).equals(fifi1.get(i)))) fail("collection contains diffrent functions");
        }
        assertTrue(true);
    }

    @Test
    public void saveToFile() {
        Functions_GUI fgi = new Functions_GUI();
        try {
            fgui.saveToFile("fgui.txt");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        try {
            fgi.initFromFile("fgui.txt");
        }
        catch (IOException e){
            e.printStackTrace();
        }
        assertFalse(!fgui.containsAll(fgi));

    }

    @Test
    public void drawFunctions()  {
        fgui.drawFunctions();
        int i=0;
        try {
            TimeUnit.SECONDS.sleep(5);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        assertTrue(true);
    }


    @Test
    public void testDrawFunctions() {
        fgui1.drawFunctions("parm.txt");
        try {
            TimeUnit.SECONDS.sleep(5);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        assertTrue(true);
    }

    @Test
    public void testDrawFunctions1() {
        fgui.addAll(fgui1);
        fgui.drawFunctions(1500,800,new Range(-40,40),new Range(-50,20),600);
        try {
            TimeUnit.SECONDS.sleep(5);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        assertTrue(true);
    }

    @Test
    public void add() {
        String s = fgui1.get(0).toString();
        fgui.add(new ComplexFunction(s));
        assertFalse(!fgui.contains(fgui1.get(0)));
    }

    @Test
    public void remove() {
        int size = fgui.size();
        int count=0;
        while (!fgui.isEmpty())
        {
            ComplexFunction cf = new ComplexFunction(fgui.get(0).toString());
            fgui.remove(cf);
            count++;
        }
        assertEquals(count,size);

    }

    @Test
    public void testToString() {
        Functions_GUI st = new Functions_GUI();
        Functions_GUI st1 = new Functions_GUI();
        st.addAll(fgui);
        st1.addAll(fgui1);
        assertEquals(fgui.toString(),st.toString());
        assertEquals(fgui1.toString(),st1.toString());

    }

    @Test
    public void containsAll() {
        Functions_GUI fg = new Functions_GUI();
        fg.addAll(fgui);
        assertFalse(!fg.containsAll(fgui));
    }

    @Test
    public void addAll() {
        String s =fgui.toString();
        String t = fgui1.toString();
        s+=",";
        s+=t;
        fgui.addAll(fgui1);
        assertEquals(fgui.toString(),s);
    }

    @Test
    public void clear() {
        fgui.clear();
        fgui1.clear();
        assertFalse(!fgui.isEmpty());
        assertFalse(!fgui1.isEmpty());
    }

    @Test
    public void size() {
        assertEquals(fgui.size(),7);
        assertEquals(fgui1.size(),3);
    }

    @Test
    public void isEmpty() {
        Functions_GUI f = new Functions_GUI();
        fgui.clear();
        assertFalse(!f.isEmpty());
        assertFalse(!fgui.isEmpty());
    }

    @Test
    public void contains() {
        String s4 = "5x^4+3x^2+1";
        String s5 = "-5x^3-7x-9";
        Polynom n1 = new Polynom(s4);
        Polynom n2 = new Polynom(s5);
        ComplexFunction cfn1 = new ComplexFunction("plus",n1,n2);
        assertFalse(!fgui1.contains(cfn1));
    }
}