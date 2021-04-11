package ru.netology;

import lombok.Data;
import lombok.Getter;

import lombok.RequiredArgsConstructor;

    @Getter
    @Data
    @RequiredArgsConstructor

    public class DataSet {
        private final String name;
        private final String city;
        private final String phone;

    }

