package br.com.aegis.stock.service;

import br.com.aegis.stock.dto.StockTransactionRequestDTO;
import br.com.aegis.stock.dto.StockTransactionResponseDTO;
import br.com.aegis.stock.enums.TransactionType;
import br.com.aegis.stock.model.Product;
import br.com.aegis.stock.model.StockTransaction;
import br.com.aegis.stock.model.User;
import br.com.aegis.stock.repository.ProductRepository;
import br.com.aegis.stock.repository.StockTransactionRepository;
import br.com.aegis.stock.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StockTransactionImpl implements StockTransactionService {

    private final StockTransactionRepository transactionRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Autowired
    public StockTransactionImpl(StockTransactionRepository transactionRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public StockTransactionResponseDTO registerNewTransaction(StockTransactionRequestDTO requestDTO) {

        Product product = productRepository.findById(requestDTO.getProductID())
                .orElseThrow(() -> new RuntimeException("Product id not found"));

        User user = userRepository.findById(requestDTO.getUserID())
                .orElseThrow(() -> new RuntimeException("User id not found"));



        if (requestDTO.getType() == TransactionType.OUT && product.getStockQuantity() < 0) {
            throw new RuntimeException("Insufficient stock for this transaction");
        }

        StockTransaction transaction = new StockTransaction();

        transaction.setProduct(product);
        transaction.setUser(user);
        transaction.setType(requestDTO.getType());
        transaction.setQuantity(requestDTO.getQuantity());
        transaction.setObservation(requestDTO.getObservation());
        transaction.setDateOfTransaction(LocalDateTime.now());

        Integer newStockQuantity = product.getStockQuantity();
        if (requestDTO.getType() == TransactionType.ENTRY) {
            newStockQuantity += requestDTO.getQuantity();
        } else if (requestDTO.getType() == TransactionType.OUT) {
            newStockQuantity -= requestDTO.getQuantity();
        }
        product.setStockQuantity(newStockQuantity);

        productRepository.save(product);
        StockTransaction savedTransaction = transactionRepository.save(transaction);

        return new StockTransactionResponseDTO(savedTransaction);
    }

    @Override
    public List<StockTransactionResponseDTO> findAllTransactions() {
        return List.of();
    }

    @Override
    public StockTransactionResponseDTO findTransactionById(Long id) {
        return null;
    }
}
