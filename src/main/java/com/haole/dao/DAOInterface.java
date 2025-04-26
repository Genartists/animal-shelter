package com.haole.dao;
import java.util.ArrayList;

public interface DAOInterface<T> {
    // Implement CRUD

     int insert(T t);

     int update(T t, int e);

     int delete(T t);

     ArrayList<T> selectAll(); //read

     T selectById(int id);
}
