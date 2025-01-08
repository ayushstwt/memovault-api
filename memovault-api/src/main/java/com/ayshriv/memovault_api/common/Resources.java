package com.ayshriv.memovault_api.common;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.json.MappingJacksonValue;

import java.util.HashMap;
import java.util.Map;

public class Resources {
    static Logger LOGGER = LoggerFactory.getLogger(Resources.class);

    public static MappingJacksonValue formatedResponse(Object obj, String[] properties) {
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(obj);
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(properties);
        FilterProvider filters = new SimpleFilterProvider().addFilter("DesiredStatusFilter", filter);
        mappingJacksonValue.setFilters(filters);
        return mappingJacksonValue;
    }

    public static DesireStatus setStatus(String type, String text, String entity) {
        DesireStatus status = new DesireStatus();
        status.setStatusType(type);
        Map<String, String> tokenKeyVal = new HashMap<>();
        tokenKeyVal.put("ENTITY", entity);
        text = replaceTokens(text, tokenKeyVal);
        status.setText(text);
        return status;
    }

    public static String replaceTokens(String content, Map<String, String> tokenValues) {
        for (Map.Entry<String, String> entry : tokenValues.entrySet()) {

            String strKey = "|" + entry.getKey() + "|";
            String strValue = entry.getValue();
            LOGGER.info("Key >> " + strKey + "\tValue >> " + strValue);
            LOGGER.info("emailContent.indexOf >> " + content.indexOf(strKey));
            while (content.contains(strKey)) {
                content = content.replace(strKey, strValue);
            }
        }
        return content;
    }
}
