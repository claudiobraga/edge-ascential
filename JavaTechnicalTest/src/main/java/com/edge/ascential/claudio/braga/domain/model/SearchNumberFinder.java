package com.edge.ascential.claudio.braga.domain.model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class SearchNumberFinder implements NumberFinder{
	

	@Override
    public boolean contains(int valueToFind, List<CustomNumberEntity> list) {
        boolean v = false;
        FastestComparator fast = new FastestComparator();
        for (CustomNumberEntity cne : list) {
            try {
                Integer.parseInt(cne.getNumber());
                System.out.println(" " + fast.compare(valueToFind, cne));
                if (cne.getNumber().equals(valueToFind+"")) {
                    v = true;
                }
            } catch (Exception e) {
            	
            }
        }
        return v;
    }

	
	@Override
    public List<CustomNumberEntity> readFromFile(String filePath) {

        JSONParser jsonParser = new JSONParser();
        List<CustomNumberEntity> customNumberEntityList = new ArrayList<>();

        try (FileReader reader = new FileReader(filePath)) {
            Object object = jsonParser.parse(reader);
            JSONArray listOfCustomValues = (JSONArray) object;
            for (Object jsonobj : listOfCustomValues) {
                CustomNumberEntity cne = new CustomNumberEntity();
                JSONObject obj = (JSONObject) jsonobj;
                String number = (String) obj.get("number");
                cne.setNumber(number);
                customNumberEntityList.add(cne);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return customNumberEntityList;
    }


}
