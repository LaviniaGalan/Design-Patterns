package com.dp.repository;

public interface IRepository<T> {
    Iterable<T> getAll();
}
