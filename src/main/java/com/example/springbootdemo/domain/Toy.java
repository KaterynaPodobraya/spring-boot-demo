package com.example.springbootdemo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
@AllArgsConstructor
public class Toy {
    String name;
    String color;
    String size;
    Integer age;
}
