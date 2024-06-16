package com.backend.flex.service;

import com.backend.flex.model.components.Image;
import com.backend.flex.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    private ImageRepository i;

    /**
     * Retrieves an image by its ID.
     *
     * @param id the ID of the image to retrieve
     */
    public Optional<Image> getImageByID(Long id){
        return i.findById(id);
    }

    /**
     * Creates a new image.
     *
     * @param image the data of the image to create
     */
    public Image createImage(Image image){
        return i.save(image);
    }

    /**
     * Deletes an image by its ID.
     *
     * @param id the ID of the image to delete
     */
    public void deleteImage(Long id){
        i.deleteById(id);
    }
}
