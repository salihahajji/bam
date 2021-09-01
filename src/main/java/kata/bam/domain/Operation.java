package kata.bam.domain;

import kata.bam.enums.OperationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@AllArgsConstructor
public class Operation {

    private OperationType operationType;
    private LocalDate operationDate;
    private Money amount;

    public Operation(OperationType operationType, Money amount) {
        this(operationType, amount, LocalDate.now());
    }

    public Operation(OperationType operationType, Money amount, LocalDate operationDate) {
        this.operationType = operationType;
        this.operationDate = operationDate;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return String.format("%s : %s of %d", operationDate.format(DateTimeFormatter.ISO_DATE),
                operationType.name(), amount);

    }
}
