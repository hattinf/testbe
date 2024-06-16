package com.backend.flex.service;

import com.backend.flex.model.Register;
import com.backend.flex.repository.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegisterService {
    @Autowired
    RegisterRepository registerRepository;

    /**
     * Retrieves a registered Participant by its ID.
     *
     * @param websiteId
     * @return an Optional containing the requested Participant, or empty if not found
     */
    public List<Register> getRegisterByWebsiteId(Long websiteId) {
        return registerRepository.findByWebsiteId(websiteId);
    }

    /**
     * Creates a new Participant.
     *
     * @param register
     * @return the created Participant or if the email already exists, throws a RuntimeException
     */
    public Register createRegister(Register register) {
        if(registerRepository.findByEmail(register.getEmail()).isPresent()){
            throw new RuntimeException("Email already exists");
        }
        return registerRepository.save(register);
    }

    /**
     * Deletes a Participant by its ID.
     *
     * @param id
     */
    public void deleteRegister(Long id) {
        registerRepository.deleteById(id);
    }
}
