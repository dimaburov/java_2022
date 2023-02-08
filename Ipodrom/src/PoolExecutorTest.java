import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PoolExecutorTest {
    private static final int POOLS_NUM = Runtime.getRuntime().availableProcessors();

    private static class MyProcess implements Runnable {
        final int number;


        public MyProcess(int n) { number = n; }

        public void run() {
            for (int i = 0; i < 3; i++) {
                int p = 0;
                for (int k = 0; k < 10_000_000; k++) p+=k;
                System.out.format("Thread: %s; Process: %d; Iteration: %d%n",
                        Thread.currentThread().getName(), number, i);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(POOLS_NUM);

        System.out.format("%d \n", POOLS_NUM);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            executor.execute(new MyProcess(i));
        }
        System.out.println("Start");

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);
        System.out.format("Времени затрачено %d\n",
                (int)(System.currentTimeMillis() - startTime));
    }
}
