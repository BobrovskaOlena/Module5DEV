package com.example.module5dev.forInsert;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Worker {
    Long id;
    String name;
    LocalDate birthday;
    int salary;
    String levels;
}
