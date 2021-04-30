package ru.kravchenko.sb;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import ru.kravchenko.sb.entity.*;
import ru.kravchenko.sb.entity.request.DealsAddRequest;
import ru.kravchenko.sb.entity.request.DealsRequest;
import ru.kravchenko.sb.utils.Utils;
import ru.qatools.json2pojo.beans.Any;
import ru.qatools.json2pojo.beans.DealB24;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static org.junit.Assert.assertThat;

public class JsonTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final String urlHookb = "https://hookb.in/JKMVpJxeexTJPPWVO11y";

    private final String webhook = "https://potolki.bitrix24.ru/rest/1/zm6bn1pw6el65nxa/";

    @Test
    public void objectToJson() throws JsonProcessingException {
        System.out.println(objectMapper.writeValueAsString(getUser()));
//        System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(getUser()));
    }

    @Test
    public void jsonToUser() throws JsonProcessingException {
        String json = "{\"id\":\"702a812f-134e-468e-ada0-6fc31d101921\",\"name\":\"Ivano\",\"age\":18,\"birthday\":\"2021-02-16T12:42:34.788Z\"}";
        User user = objectMapper.readValue(json, User.class);
        System.out.println(user);
    }

    @Test
    public void jsonToUserFromFile() throws IOException {
        String json = Utils.readFile(getClass().getResourceAsStream("/ex5.json"));
        User user = objectMapper.readValue(json, User.class);
        System.out.println(user);
    }

    @Test
    public void objectToJsonDeal() throws JsonProcessingException {
        System.out.println(objectMapper.writeValueAsString(getDeals()));
//        System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(getDeals()));
    }

    @Test
    public void jsonToDealTest() throws JsonProcessingException {
        final String json = "{\"result\":[{\"id\":\"777\",\"description\":\"IHA MAN LALAL\"},{\"id\":\"888\",\"description\":\"Mister TROLOLOLO\"}],\"next\":1234,\"total\":4321,\"time\":{\"1111\":{\"id\":\"0909\",\"time\":\"Any time\"},\"2222\":{\"id\":\"77889898\",\"time\":\"What time is it?\"}}}\n";
        DealsTest dealsTest = objectMapper.readValue(json, DealsTest.class);
        System.out.println(dealsTest);
    }

    @Test
    public void jsonToDeal() throws IOException {
        String json = Utils.readFile(getClass().getResourceAsStream("/ex3.json"));
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES); //TODO настройка игнорирует мапинг полей, которых нет в DTO
        Deal deal = objectMapper.readValue(json, Deal.class);
        System.out.println(deal.getOPPORTUNITY());
    }

    @Test
    public void jsonToDealsList() throws IOException {
        String json = Utils.readFile(getClass().getResourceAsStream("/ex1.json"));
        ResponseResultDeals resultDeals = getDealFromJson(json);
        System.out.println(resultDeals);
    }

    @Test
    public void jsonToAny() throws JsonProcessingException {
         String json = "{\n" +
                "  \"bounce\": {\n" +
                "    \"final-recipient\": \"<email>\",\n" +
                "    \"status\": \"<cod2222e>\",\n" +
                "    \"type\": \"failed\"\n" +
                "  }\n" +
                "}";
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES); //TODO настройка игнорирует мапинг полей, которых нет в DTO
        Any any = objectMapper.readValue(json, Any.class);
        System.out.println(any.getBounce().getStatus());

    }

    @Test
    public void jsonToAnyFromFile() throws IOException {
        String json = Utils.readFile(getClass().getResourceAsStream("/any.json"));
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        Any any = objectMapper.readValue(json, Any.class);
        System.out.println(any.getBounce().getStatus());
    }

    @Test
    public void jsonToDealB24FromFile() throws IOException {
        String json = Utils.readFile(getClass().getResourceAsStream("/dealB24.json"));
        Gson gson = new Gson();
        DealB24 dealB24 = gson.fromJson(json, DealB24.class);
        System.out.println(dealB24.getResult().getTITLE());
    }


    private ResponseResultDeals getDealFromJson(String json) throws JsonProcessingException {
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES); //TODO настройка игнорирует мапинг полей, которых нет в DTO
        return objectMapper.readValue(json, ResponseResultDeals.class);
    }

    @Test
    public void jsonToObject() throws IOException {
        final String json = "{\"id\":\"3afcf050-1941-457b-b06e-c0f3bcad518e\",\"name\":\"Ivano22\",\"age\":18,\"birthday\":null}\n";
        User user = objectMapper.readValue(json, User.class);
        System.out.println(user);
        Assert.assertNotNull(user);
    }

    @Test
