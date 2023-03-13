package com.demo_collab.service.Province;

import com.demo_collab.model.Province;
import com.demo_collab.service.IGeneric;

import java.util.List;

public interface IProvince extends IGeneric<Province> {
    @Override
    List<Province> findAll();

    @Override
    void save(Province customer);

    @Override
    Province findById(int id);

    @Override
    void update(int id, Province customer);

    @Override
    void remove(int id);
}
