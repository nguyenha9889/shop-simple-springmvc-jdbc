package com.hng.dao;

import java.util.List;

public interface IGenericDao<T, E> {
   default List<T> findAll() {
      return null;
   }

   default T findById(E id) {
      return null;
   }

   default int save(T t) {
      return 0;
   }

   default int delete(E id) {
      return 0;
   }
}