//    @Ignore
    public void getRequest() {
        String url = "https://potolki.bitrix24.ru/rest/1/zm6bn1pw6el65nxa/crm.activity.list.json?OWNER_TYPE_ID=1&OWNER_ID=3340&next=250";
        RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        String request1 = "https://example.com/hotels/{hotel}/bookings/{booking}";
        String request2 = "https://potolki.bitrix24.ru/rest/1/zm6bn1pw6el65nxa/crm.activity.list.json?OWNER_TYPE_ID=1&OWNER_ID=3340&next=250{}";
        String request3 = "https://potolki.bitrix24.ru/rest/1/zm6bn1pw6el65nxa/crm.deal.get";
        String request4 = "https://potolki.bitrix24.ru/rest/1/zm6bn1pw6el65nxa/crm.deal.get?id=4718";

        String result1 = restTemplate.getForObject(request1, String.class, "42", "21");

        String result = restTemplate.getForObject( request4, String.class);

        System.out.println(result);
    }

    @Test
    public void sendSimplePostRequest() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity entity = new HttpEntity(objectMapper.writeValueAsString(getDeals()), httpHeaders);
        String result = restTemplate.postForObject(urlHookb, entity, String.class);

        System.out.println(result);
    }

    @Test
    public void sendPostRequestToB24Get50Deals() throws JsonProcessingException, UnsupportedEncodingException, JSONException {
        DealsRequest dealsRequest = new DealsRequest();
        dealsRequest.setOrder(getOrderMap());
        dealsRequest.setFilter(getFilterMap());
        dealsRequest.setSelect(getSelectList());

//        String url = "https://hookb.in/G9Zy1a3ZL1hWGGeQqzOm";
        String url = "https://potolki.bitrix24.ru/rest/1/zm6bn1pw6el65nxa/crm.deal.list/";

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setContentType(MediaType.TEXT_PLAIN);

        HttpEntity entity = new HttpEntity(objectMapper.writeValueAsString(dealsRequest), httpHeaders);
        String result = restTemplate.postForObject(url, entity, String.class);

        JSONObject object = new JSONObject(result);

        ResponseResultDeals resultDeals = getDealFromJson(object.toString());
        Assert.assertNotNull(resultDeals);
        System.out.println(object.toString());
    }

    @Test //TODO что работает
    public void sendPostRequestToB24AddDeal() throws JsonProcessingException {
        DealsAddRequest dealsAddRequest = new DealsAddRequest();
        Deal deal = new Deal();
        deal.setTITLE("FROM 3213JAVA из Джавы с любовью2231");
        dealsAddRequest.setFields(Map.of("TITLE", "FROM 3213JAVA из Джавы с любовью2231")); //
//        String url = "https://hookb.in/JKMVpJxeexTJPPWVO11y";
        String url = "https://potolki.bitrix24.ru/rest/1/zm6bn1pw6el65nxa/crm.deal.add/";

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
//        httpHeaders.setContentType(MediaType.TEXT_PLAIN);

        HttpEntity entity = new HttpEntity(objectMapper.writeValueAsString(dealsAddRequest), httpHeaders);
        String result = restTemplate.postForObject(url, entity, String.class);

        System.out.println(result);
    }

    @Test //TODO что работает
    public void getResponseDealById() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        String url = webhook + "crm.deal.get?id=4718";

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        restTemplate.getMessageConverters()
                .add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));

        String json = response.getBody();

        Gson gson = new Gson();
        DealB24 dealB24 = gson.fromJson(json, DealB24.class);
        System.out.println(dealB24.getResult().getTITLE());
    }

    @Test
    public void decoderTest(){
        String code = "\\u0438\\u0437 \\u0414\\u0436\\u0430\\u0432\\u044b \\u0441 \\u043b\\u044e\\u0431\\u043e\\u0432\\u044c\\u044e2231";
        System.out.println(Utils.decoder(code));
    }


    private Map<String, Integer> getFilterMap() {
        return Map.of(">" + Select.PROBABILITY.name(), 50);
    }

    private Map<String, String> getOrderMap() {
        return Map.of(Select.STAGE_ID.name(), "ASC");
    }

    private List<String> getSelectList() {
        return List.of(Select.ID.name(), Select.TITLE.name(), Select.STAGE_ID.name(),
                Select.PROBABILITY.name(), Select.OPPORTUNITY.name(), Select.CURRENCY_ID.name());
    }

    private DealsTest getDeals() {
        DealsTest dealsTest = new DealsTest();
        dealsTest.setNext(1234);
        dealsTest.setTotal(4321);
        dealsTest.setResult(getResultList());
        dealsTest.setTime(getTime());

        return dealsTest;
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
