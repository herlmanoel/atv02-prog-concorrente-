import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        int NUMBER_OF_INTERACTIONS = 5000;
        int availableProcessors = Runtime.getRuntime().availableProcessors();

        Map<String, ExecutorService> listExecutorService = new HashMap<>();
        listExecutorService.put("newFixedThreadPool", Executors.newFixedThreadPool(availableProcessors));
        listExecutorService.put("newCachedThreadPool", Executors.newCachedThreadPool());
        listExecutorService.put("newWorkStealingPool", Executors.newWorkStealingPool());

        listExecutorService.forEach((key, itemExecutorService) -> {
            long TEMPO_INICIAL = System.currentTimeMillis();
            ExecutorCalculateEuler calculateEulerFixed = new ExecutorCalculateEuler(NUMBER_OF_INTERACTIONS, itemExecutorService);
            System.out.println(calculateEulerFixed.getNumberEuler());
            long TEMPO_FINAL = System.currentTimeMillis();

            System.out.println(key + ". TEMPO: " + (TEMPO_FINAL - TEMPO_INICIAL) + "ms");
        });
    }
}
