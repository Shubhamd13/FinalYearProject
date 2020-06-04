
package com.mycompany.mavenproject1.features;

public class CorreationHelper
{
   public  double getCorrelation(int p,int n)
    {
        System.out.println("p="+p+" n="+n);
        int xc=0;
        int yc=0;
       
        double corr=0;
        int size=0;
      
       if(p==0 && n==0)
       {
           corr=0.5;
       }
       else
       {
           int big = 0;
           if (p > n) 
           {
               big = p;
           } 
           else
           {
               big = n;
           }
           size = big + 1;
           double x[] = new double[size];
           double y[] = new double[size];
           x[xc++]=0;
           y[yc++]=0;
           //x=[0]
          //y=[0] 
           if(p>n && n==0)
           {
               
                 for(int i=0;i<p;i++)
                 {
                     x[xc++]=1;
                     y[yc++]=1;
                     
                 }
                      corr=new PersonCorrelation().getCorrelation(x, y);
           }
           else if(p<n && p==0)
           {
               for(int i=0;i<n;i++)
                 {
                     x[xc++]=1;
                     y[yc++]=0;
                     
                 } 
               //x=[0,1,1,1,1]
               //y=[0,0,0,0,0]
                corr=new PersonCorrelation().getCorrelation(x, y);
           }
           else if(p==n)
           {
               corr=0.5;
           }
           else
          {
              for(int i=0;i<p;i++)
                 {
                     x[xc++]=1;
                                        
                 }  
              
              if(xc<size)
              {
                  int r=size-xc;
                   for(int i=0;i<r;i++)
                 {
                     x[xc++]=0;
                                        
                 }  
                
              }

            for(int i=0;i<n;i++)
                 {
                     y[yc++]=1;
                                        
                 }  
              
              if(yc<size)
              {
                  int r=size-yc;
                   for(int i=0;i<r;i++)
                 {
                     y[yc++]=0;
                                        
                 }  
                
              }
                      
               corr=new PersonCorrelation().getCorrelation(x, y);
               if(p>n)
               {
                   if(corr<0.5)
                   {
                       corr=1-corr;
                   }
                   
               }
               else
               {
                    if(corr>0.5)
                   {
                       corr=1-corr;
                   }
               }
              
          }
       }   
        return corr;
    }
}
