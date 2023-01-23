package com.socks.course3.service;

import com.socks.course3.model.*;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

@Service
public interface SockService {

    @Nullable
    void add(SockItem sockItem);

    int get(String color,
            int size,
            int cottonMin,
            int cottonMax);

    void put(SockItem sockItem);

    Map<Socks, Integer> getAll();
}
