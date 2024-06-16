package com.backend.flex.controller;

import com.backend.flex.model.components.Components;
import com.backend.flex.model.components.ComponentsUpdate;
import com.backend.flex.security.services.UserDetailsImpl;
import com.backend.flex.service.ComponentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller class for handling HTTP requests related to Components.
 * This class defines endpoints for retrieving, updating, and deleting components.
 */
@CrossOrigin(origins = "http://localhost:4200", exposedHeaders = {"Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"})
@RestController
@RequestMapping("/api/components")
public class ComponentController {

    private final ComponentService componentService;

    /**
     * Constructs a ComponentController object with the specified ComponentService.
     *
     * @param componentService the service responsible for managing components
     */
    public ComponentController(ComponentService componentService) {
        this.componentService = componentService;
    }

    /**
     * Handles GET requests to retrieve all components.
     *
     * @return a ResponseEntity containing a list of Components if successful, or INTERNAL_SERVER_ERROR if an exception occurs
     */
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping()
    public ResponseEntity<List<Components>> getComponent(){
        return new ResponseEntity<>(componentService.getAllComponents(), HttpStatus.OK);
    }

    /**
     * Handles GET requests to retrieve a component by its ID.
     *
     * @param id the ID of the component to retrieve
     * @return a ResponseEntity containing the requested Component if found, or INTERNAL_SERVER_ERROR if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<Components> getComponentsByID(@PathVariable("id") Long id, @AuthenticationPrincipal UserDetailsImpl user){
        Optional<Components> component = componentService.getComponentById(id);
        if(component.isPresent()) {
            user.validateAccess(component.get().getWebsite());
            return new ResponseEntity<>(component.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Handles PUT requests to update a component by its ID.
     *
     * @param id the ID of the component to update
     * @param component the updated Component data
     * @return a ResponseEntity containing the updated Component if successful, NOT_FOUND if the component is not found
     */
    @PutMapping("/{id}")
    public ResponseEntity<Components> updateComponentById(@PathVariable("id") Long id, @RequestBody ComponentsUpdate component, @AuthenticationPrincipal UserDetailsImpl user) {
        Optional<Components> componentData = componentService.getComponentById(id);
        if(componentData.isPresent()){
            user.validateAccess(componentData.get().getWebsite());
            return new ResponseEntity<>(componentService.updateComponent(componentData.get(), component, new ObjectMapper()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Handles DELETE requests to delete a component by its ID.
     *
     * @param id the ID of the component to delete
     * @return a ResponseEntity with NO_CONTENT if successful, or INTERNAL_SERVER_ERROR if an exception occurs
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteComponents(@PathVariable("id") long id, @AuthenticationPrincipal UserDetailsImpl user) {
        Optional<Components> componentData = componentService.getComponentById(id);
        if(componentData.isPresent()){
            user.validateAccess(componentData.get().getWebsite());
            componentService.deleteComponent(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
