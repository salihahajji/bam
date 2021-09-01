package kata.bam.storage;

import kata.bam.domain.Account.AccountId;
import kata.bam.domain.Operation;

import java.util.List;

public interface OperationStorage {

    List<Operation> getOperations(AccountId accountId);

    void saveOperation(Operation operation, AccountId accountId);
}
