package com.vn.equipment_manager.repository;

import com.vn.equipment_manager.entity.Storage;
import com.vn.equipment_manager.model.StorageStatistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "storageRepository")
public interface StorageRepository extends JpaRepository<Storage, Long> {
    Storage findStorageById(long id);

    @Query("SELECT new com.vn.equipment_manager.model.StorageStatistic(s.id, s.name, " +
            "       COUNT(DISTINCT e.type.id), " +
            "       COUNT(e)) " +
            "FROM Equipment e " +
            "RIGHT JOIN Storage s ON e.storage.id = s.id " +
            "GROUP BY s.id, s.name")
    List<StorageStatistic> getStorageStatistics();
}
