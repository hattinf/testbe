package com.backend.flex.service;

import com.backend.flex.model.components.Components;
import com.backend.flex.model.components.ComponentsCreate;
import com.backend.flex.model.Page;
import com.backend.flex.model.components.ComponentsUpdate;
import com.backend.flex.model.components.props.*;
import com.backend.flex.model.components.props.bar.BarProps;
import com.backend.flex.model.components.props.cards.CardProps;
import com.backend.flex.model.components.props.faq.FaqProps;
import com.backend.flex.model.components.props.schedule.ScheduleProps;
import com.backend.flex.model.components.props.showcase.ShowProps;
import com.backend.flex.repository.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationObservationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing components.
 * This class provides methods for CRUD operations on components and their properties.
 */
@Service
public class ComponentService {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    ComponentRepository componentRepository;

    /**
     * Retrieves all components.
     *
     * @return a list of all components
     */
    public List<Components> getAllComponents() {
        return componentRepository.findAll();
    }

    /**
     * Retrieves a component by its ID.
     *
     * @param id the ID of the component to retrieve
     * @return an Optional containing the requested component, or empty if not found
     */
    public Optional<Components> getComponentById(Long id){
        return componentRepository.findById(id);
    }

    /**
     * Creates a new component.
     *
     * @param component the data of the component to create
     * @param page the associated page for the component
     * @param prop the properties of the component
     * @return the created component
     */
    public Components createComponents(ComponentsCreate component, Page page, Prop prop) {
        return  componentRepository.save(new Components(component.getName(), page, prop, component.getType(), page.getWebsite(), component.getPosition()));
    }

    /**
     * Updates an existing component.
     *
     * @param componentData the existing component to update
     * @param newComponent the updated component data
     * @param mapper the ObjectMapper for mapping JSON to objects
     * @return the updated component
     */
    public Components updateComponent (Components componentData, ComponentsUpdate newComponent, ObjectMapper mapper) {
        componentData.setName(newComponent.getName());
        componentData.setPosition(newComponent.getPosition());
        switch (newComponent.getType()) {
            case ("TPR") -> {
                updateProps(componentData, newComponent.getProps(), TextProps.class, TextPropsRepository.class, mapper);
            }
            case ("HRP") -> {
                updateProps(componentData, newComponent.getProps(), HeroProps.class, HeroPropsRepository.class, mapper);
            }
            case ("RPR") -> {
                updateProps(componentData, newComponent.getProps(), RegisterProps.class, RegisterPropsRepository.class, mapper);
            }
            case ("CRP") -> {
                updateProps(componentData, newComponent.getProps(), CardProps.class, CardPropRepository.class, mapper);
            }
            case ("SHP") -> {
                updateProps(componentData, newComponent.getProps(), ScheduleProps.class, SchedulePropRepository.class, mapper);
            }
            case("DIV") -> {
                updateProps(componentData, newComponent.getProps(), DividerProps.class, DividerRepository.class, mapper);
            }
            case ("FPR") -> {
                updateProps(componentData, newComponent.getProps(), FaqProps.class, FaqsPropRepository.class, mapper);
            }
            case("SHC") -> {
                updateProps(componentData, newComponent.getProps(), ShowProps.class, ShowcasePropRepository.class, mapper);
            }
            case("MDA") -> {
                updateProps(componentData, newComponent.getProps(), MediaProps.class, MediaPropsRepository.class, mapper);
            }
            case("BRP") -> {
                updateProps(componentData, newComponent.getProps(), BarProps.class, BarPropRepository.class, mapper);
            }
            case("IMG") -> {
                updateProps(componentData, newComponent.getProps(), ImageProp.class, ImagePropRepository.class, mapper);
            }
            default -> throw new ResponseStatusException(
                    HttpStatus.NOT_ACCEPTABLE, "Incorrect type has been provided!");
        }
        return componentRepository.save(componentData);

    }

    /**
     * Updates the properties of a component.
     *
     * @param componentData the existing component whose properties need to be updated
     * @param props the updated properties data
     * @param propsClass the class representing the type of properties (e.g., TextProps or HeroProps)
     * @param repositoryClass the class representing the repository for the properties
     * @param mapper the ObjectMapper for mapping JSON to objects
     * @param <T> the type of properties
     * @param <R> the type of JpaRepository for the properties
     */
    private <T, R extends JpaRepository<T, Long>> void updateProps(Components componentData, Object props, Class<T> propsClass, Class<R> repositoryClass, ObjectMapper mapper) {
        T update = mapper.convertValue(props, propsClass);
        JpaRepository<T, Long> repository = applicationContext.getBean(repositoryClass);
        Optional<T> fetch = repository.findById(((Prop) update).getId());
        if (fetch.isPresent()) {
            T current = fetch.get();
            BeanUtils.copyProperties(update, current, "id");
            repository.save(current);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Incorrect ID Provided");
        }
    }

