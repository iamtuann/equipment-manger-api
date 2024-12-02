package com.vn.equipment_manager.repository;

import com.vn.equipment_manager.entity.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(value = "storageRepository")
public interface StorageRepository extends JpaRepository<Storage, Long> {
    Storage findStorageById(long id);
}
