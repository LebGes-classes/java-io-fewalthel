package org.example;

//java standart utilits
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

//JSON simple library
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;

public class Output {
    //возвращает группу в виде мапы по номеру
    public static Map<String, Object> getGroup(String number, LinkedList<Map<String, Object>> listOfMaps) {
        for (int i = 0; i < listOfMaps.size(); i ++) {
            if (listOfMaps.get(i).containsValue(number) ) {
                return listOfMaps.get(i);
            }
        }
        return null; //если группы с таким номером нет, возвращаем null
    }

    public static void readFromJson(String group, String day) throws IOException{
        JSONParser parser = new JSONParser();
        //считываем файл и преобразуем его в массив json объектов
        try (FileReader reader = new FileReader("C:\\Users\\User\\Documents\\GitHub\\java-io-fewalthel\\src\\main\\java\\org\\example\\raspisan.json")) {
            Object obj = parser.parse(reader);
            JSONArray jsonArray = (JSONArray) obj;

            //создаем наборы пар ключ-значение, где ключ - это название поля Json объекта
            LinkedList<Map<String, Object>> listOfMaps = new LinkedList<>();
            for (Object item : jsonArray) {
                JSONObject jsonObject = (JSONObject) item;

                Map<String, Object> map = new HashMap<>();
                for (Object key : jsonObject.keySet()) {
                    String keyStr = (String) key;
                    Object keyValue = jsonObject.get(keyStr);
                    map.put(keyStr, keyValue);
                }

                listOfMaps.add(map);
            }
            //извлекаем список предметов группы с номером group н адень недели day
            JSONArray array = (JSONArray) getGroup(group, listOfMaps).get(day);
            //выводим в консоль расписание
            for (Object element : array) {
                System.out.println(element);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
