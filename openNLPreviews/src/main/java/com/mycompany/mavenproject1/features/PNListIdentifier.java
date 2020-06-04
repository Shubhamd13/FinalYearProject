
package com.mycompany.mavenproject1.features;

import java.util.ArrayList;
import com.mycompany.mavenproject1.util.DuplicateEliminator;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class PNListIdentifier 
{
    private ArrayList readFile(String filename) throws FileNotFoundException{
        ArrayList<String> result = new ArrayList<>();
 
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            while (br.ready()) {
                result.add(br.readLine());
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return result;
    }
    public ArrayList  getPNList(String str) throws FileNotFoundException
    {
        ArrayList pn=new ArrayList();        
        ArrayList pos=new ArrayList();
        ArrayList neg=new ArrayList();
        String st[]=str.split(" ");
        for(int i=0;i<st.length;i++)
        {
            String wrd=st[i];
            wrd=wrd.trim();
           if(isPositive(wrd))
           {
               if(i>0)
               {
                   if(st[i-1].equals("not") )
                   {
                       String addword="not "+wrd;
                      neg.add(addword);
                   }
                   else  if(st[i-1].equals("no") )
                   {
                        String addword="no "+wrd;
                      neg.add(addword);
                   }
                   
                   else
                   {
                       pos.add(wrd);
                   }
                       
               }
               else
               {
                 pos.add(wrd);  
               }
               
           }
           
           if(isNegative(wrd))
           {
               if(i>0)
               {
                   if(st[i-1].equals("not")  )
                   {
                       String addword="not "+wrd;
                      pos.add(addword);
                   }
                   else  if(st[i-1].equals("no") )
                   {
                        String addword="no "+wrd;
                      pos.add(addword);
                   }
                   else
                   {
                       neg.add(wrd);
                   }
                       
               }
               else
               {
                 neg.add(wrd);  
               }
               
           }
            
            
            
        } 
       pos=new DuplicateEliminator().getUnique(pos);
       neg=new DuplicateEliminator().getUnique(neg);
       pn.add(pos);
       pn.add(neg);
        return pn;
    }
    
    
    public boolean isPositive(String word) throws FileNotFoundException{        
        ArrayList positive=readFile("positive.txt");
        return (positive.contains(word));
    }
    
       
    public boolean isNegative(String word) throws FileNotFoundException{
        ArrayList negative=readFile("negative.txt");
        return negative.contains(word);
    }
    
}
