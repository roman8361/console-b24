package ru.kravchenko.sb.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
//@JsonIgnoreProperties
public class Deal {

    @JsonProperty("CURRENCY_ID")
    private String currencyId;

//    private Integer CATEGORY_ID;
//    private String CLOSED;
//    private String ASSIGNED_BY_ID;
//    private String IS_REPEATED_APPROACH;
//    private String UTM_SOURCE;
//    private String COMMENTS;
//    private String ADDITIONAL_INFO;
//    private String IS_RECURRING;
//    private String LEAD_ID;
//    private String UTM_MEDIUM;
//    private String UTM_CONTENT;
//    private String LOCATION_ID;
//    private String ID;
//    private Date BEGINDATE;
//    private Date CLOSEDATE;


//    private String TITLE;
//    private String OPPORTUNITY;

}
