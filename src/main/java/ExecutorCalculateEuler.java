import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;

/**
 * The type Executor calculate euler.
 */
public class ExecutorCalculateEuler {
    /**
     * The Number of interactions.
     */
    private final Integer numberOfIterations;
    /**
     * The Number for sum.
     */
    private final List<BigDecimal> numberForSum = Collections.synchronizedList(new ArrayList<>(List.of(BigDecimal.ONE)));
    /**
     * The Executor service.
     */
    private final ExecutorService executorService;

    /**
     * Instantiates a new Executor calculate euler.
     *
     * @param numberOfIterations the number of interactions
     * @param executorService      the executor service
     */
    public ExecutorCalculateEuler(Integer numberOfIterations, ExecutorService executorService) {
        this.numberOfIterations = numberOfIterations;
        this.executorService = executorService;
    }

    /**
     * Gets number euler.
     *
     * @return the number euler
     */
    BigDecimal getNumberEuler () {
        for (int i = 1; i < numberOfIterations; i++) {
            executorService.execute(new CalculateEuler(i, numberForSum));
        }

        executorService.shutdown();

        waitExecutorService();

        return numberForSum
                .stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * Wait executor service.
     */
    void waitExecutorService() {
        while (!executorService.isTerminated()) {}
    }
}
