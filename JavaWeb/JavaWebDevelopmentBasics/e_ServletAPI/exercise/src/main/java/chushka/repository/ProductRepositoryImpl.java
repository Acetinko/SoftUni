package chushka.repository;

import chushka.domain.entities.Product;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

import static chushka.utils.Constants.NAME_PROPERTY;

public class ProductRepositoryImpl implements ProductRepository {

    private EntityManager entityManager;

    public ProductRepositoryImpl() {
        this.entityManager = Persistence.
                createEntityManagerFactory("chushka")
                .createEntityManager();
    }

    @Override
    public Product save(Product entity) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(entity);
        this.entityManager.getTransaction().commit();
        return entity;
    }

    @Override
    public Product findById(String id) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
        Root<Product> root = criteriaQuery.from(Product.class);
        criteriaQuery.where(criteriaBuilder.equal(root.get("id"), id));
        TypedQuery<Product> q = entityManager.createQuery(criteriaQuery);
        return q.getSingleResult();

    }

    @Override
    public List<Product> findAll() {
        CriteriaQuery<Product> query = entityManager.getCriteriaBuilder().createQuery(Product.class);
        query.from(Product.class);

        return entityManager.createQuery(query)
                .getResultList();
    }

    @Override
    public Product findByName(String name){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
        Root<Product> root = criteriaQuery.from(Product.class);
        criteriaQuery.where(criteriaBuilder.equal(root.get(NAME_PROPERTY), name));
        TypedQuery<Product> q = entityManager.createQuery(criteriaQuery);
        return q.getSingleResult();
    }
}
