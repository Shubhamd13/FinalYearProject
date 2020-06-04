
package com.mycompany.mavenproject1.comment_classification;

import com.mycompany.mavenproject1.fuzzyANN.InitiateFuzzy_Classification;
import java.io.IOException;
import java.util.ArrayList;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;

public class ClassificationProcess
{
    public ArrayList initProcess(ArrayList mas) throws WriteException, BiffException, IOException
    {
        ArrayList classlist= new ProcessInit().getClassificationList(mas);
        
        System.out.println("CORRELATION LIST IS "+classlist);
       ArrayList clssified=new  InitiateFuzzy_Classification().getClusters(classlist);
        
        System.out.println("clssified "+clssified);
         
        ArrayList ctype=new ArrayList();
        ctype.add("WORST");
        ctype.add("DISAPPOINTED");
        ctype.add("AVERAGE");
        ctype.add("SATISFACTORY");
        ctype.add("EXCELLENT");

        for(int i=0;i<clssified.size();i++)
        {
            ArrayList Single=(ArrayList)clssified.get(i);
           System.out.println("CLASSIFIED FOR "+ctype.get(i));

            for(int j=0;j<Single.size();j++)
            {
                ArrayList c2=(ArrayList)Single.get(j);
                String sub=(String)c2.get(0);                                                       
                System.out.println("SUBJECT is :"+sub);                                                                                                                                            
            }
            System.out.println("\n-------------------------------------------------------\n");
        }
        return clssified;
 }
}
