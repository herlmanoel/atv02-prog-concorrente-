import java.math.BigDecimal;
import java.time.Instant;
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
    private final Integer numberOfInteractions;
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
     * @param numberOfInteractions the number of interactions
     * @param executorService      the executor service
     */
    public ExecutorCalculateEuler(Integer numberOfInteractions, ExecutorService executorService) {
        this.numberOfInteractions = numberOfInteractions;
        this.executorService = executorService;
    }

    /**
     * Gets number euler.
     *
     * @return the number euler
     */
    BigDecimal getNumberEuler () {
        for (int i = 1; i < numberOfInteractions; i++) {
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
