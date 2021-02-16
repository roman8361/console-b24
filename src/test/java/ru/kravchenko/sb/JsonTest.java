package ru.kravchenko.sb;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import ru.kravchenko.sb.entity.*;
import ru.kravchenko.sb.entity.request.DealsRequest;

import java.io.IOException;
import java.util.*;

public class JsonTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void objectToJson() throws JsonProcessingException {
        System.out.println(objectMapper.writeValueAsString(getUser()));
    }

    @Test
    public void objectToJsonDeal() throws JsonProcessingException {
        System.out.println(objectMapper.writeValueAsString(getDeals()));
//        System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(getDeals()));
    }

    @Test
    public void jsonToDeal() throws JsonProcessingException {
        final String json = "{\"result\":[{\"id\":\"777\",\"description\":\"IHA MAN LALAL\"},{\"id\":\"888\",\"description\":\"Mister TROLOLOLO\"}],\"next\":1234,\"total\":4321,\"time\":{\"1111\":{\"id\":\"0909\",\"time\":\"Any time\"},\"2222\":{\"id\":\"77889898\",\"time\":\"What time is it?\"}}}\n";
        Deals deals = objectMapper.readValue(json, Deals.class);
        System.out.println(deals);
    }

    @Test
    public void jsonToObject() throws IOException {
        final String json = "{\"id\":\"3afcf050-1941-457b-b06e-c0f3bcad518e\",\"name\":\"Ivano22\",\"age\":18,\"birthday\":null}\n";
        User user = objectMapper.readValue(json, User.class);
        System.out.println(user);
        Assert.assertNotNull(user);
    }

    @Test
    public void getRequest() {
        String url = "https://potolki.bitrix24.ru/rest/1/zm6bn1pw6el65nxa/crm.activity.list.json?OWNER_TYPE_ID=1&OWNER_ID=3340&next=250";
        RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        String result1 = restTemplate.getForObject("https://example.com/hotels/{hotel}/bookings/{booking}", String.class, "42", "21");

        String result =  restTemplate.getForObject(
                "https://potolki.bitrix24.ru/rest/1/zm6bn1pw6el65nxa/crm.activity.list.json?OWNER_TYPE_ID=1&OWNER_ID=3340&next=250{}", String.class, "");

        System.out.println(result);
    }

    @Test
    public void sendSimplePostRequest() throws JsonProcessingException {
        String url = "https://hookb.in/ggRxrd1Wm7FwWW1NZQ7m";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity entity = new HttpEntity(objectMapper.writeValueAsString(getDeals()), httpHeaders);
        String result = restTemplate.postForObject(url, entity, String.class);

        System.out.println(result);
    }

    @Test
    public void sendPostRequestToB24() throws JsonProcessingException {
        DealsRequest dealsRequest = new DealsRequest();
        dealsRequest.setOrder(getOrderMap());
        dealsRequest.setFilter(getFilterMap());
        dealsRequest.setSelect(getSelectList());

        String url = "https://hookb.in/ggRxrd1Wm7FwWW1NZQ7m";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity entity = new HttpEntity(objectMapper.writeValueAsString(dealsRequest), httpHeaders);
        String result = restTemplate.postForObject(url, entity, String.class);

        System.out.println(result);
    }

    private Map<String, Integer> getFilterMap() {
        return Map.of(">" + Select.STAGE_ID.name(),50);
    }

    private Map<String, String> getOrderMap() {
        return Map.of(Select.STAGE_ID.name(),"ASC");
    }

    private List<String> getSelectList() {
        return List.of(Select.ID.name(), Select.TITLE.name(), Select.STAGE_ID.name(),
                Select.PROBABILITY.name(), Select.OPPORTUNITY.name(), Select.CURRENCY_ID.name());
    }

    private Deals getDeals() {
        Deals deals = new Deals();
        deals.setNext(1234);
        deals.setTotal(4321);
        deals.setResult(getResultList());
        deals.setTime(getTime());

        return deals;
    }

    private List<Result> getResultList() {
        Result result1 = new Result();
        result1.setId("777");
        result1.setDescription("IHA MAN LALAL");

        Result result2 = new Result();
        result2.setId("888");
        result2.setDescription("Mister TROLOLOLO");

        List<Result> resultList = Arrays.asList(result1, result2);
        return resultList;
    }

    private Map<String, TimeItems> getTime() {
        TimeItems timeItems1 = new TimeItems();
        timeItems1.setId("0909");
        timeItems1.setTime("Any time");

        TimeItems timeItems2 = new TimeItems();
        timeItems2.setId("77889898");
        timeItems2.setTime("What time is it?");

        Map<String, TimeItems> resultMap = new HashMap<>();
        resultMap.put("1111", timeItems1);
        resultMap.put("2222", timeItems2);

        return resultMap;
    }

    private User getUser() {
        return new User("Ivano", 18, new Date());
    }

//        @Test

//    {"id":"3afcf050-1941-457b-b06e-c0f3bcad518e","name":"Ivano","age":18,"birthday":"2021-02-10T15:21:40.399Z"}
//        public void sendPostRequest() throws IOException {
//            String url = "http://localhost:8080/hello";
////        String url = "https://hookb.in/zro12Q1y9EuykkGKwaPJч";
//            RestTemplate restTemplate = new RestTemplate();
//            HttpHeaders httpHeaders = new HttpHeaders();
//            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
//
//            RequestMobileDto requestMobileDto = new RequestMobileDto();
//            requestMobileDto.setCodeActivate("2222");
//            requestMobileDto.setToken("1111");
//
//            HttpEntity entity = new HttpEntity(objectMapper.writeValueAsString(requestMobileDto), httpHeaders);
//            String result = restTemplate.postForObject(url, entity, String.class);
//
//            ResponseMobileDto responseMobileDto = objectMapper.readValue(result, ResponseMobileDto.class);
//            System.out.println(responseMobileDto.getMetaMessage());
//            System.out.println(responseMobileDto.getFirstName());
//        }
//
//        @Test
//        public void sendPostRequestToActivateCodeUrl() throws IOException {
//            String url = "http://localhost:8080/activateCodeUrl";
////        String url = "https://hookb.in/zro12Q1y9EuykkGKwaPJч";
//            RestTemplate restTemplate = new RestTemplate();
//            HttpHeaders httpHeaders = new HttpHeaders();
//            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
//
//            RequestMobileDto requestMobileDto = new RequestMobileDto();
//            requestMobileDto.setCodeActivate("2222");
//            requestMobileDto.setToken("1111");
//
//            HttpEntity entity = new HttpEntity(objectMapper.writeValueAsString(requestMobileDto), httpHeaders);
//            String result = restTemplate.postForObject(url, entity, String.class);
//
//            ResponseMobileDto responseMobileDto = objectMapper.readValue(result, ResponseMobileDto.class);
//            System.out.println(responseMobileDto.getMetaMessage());
//            System.out.println(responseMobileDto.getFirstName());
//        }
//
//        private GuestDto getGuestDto() {
//            GuestDto result = new GuestDto();
//            result.setFirstName("Андрей");
//            result.setSecondName("Васильевич");
//            result.setSurName("Чернец");
//            result.setArrivalDate(new GregorianCalendar(2020, Calendar.JULY, 11).getTime());
//            result.setDepartureDate(new GregorianCalendar(2020, Calendar.JULY, 15).getTime());
//            result.setRoomNumber("1");
//            return result;
//        }

}
