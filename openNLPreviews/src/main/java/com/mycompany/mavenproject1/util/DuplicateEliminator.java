
package com.mycompany.mavenproject1.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class DuplicateEliminator 
{
 
    public ArrayList getUnique(ArrayList aa)
    {
              Set hh1=new HashSet();
              hh1.addAll(aa);
             aa.clear();
             aa.addAll(hh1);
             //System.out.println(aa);
             
             return aa;
    }
    
}
