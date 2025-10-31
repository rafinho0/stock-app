package br.com.aegis.stock.dto;

import br.com.aegis.stock.model.Product;
import br.com.aegis.stock.model.StockTransaction;
import br.com.aegis.stock.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class StockTransactionResponseDTO {

    private Long id;
    private Long productId;
    private String productName;
    private Long userId;
    private String username;
    private Integer quantity;
    private LocalDateTime dateOfTransaction;
    private String observation;

    public StockTransactionResponseDTO(StockTransaction transaction) {
        this.id = id;


        Product product = transaction.getProduct();
        if (product != null) {

            this.productId = product.getId();
            this.productName = product.getName();
        }

        User user = transaction.getUser();
        if (user != null) {
            this.userId = user.getId();
            this.username = user.getUsername();
        }

        this.username = username;
        this.quantity = quantity;
        this.dateOfTransaction = dateOfTransaction;
        this.observation = observation;
    }
}
