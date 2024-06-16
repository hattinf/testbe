package com.backend.flex.controller;

import com.backend.flex.model.Register;
import com.backend.flex.model.Website;
import com.backend.flex.model.components.Components;
import com.backend.flex.security.services.UserDetailsImpl;
import com.backend.flex.service.RegisterService;
import com.backend.flex.service.WebsiteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:4200", exposedHeaders = {"Access-Control-Allow-Origin","Access-Control-Allow-Credentials"})
@RestController
@RequestMapping("/api/register")
public class RegisterController {

    private final RegisterService registerService;
    private final WebsiteService websiteService;

    public RegisterController(RegisterService registerService, WebsiteService websiteService) {
        this.registerService = registerService;
        this.websiteService = websiteService;
    }

    /**
     *
     * Handles GET requests to retrieve all registered Participants by website.
     *
     * @param websiteId
     * @param user
     * @return a ResponseEntity containing a list of Participants if successful, or INTERNAL_SERVER_ERROR if an exception occurs
     */
    @GetMapping()
    public ResponseEntity<List<Register>> getAllPagesByWebsite(@RequestParam Long websiteId, @AuthenticationPrincipal UserDetailsImpl user) {
        user.validateAccess(websiteService.getWebsiteById(websiteId).orElseThrow());
        return new ResponseEntity<>(registerService.getRegisterByWebsiteId(websiteId), HttpStatus.OK);
    }

    /**
     *  Participant registration
     *
     * @param register
     * @param websiteId
     * @return a ResponseEntity containing the created Participant if successful, or INTERNAL_SERVER_ERROR if an exception occurs
     */
    @PostMapping()
    public ResponseEntity<Register> createRegister(@RequestBody Register register, @RequestParam Long websiteId) {
            Optional<Website> website = websiteService.getWebsiteById(websiteId);

            if(website.isPresent()){
                boolean contains = false;
                for (Components comp : website.get().getComponents()) {
                    if (comp.getType().equals("RPR")) {
                        contains = true;
                        break;
                    }
                }
                if(contains){
                    register.setWebsite(websiteService.getWebsiteById(websiteId).orElseThrow());
                    return new ResponseEntity<>(registerService.createRegister(register), HttpStatus.CREATED);
                } else {
                    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
    }

    /**
     *
     * Delete a participant registration entry
     *
     * @param id
     * @return a ResponseEntity with NO_CONTENT if successful, or INTERNAL_SERVER_ERROR if an exception occurs
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteRegister(@PathVariable Long id) {
        registerService.deleteRegister(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
