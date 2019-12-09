package myMath;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;



public  class Functions_GUI implements functions{
    private ArrayList<function> functions = new ArrayList<function>();

    @Override
    public void initFromFile(String file) throws IOException {
        File ourfile=new File(file);
        BufferedReader buff=new BufferedReader (new FileReader(ourfile));
        String t="";
        while((t=buff.readLine())!=null){
            try{
                add(new Polynom(t));
            }
            catch (Exception e){
                add(new ComplexFunction(t));
            }
        }
    }

    @Override
    public void saveToFile(String file) throws IOException {
            try{
                Writer w=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),"utf-8"));
                for(int i=0;i<functions.size();i++){
                    w.write(functions.get(i).toString()+"\n");
                }
                w.close();
            }
            catch (Exception e){
                throw new IOException("Error: Can't save this collection of functions into a file.");
            }
    }
    public void drawFunctions(){

    }

    public void drawFunctions(int w, int h, Range rx, Range ry, int res) {
        Color[] colors = {Color.blue, Color.cyan, Color.MAGENTA, Color.ORANGE, Color.red, Color.GREEN, Color.PINK};
        StdDraw.setCanvasSize(w,h);
        StdDraw.setXscale(rx.get_min(), rx.get_max());
        StdDraw.setYscale(ry.get_min(), ry.get_max());
        setAxis(rx,ry);
        StdDraw.setPenRadius(0.005);
        double xstep=(rx.get_max()-rx.get_min())/res;
        int indexOfColor=0;
        Iterator<function> iter=functions.iterator();
        while(iter.hasNext()){
            function fx=iter.next();
            if(indexOfColor==colors.length){ indexOfColor=0; }
            StdDraw.setPenColor(colors[indexOfColor++]); //setting different color for every function to draw.
            for(double rng = rx.get_min()+xstep; rng<rx.get_max(); rng+=xstep) {
                double x1 = rng-xstep;
                double x2 = rng;
                double y1 = fx.f(x1);
                double y2 = fx.f(x2);
                StdDraw.line(x1, y1, x2, y2);
            }
        }
        StdDraw.save("Functions_GUI.jpg");
    }

    @Override
    public void drawFunctions(String json_file) {
        JSONParser jPar = new JSONParser(); //need to check this with json file
        try{
            FileReader fr=new FileReader(json_file);
            JSONObject jOb = (JSONObject) jPar.parse(fr);
            double rx1,rx2,ry1,ry2;
            Range rx,ry;
            Long height1=(Long)jOb.get("Height");
            int height=height1.intValue();
            Long wid1=(Long)jOb.get("Width");
            int wid=wid1.intValue();
            Long reso1=(Long)jOb.get("Resolution");
            int reso=reso1.intValue();
            JSONArray rxj=(JSONArray)jOb.get("Range_X");
            JSONArray ryj=(JSONArray)jOb.get("Range_Y");
            Long Rx0 = (Long) rxj.get(0);
            Long Rx1 = (Long) rxj.get(1);
            Long Ry0 = (Long) ryj.get(0);
            Long Ry1 = (Long) ryj.get(1);
            rx1 = Rx0.doubleValue();
            rx2 = Rx1.doubleValue();
            ry1 = Ry0.doubleValue();
            ry2 = Ry1.doubleValue();

            if(rx1<rx2) {
                rx = new Range(rx1, rx2);
            }else {
                rx = new Range(rx2, rx1);
            }if(ry1<ry2) {
                ry = new Range(ry1, ry2);
            }else {
                ry = new Range(ry2, ry1);
            }
            this.drawFunctions(wid,height,rx,ry,reso);

        }
        catch(FileNotFoundException e){
            e.printStackTrace();

        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public boolean add(function cf) {
        if (functions.contains(cf)) {
            return false;
        } else {
            getFunctions().add(cf.copy());
            return true;
        }
    }
    public void setAxis(Range x,Range y){
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.setPenRadius(0.005);
        StdDraw.line(x.get_min(),0,x.get_max(),0);
        StdDraw.line(0,y.get_min(),0,y.get_max());
        for(int i=(int)x.get_min();i<(int)x.get_max();i++){
            StdDraw.text(i,-0.5,Integer.toString(i));
        }
        for(int i=(int)y.get_min();i<(int)y.get_max();i++){
            StdDraw.text(-0.5,i,Integer.toString(i));
        }
        StdDraw.setPenColor(Color.gray);
        StdDraw.setPenRadius(0.0009);
        for(int i=(int)x.get_min();i<(int)x.get_max();i++){

                StdDraw.line(i,y.get_min(),i,y.get_max());
            }
        for(int i=(int)y.get_min();i<(int)y.get_max();i++){

            StdDraw.line(x.get_min(),i,x.get_max(),i);
        }




    }

    @Override
    public boolean remove(Object o) {
        if(!functions.contains(o)){
            return false;
        }
        else{
            functions.remove(o);
            return true;
        }
    }
    @Override
    public String toString() {
        String ans="";
        for(int i = 0; i<functions.size(); i++)
            ans += functions.get(i)+",";
        ans = ans.substring(0, ans.length()-2);
        return ans;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return functions.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends function> c) {
        return functions.addAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return functions.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return functions.retainAll(c);
    }

    @Override
    public void clear() {
        functions.clear();
    }

    public ArrayList<function> getFunctions() {
        return functions;
    }

    public function get(int i) {
        return getFunctions().get(i);
    }

    public int size() {
        return getFunctions().size();
    }

    @Override
    public boolean isEmpty() {
        return (getFunctions().size()>0);
    }

    @Override
    public boolean contains(Object o) {
        if(o instanceof function){
            function f1=(function)o;
            for(int i=0;i<functions.size();i++){
                if(functions.get(i).equals(f1)){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Iterator<function> iterator() {
        Iterator<function> iter=functions.iterator();
        return iter;
    }

    @Override
    public Object[] toArray() {
        return functions.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return functions.toArray(a);
    }
}
