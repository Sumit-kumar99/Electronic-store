package com.sumit.electronic.store.ElectronicStore.repositories;

import com.sumit.electronic.store.ElectronicStore.entities.Cart;
import com.sumit.electronic.store.ElectronicStore.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart,String> {
    Optional<Cart> findByUser(User user);
}
