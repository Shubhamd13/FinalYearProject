
package com.mycompany.mavenproject1.fuzzyANN;

import java.util.ArrayList;

public class FuzzyRules {

    public ArrayList getFuzzyRules(ArrayList mas)
    {
          ArrayList col=new ArrayList();
         
            for(int j=0;j<mas.size();j++)
            {
                ArrayList row1=(ArrayList)mas.get(j);
                String v=(String)row1.get(1);
                double v1=Double.parseDouble(v);
                col.add(v1);
            }
              ArrayList r=new Range().getMinMax(col); 
     return r;
    }

}
