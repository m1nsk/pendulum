package com.minsk.pendulum.repository.dataJpa;

import com.minsk.pendulum.model.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface CrudImageRepository extends JpaRepository<ImageEntity, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM ImageEntity i WHERE i.id=:id")
    int delete(@Param("id") int id);

    @Modifying
    @Transactional
    @Query("GET FROM ImageEntity i WHERE i.hash=:hash")
    ImageEntity findByHash(@Param("hash") int hash);

}

