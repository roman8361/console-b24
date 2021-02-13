package ru.kravchenko.sb.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class AbstractEntity {

    protected String id = UUID.randomUUID().toString();

}
