package com.hng.service;

import java.util.List;

public interface IGenericService<T, E> {
   default List<T> findAll() {
      return null;
   }

   default T findById(E id) {
      return null;
   }

   default void save(T t) {
   }

   default int delete(E id) {
      return 0;
   }
}
