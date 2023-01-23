package com.socks.course3.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.socks.course3.exception.IncorrectParamException;
import com.socks.course3.model.*;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;


import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class SockServiceImpl implements SockService {

    private static Map<Socks, Integer> SOCKS = new HashMap<>();
    private FileService fileService;
    public SockServiceImpl(FileService fileService) {
        this.fileService = fileService;
    }
    @PostConstruct
    private void init() {
        try {
            readFromFile();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    @Override
    @Nullable
    public void add(SockItem sockItem) {
       if (isNoValid(sockItem)){
           throw new IncorrectParamException();
       }
       Socks socks = sockItem.getSocks();
       if (SOCKS.containsKey(socks)){
           SOCKS.replace(socks, SOCKS.get(socks) + sockItem.getQuantity());
       } else {
           SOCKS.put(socks, sockItem.getQuantity());
           saveFile();
       }
    }

    @Override
    public int get(String color,
                    int size,
                    int cottonMin,
                    int cottonMax) {
        SocksColor c = SocksColor.parse(color);
        SocksSize s = SocksSize.parse(size);
        if (Objects.isNull(c) || Objects.isNull(s) || cottonMin >= cottonMax ||
                cottonMin < 0 || cottonMax > 100) {
            throw new IncorrectParamException();
        }
        for (Map.Entry<Socks, Integer> entry : SOCKS.entrySet()) {
            Socks socks = entry.getKey();
            int available = entry.getValue();
            if (socks.getColor() == c && socks.getSize() == s && socks.getCotton() >= cottonMin &&
            socks.getCotton() <= cottonMax) {
                return available;
            }
        }
        return 0;
    }
    @Override
    public void put(SockItem sockItem) {
        Socks socks = sockItem.getSocks();
        if (!SOCKS.containsKey(socks) || isNoValid(sockItem)){
            throw new IncorrectParamException();
        }
        int available = SOCKS.get(socks);
        int result = available - sockItem.getQuantity();
        saveFile();
        if (result < 0){
            throw new IncorrectParamException();
        }
        SOCKS.replace(socks, result);
        saveFile();
    }

    @Override
    public Map<Socks, Integer> getAll() {
        return new HashMap<>(SOCKS);
    }

    public void saveFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(SOCKS);
            fileService.saveFileSocks(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    private void readFromFile() {
        try {
            String json = fileService.readFileSocks();
            SOCKS = new ObjectMapper().readValue(json, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private boolean isNoValid(SockItem sockItem){
        Socks socks = sockItem.getSocks();
        boolean b = (socks.getCotton() < 0) || (socks.getCotton() > 100) ||
                (sockItem.getQuantity() <= 0);
        return b;
    }
}
