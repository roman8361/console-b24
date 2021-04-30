package ru.kravchenko.sb.entity.request;

import lombok.Data;
import ru.kravchenko.sb.entity.Deal;

import java.util.List;
import java.util.Map;

@Data
public class DealsAddRequest {

    Map<String, String> fields;

}
