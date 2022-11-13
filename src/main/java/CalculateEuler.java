import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

/**
 * The type Calculate euler.
 */
public class CalculateEuler extends Thread {
    /**
     * The Index.
     */
    private final BigDecimal index;
    /**
     * The Number for sum.
     */
    private final List<BigDecimal> numberForSum;

    /**
     * Instantiates a new Calculate euler.
     *
     * @param index        the index
     * @param numberForSum the number for sum
     */
    public CalculateEuler(Integer index, List<BigDecimal> numberForSum) {
        this.numberForSum = numberForSum;
        this.index = new BigDecimal(index);
    }

    /**
     * Run.
     */
    @Override
    public void run () {
        BigDecimal currentDivisionForONE = tryDivisionForONE(fat(index));
        numberForSum.add(currentDivisionForONE);
    }

    /**
     * Try division for one big decimal.
     *
     * @param n the n
     * @return the big decimal
     */
    private BigDecimal tryDivisionForONE(BigDecimal n) {
        BigDecimal currentDivisionForONE = null;
        try {
            currentDivisionForONE = BigDecimal.ONE.divide(n, MathContext.DECIMAL128);
        } catch (Exception e) {
            System.out.println("Erro ao realizar divisão. " + e.getMessage());
        }
        return currentDivisionForONE;
    }

    /**
     * Fat big decimal.
     *
     * @param n the n
     * @return the big decimal
     */
    private BigDecimal fat(BigDecimal n) {
        if (n.equals(BigDecimal.ZERO)) {
            return BigDecimal.ONE;
        } else {
            return n.multiply(fat(n.subtract(BigDecimal.ONE)));
        }
    }
}
