
package com.mycompany.mavenproject1.tf_idf;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class IDFInitiator_FIN 
{
    public ArrayList  getIDF(ArrayList tfv,ArrayList doc)
    {
        ArrayList tfidf=new ArrayList();
        for(int i=0;i<tfv.size();i++)
        {
           ArrayList temp=(ArrayList)tfv.get(i);
           String wrd=(String)temp.get(0);
           String tfvalue=(String)temp.get(1);
          
           
           int count=getCount(doc,wrd);
               double ent=0.0;
               double didf=0.0;
           if(count==0)
           {
               ent=0.0;
               didf=0.0;
           }
           else
           {
               ent=(double)doc.size()/count;
               didf= Math.log10(ent);
           }
       
       
       
       // System.out.println("^^^^^ "+wrd+" "+nodoc+" "+count+" "+ent+" "+didf);
       double finidf=(double)Integer.parseInt(tfvalue)*didf;
         DecimalFormat df=new DecimalFormat("0.000");
           String  idf=df.format(finidf);
      
           ArrayList tt=new ArrayList();
           tt.add(wrd);
           tt.add(idf);
           tfidf.add(tt);
          //  System.out.println();
                            
            
        }
        
        return tfidf;
    }
    
    
    int getCount(ArrayList doc,String wrd)
    {
         int count=0;
        for(int i=0;i<doc.size();i++)
        {
            ArrayList temp=(ArrayList)doc.get(i);
           
            String cont=(String)temp.get(1);
            cont=cont.trim();
            String st[]=cont.split(" ");
            for(int j=0;j<st.length;j++)
            {
                String mat=st[j];
                mat=mat.trim();
                if(mat.equals(wrd))
                {
                    count++;
                    break;
                }
            }
            
            
        }
        
        return  count;
    }
}
