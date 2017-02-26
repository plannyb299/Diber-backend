package com.github.handioq.diber.repository;

import com.github.handioq.diber.model.entity.Address;
import com.github.handioq.diber.model.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface AddressRepository extends CrudRepository<Address, Long> {

    List<Address> findByUserId(long id);

    Address findByName(String name);

    Address findByNameAndUser(String name, User user);

}