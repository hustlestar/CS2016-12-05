package by.it.du4.lesson01.nod;


class AB {
    private int a;
    private int b;

    public AB() {
    }

    AB(int a, int b) {
        this.a = a;
        this.b = b;
        swap();
    }

    void validate() {
        if (a == 0 && b == 0) throw new IllegalArgumentException("Both argument can't equals to zero");
    }


    private void  swap(){
        if (a < b){
            int tmp = a;
            a = b;
            b = tmp;
        }
    }

    void euclidIter(){
        a = a%b;
        swap();
    }

    int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }
}
