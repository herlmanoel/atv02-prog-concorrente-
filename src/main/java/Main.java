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
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        Scanner in = new Scanner(System.in);

        System.out.print("Digite o número de iterações: ");
        int numberOfIterations = in.nextInt();

        while (numberOfIterations <= 0) {
            System.out.println("O número digitado é menor ou igual a 0.");
            System.out.print("Por favor, digite novamente o número de iterações: ");
            numberOfIterations = in.nextInt();
        }

        Map<String, ExecutorService> listExecutorService = new HashMap<>();
        listExecutorService.put("newFixedThreadPool", Executors.newFixedThreadPool(availableProcessors));
        listExecutorService.put("newCachedThreadPool", Executors.newCachedThreadPool());
        listExecutorService.put("newWorkStealingPool", Executors.newWorkStealingPool());

        int finalNumberOfIterations = numberOfIterations;
        listExecutorService.forEach((key, itemExecutorService) -> {
            long TEMPO_INICIAL = System.currentTimeMillis();
            ExecutorCalculateEuler calculateEulerFixed = new ExecutorCalculateEuler(finalNumberOfIterations, itemExecutorService);
            System.out.println(calculateEulerFixed.getNumberEuler());
            long TEMPO_FINAL = System.currentTimeMillis();

            System.out.println(key + ". TEMPO: " + (TEMPO_FINAL - TEMPO_INICIAL) + "ms");
        });
    }
}
