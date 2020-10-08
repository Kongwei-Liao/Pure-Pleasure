package lambda;

import org.junit.Test;

public class OneFuncInterfaceLambdaTest {

    class Numbers {
        public int a;
        public int b;
        public int c;

        Numbers(int a, int b, int c) {

        }
    }

    interface Sum1 {
        public int sumMethod1(int a, int b);
    }

    interface Sum2 {
        public int sumMethod2(int a, int b);
    }

    interface Sum3 {
        public int sumMethod3(int a, int b);
    }

    static void callAll(Numbers number, Sum1 sum1, Sum2 sum2, Sum3 sum3) {
        System.out.println(sum1.sumMethod1(number.a, number.b));
    }

    @Test
    public void test() {
        callAll(new Numbers(1, 2, 3), (a, b) -> a + b, (a, b) -> a + b, (a, b) -> a + b);
    }
}
