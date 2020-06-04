
package com.mycompany.mavenproject1.comment_classification;

import java.io.*;
import java.util.ArrayList;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
public class ExcelReader 
{
    public ArrayList getExcelData(String sf)
    {
        ArrayList m=new ArrayList();
      try
        {
            Workbook ReadExcel = Workbook.getWorkbook(new File(sf));
            Sheet sheet = ReadExcel.getSheet(0);
            int a=sheet.getColumns();
            a=2;
            int b=sheet.getRows();          
            for (int i=1;i<b;i++)//rows
            {
                ArrayList in=new ArrayList();
                for(int j=0;j<a;j++)
                {
                Cell a1 = sheet.getCell(j,i);//column ,row 
                String ed = a1.getContents();
                ed=ed.trim();
                ed=ed.toLowerCase();
                
                in.add(ed);
                }
                m.add(in);
            }
              ReadExcel.close();
            }
        catch (Exception i)
        {
            System.out.println("Exception is "+i);
        }
        return m;
    }
}