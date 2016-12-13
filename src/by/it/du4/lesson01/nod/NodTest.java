package by.it.du4.lesson01.nod;

import org.junit.Test;

import static org.junit.Assert.*;

public class NodTest {

    @Test(timeout = 8000)
    public void testNodA() throws Exception {
        Nod nod = new Nod();
        boolean ok = (nod.calcNaive(391884842, 1653264423) == 19);
        assertTrue("testNodA failed", ok);
    }

    @Test(timeout = 2000)
    public void testNodB() throws Exception {
        Nod nod = new Nod();
        boolean ok = (nod.calcEuclid(391884842, 1653264423) == 19);
        assertTrue("testNodB failed", ok);
    }



}