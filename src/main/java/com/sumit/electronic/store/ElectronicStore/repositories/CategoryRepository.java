package com.sumit.electronic.store.ElectronicStore.repositories;

import com.sumit.electronic.store.ElectronicStore.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,String> {
}
