/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.mavenproject1.preprocessing;

public class Preprocessor
{

    public String  textProcessing(String str)
    {
         String [] words=str.split(" ");
        String temp="";
        for(int i=0;i<words.length;i++)
        {
            String w=words[i];

                w=w.replace("\"", "");
               w=w.replace("\'", "");
                w=w.replace(",", "");
               w=w.replace("!", "");
               w=w.replace(":", "");
               w=w.replace("  ", " ");
               w=w.replace("    ", " ");
                w=w.replace("(", "");
               w=w.replace(")", "");
               w=w.replace("[", "");
               w=w.replace("]", "");
               w=w.replace("{", "");
               w=w.replace("}", "");
               w=w.replace("?", "");
               w=w.replace("<", "");
               w=w.replace(">", "");
               w=w.replace("=", "");
                w=w.replace(".", "");

           boolean val12=StopWords.getWords(w);

           if( val12==false)
           {
             temp=temp+w+" ";
           }

        }

       Stemmer s1=new Stemmer();
       String []sw=temp.split(" ");
       String sa="";
       for(int i=0;i<sw.length;i++)
       {
           String st=sw[i];
           String sr=s1.stemmer(st);
           sa=sa+sr+" ";
      }

         return sa;
    }
}
