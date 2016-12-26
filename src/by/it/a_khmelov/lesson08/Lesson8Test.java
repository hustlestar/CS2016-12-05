package by.it.a_khmelov.lesson08;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Lesson8Test {
    @Test
    public void A() throws Exception {
        A_EditDist instance = new A_EditDist();
        assertEquals("A1 failed", instance.getDistanceEdinting("ab","ab"),0);
        assertEquals("A2 failed", instance.getDistanceEdinting("short","ports"),3);
        assertEquals("A3 failed", instance.getDistanceEdinting("distance","editing"),5);
    }


    @Test
    public void B() throws Exception {
        B_EditDist instance = new B_EditDist();
        assertEquals("B1 failed", instance.getDistanceEdinting("ab","ab"),0);
        assertEquals("B2 failed", instance.getDistanceEdinting("short","ports"),3);
        assertEquals("B3 failed", instance.getDistanceEdinting("distance","editing"),5);
    }

    @Test
    public void C() throws Exception {
        C_EditDist instance = new C_EditDist();
        assertEquals("C1 failed", instance.getDistanceEdinting("ab","ab"),"#,#,");
        assertEquals("C2 failed", instance.getDistanceEdinting("short","ports"),"~p,-h,#,#,5+s,");
        assertEquals("C3 failed", instance.getDistanceEdinting("distance","editing"),"+e,#,#,-,#,~i,#,-c,~g,");
    }

}
