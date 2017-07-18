package com.ttn.intellimeet.personalization;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.sling.api.resource.ValueMap;

import com.adobe.cq.sightly.WCMUsePojo;

public class PersonalizationStates extends WCMUsePojo{
    private Map<String, String> optionsMap;

    @Override
    public void activate() throws Exception {
        ValueMap valueMap = getResource().getValueMap();
        String[] states = valueMap.get("states", String[].class);
        mapOptions(states);
    }
    private void mapOptions(String[] options){
    		if(options != null) {
	        optionsMap = new LinkedHashMap<String,String>();
	        for(String option : options){
	            if(option.matches(".+=.+")){
	                String[] keyValuePair = option.split("=");
	                optionsMap.put(keyValuePair[0], keyValuePair[1]);
	            }
	        }
    		}
    }
    
    public Map<String, String> getOptionsMap() {
        return optionsMap;
    }
    
}
