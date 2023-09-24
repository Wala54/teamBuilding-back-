package com.stage.teamb.services;

import java.util.List;
import java.util.Optional;
public interface GeneriqueService<T> {
    List<T> findAll();
    Optional<T> findOne(Long id);
    T saveOne(T t);

    void deleteOne(Long id);
    T update(T t);
}

