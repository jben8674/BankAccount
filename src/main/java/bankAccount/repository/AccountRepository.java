package bankAccount.repository;

import bankAccount.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface  AccountRepository extends JpaRepository<Account, Long> {
}
