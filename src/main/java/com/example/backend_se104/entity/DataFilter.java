package com.example.backend_se104.entity;

import lombok.Data;

import java.util.List;

@Data
public class DataFilter {
    private List<String> tacgia;
    private List<Integer> gia;
    private List<String> namsb;
}
