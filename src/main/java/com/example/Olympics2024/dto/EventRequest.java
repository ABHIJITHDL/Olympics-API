package com.example.Olympics2024.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventRequest {
    private int events;
    private int athletes;
    private int participants;
    private String country;
}
