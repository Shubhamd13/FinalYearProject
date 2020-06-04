
package com.mycompany.mavenproject1.features;

import java.util.ArrayList;
import com.mycompany.mavenproject1.util.DuplicateEliminator;

public class TermWeightCalculator 
{
    public ArrayList termWeightEstimator(String str)
    {
        ArrayList freq=new ArrayList();
        String st[]=str.split(" ");
        ArrayList words=new ArrayList();
         ArrayList org=new ArrayList();
        for(int i=0;i<st.length;i++)
        {
            words.add(st[i]);
            org.add(st[i]);
        }
      words= new DuplicateEliminator().getUnique(words);
   
        for(int i=0;i<words.size();i++)
        {
            String wrd=(String)words.get(i);
            
            int count=0;
             for(int j=0;j<org.size();j++)
             {
                  String og=(String)org.get(j);
                 if(wrd.equals(og))
                     count++;
                 
             }
             ArrayList temp=new ArrayList();
             temp.add(wrd);
             temp.add(Integer.toString(count));
             freq.add(temp);   
        }        
        return freq;
    }
}
