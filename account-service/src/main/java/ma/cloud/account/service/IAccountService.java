package ma.cloud.account.service;

import ma.cloud.account.entities.Account;
import ma.cloud.account.entities.Operation;

import java.util.List;

public interface IAccountService {
    public Account addAccount(Account c);
    public Operation makeTransfer(Long id,double montant);
    public Operation makeWithdrawal(Long id,double montant);
    public void transfer(Long id1,Long id2,double montan);
    public Account getAccount(Long id);
    public Account activateAccount(Long id);
    public Account suspendAccount(Long id);
    public List<Operation> getOperation(Long id);
}
