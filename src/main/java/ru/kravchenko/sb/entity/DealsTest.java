package ru.kravchenko.sb.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class DealsTest {

    private List<Result> result;

    private Integer next;

    private Integer total;

    private Map<String, TimeItems> time;

}
