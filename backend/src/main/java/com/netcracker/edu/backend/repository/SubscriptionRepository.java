package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.entity.Subscription;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends CrudRepository<Subscription, Long> {
    Iterable<Subscription> findAll(Pageable pageable);
    Iterable<Subscription> findByOwnerId(Long id);
    Page<Subscription> findByNameContaining(String name, Pageable pageable); //%name% т.е. ищем имя которое содержит наш запрос
    Page<Subscription> findByCategoryId(Long id, Pageable pageable);
//    @Query(value = "SELECT * FROM subscription WHERE subscription.name = ?1", nativeQuery = true)
//    Iterable<Subscription>test(String name);
}
