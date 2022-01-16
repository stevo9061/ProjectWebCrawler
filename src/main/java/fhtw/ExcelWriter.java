package fhtw;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Helper class - to write table data to an xls file, the records are written to separate tables.
 */


public class ExcelWriter {


    // Determine columns of the tables.
    final private String[] columns = {"Element", "Postleitzahl", "ID", "Preis", "Webseite"};

    /**
     * @param filePath       Here you enter the path in which the Excel file is to be created.
     * @param arraylistExcel Pass an object of type Webscraper with an arraylist.
     */
    public void createFile(String filePath, ArrayList<WebScraper> arraylistExcel) {

        // Create Excel file
        // HSSFWorkbook Class for .xls Excel Files
        // XSSFWorkbook Class for .xlsx Excel Files


        // Empty Excel file created
        Workbook workbook = new HSSFWorkbook();

        // Formatting in Excel
        CreationHelper creationHelper = workbook.getCreationHelper();

        // Table created in Excel
        Sheet sheet = workbook.createSheet("Willhaben Export");

        Font headerFont = workbook.createFont();
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.BLACK.getIndex());
//        headerFont.setBold((short) 10);

        // We have passed our created fonts to one line
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        // Create row, start at 0
        Row headerRow = sheet.createRow(0);

        // Create content box - Here are the column headers.
        // We create our Item, Postcode, ID, Price and Website columns with the defined formatting.

        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
        }


        // Write objects to table
        int rowNum = 1;
        for (WebScraper iterator : arraylistExcel) {

            Row row = sheet.createRow(rowNum++);

            //In line 0 element is created, in line 1 postal code etc.
            row.createCell(0).setCellValue(iterator.getTbl_element());
            row.createCell(1).setCellValue(iterator.getTbl_postcode());
            row.createCell(2).setCellValue(iterator.getTbl_id());
            row.createCell(3).setCellValue(iterator.getTbl_preis());
            row.createCell(4).setCellValue(iterator.getTbl_webseite());
        }

        // We traverse through each column and automatically adjust it to the text (column width).
        for (int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }

        try {
            // Write / create file
            File file = new File(filePath);

            FileOutputStream fileOutputStream = new FileOutputStream(file);
            workbook.write(fileOutputStream); //Wir schreiben in den Outputstream

            fileOutputStream.close();

        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (Exception exc) {
            exc.printStackTrace();
        }


    }
}
