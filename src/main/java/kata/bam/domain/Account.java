package kata.bam.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Account {

    private Money amount;
    private AccountId accountId;

    private List<Operation> operations;

    public boolean canWithdraw(Money amount) {
        return this.amount.isGreaterThanOrEqualTo(amount) && amount.isPositive();
    }

    public Money withdraw(Money amount) {
        this.amount = this.amount.minus(amount);
        return this.amount;
    }

    public Money deposit(Money amount) {
        this.amount = this.amount.plus(amount);
        return this.amount;
    }

    @Value
    public static class AccountId {
        private Long value;
    }
}
