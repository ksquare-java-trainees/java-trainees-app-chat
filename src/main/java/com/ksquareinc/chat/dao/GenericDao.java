package com.ksquareinc.chat.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

public interface GenericDao <K extends Serializable> {
	
    K create(K k);

    K findOne(long id);

    List<K> findAll();

    @Transactional
    K update(K k);

    @Transactional
    void delete(K k);

    @Transactional
    void deleteById(long id);
}