package br.com.aegis.stock.service;

import br.com.aegis.stock.dto.StockTransactionRequestDTO;
import br.com.aegis.stock.dto.StockTransactionResponseDTO;
import br.com.aegis.stock.enums.TransactionType;
import br.com.aegis.stock.exception.GlobalExceptionHandler;
import br.com.aegis.stock.exception.InsufficientStockException;
import br.com.aegis.stock.exception.ResourceNotFoundException;
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
import java.util.stream.Collectors;

@Service
public class StockTransactionImpl implements StockTransactionService {

    private final StockTransactionRepository transactionRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Autowired
    public StockTransactionImpl(StockTransactionRepository transactionRepository,
                                ProductRepository productRepository,
                                UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public StockTransactionResponseDTO registerNewTransaction(StockTransactionRequestDTO requestDTO) {


        // Just checking if the product and user is valid
        Product product = productRepository.findById(requestDTO.getProductID())
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));

        User user = userRepository.findById(requestDTO.getUserID())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));


        // if the transaction is an output and the stock quantity is less than 0 we throw an error
        if (requestDTO.getType() == TransactionType.OUT && product.getStockQuantity() < 0) {
            throw new InsufficientStockException("Not enough stock to output. Current stock quantity: " + product.getStockQuantity());
        }

        StockTransaction transaction = new StockTransaction();

        // right here we instantiate the fields in the stock transaction entity
        transaction.setProduct(product);
        transaction.setUser(user);
        transaction.setType(requestDTO.getType());
        transaction.setQuantity(requestDTO.getQuantity());
        transaction.setObservation(requestDTO.getObservation());
        transaction.setDateOfTransaction(LocalDateTime.now());

        // checking if we're trying to input or output products
        Integer newStockQuantity = product.getStockQuantity();
        if (requestDTO.getType() == TransactionType.ENTRY) {
            newStockQuantity += requestDTO.getQuantity();
        } else if (requestDTO.getType() == TransactionType.OUT) {
            newStockQuantity -= requestDTO.getQuantity();
        }
        product.setStockQuantity(newStockQuantity);

        // persisting product and stockTransaction
        productRepository.save(product);
        StockTransaction savedTransaction = transactionRepository.save(transaction);

        return new StockTransactionResponseDTO(savedTransaction);
    }

    @Override
    @Transactional(readOnly = true)
    public List<StockTransactionResponseDTO> findAllTransactions() {
        return transactionRepository.findAll().stream()
                .map(StockTransactionResponseDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public StockTransactionResponseDTO findTransactionById(Long id) {
        StockTransaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Stock", "id", id));

        return new StockTransactionResponseDTO(transaction);
    }
}
