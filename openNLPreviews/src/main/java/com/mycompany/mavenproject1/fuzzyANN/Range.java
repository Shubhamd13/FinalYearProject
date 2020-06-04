
package com.mycompany.mavenproject1.fuzzyANN;

import java.util.ArrayList;

public class Range {

    public ArrayList getMinMax(ArrayList col)
    {
        double min=Double.parseDouble(col.get(0).toString());
        double max=Double.parseDouble(col.get(0).toString());

        for(int i=0;i<col.size();i++)
        {
            double v=Double.parseDouble(col.get(i).toString());
            if(min>v)
                min=v;
            if(max<v)
                max=v;
        }
        System.out.println("min---"+min+"max----"+max);
        ArrayList r=getRange(min,max);
        return r;
    }
    public ArrayList getRange(double min,double max)
    {

       double r1=0,r2=0;
       double diff=max-min;
       double addup=diff/5;
       ArrayList r=new ArrayList();
        for(int i=0;i<5;i++)
        {
            ArrayList ri=new ArrayList();
            r1=min;

            r2=r1+addup;

            min=r2;
            ri.add(r1);
            ri.add(r2);
            r.add(ri);
        }
  return r;

    }

}
