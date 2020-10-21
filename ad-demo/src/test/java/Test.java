import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {
    private static Integer OINDEX=100;
    private static final Integer AA = 100;
    public static void main(String[] args) {
        ExecutorService cachedThreadPool = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 100; i++) {
            final int index = i;
            cachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+ ":====:"+index);
                }
            });
        }
    }
}
