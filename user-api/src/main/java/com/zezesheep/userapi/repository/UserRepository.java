package com.zezesheep.userapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zezesheep.userapi.model.User;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public User findByCpf(String cpf);

    public List<User> queryByNomeLike(String nome);
    
}
