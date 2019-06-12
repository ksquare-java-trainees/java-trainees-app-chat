package com.ksquareinc.chat.service;

import java.io.Serializable;
import java.util.List;

public interface GenericService<K extends Serializable> {

	K create(K k);

	K findOne(long id);

	List<K> findAll();

	K update(K k);

	void deleteById(long id);

	void delete(K k);
}