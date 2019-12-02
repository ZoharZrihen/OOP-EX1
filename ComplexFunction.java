package myMath;

import groovyx.gpars.extra166y.Ops;

public class ComplexFunction implements complex_function{
    private function _functionL;
    private function _functionR;
    private Operation _operation;

    public ComplexFunction(){
        _functionL=new Polynom();
        _functionR=null;
        _operation=Operation.None;
    }
    public ComplexFunction(Object o1){
        if ((o1 instanceof Polynom) || (o1 instanceof Monom)) {
            _functionL=new Polynom(o1.toString());
            _functionR=null;
            _operation=Operation.None;
        }
        else if(o1 instanceof ComplexFunction){
            ComplexFunction cf = (ComplexFunction)o1;
            set_functionR(cf.right());
            set_functionL(cf.left());
            set_operation(cf.getOp());
        }
        else throw new RuntimeException("Error: Invalid object initalizer");

    }
    public ComplexFunction(String s, Object o1, Object o2){
    }
    public void set_functionL(function f) {
        _functionL = f.copy();
    }
    public void set_functionR(function f){
        _functionR = f.copy();
    }
    public void set_operation(Operation o){
        _operation=o;
    }



    @Override
    public void plus(function f1) {

    }

    @Override
    public void mul(function f1) {

    }

    @Override
    public void div(function f1) {

    }

    @Override
    public void max(function f1) {

    }

    @Override
    public void min(function f1) {

    }

    @Override
    public void comp(function f1) {

    }

    @Override
    public function left() {
        return _functionL;
    }

    @Override
    public function right() {
        return _functionR;
    }

    @Override
    public Operation getOp() {
        return _operation;
    }

    @Override
    public double f(double x) {
        return 0;
    }

    @Override
    public function initFromString(String s) {
        return null;
    }

    @Override
    public function copy() {
        return null;
    }
}
