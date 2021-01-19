package core;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExcelUtils {
    private static XSSFSheet excelWSheet;

    public static Object[][] getDataArray(String filePath, String sheetName, int startRowNum, int startColNum, int endRowNum, int endColNum) throws Exception {

        String[][] tableArr = null;
        XSSFWorkbook excelWBook;

        try {
            FileInputStream excelFile = new FileInputStream(filePath);
            // Access the required test data sheet
            excelWBook = new XSSFWorkbook(excelFile);
            excelWSheet = excelWBook.getSheet(sheetName);
            int totalRows = endRowNum - startRowNum + 1;
            int totalCols = endColNum - startColNum + 1;

            tableArr = new String[totalRows][totalCols];
            int arrRowNum = 0;
            int arrColNum;
            for (int excelRowNum = startRowNum; excelRowNum <= endRowNum; excelRowNum++, arrRowNum++) {
                arrColNum = 0;
                for (int excelColIndex = startColNum; excelColIndex <= endColNum; excelColIndex++, arrColNum++) {
                    tableArr[arrRowNum][arrColNum] = getCellData(excelRowNum, excelColIndex);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Could not read the Excel sheet");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Could not read the Excel sheet");
            e.printStackTrace();
        }

        return tableArr;
    }

    private static String getCellData(int rowIndex, int colIndex) throws Exception {
        XSSFCell cell;
        try {
            cell = excelWSheet.getRow(rowIndex).getCell(colIndex);

            if (cell == null) {
                return "";
            } else {
                return cell.getStringCellValue();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
