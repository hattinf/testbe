package com.backend.flex.controller;

import com.backend.flex.model.components.Image;
import com.backend.flex.model.footer.Footer;
import com.backend.flex.service.ImageService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:4200", exposedHeaders = {"Access-Control-Allow-Origin","Access-Control-Allow-Credentials"})
@RestController
@RequestMapping("/api/image")
public class ImageController {


    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    /**
     * Get image by ID
     *
     * @param id
     * @return a ResponseEntity containing the requested Image if found, or NOT_FOUND if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<byte[]>  getImageById(@PathVariable Long id){
        Optional<Image> image = imageService.getImageByID(id);
        if (image.isPresent()) {

            String img = image.get().getImage();
            byte[] imageBytes = Base64.getDecoder().decode(img);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG);

            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     *  Create image
     *
     * @param image
     * @return a ResponseEntity containing the created Image if successful, or INTERNAL_SERVER_ERROR if an exception occurs
     */
    @PostMapping
    public ResponseEntity<Image> createImage(@RequestBody Image image){
        try {
            return new ResponseEntity<>(imageService.createImage(image), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Delete image by ID
     *
     * @param id
     * @return a ResponseEntity containing NO_CONTENT if successful, or INTERNAL_SERVER_ERROR if an exception occurs
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteImage(@PathVariable Long id) {
        try {
            imageService.deleteImage(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
