package com.momenta.service;

import com.momenta.model.Provider;
import com.momenta.repository.ProviderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProviderService {

    private final ProviderRepository providerRepository;

    public ProviderService(ProviderRepository providerRepository) {
        this.providerRepository = providerRepository;
    }

    public Provider save(Provider provider) {
        return providerRepository.save(provider);
    }

    public List<Provider> getAll() {
        return providerRepository.findAll();
    }

    public void delete(Long id) {
        providerRepository.deleteById(id);
    }
}
