import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;

public class ExecutorCalculateEuler {
    private final Integer numberOfInteractions;
    private final List<BigDecimal> numberForSum = Collections.synchronizedList(new ArrayList<>(List.of(BigDecimal.ONE)));
    private final ExecutorService executorService;

    public ExecutorCalculateEuler(Integer numberOfInteractions, ExecutorService executorService) {
        this.numberOfInteractions = numberOfInteractions;
        this.executorService = executorService;
    }

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

    void waitExecutorService() {
        while (!executorService.isTerminated()) {}
    }
}
