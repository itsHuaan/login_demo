package org.example.login_demo.service;

import java.util.List;

public interface IBaseService<T, U, K> {
    List<T> getAll();
    T getById(K id);
    int save(U u);
    int delete(K id);
}
