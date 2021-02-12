package ma.cloud.account.service;

import ma.cloud.account.Repository.AccountRepository;
import ma.cloud.account.Repository.OperationRepository;
import ma.cloud.account.entities.Account;
import ma.cloud.account.entities.Operation;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;

public class IAccountServiceImpl implements IAccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    OperationRepository operationRepository;

    @Autowired
    ClientRestService clientRestService;


    @Override
    public Account addAccount(Account c) {
        return accountRepository.save(c);
    }

    @Override
    public Operation makeTransfer(Long id, double montant) {
        Account c= accountRepository.getOne(id);
        Operation o=new Operation();
        o.setAccount(c);
        o.setMontant(montant);
        o.setType("virement");
        c.setSold(c.getSold()+montant);
        accountRepository.save(c);
        return operationRepository.save(o);
    }

    @Override
    public Operation makeWithdrawal(Long id, double montant) {
        Account c= accountRepository.getOne(id);
        Operation o=new Operation();
        o.setAccount(c);
        o.setMontant(montant);
        o.setType("retirer");
        c.setSold(c.getSold()-montant);
        accountRepository.save(c);
        return operationRepository.save(o);
    }

    @Override
    @Transactional
    public void transfer(Long id1, Long id2 ,double montant) {
        makeWithdrawal(id1,montant);
        makeWithdrawal(id2,montant);
    }

    @Override
    public Account getAccount(Long id) {
        Account c= accountRepository.getOne(id);
        c.setClient(clientRestService.findClientById(c.getClientID()));
        return c;
    }

    @Override
    public Account activateAccount(Long id) {
        Account c= accountRepository.getOne(id);
        c.setState("Activer");
        accountRepository.save(c);
        return c;
    }

    @Override
    public Account suspendAccount(Long id) {
        Account c= accountRepository.getOne(id);
        c.setState("suspendre");
        accountRepository.save(c);
        return c;
    }

    @Override
    public List<Operation> getOperation(Long id) {
        Account c= accountRepository.getOne(id);
        return c.getOperations();
    }
}
