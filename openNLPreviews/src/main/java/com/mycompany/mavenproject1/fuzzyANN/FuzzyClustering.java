
package com.mycompany.mavenproject1.fuzzyANN;

import java.util.ArrayList;

public class FuzzyClustering {

   
    public ArrayList getFuzzyClusters(ArrayList mas,ArrayList rule)
    {
        
          ArrayList fincluster=new ArrayList();   
        for(int i=0;i<rule.size();i++)
        {        
            ArrayList trule=(ArrayList)rule.get(i); 
             
               ArrayList cluster=new ArrayList();
     
               double r1=Double.parseDouble(trule.get(0).toString());
               double r2=Double.parseDouble(trule.get(1).toString());
              
                for(int j=0;j<mas.size();j++)
                {
                ArrayList row=(ArrayList)mas.get(j);
                double rv=Double.parseDouble(row.get(1).toString());
                 
                if(rv>=r1&&rv<=r2)
                {
                    
                    cluster.add(row);
                }

                }
              
                if(cluster.size()>0)
               fincluster.add(cluster);
    
        
        }

return fincluster;

    }
}
