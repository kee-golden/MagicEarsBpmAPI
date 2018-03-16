package com.magicears.bpm.helper.mybatis;

import java.util.List;

/**
 * Created by wangfei on 2016/7/1.
 */
public interface BaseMapper<E> {
    List<E> findAll();

    E find(Long id);

    int insert(E e);

    int update(E e);

    int delete(Long id);
}