    /**
     * Deletes a component by its ID.
     *
     * @param id the ID of the component to delete
     * @throws ResponseStatusException if the component is not found by the given ID
     */
    public void deleteComponent (Long id) {
        Optional<Components> component = componentRepository.findById(id);
        if (component.isPresent()) {
            switch (component.get().getType()) {
                case ("TPR") -> {
                    deleteProps(TextPropsRepository.class, component.get().getProp().getId());
                }
                case ("HRP") -> {
                    deleteProps(HeroPropsRepository.class, component.get().getProp().getId());
                }
                case ("RPR") -> {
                    deleteProps(RegisterPropsRepository.class, component.get().getProp().getId());
                }
                case ("CRP") -> {
                    deleteProps(CardPropRepository.class, component.get().getProp().getId());
                }
                case ("SHP") -> {
                    deleteProps(SchedulePropRepository.class, component.get().getProp().getId());
                }
                case("DIV") -> {
                    deleteProps(DividerRepository.class, component.get().getProp().getId());
                }
                case ("FPR") -> {
                    deleteProps(FaqsPropRepository.class, component.get().getProp().getId());
                }
                case("SHC") -> {
                    deleteProps(ShowcasePropRepository.class, component.get().getProp().getId());
                }
                case("MDA") -> {
                    deleteProps(MediaPropsRepository.class, component.get().getProp().getId());
                }
                case("BRP") -> {
                    deleteProps(BarPropRepository.class, component.get().getProp().getId());
                }
                case("IMG") -> {
                    deleteProps(ImagePropRepository.class, component.get().getProp().getId());
                }
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Component with not found, ID:" + id);
        }
        componentRepository.deleteById(id);
    }

    /**
     * Deletes properties of a component by their ID.
     *
     * @param repositoryClass the class representing the repository for the properties
     * @param id the ID of the properties to delete
     * @throws ResponseStatusException if the properties are not found by the given ID
     */
    private <T, R extends JpaRepository<T, Long>> void deleteProps(Class<R> repositoryClass, Long id) {
        JpaRepository<T, Long> repository = applicationContext.getBean(repositoryClass);
        try {
            repository.deleteById(id);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Incorrect ID Provided");
        }
    }

    /**
     * Creates properties for a component based on type.
     *
     * @param type the type of properties to create (e.g., "TPR" for TextProps)
     * @param prop the data representing the properties
     * @param mapper the ObjectMapper for mapping JSON to objects
     * @return the created properties
     * @throws ResponseStatusException if an incorrect type is provided
     */
    public Prop createComponentProp (String type, Object prop, ObjectMapper mapper) {
        switch (type) {
            case ("TPR") -> {
                return saveComponentProp(prop, mapper, TextProps.class, TextPropsRepository.class);
            }
            case ("HRP") -> {
                return saveComponentProp(prop, mapper, HeroProps.class, HeroPropsRepository.class);
            }
            case ("RPR") -> {
                return saveComponentProp(prop, mapper, RegisterProps.class, RegisterPropsRepository.class);
            }
            case ("CRP") -> {
                return saveComponentProp(prop, mapper, CardProps.class, CardPropRepository.class);
            }
            case ("SHP") -> {
                return saveComponentProp(prop, mapper, ScheduleProps.class, SchedulePropRepository.class);
            }
            case("DIV") -> {
                return saveComponentProp(prop, mapper, DividerProps.class, DividerRepository.class);
            }
            case ("FPR") -> {
                return saveComponentProp(prop, mapper, FaqProps.class, FaqsPropRepository.class);
            }
            case("SHC") -> {
                return saveComponentProp(prop, mapper, ShowProps.class, ShowcasePropRepository.class);
            }
            case("MDA") -> {
                return saveComponentProp(prop, mapper, MediaProps.class, MediaPropsRepository.class);
            }
            case("BRP") -> {
                return saveComponentProp(prop, mapper, BarProps.class, BarPropRepository.class);
            }
            case("IMG") -> {
                return saveComponentProp(prop, mapper, ImageProp.class, ImagePropRepository.class);
            }
            default -> throw new ResponseStatusException(
                    HttpStatus.NOT_ACCEPTABLE, "Incorrect type has been provided!");
        }
    }

    /**
     * Saves properties for a component.
     *
     * @param prop the data representing the properties
     * @param mapper the ObjectMapper for mapping JSON to objects
     * @param propClass the class representing the type of properties
     * @param repositoryClass the class representing the repository for the properties
     * @return the saved properties
     */
    private <T, R extends JpaRepository<T, Long>> T saveComponentProp(Object prop, ObjectMapper mapper, Class<T> propClass, Class<R> repositoryClass) {
        JpaRepository<T, Long> repository = applicationContext.getBean(repositoryClass);
        T componentProp = mapper.convertValue(prop, propClass);
        return repository.save(componentProp);
    }

}
