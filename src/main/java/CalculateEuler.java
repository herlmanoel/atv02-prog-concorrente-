import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

public class CalculateEuler extends Thread {
    private final BigDecimal index;
    private final List<BigDecimal> numberForSum;

    public CalculateEuler(Integer index, List<BigDecimal> numberForSum) {
        this.numberForSum = numberForSum;
        this.index = new BigDecimal(index);
    }

    @Override
    public void run () {
        BigDecimal currentDivisionForONE = tryDivisionForONE(fat(index));
        numberForSum.add(currentDivisionForONE);
    }

    private BigDecimal tryDivisionForONE(BigDecimal n) {
        BigDecimal currentDivisionForONE = null;
        try {
            currentDivisionForONE = BigDecimal.ONE.divide(n, MathContext.DECIMAL128);
        } catch (Exception e) {
            System.out.println("Erro ao realizar divisão. " + e.getMessage());
        }
        return currentDivisionForONE;
    }

    private BigDecimal fat(BigDecimal n) {
        if (n.equals(BigDecimal.ZERO)) {
            return BigDecimal.ONE;
        } else {
            return n.multiply(fat(n.subtract(BigDecimal.ONE)));
        }
    }
}
