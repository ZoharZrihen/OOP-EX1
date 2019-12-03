package myMath;

import groovyx.gpars.extra166y.Ops;

class Node {
    function func;
    Operation oper;
    Node left, right;
    public Node(){
        func=null;
        oper= Operation.None;
        left = right = null;
    }
    public Node(Node n){
        this.func=n.getFunc();
        this.oper=n.getOper();
        this.left=n.getLeft();
        this.right=n.getRight();
    }
    public Node(Operation op) {
        this.oper=op;
        func=null;
        left = right = null;
    }
    public Node(function f1) {
        if (f1 instanceof ComplexFunction) {
            ComplexFunction cf=(ComplexFunction)f1;
            this.oper = cf.getOp();
            this.func = null;
            this.left = new Node(cf.left());
            this.right = new Node(cf.right());
        }
        else{
            this.oper = Operation.None;
            this.func = f1;
            left = right = null;
        }
    }

    public void setFunc(function func) {
        this.func = func;
    }

    public void setOper(Operation oper) {
        this.oper = oper;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Operation getOper() {
        return oper;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public function getFunc() {
        return func;
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
        root = new Node();
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }
    public void insert(Node n){
        n.setLeft(this.getRoot());
        this.setRoot(n);
    }
    public void makeNode(function f,Operation o){
        Node n = new Node (f);
        Node op = new Node (o);
        op.setRight(n);
        insert(op);
    }
}
    public class ComplexFunction implements complex_function{
    private BinaryTree bt=new BinaryTree();

    public ComplexFunction(){
        Polynom p = new Polynom();
        bt.setRoot(new Node(p));
    }

    public ComplexFunction(Object o1){
        if ((o1 instanceof Polynom) || (o1 instanceof Monom)) {
            function f=new Polynom(o1.toString());
            Node t=new Node(f);
            bt.setRoot(t);
        }
        else if(o1 instanceof ComplexFunction){
            ComplexFunction cf = (ComplexFunction)o1;
            this.getBt().getRoot().setOper(cf.getOp());
            Node nL = new Node(cf.getBt().getRoot().getLeft());
            Node nR=new Node(cf.getBt().getRoot().getRight());
            getBt().getRoot().setLeft(nL);
            getBt().getRoot().setRight(nR);
        }
        else throw new RuntimeException("Error: Invalid object initalizer");

    }
    public ComplexFunction(String s, Object o1, Object o2){
        if( o1 instanceof function && o2 instanceof function ){
            switch (s){
                case "plus":
                    getBt().getRoot().setOper(Operation.Plus);
                    break;
                case "div":
                    getBt().getRoot().setOper(Operation.Divid);
                    break;
                case "max":
                    getBt().getRoot().setOper(Operation.Max);
                    break;
                case "min":
                    getBt().getRoot().setOper(Operation.Min);
                    break;
                case "mul":
                    getBt().getRoot().setOper(Operation.Times);
                    break;
                case"comp":
                    getBt().getRoot().setOper(Operation.Comp);
                default:
                    throw new RuntimeException("Error: Invalid string input");
            }
            //_functionL=((function) o1).copy();
            //_functionR=((function) o2).copy();
            function f1 = ((function) o1).copy();
            function f2 = ((function) o2).copy();
            //bt.setRoot(new Node(_operation));
            bt.getRoot().setLeft(new Node(f1));
            bt.getRoot().setRight(new Node(f2));
           // bt.getRoot().setOper(_operation);

        }
        else{
            throw new RuntimeException("Error: Invalid object initalizer");
        }
    }

    public void set_operation(Operation o){
        getBt().getRoot().setOper(o);
    }
    public BinaryTree getBt() {
        return bt;
    }
    public void setBt(BinaryTree bt) {
        this.bt = bt;
    }

        @Override
    public void plus(function f1) {
        bt.makeNode(f1,Operation.Plus);

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
        return bt.getRoot().getLeft().getFunc();
    }

    @Override
    public function right() {
        return bt.getRoot().getRight().getFunc();
    }

    @Override
    public Operation getOp() {
        return getBt().getRoot().getOper();
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
        ComplexFunction f = new ComplexFunction();
        f.setBt(this.getBt());
        f.set_operation(this.getOp());
        return f;
    }
}
