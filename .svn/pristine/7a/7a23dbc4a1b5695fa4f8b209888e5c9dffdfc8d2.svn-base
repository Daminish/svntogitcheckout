package com.capco.hcm.utils;
 
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
 
/**
 * @author Mohit Gangil
 *
 */
public class ExcelFile {
	
	
     
    public static void main(String[] args) throws IOException {
 
    	FileInputStream file = null;
    	try
          {
              file = new FileInputStream(new File("C:\\Users\\e1093330\\Desktop\\HCM_Template.xlsx"));
   
              //Create Workbook instance holding reference to .xlsx file
              XSSFWorkbook workbook = new XSSFWorkbook(file);
   
              //Get first/desired sheet from the workbook
              for(int i=0; i<workbook.getNumberOfSheets(); i++)
              {
              XSSFSheet sheet = workbook.getSheetAt(i);
              System.out.println("Name of the Sheet" +sheet.getSheetName());
              //Iterate through each rows one by one
             FormulaEvaluator formula = workbook.getCreationHelper().createFormulaEvaluator();
             for(Row row : sheet)
             {
            	 for(Cell cell : row)
            	 {
            		 switch (formula.evaluateInCell(cell).getCellType()) {
					
                          case Cell.CELL_TYPE_NUMERIC:
                        	  
                        	  if (DateUtil.isCellDateFormatted(cell)) {

                                  Date date = cell.getDateCellValue();
                                  System.out.print(date+"\t\t");
                                  break;
                              } 
                        	  else {
                        		  System.out.print(Math.round(cell.getNumericCellValue())+"\t\t\t");
                            	  break;
                              }
                              
                          case Cell.CELL_TYPE_STRING:
                              System.out.print(cell.getStringCellValue()+"\t\t\t");
                              break;
                         case Cell.CELL_TYPE_BOOLEAN:
                  			System.out.print(cell.getBooleanCellValue()+"\t\t\t");
                  			break;
                         case Cell.CELL_TYPE_BLANK:
                  			System.out.print(cell);
                  			break;
                         default:
                        	
                        	 
            		 }
                  }
	             System.out.println();
              }
              }
              file.close();
          } 
    		
          catch (Exception e) 
          {
              e.printStackTrace();
          }
    /*	finally {
			file.close();
		}*/
    	
}}