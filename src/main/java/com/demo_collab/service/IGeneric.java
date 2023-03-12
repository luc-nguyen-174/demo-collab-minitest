package com.demo_collab.service;

import java.util.List;

public interface IGeneric<T> {
    List<T> findAll();

    void save(T user);

    T findById(int id);

    boolean update(int id, T user);

    boolean remove(int id);
}
