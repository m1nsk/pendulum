package com.minsk.pendulum.repository.dataJpa;

import com.minsk.pendulum.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface CrudDeviceRepository extends JpaRepository<Device, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Device d WHERE d.id=:id AND d.user.id=:userId")
    int delete(@Param("id") int id, @Param("userId") int userId);

    @Override
    @Transactional
    Device save(Device item);

    @Query("SELECT d FROM Device d WHERE d.user.id=:userId ORDER BY d.serial desc")
    List<Device> getAll(@Param("userId") int userId);
}

