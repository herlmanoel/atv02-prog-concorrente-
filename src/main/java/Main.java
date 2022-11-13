import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The type Main.
 */
public class Main {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("Digite o número de iterações: ");
        int numberOfIterations = in.nextInt();
        System.out.print("Digite o número de Threads para a versão newFixedThreadPool: ");
        int numberOfThreadsForFixed = in.nextInt();

        Map<String, ExecutorService> listExecutorService = new HashMap<>();
        listExecutorService.put("newFixedThreadPool", Executors.newFixedThreadPool(numberOfThreadsForFixed));
        listExecutorService.put("newCachedThreadPool", Executors.newCachedThreadPool());
        listExecutorService.put("newWorkStealingPool", Executors.newWorkStealingPool());

        listExecutorService.forEach((key, itemExecutorService) -> {
            long TEMPO_INICIAL = System.currentTimeMillis();
            ExecutorCalculateEuler calculateEulerFixed = new ExecutorCalculateEuler(numberOfIterations, itemExecutorService);
            System.out.println(calculateEulerFixed.getNumberEuler());
            long TEMPO_FINAL = System.currentTimeMillis();

            System.out.println(key + ". TEMPO: " + (TEMPO_FINAL - TEMPO_INICIAL) + "ms");
        });
    }
}
