package br.com.aegis.stock.dto;

import br.com.aegis.stock.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockTransactionRequestDTO {

    private Long productID;
    private Long userID;
    private TransactionType type;
    private Integer quantity;
    private String observation;

}
