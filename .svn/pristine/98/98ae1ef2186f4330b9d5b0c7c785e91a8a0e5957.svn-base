package com.capco.hcm.view.fragments;


import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.model.UploadedFile;

import com.capco.hcm.model.ReportGenerateObj;
import com.capco.hcm.utils.ExcelFileReader;


@ManagedBean (name = "reportInfo")
public class ReportGenerateView {

	
	private static final String FILE_PATH = "C:/moit/HCM_Template.xlsx"; 
	//private static final String FILE_PATH = "C:/New folder/HCM_Template.xlsx";
	
	private UploadedFile file;
	 
    public UploadedFile getFile() {
  /*  	System.out.println("=====getfile======"+file.getFileName());
  */      return file;
    }
 
    public void setFile(UploadedFile file) {
  /*  	System.out.println("=====file======"+file);
  */      this.file = file;
    }
     
    
	

	private ReportGenerateObj reportgenerate;
	
	@ManagedProperty(value="#{excelFileReader}")
	private ExcelFileReader excelFileReader;

	public ExcelFileReader getExcelFileReader() {
		return excelFileReader;
	}

	public void setExcelFileReader(ExcelFileReader excelFileReader) {
		this.excelFileReader = excelFileReader;
	}

	public ReportGenerateObj getReportgenerate() {
		if(reportgenerate == null)
			reportgenerate = new ReportGenerateObj();
		return reportgenerate;
	}

	public void setReportgenerate(ReportGenerateObj reportgenerate) {
		this.reportgenerate = reportgenerate;
	}
	
	
	public void exportToExcel(ActionEvent event)
	{	
			
			try {
				excelFileReader.readFile(FILE_PATH,reportgenerate.getSelectreport());
				
				
				FacesContext fc = FacesContext.getCurrentInstance();
				HttpServletResponse response = (HttpServletResponse) fc.getExternalContext().getResponse();

				response.reset();
				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Disposition", "attachment; filename=\"HCM_file.xlsx");
				XSSFWorkbook	workbook = new XSSFWorkbook(new FileInputStream("C:/moit/HCM_file.xlsx"));
				workbook.write(response.getOutputStream());

				fc.responseComplete();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}

	public void upload()
	{
		try{
			System.out.println("==========="+file.getFileName());
			System.out.println("==========="+file.getContentType());
			byte[] contents = file.getContents();
			System.out.println("Contents of files "+contents);
			InputStream input = new ByteArrayInputStream(contents);
			if(file != null) {
				System.out.println("=====33======"+file);
				excelFileReader.readuploadFile(input);
	        }
			
		}catch (Exception e) {
			e.printStackTrace();
		}

	            
	}
	
}
