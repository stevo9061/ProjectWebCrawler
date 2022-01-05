package fhtw;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ExcelWriter {


    // Spalten der Tabellen bestimmen
    private String[] columns = {"Element", "Postleitzahl", "Beschreibung", "Preis", "Webseite"};


    public void createFile(String filePath, ArrayList<WebScraper> myArrayListTwo) throws Exception {
        //Excel Datei erstellen
        // HSSFWorkbook Klasse für .xls Excel Dateien
        // XSSFWorkbook für .xlsx Excel Dateien

        // Excel File erstellt
        Workbook workbook = new HSSFWorkbook();

        //Formatierung in Excel
        CreationHelper creationHelper = workbook.getCreationHelper();

        // Tabelle in Excel erstellt
        Sheet sheet = workbook.createSheet("Willhaben Export");

        Font headerFont = workbook.createFont();
        headerFont.setFontHeightInPoints((short)14);
        headerFont.setColor(IndexedColors.BLACK.getIndex());
//        headerFont.setBold((short) 10);

        // Wir haben einer Zeile unsere erstellten Fonts übergeben
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        // Zeile (Reihe) erstellen, beginn bei 0
        Row headerRow = sheet.createRow(0);

        // Kästchen Inhalt erstellen - Hier die Spaltenüberschriften
        // Wir erstellen unsere Spalten ID, Beschreibung, etc mit den definierten Formatierungen
        for(int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
        }


        // Objekte in Tabelle schreiben
        int rowNum = 1;


          for(WebScraper iterator : myArrayListTwo) {

            Row row = sheet.createRow(rowNum++);
            // In Zeile 0 wird Hersteller erstellt, in Zeile 1 Objekt etc.
              row.createCell(0).setCellValue(iterator.getTbl_element());
              row.createCell(1).setCellValue(iterator.getTbl_postcode());
              row.createCell(2).setCellValue(iterator.getTbl_beschreibung());
              row.createCell(3).setCellValue(iterator.getTbl_preis());
              row.createCell(4).setCellValue(iterator.getTbl_webseite());
        }

        // Wir gehen jede Spalte durch und passen diese automatisch an dem Text an (Spaltenbreite)
        for (int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }

        try{
            // Datei schreiben / erstellen
            File file = new File(filePath);

            FileOutputStream fileOutputStream = new FileOutputStream(file);
            workbook.write(fileOutputStream); //Wir schreiben in den Outputstream

            fileOutputStream.close();

        } catch(FileNotFoundException fileNotFoundException) {

        } catch(IOException ioException) {

        }

//        workbook.close(); //TODO: Warum nimmt er das nicht


    }
}
