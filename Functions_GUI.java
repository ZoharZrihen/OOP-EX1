package myMath;

import java.util.ArrayList;

public class Functions_GUI {
    private ArrayList<function> functions = new ArrayList<function>();
    public void drawFunctions() {
    }

    public void drawFunctions(int w, int h, Range rx, Range ry, int res) {
    }

    public void add(function cf) {
        getFunctions().add(cf.copy());
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
}
