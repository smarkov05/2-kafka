package com.mentoring.palmetto_service.utils;

import java.util.List;

public interface JsonConvertor {


    String serializeToJson(Object obj);

    <T> T deserializeJsonToClazz(String itemJson, Class<T> clazz);

    <T> List<T> deserializeListItems(String itemsJson, Class<T> clazz);

}
