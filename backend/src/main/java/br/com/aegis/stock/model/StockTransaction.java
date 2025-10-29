package br.com.aegis.stock.model;

import br.com.aegis.stock.enums.TransactionType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "stock_transaction")
@Data
@NotNull
@AllArgsConstructor
public class StockTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    @NotNull
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull
    private User user;

    /*
        This is the enum that we created for Entry and Out
        We're using the String type, because our enum is a String
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type")
    @NotNull
    private TransactionType type;

    @Column(name = "quantity")
    @NotNull
    private Integer quantity;

    @Column(name = "date_transaction")
    @NotNull
    private LocalDateTime dateOfTransaction;

    @Column(name = "observation", length = 250)
    private String observation;
}
