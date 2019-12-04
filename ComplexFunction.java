package myMath;

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
    public ComplexFunction(String s){
        int psik=numOfPsik(s);
        if(psik==0){
            Polynom fl = new Polynom(s);
            Polynom fr = new Polynom("0");
            getBt().getRoot().setOper(Operation.None);
            getBt().getRoot().setLeft(new Node(fl));
            getBt().getRoot().setRight(new Node(fr));
            return;
        }
        if(psik==1){
            int mid = indexOfMiddlePsik(s);
            int left = findFirstIndex(s);
            getBt().getRoot().setOper(findop(s));
            function fL = new Polynom(s.substring(left, mid));
            function fR = new Polynom(s.substring(mid + 1, s.length() - 1));
            getBt().getRoot().setLeft(new Node(fL));
            getBt().getRoot().setRight(new Node(fR));
            return;
        }
        else{
            int mid = indexOfMiddlePsik(s);
            int left = findFirstIndex(s);
            getBt().getRoot().setOper(findop(s));
            try{
                function fL = new Polynom(s.substring(left, mid));
                getBt().insert(new Node(fL));
            }
            catch(Exception e){
                function fL=new ComplexFunction(s.substring(left, mid));
            }
            try{
                function fR = new Polynom(s.substring(mid + 1, s.length() - 1));
                getBt().insert(new Node(fR));
            }
            catch(Exception e){
                function fR = new ComplexFunction(s.substring(mid + 1, s.length() - 1));
            }
        }
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
            function f1 = ((function) o1).copy();
            function f2 = ((function) o2).copy();
            bt.getRoot().setLeft(new Node(f1));
            bt.getRoot().setRight(new Node(f2));

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
        bt.makeNode(f1,Operation.Times);
    }

    @Override
    public void div(function f1) {
        bt.makeNode(f1,Operation.Divid);
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
        ComplexFunction cf1=new ComplexFunction(s);
        return cf1;

    }
    public int indexOfMiddlePsik(String s){
        int count=0;
        int ans=-1;
        for(int i=0;i<s.length();i++){
            char ch=s.charAt(i);
            if(ch=='('){count++;}
            if(ch==')'){count--;}
            if(ch==','&& count==1){ans=i;}
        }
        if(count!=0){
            throw new RuntimeException("Wrong string input for ComplexFunction");
        }
        return ans;
    }
    public  Operation findop(String s){
        String t=s.substring(0,2);
        switch (t){
            case "pl":
                return Operation.Plus;
            case "di":
                return Operation.Divid;
            case "mi":
                return Operation.Min;
            case "ma":
                return Operation.Max;
            case "mu":
                return Operation.Times;
            case "co":
                return Operation.Comp;
            default:
                throw new RuntimeException("wrong input string for complex function");
        }

    }
    public int findFirstIndex(String s){
        String t=s.substring(0,2);
        switch (t){
            case "pl":
                return 5;
            case "di" :
                return 4;
            case "mi":
                return 4;
            case "ma":
                return 4;
            case "mu":
                return 4;
            case "co":
                return 5;
            default:
                throw new RuntimeException("wrong input string for complex function");
        }
    }

    @Override
    public function copy() {
        ComplexFunction f = new ComplexFunction(this.toString());
        return f;
    }
    public int numOfPsik(String s){
        int ans=0;
        for(int i=0;i<s.length();i++){
            char ch=s.charAt(i);
            if(ch == ','){ans++;}
        }
        return ans;
    }
    public void printInOrder(Node root){
        if(root==null){return;}
        printInOrder(root.getLeft());
        if((root.getOper()==null || root.getOper()==Operation.None)&&root.getFunc()!=null){
            Polynom p=new Polynom(root.getFunc().toString());
            int i =0;
            System.out.print(root.getFunc().toString());
        }
        else{
            System.out.print(root.getOper());
        }
        printInOrder(root.getRight());
    }
}
