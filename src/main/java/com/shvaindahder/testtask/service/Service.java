package com.shvaindahder.testtask.service;

public interface Service <DTO, T> {
    DTO toDTO(T object);
    T fromDTO(DTO dto);
}
