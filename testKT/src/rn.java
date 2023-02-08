import java.lang.reflect.Method;

public class rn {


    public static int test(int n) { return n+10; }

    public static void main(String[] args)
            throws ReflectiveOperationException {
        Method test = rn.class.getMethod("test", Integer.TYPE);
        System.out.println(test.invoke(null,20));
    }



}
