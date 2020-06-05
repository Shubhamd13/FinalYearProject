
package com.mycompany.mavenproject1.parse;


import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class GetAndParse {
    public GetAndParse() {
        
    }
    public static ArrayList getReviewsFromDoc(Document doc){
        ArrayList reviews=new ArrayList();
        Elements reviewList = doc.select("#cm_cr-review_list");
         // Elements ratingsList = reviewList.select(".review-rating");
          Elements reviewTextList = reviewList.select(".review-text");
          for (int i = 0; i < reviewTextList.size(); i++) {
              ArrayList review=new ArrayList();
              review.add(reviewTextList.get(i).text());
              review.add(reviewTextList.get(i).text());
              reviews.add(review);
          }
          return reviews;
    }
    public static Document getDocFromUrl(String url) throws IOException{
        Document doc = Jsoup.connect(url).get();
        return doc;
    }
    public static ArrayList getData(String asin) throws ParseException, IOException {
    	 ArrayList allReviews=new ArrayList();
         //String asin="B07X2KLYSF";
         String url="https://www.amazon.in/product-reviews/"+asin+"/?s&pageNumber=";
         Document currentPage=getDocFromUrl(url+"1");
         //loop to get pages
         //Showing 1-10 of 2,752 reviews
         int size = currentPage.select("[data-hook='cr-filter-info-review-count']").size();
         if(size==0) {
        	 return allReviews;
         }
         Element count = currentPage.select("[data-hook='cr-filter-info-review-count']").get(0);
         String scount=count.text();
         int pos=scount.indexOf("of");
         int noOfReviews=((Long) NumberFormat.getNumberInstance(java.util.Locale.US).parse(scount.substring(pos+3))).intValue();
         int numberOfPages=noOfReviews/10;
         numberOfPages=(numberOfPages<10)?(numberOfPages):(10);
         if(noOfReviews>0 && numberOfPages==0) {
        	 numberOfPages=1;
         }
         for (int i = 1; i <= numberOfPages; i++) {
             allReviews.addAll(getReviewsFromDoc(currentPage));
             currentPage=getDocFromUrl(url+Integer.toString(i+1));
         }
         return allReviews;
    }
    public static void main(String args[]) throws IOException, ParseException{
        ArrayList allReviews=new ArrayList();
          String asin="B07X2KLYSF";
          String url="https://www.amazon.in/product-reviews/"+asin+"/?s&pageNumber=";
          Document currentPage=getDocFromUrl(url+"1");
          //loop to get pages
          //Showing 1-10 of 2,752 reviews
          Element count = currentPage.select("[data-hook='cr-filter-info-review-count']").get(0);
          String scount=count.text();
          int pos=scount.indexOf("of");
          int noOfReviews=((Long) NumberFormat.getNumberInstance(java.util.Locale.US).parse(scount.substring(pos+3))).intValue();
          int numberOfPages=noOfReviews/10;
          numberOfPages=(numberOfPages<10)?(numberOfPages):(10);
          for (int i = 2; i <= numberOfPages; i++) {
              allReviews.addAll(getReviewsFromDoc(currentPage));
              currentPage=getDocFromUrl(url+Integer.toString(i));
          }
          System.out.println(allReviews.size());      
    }
}
