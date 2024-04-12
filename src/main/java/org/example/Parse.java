package org.example;

//JACKSON for work with json data type
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;

//APACHE for work with Microsoft's data types
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

//Stream API
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;

//java standart utilits
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Parse {
    public Pattern patternGroup = Pattern.compile("11-\\d\\d\\d"); //шаблон для номера группы
    public Pattern patternTeacher = Pattern.compile("\\D.+\\D"); //шаблон фио преподавателя
    public Pattern patternSubject = Pattern.compile("\\D.+\\D"); //шаблон названия предмета

    //TODO
    //method to parse data from exel file
    //method to make objects type of Student, Teacher
    //method to write objects to json files


    //TODO СВЕТА ИЗ БУДУЩЕГО УМОЛЯЮ РЕАЛИЗУЙ ЭТОД МЕТОД НЕ ЧЕРЕЗ ЖОПУ
    //сделать так чтобы даннее с эксель файла записывались не в один текстовый файл,
    //а в параметры экземпляров классов студент и учитель(студентики и тичеры хранятся в списках,
    //а потом мы записывали эти данные в соответсвующие Json файлы

    public static void Parsing() { //записываем данные из таблицы в Json файл
        String excelFilePath = "C:\\Users\\User\\Documents\\GitHub\\java-io-fewalthel\\src\\main\\java\\org\\example\\raspisanie.xlsx";
        String jsonFilePath = "C:\\Users\\User\\Documents\\GitHub\\java-io-fewalthel\\src\\main\\java\\org\\example\\subjects.json";
        if (isJsonFileEmpty(jsonFilePath)) { //проверяем пуст ли файл, чтобы не допустить дублирования
            try (
                    FileInputStream fis = new FileInputStream(excelFilePath);
                    Workbook workbook = new XSSFWorkbook(fis)) {

                Sheet sheet = workbook.getSheetAt(0); // Получаем первый лист
                List<List<String>> data = new ArrayList<>();

                for (Row row : sheet) {
                    List<String> rowData = new ArrayList<>();
                    for (Cell cell : row) {
                        rowData.add(cell.getStringCellValue());
                    }
                    data.add(rowData);
                }

                // Преобразование данных в JSON
                ObjectMapper objectMapper = new ObjectMapper();
                String json = objectMapper.writeValueAsString(data);

                // Запись JSON в файл
                try (FileOutputStream fos = new FileOutputStream(jsonFilePath)) {
                    fos.write(json.getBytes());
                }

                System.out.println("Данные успешно преобразованы и сохранены в файл " + jsonFilePath);

            } catch (
                    IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void OutputJsonFile() {

    }

    public static boolean isJsonFileEmpty(String filePath) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(new File(filePath));

            // Проверяем, является ли корневой узел пустым
            return rootNode.isEmpty();
        } catch (IOException e) {
            // Обработка исключения, если файл не найден или недоступен
            e.printStackTrace();
            return true; // Предполагаем, что файл пуст, если произошла ошибка
        }
    }
}
