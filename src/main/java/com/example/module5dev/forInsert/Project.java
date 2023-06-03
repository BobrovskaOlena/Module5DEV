package com.example.module5dev.forInsert;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    Long id;
    Long client_id;
    LocalDate start_date;
    LocalDate finish_date;
}
