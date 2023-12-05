package com.hng.service;

import java.util.List;

public interface IGenericService<T, E> {
   List<T> findAll();
   T findById(E id);
   void save(T t);
   int delete(E id);
}
