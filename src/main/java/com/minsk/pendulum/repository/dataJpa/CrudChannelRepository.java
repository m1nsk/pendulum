package com.minsk.pendulum.repository.dataJpa;

import com.minsk.pendulum.model.Channel;
import com.minsk.pendulum.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface CrudChannelRepository extends JpaRepository<Channel, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Channel c WHERE c.id=:id AND c.user.id=:userId")
    int delete(@Param("id") int id, @Param("userId") int userId);

    @Override
    @Transactional
    Channel save(Channel item);

    @Query("SELECT c FROM Channel c WHERE c.user.id=:userId order by c.id desc")
    List<Channel> getAll(@Param("userId") int userId);
}

