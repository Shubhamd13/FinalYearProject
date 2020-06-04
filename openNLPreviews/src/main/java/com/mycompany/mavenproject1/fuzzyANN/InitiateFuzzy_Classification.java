
package com.mycompany.mavenproject1.fuzzyANN;

import java.util.ArrayList;


public class InitiateFuzzy_Classification {

    public ArrayList getClusters( ArrayList mas)
    {
        FuzzyRules fz=new FuzzyRules();
        ArrayList rule=fz.getFuzzyRules(mas);
       
        System.out.println("RULES ARE GENERATED ARE ");
        System.out.println(rule);

        FuzzyClustering fc = new FuzzyClustering();
        ArrayList c = fc.getFuzzyClusters(mas, rule);  
    return c;
   }    
 }


