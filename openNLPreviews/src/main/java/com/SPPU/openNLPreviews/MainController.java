package com.SPPU.openNLPreviews;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.mavenproject1.comment_classification.ClassificationProcess;
import com.mycompany.mavenproject1.parse.GetAndParse;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;  

	@RestController  
	public class MainController {  
		@GetMapping("/greet")  
	    public String display(@RequestParam
	    		(name="url", required=true) String url)  
	    {  
	        return "HI"+url;  
	    }
		@GetMapping("/getLabelCount")
		@ResponseBody
		public Map<String, Object> endPointExample(@RequestParam
	    		(name="asin", required=true) String asin) throws ParseException, IOException {

		    Map<String, Object> rtn = new LinkedHashMap<>();
		    rtn.put("pic", asin);
		    ArrayList data=new GetAndParse().getData(asin);
		    if(data.size()>0) {
		    System.out.println("scrapes are "+data);
		    try {
		    	ArrayList classList=new ClassificationProcess().initProcess(data);
		    	ArrayList values=new ArrayList();
		    	for(int i=0;i<classList.size();i++)
		        {
		            ArrayList Single=(ArrayList)classList.get(i);
		            values.add(Single.size());
		        }
		    	rtn.put("values", values.toString());
			} catch (WriteException | BiffException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    }else {
		    	rtn.put("error", "No reviews");
		    }
		    return rtn;

		}
	}  