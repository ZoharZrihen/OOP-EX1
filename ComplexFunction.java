package myMath;

    public class ComplexFunction implements complex_function{
        private function funcright;
        private function funcleft;
        private Operation oper;

    public ComplexFunction(){
        funcleft=new Polynom("0");
        funcright=null;
        oper=Operation.None;
    }
    public ComplexFunction(Object o1){
        if ((o1 instanceof Polynom) || (o1 instanceof Monom)) {
            function f=new Polynom(o1.toString());
           funcleft=f;
           funcright=null;
           oper=Operation.None;
        }
        else if(o1 instanceof ComplexFunction){
            ComplexFunction cf = (ComplexFunction)o1;
            funcright=cf.right().copy();
            funcleft=cf.left().copy();
            oper=cf.getOp();
        }
        else throw new RuntimeException("Error: Invalid object initalizer");

    }
    public ComplexFunction(String s){
        int psik=numOfPsik(s);
        if(psik==0){
            funcleft = new Polynom(s);
            funcright=null;
            oper=Operation.None;
            return;
        }
        if(psik==1){
            int mid = indexOfMiddlePsik(s);
            int left = findFirstIndex(s);
            funcleft = new Polynom(s.substring(left, mid));
            funcright = new Polynom(s.substring(mid + 1, s.length() - 1));
            oper=findop(s);
            return;
        }
        else{
            int mid = indexOfMiddlePsik(s);
            int left = findFirstIndex(s);
             oper=findop(s);
            try{
                funcleft = new Polynom(s.substring(left, mid));
            }
            catch(Exception e){
                funcleft=new ComplexFunction(s.substring(left, mid));
            }
            try{
                funcright= new Polynom(s.substring(mid + 1, s.length() - 1));
            }
            catch(Exception e){
                funcright = new ComplexFunction(s.substring(mid + 1, s.length() - 1));
            }
        }
    }
    public ComplexFunction(String s, Object o1, Object o2){
        if( o1 instanceof function && o2 instanceof function ){
            oper=findop(s);
            funcleft = ((function) o1).copy();
            funcright = ((function) o2).copy();
        }
        else{
            throw new RuntimeException("Error: Invalid object initalizer");
        }
    }
    @Override
    public void plus(function f1) {
        ComplexFunction cf = new ComplexFunction("plus",this,f1);
        this.funcleft=cf.left();
        this.funcright=cf.right();
        this.oper=cf.getOp();
    }
    @Override
    public void mul(function f1) {
        ComplexFunction cf = new ComplexFunction("mul",this,f1);
        this.funcleft=cf.left();
        this.funcright=cf.right();
        this.oper=cf.getOp();
    }
    @Override
    public void div(function f1) {
        ComplexFunction cf = new ComplexFunction("div",this,f1);
        this.funcleft=cf.funcleft;
        this.funcright=cf.funcright;
        this.oper=cf.getOp();
    }
    @Override
    public void max(function f1) {
        ComplexFunction cf = new ComplexFunction("max",this,f1);
        this.funcleft=cf.left();
        this.funcright=cf.right();
        this.oper=cf.getOp();

    }
    @Override
    public void min(function f1) {
        ComplexFunction cf = new ComplexFunction("min",this,f1);
        this.funcleft=cf.left();
        this.funcright=cf.right();
        this.oper=cf.getOp();
    }
    @Override
    public void comp(function f1) {
        ComplexFunction cf = new ComplexFunction("comp",this,f1);
        this.funcleft=cf.left();
        this.funcright=cf.right();
        this.oper=cf.getOp();
    }
    @Override
    public function left() {
        return funcleft;
    }
    @Override
    public function right() {
        return funcright;
    }
    @Override
    public Operation getOp() {
        return oper;
    }
    @Override
    public double f(double x) {
        switch (oper){
            case Plus:
                return left().f(x)+right().f(x);
            case Divid:
                return left().f(x)/right().f(x);
            case Times:
                return left().f(x)*right().f(x);
            case Max:
                return Math.max(left().f(x),right().f(x));
            case Min:
                return Math.min(left().f(x),right().f(x));
            case Comp:
                return left().f(right().f(x));
            case None:
                return left().f(x);
            default:
                throw new RuntimeException("ERROR: illegal operation");
        }

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
        if (s.length() == 3)
            s+="(";
        String t=s.substring(0,4);
        switch (t){
            case "plus":
                return Operation.Plus;
            case "div(":
                return Operation.Divid;
            case "min(":
                return Operation.Min;
            case "max(":
                return Operation.Max;
            case "mul(":
                return Operation.Times;
            case "comp":
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
    public int numOfPsik(String s){
            int ans=0;
            for(int i=0;i<s.length();i++){
                char ch=s.charAt(i);
                if(ch == ','){ans++;}
            }
            return ans;
        }
    @Override
    public function copy() {
        ComplexFunction f = new ComplexFunction ();
        f.funcleft=left();
        f.funcright=right();
        f.oper=getOp();
        return f;
    }
    @Override
    public boolean equals(Object obj) {
        if((obj instanceof Polynom || obj instanceof Monom) && (oper == Operation.None)){   //check simple case, complex function contains a single non complex function, compared with a polynom/monom
            return this.oper==Operation.None&&this.funcleft.equals((Polynom)obj);
        }
        else if(obj instanceof function) {  //this complex function contains more than one function, run test only on sample range 0-1000
            for(int i=0;i<1000;i++){
                function func = (function) obj;
                if (Double.isInfinite(this.f(i)) || Double.isInfinite(func.f(i)) ||Double.isNaN(this.f(i)) || Double.isNaN(func.f(i)))
                    continue;           //checking non continuous points in function, ignore these tests - only check where function is continuous
                else if(this.f(i) !=((function) obj).f(i)) return false;
            }
            return true;        //f(x) values are equal on selected points in both functions
        }
        return false;   //unsupported object
        }
    @Override
    public String toString(){
       String ans = operToString(getOp());
       if (ans == "")
           return funcleft.toString();
       ans+="(";
       ans+=(funcleft.toString());
       ans += ",";
       ans += (funcright.toString());
       ans+=")";
       return ans;

        }
    public String operToString(Operation op){
            switch (op){
                case Plus:
                    return "plus";
                case Divid:
                    return "div";
                case Times:
                    return "mul";
                case Max:
                    return "max";
                case Min:
                    return "min";
                case Comp:
                    return "comp";
                case None:
                    return "";
                default:
                    throw new RuntimeException("ERROR: illegal operation");
            }

        }
    }

