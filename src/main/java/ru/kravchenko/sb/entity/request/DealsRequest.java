package ru.kravchenko.sb.entity.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * @author Roman Kravchenko
 */

@Getter
@Setter
public class DealsRequest {

    private Map<String, String> order;

    private List<String> select;

    private Map<String, Integer> filter;

}
