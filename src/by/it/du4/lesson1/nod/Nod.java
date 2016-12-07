package by.it.du4.lesson1.nod;

public class Nod {

    private long startTime = System.currentTimeMillis();

    private long time() {
        return System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args) {

        Nod nod = new Nod();
        int a = 391884842;
        int b = 1653264423;

        System.out.printf("CalcNaive nod(%d, %d)=%d \n\t time=%d \n\n", a, b, nod.calcNaive(a, b), nod.time());

        nod = new Nod();
        System.out.printf("nod(%d, %d)=%d \n\t time=%d \n\n", a, b, nod.calcEuclid(a, b), nod.time());

        nod = new Nod();
        System.out.printf("nod(%d, %d)=%d \n\t time=%d \n\n", a, b, nod.calcEuclid(a, b), nod.time());

    }

    int calcNaive(int a, int b) {


        if (a == 0 && b == 0) throw new IllegalArgumentException("Both argument can't equals to zero");

        if (a == 0)return b;
        if (b == 0)return a;
        if (a == b)return a;

        int res = 1;

        if (a < b){
            int tmp = a;
            a = b;
            b = tmp;
        }

        if (a%b == 0) return b;

        for (int i = 2; i <= a; i++) {
            if (a%i == 0 && b%i == 0){
                res  = i;
            }
        }

        return res;
    }

    int calcEuclid(int a, int b) {

        AB ab = new AB(a,b);

        ab.validate();

        while ( ab.getB() !=  0){
            ab.euclidIter();
        }
        return ab.getA();
    }

    int calcMyFasterNod(int a, int b) {

        System.out.println("There would be implementation if I had enough time)))");

        return 0;
    }








}
