package kata.bam.domain;

import lombok.Value;
import java.math.BigInteger;

@Value
public class Money {

    public static Money ZERO = Money.of(0L);

    private final BigInteger amount;

    public boolean isPositive() {
        return this.amount.compareTo(BigInteger.ZERO) > 0;
    }

    public boolean isGreaterThanOrEqualTo(Money money) {
        return this.amount.compareTo(money.amount) >= 0;
    }

    public static Money of(long value) {
        return new Money(BigInteger.valueOf(value));
    }

    public Money minus(Money money) {
        return new Money(this.amount.subtract(money.amount));
    }

    public Money plus(Money money) {
        return new Money(this.amount.add(money.amount));
    }
}
