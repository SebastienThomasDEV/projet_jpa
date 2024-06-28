package imdb;

import jakarta.persistence.EntityManager;

import java.util.List;

public abstract class AbstractRepository<T> {

    private EntityManager entityManager;
    private Class<T> entityClass;

    public AbstractRepository(EntityManager entityManager, Class<T> entityClass) {
        this.entityManager = entityManager;
        this.entityClass = entityClass;
    }

    public void save(T entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
    }

    public void delete(T entity) {
        entityManager.getTransaction().begin();
        entityManager.remove(entity);
        entityManager.getTransaction().commit();
    }

    public T update(T entity) {
        entityManager.getTransaction().begin();
        T updatedEntity = entityManager.merge(entity);
        entityManager.getTransaction().commit();
        return updatedEntity;
    }

    public T findById(int id) {
        return entityManager.find(entityClass, id);
    }

    public List<T> findAll() {
        return entityManager.createQuery("SELECT e FROM " + entityClass.getSimpleName() + " e", entityClass)
                .getResultList();
    }
}