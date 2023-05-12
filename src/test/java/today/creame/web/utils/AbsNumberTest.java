package today.creame.web.utils;

import org.junit.Test;

public class AbsNumberTest {
    @Test
    public static void main(String[] args) {

        int a = 10;
        int b = -10;

        System.out.println("a: " + -a);
        System.out.println("b: " + -b);


        System.out.println("a: " + -(Math.abs(a)));
        System.out.println("b: " + -(Math.abs(b)));
    }
}
