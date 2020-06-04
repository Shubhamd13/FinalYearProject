
package com.mycompany.mavenproject1.fuzzyANN;

import java.util.ArrayList;

public class SarcasmHelp
{
    public ArrayList getEnhancedList(ArrayList tfidf,ArrayList pos,ArrayList neg)
    {
        ArrayList list=new ArrayList();
        
        if(pos.size()==0 && neg.size()==0)//if no pos/neg word found
        {   //if important word , adds to neg list
            for(int i=0;i<tfidf.size();i++)
            {
                ArrayList temp=(ArrayList)tfidf.get(i);
                String wrd=(String)temp.get(0);
                String tfidfindex=(String)temp.get(1);
                double dv=Double.parseDouble(tfidfindex);
                if(dv>0.9)
                {
                    neg.add(wrd);
                }
                
            }
            
             list.add(pos);
            list.add(neg);
        }
        else
        {
            list.add(pos);
            list.add(neg);
            
        }
        return list;
    }
}
