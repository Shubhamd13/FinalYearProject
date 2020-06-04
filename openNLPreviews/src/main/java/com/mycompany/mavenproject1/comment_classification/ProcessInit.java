
package com.mycompany.mavenproject1.comment_classification;

import com.mycompany.mavenproject1.features.CorreationHelper;
import com.mycompany.mavenproject1.features.PNListIdentifier;
import com.mycompany.mavenproject1.features.TermWeightCalculator;
import com.mycompany.mavenproject1.fuzzyANN.SarcasmHelp;
import com.mycompany.mavenproject1.fuzzyANN.TermWeight;
import java.util.ArrayList;
import com.mycompany.mavenproject1.preprocessing.Preprocessor;
import com.mycompany.mavenproject1.tf_idf.IDFInitiator_FIN;
import com.mycompany.mavenproject1.tf_idf.TF_Evaluator_FIN;
import java.io.File;
import java.io.IOException;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.*;
import jxl.write.Number;

public class ProcessInit
{
    private static final String EXCEL_FILE_LOCATION = "C:\\Users\\lenovo\\Downloads\\hima1.xls";
    
    public ArrayList getClassificationList(ArrayList  mas) throws WriteException, IOException, BiffException
    {
      WritableWorkbook myFirstWbook = null;
      myFirstWbook = Workbook.createWorkbook(new File(EXCEL_FILE_LOCATION));

            // create an Excel sheet
            WritableSheet excelSheet = myFirstWbook.createSheet("Sheet 1", 0);
        ArrayList fincorr=new ArrayList();
        
            System.out.println("\n\n CLASSIFICATION PROCESS BEGINS\n\n");
        
        for(int i=0;i<mas.size();i++)
        {
            System.out.println("CLASSIFICATION OF SUBJECT NO  "+(i+1));
          
            
             ArrayList temp=(ArrayList)mas.get(i);
             String sub=(String)temp.get(0);
             String cont=(String)temp.get(1);
             cont=cont.toLowerCase();
             cont=cont.trim();
               System.out.println("SUBJECT IS : "+sub);
             System.out.println("CONTENTS IS : "+cont);
             String pre=new Preprocessor().textProcessing(cont);
             System.out.println("PREPROCESSED SUBJECT CONTENTS IS : "+pre);
             
            ArrayList pn = new PNListIdentifier().getPNList(pre);
            ArrayList pos = (ArrayList) pn.get(0);
            ArrayList neg = (ArrayList) pn.get(1);

            System.out.println("Positive Words : " + pos);
            System.out.println("Negative Words : " + neg);
            //unique positive word no duplicates
            ArrayList term = new TermWeightCalculator().termWeightEstimator(pre);
            System.out.println("FREQUENCY OF WORDS ARE  : " + term);

            ArrayList pos1 = new TermWeight().EnhancedTermweight(term, pos);
            ArrayList neg1 = new TermWeight().EnhancedTermweight(term, neg);
               
                System.out.println("Enhanced Positive Neurons Through Term Weight : " + pos1);
                System.out.println("Enhanced Negative Neurons Through Term Weight: " + neg1);
                //print frequency times pos /neg word                   
               

                ArrayList tflist= new TF_Evaluator_FIN().getTF(pre);
                ArrayList tfidflist= new IDFInitiator_FIN().getIDF(tflist, mas);
                System.out.println("TF IDF OF SUBJECT IS  : "+tfidflist);
                 
                ArrayList list=new SarcasmHelp().getEnhancedList(tflist, pos1, neg1);
                ArrayList pos2=(ArrayList)list.get(0);
                ArrayList neg2=(ArrayList)list.get(1);
                
                System.out.println("Final Enhanced Positive Neurons Through  : " + pos2);
                System.out.println("Final Enhanced Negative Neurons : " + neg2);
                
                int p=pos2.size();
                int n=neg2.size();
               double corr=new CorreationHelper().getCorrelation(p,n);
               System.out.println("CORRELATION IS "+corr);
               Number number = new Number(3, i,corr );
                excelSheet.addCell(number);
               String str=Double.toString(corr);
               ArrayList tt=new ArrayList();
               tt.add(sub);
               tt.add(str);
               fincorr.add(tt);
             
             System.out.println("\n\n =================================================");
        }     
        myFirstWbook.write();
        myFirstWbook.close();
        return fincorr;    
    }
}
