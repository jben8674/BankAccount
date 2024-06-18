package bankAccount.model;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class Transaction {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account ;
    @Column(nullable = false)
    private BigDecimal amount;
    @Column
    private String description;
    @Column(nullable = false)
    private LocalDateTime date;
    @Column(nullable = false)
    private TransactionType type;














}
