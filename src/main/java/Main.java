import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        ExecutorCalculateEuler c = new ExecutorCalculateEuler(5000, Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()));
        System.out.println(c.getNumberEuler());
    }
}
