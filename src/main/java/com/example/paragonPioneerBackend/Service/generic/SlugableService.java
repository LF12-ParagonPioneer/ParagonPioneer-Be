package com.example.paragonPioneerBackend.Service.generic;

import com.example.paragonPioneerBackend.Entity.abstractEntity.Slugable;
import com.example.paragonPioneerBackend.Exception.EntityExistsException;
import com.example.paragonPioneerBackend.Exception.EntityNotFoundException;
import com.example.paragonPioneerBackend.Exception.ParagonPioneerBeException;
import com.example.paragonPioneerBackend.Repository.SlugableReposetory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

/**
 * Abstract base service class providing CRUD functionalities for entities with slugs.
 * This class encapsulates common service layer operations for entities with slugs, offering a foundation for entity-specific services.
 * It leverages Spring Data JPA repositories for database interactions.
 *
 * @param <Type>       The entity type this service is responsible for. Must extend BaseEntity and implement Slugable.
 * @param <Repository> The JPA repository type used by this service. Must extend SlugableReposetory.
 * @param <Dto>        The data transfer object (DTO) type used for transferring data between processes.
 */
public abstract class SlugableService<Type extends Slugable, Repository extends SlugableReposetory<Type>, Dto> extends BaseService<Type, Repository, Dto> {

    /**
     * Constructs a BaseService with the specified repository.
     *
     * @param repository The repository used for database operations.
     */
    public SlugableService(Repository repository) {
        super(repository);
    }

    /**
     * Retrieves an entity by its slug.
     *
     * @param slug The slug of the entity to retrieve.
     * @return The entity with the specified slug.
     * @throws EntityNotFoundException If no entity with the specified slug is found.
     */
    public Type findBySlug(String slug) throws EntityNotFoundException {
        return repository.findBySlug(slug).orElseThrow(() -> new EntityNotFoundException(repository.getClass().toString(), slug));
    }

    @Override
    public Type post(Dto dto) {
        var found = repository.findBySlug(mapToEntity(dto).getSlug());
        if (found.isPresent()) {
            throw new EntityExistsException(found.get().getSlug(), found.get().getName());
        }
        return super.post(dto);
    }

    /**
     * Retrieves all entities that contain the provided string in their name.
     *
     * @param pageable
     * @param name     The string to be searched for in the names of the entities.
     * @return A Set of entities that contain the provided string in their name.
     */
    public Page<Type> findAllContainingName(Pageable pageable, String name) {
        return repository.findAllByNameContains(pageable, name);
    }

    /**
     * This method is used to find an entity by its ID, slug, or name.
     * It first tries to parse the provided string as a UUID and find the entity by its ID.
     * If the entity is not found by its ID, it tries to find the entity by its slug.
     * If the entity is still not found, it tries to find the entity by its name.
     * If the entity is not found by its ID, slug, or name, it throws an EntityNotFoundException.
     *
     * @param smth The string that represents the ID, slug, or name of the entity.
     * @return The entity if found by its ID, slug, or name. When Multiple entities are found by name, the first one is returned.
     * @throws EntityNotFoundException if the entity is not found by its ID, slug, or name.
     */
    public Type findSmthByIdSlugName(String smth) {
        try {
            // Try to parse the string as a UUID and find the entity by its ID
            return repository.findById(UUID.fromString(smth)).orElseThrow(() -> new EntityNotFoundException("Recipe", smth));
        } catch (IllegalArgumentException e) {
            // If the string is not a valid UUID, try to find the entity by its slug or name
            return repository.findBySlug(smth).orElse(
                    repository.findAllByNameContains(Pageable.unpaged(), smth).stream().findFirst()
                            .orElseThrow(() -> new EntityNotFoundException("Recipe", smth))
            );
        }
    }

    /**
     * This method is used to retrieve an entity by its ID, IRI, or slug.
     * It first tries to retrieve the entity by its ID or IRI using the inherited getByIdOrIri method.
     * If this fails (i.e., if an exception is thrown), it assumes the provided string is a slug.
     * It then tries to retrieve the entity by its slug.
     * If the entity is not found by its slug, it throws an EntityNotFoundException.
     *
     * @param idOrIri The ID, IRI, or slug of the entity to retrieve.
     * @return The retrieved entity.
     * @throws ParagonPioneerBeException if the entity with the provided ID, IRI, or slug is not found in the repository.
     */
    public Type getByIdIriOrSlug(String idOrIri) throws ParagonPioneerBeException {
        try {
            // Try to retrieve the entity by its ID or IRI
            return super.getByIdOrIri(idOrIri);
        } catch (Exception e) {
            try {
                // If the ID or IRI retrieval fails, try to retrieve the entity by its slug
                return repository.findBySlug(idOrIri).orElseThrow(() -> new EntityNotFoundException("Recipe", idOrIri));
            } catch (Exception e2) {
                // If the slug retrieval also fails, throw an EntityNotFoundException
                throw new EntityNotFoundException("Recipe", idOrIri);
            }
        }
    }
}