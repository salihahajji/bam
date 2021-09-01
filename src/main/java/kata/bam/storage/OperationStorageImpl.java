package kata.bam.storage;

import kata.bam.domain.Account;
import kata.bam.domain.Operation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class OperationStorageImpl implements  OperationStorage{

    Map<Long, List<Operation>> operations = new HashMap<>();

    @Override
    public List<Operation> getOperations(Account.AccountId accountId) {
        return operations.get(accountId.getValue());
    }

    @Override
    public void saveOperation(Operation operation, Account.AccountId accountId) {
        operations.getOrDefault(accountId.getValue(), new ArrayList<>()).add(operation);
    }
}
