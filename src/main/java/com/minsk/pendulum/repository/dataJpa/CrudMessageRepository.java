package com.minsk.pendulum.repository.dataJpa;

import com.minsk.pendulum.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface CrudMessageRepository extends JpaRepository<Message, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Message m WHERE m.id=:id AND m.user.id=:userId")
    int delete(@Param("id") int id, @Param("userId") int userId);

    @Query("SELECT m FROM Message m WHERE m.user.id=:userId order by m.id desc")
    List<Message> getAll(@Param("userId") int userId);
}

