package com.mentoring.client_service.service;

import com.mentoring.client_service.model.Client;
import com.mentoring.client_service.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepo;

    @Override
    public Client getClient(Long id) {
        return clientRepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Can't find client by id. Id = %d".formatted(id)));
    }

}
