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
import java.io.*;

//java standart utilits
import java.util.LinkedList;



public class Parse {

    public static void Parsing() { //записываем данные из таблицы в Json файл
        String excelFilePath = "C:\\Users\\User\\Documents\\GitHub\\java-io-fewalthel\\src\\main\\java\\org\\example\\raspisanie.xlsx";
        String jsonFilePath = "C:\\Users\\User\\Documents\\GitHub\\java-io-fewalthel\\src\\main\\java\\org\\example\\raspisan.json";
        if (isJsonFileEmpty(jsonFilePath)) { //проверяем пуст ли файл, чтобы не допустить дублирования
            try (
                    FileInputStream fis = new FileInputStream(excelFilePath);
                    Workbook workbook = new XSSFWorkbook(fis)) {

                Sheet sheet = workbook.getSheetAt(0); //первый лист

                LinkedList<Group> ListOfGroups = new LinkedList<Group>(); //список для учебных групп

                for (Row row : sheet) { //парсинг по строкам
                    for (Cell cell : row) {
                        if (cell.getColumnIndex() >= 2 && cell.getColumnIndex() <= 53 + 1) {
                            if (cell.getRowIndex() == 1) { //на 2 строке(по индексу 1) хранятся номера групп, поэтому тут мы будем создавать новые экземпляры классов групп
                                ListOfGroups.add(new Group(cell.getStringCellValue()));
                            }
                            processCell(cell, ListOfGroups);
                        }
                    }
                }

                //data.stream().forEach(System.out::println);*/

                // преобразование данных о каждой группе в JSON
                ObjectMapper mapper = new ObjectMapper();
                mapper.writeValue(new File(jsonFilePath), ListOfGroups);

            } catch (
                    IOException e) {
                e.printStackTrace();
            }
        }
    }

    //проверяем, пуст ли файл json
    public static boolean isJsonFileEmpty(String filePath) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(new File(filePath));

            return rootNode.isEmpty();
        } catch (IOException e) {
            // обработка исключения, если файл не найден или недоступен
            e.printStackTrace();
            return true; // предполагаем, что файл пуст, если произошла ошибка
        }
    }

    public static String removeExtraSpaces(String input) {
        if (input == null || input.isEmpty()) {
            return input; // если строка пустая или null, возвращаем её без изменений
        }
        return input.trim().replaceAll("\\s+", " ");
    }

    //enum для избежания дублирования кода
    public enum DayOfWeek {
        MONDAY(2, 8),
        TUESDAY(9, 15),
        WEDNESDAY(16, 22),
        THURSDAY(23, 29),
        FRIDAY(30, 36),
        SATURDAY(37, 43);

        private final int startRow;
        private final int endRow;

        DayOfWeek(int startRow, int endRow) {
            this.startRow = startRow;
            this.endRow = endRow;
        }

        public boolean isInRange(int rowIndex) {
            return rowIndex >= startRow && rowIndex <= endRow;
        }
    }

    //метод который парсит данные по дням недели
    public static void processCell(Cell cell, LinkedList<Group> ListOfGroups) {
        int columnIndex = cell.getColumnIndex() - 2;
        String cellValue = removeExtraSpaces(cell.getStringCellValue());

        for (DayOfWeek day : DayOfWeek.values()) {
            if (day.isInRange(cell.getRowIndex())) {
                switch (day) {
                    case MONDAY -> ListOfGroups.get(columnIndex).monday.add(cellValue);
                    case TUESDAY -> ListOfGroups.get(columnIndex).tuesday.add(cellValue);
                    case WEDNESDAY -> ListOfGroups.get(columnIndex).wednesday.add(cellValue);
                    case THURSDAY -> ListOfGroups.get(columnIndex).thirsday.add(cellValue);
                    case FRIDAY -> ListOfGroups.get(columnIndex).friday.add(cellValue);
                    case SATURDAY -> ListOfGroups.get(columnIndex).saturday.add(cellValue);
                }
                break; // прерываем цикл после нахождения соответствующего дня недели
            }
        }
    }
}
