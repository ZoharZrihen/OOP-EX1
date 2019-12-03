package myMath;
class Node {
    function func;
    Operation oper;
    Node left, right;
    public Node(Operation op) {
        this.oper=op;
        left = right = null;
    }
    public Node(function f1){
        this.oper=Operation.None;
        this.func=f1;
        left=right=null;
    }
    public Node(ComplexFunction cf){
        this.oper=cf.getOp();
        this.func=null;
        this.left=new Node(cf.left());
        this.right=new Node(cf.right());
    }
}
class BinaryTree {
    Node root;

    BinaryTree(function func) {
        root = new Node(func);
        root.oper=Operation.None;
        root.left=root.right=null;
    }
    BinaryTree(ComplexFunction cf){
        root= new Node(cf.getOp());
        root.left=new Node(cf.left());
        root.right=new Node(cf.right());
    }

    BinaryTree() {
        root = null;
    }
}
    public class ComplexFunction implements complex_function{
    private function _functionL;
    private function _functionR;
    private Operation _operation;
    private BinaryTree bt=new BinaryTree();

    public ComplexFunction(){
        _functionL=new Polynom();
        _functionR=null;
        _operation=Operation.None;
        bt=null;
    }
    public ComplexFunction(Object o1){
        if ((o1 instanceof Polynom) || (o1 instanceof Monom)) {
            _functionL=new Polynom(o1.toString());
            _functionR=null;
            _operation=Operation.None;
            bt=null;
        }
        else if(o1 instanceof ComplexFunction){
            ComplexFunction cf = (ComplexFunction)o1;
            set_functionR(cf.right());
            set_functionL(cf.left());
            set_operation(cf.getOp());
            bt=new BinaryTree((ComplexFunction)o1);
        }
        else throw new RuntimeException("Error: Invalid object initalizer");

    }
    public ComplexFunction(String s, Object o1, Object o2){
        if( o1 instanceof function && o2 instanceof function ){
            switch (s){
                case "plus":
                    _operation=Operation.Plus;
                    break;
                case "div":
                    _operation=Operation.Divid;
                    break;
                case "max":
                    _operation=Operation.Max;
                    break;
                case "min":
                    _operation=Operation.Min;
                    break;
                case "mul":
                    _operation=Operation.Times;
                    break;
                case"comp":
                    _operation=Operation.Comp;
                default:
                    throw new RuntimeException("Error: Invalid string input");
            }
            _functionL=(function)o1;
            _functionR=(function)o2;
            bt=new BinaryTree(this);

        }
        else{
            throw new RuntimeException("Error: Invalid object initalizer");
        }
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
