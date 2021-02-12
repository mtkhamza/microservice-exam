package ma.cloud.account.Repository;

import ma.cloud.account.entities.Account;
import ma.cloud.account.entities.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AccountRepository extends JpaRepository<Account, Long> {
}
