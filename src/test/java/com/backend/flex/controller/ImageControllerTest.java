package com.backend.flex.controller;

import com.backend.flex.model.components.Image;
import com.backend.flex.service.ImageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ImageControllerTest {

    @Mock
    private ImageService imageService;

    @InjectMocks
    private ImageController imageController;

    private Image image;

    @BeforeEach
    public void setUp() {
        image = new Image();
        image.setId(1L);
        image.setImage("iVBORw0KGgoAAAANSUhEUgAAAAgAAAAIAQMAAAD+wSzIAAAABlBMVEX///+/v7+jQ3Y5AAAADklEQVQI12P4AIX8EAgALgAD/aNpbtEAAAAASUVORK5CYI");
    }

    @Test
    public void getImageByIdReturnsImageWhenPresent() {
        when(imageService.getImageByID(1L)).thenReturn(Optional.of(image));
        ResponseEntity<byte[]> response = imageController.getImageById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void getImageByIdReturnsNotFoundWhenImageNotPresent() {
        when(imageService.getImageByID(1L)).thenReturn(Optional.empty());
        ResponseEntity<byte[]> response = imageController.getImageById(1L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void createImageReturnsImageWhenSuccessful() {
        when(imageService.createImage(image)).thenReturn(image);
        ResponseEntity<Image> response = imageController.createImage(image);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(image, response.getBody());
    }

    @Test
    public void createImageReturnsInternalServerErrorWhenExceptionThrown() {
        when(imageService.createImage(image)).thenThrow(RuntimeException.class);
        ResponseEntity<Image> response = imageController.createImage(image);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void deleteImageReturnsNoContentWhenSuccessful() {
        ResponseEntity<HttpStatus> response = imageController.deleteImage(1L);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void deleteImageReturnsInternalServerErrorWhenExceptionThrown() {
        doThrow(RuntimeException.class).when(imageService).deleteImage(1L);
        ResponseEntity<HttpStatus> response = imageController.deleteImage(1L);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}