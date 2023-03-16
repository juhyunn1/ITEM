package com.example.shop.repository;

import com.example.shop.domain.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class OrderRepository {
    private final EntityManager entityManager;

    @Autowired
    public OrderRepository(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public void save(Orders order){
        entityManager.persist(order);
    }
    
    public Orders findByOne(Long id){
        return Optional.ofNullable(entityManager.find(Orders.class, id)).get();
    }

    public List<Orders> findAll(String name){
        return entityManager.createQuery("select o from Orders o join o.member m where m.name = :name", Orders.class)
                .setParameter("name", name)
                .setMaxResults(100)
                .getResultList();
    }
}
