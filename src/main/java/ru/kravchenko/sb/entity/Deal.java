package ru.kravchenko.sb.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class Deal {

    @JsonProperty("CATEGORY_ID")
    private Integer CATEGORY_ID;

    @JsonProperty("CLOSED")
    private String CLOSED;

    @JsonProperty("ASSIGNED_BY_ID")
    private String ASSIGNED_BY_ID;

    @JsonProperty("IS_REPEATED_APPROACH")
    private String IS_REPEATED_APPROACH;

    @JsonProperty("UTM_SOURCE")
    private String UTM_SOURCE;

    @JsonProperty("COMMENTS")
    private String COMMENTS;

    @JsonProperty("ADDITIONAL_INFO")
    private String ADDITIONAL_INFO;

    @JsonProperty("IS_RECURRING")
    private String IS_RECURRING;

    @JsonProperty("LEAD_ID")
    private String LEAD_ID;

    @JsonProperty("UTM_MEDIUM")
    private String UTM_MEDIUM;

    @JsonProperty("UTM_CONTENT")
    private String UTM_CONTENT;

    @JsonProperty("LOCATION_ID")
    private String LOCATION_ID;

    @JsonProperty("ID")
    private String ID;

    @JsonProperty("BEGINDATE")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date BEGINDATE;

    @JsonProperty("CLOSEDATE")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date CLOSEDATE;

    @JsonProperty("STAGE_ID")
    private String STAGE_ID;

    @JsonProperty("IS_RETURN_CUSTOMER")
    private String IS_RETURN_CUSTOMER;

    @JsonProperty("COMPANY_ID")
    private String COMPANY_ID;

    @JsonProperty("QUOTE_ID")
    private String QUOTE_ID;

    @JsonProperty("DATE_CREATE")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date DATE_CREATE;

    @JsonProperty("TAX_VALUE")
    private String TAX_VALUE;

    @JsonProperty("SOURCE_ID")
    private String SOURCE_ID;

    @JsonProperty("ORIGIN_ID")
    private String ORIGIN_ID;

    @JsonProperty("IS_NEW")
    private String IS_NEW;

    @JsonProperty("ORIGINATOR_ID")
    private String ORIGINATOR_ID;

    @JsonProperty("STAGE_SEMANTIC_ID")
    private String STAGE_SEMANTIC_ID;

    @JsonProperty("UTM_TERM")
    private String UTM_TERM;

    @JsonProperty("UTM_CAMPAIGN")
    private String UTM_CAMPAIGN;

    @JsonProperty("TYPE_ID")
    private String TYPE_ID;

    @JsonProperty("PROBABILITY")
    private String PROBABILITY;

    @JsonProperty("MODIFY_BY_ID")
    private String MODIFY_BY_ID;

    @JsonProperty("DATE_MODIFY")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
    private Date DATE_MODIFY;

    @JsonProperty("SOURCE_DESCRIPTION")
    private String SOURCE_DESCRIPTION;

    @JsonProperty("TITLE")
    private String TITLE;

    @JsonProperty("CONTACT_ID")
    private String CONTACT_ID;

    @JsonProperty("OPPORTUNITY")
    private Double OPPORTUNITY;

    @JsonProperty("CREATED_BY_ID")
    private String CREATED_BY_ID;

    @JsonProperty("IS_MANUAL_OPPORTUNITY")
    private String IS_MANUAL_OPPORTUNITY;

    @JsonProperty("OPENED")
    private String OPENED;

}
