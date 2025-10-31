package br.com.aegis.stock.service;

import br.com.aegis.stock.dto.StockTransactionRequestDTO;
import br.com.aegis.stock.dto.StockTransactionResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StockTransactionService {

    StockTransactionResponseDTO registerNewTransaction(StockTransactionRequestDTO requestDTO);

    List<StockTransactionResponseDTO> findAllTransactions();

    StockTransactionResponseDTO findTransactionById(Long id);
}
