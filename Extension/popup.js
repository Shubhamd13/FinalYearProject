// Copyright 2018 The Chromium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

'use strict';


chrome.storage.sync.get('color', function(data) {
  //data.color;
});

var myVar;
function myFunction() {
  //myVar = setTimeout(showPage, 6000);
  chrome.tabs.query({currentWindow: true, active: true}, function(tabs){
	  console.log('current url:'+tabs[0].url);
	  var currenturl=tabs[0].url;
	  var pos=currenturl.indexOf("/dp/");
	  var asin=currenturl.substr(pos+4,10);
	  //debugger;
	  var url = new URL("http://localhost:8080/getLabelCount"),
			params = {asin:asin}
		Object.keys(params).forEach(key => url.searchParams.append(key, params[key]))
	
	  fetch(url)
	  .then(response => response.json())
	  .then(data => {  
		//sendResponse({res:data});
		console.log(data);
		var labels=['WORST', 'DISAPPOINTED', 'AVERAGE', 'SATISFACTORY', 'EXCELLENT'];
		var dataArr=JSON.parse(data.values);
		plotChart(labels,dataArr);
		showPage();
	  });
	});
}

function showPage() {
  document.getElementById("loader").style.display = "none";
  document.getElementById("myDiv").style.display = "block";
}
myFunction();
function summarize() {
	chrome.tabs.executeScript(null, { file: "jquery-3.5.1.min.js" }, function() {
	  chrome.tabs.executeScript(null, { file: "content.js" });
	});
}
function clickHandle(var1,var2,var3){
	//debugger;
	if(var2[0]){
		console.log(var2[0]._index);
		document.getElementById('reviews').innerHTML=var2[0]._index;
	}
}
//chartjs code--------------------------------------
function plotChart(labels,dataArr){
	
	var ctx = document.getElementById('myChart').getContext('2d');
	var chart = new Chart(ctx, {
		// The type of chart we want to create
		type: 'bar',

		// The data for our dataset
		data: {
			labels:labels ,
			datasets: [{
				label: 'My First dataset',
				backgroundColor:[
				"rgba(255, 99, 132, 0.2)",
				"rgba(255, 159, 64, 0.2)",
				"rgba(255, 205, 86, 0.2)",
				"rgba(173, 255, 47, 0.2)",
				"rgba(127, 255, 0 , 0.2)"],
				borderColor:[
				"rgb(255, 99 , 132)",
				"rgb(255, 159, 64)",
				"rgb(255, 205, 86)",
				"rgb(173, 255, 47)",
				"rgb(127, 255, 0)"],
				borderWidth:1,
				data: dataArr
			}]
		},

		// Configuration options go here
		options: {
			legend: {
				display: false
			},
			tooltips: {
				callbacks: {
				label: function(tooltipItem) {
				//console.log(tooltipItem)
					return tooltipItem.yLabel;
				}
			  }
			},
			onClick:clickHandle
		  }
	});
	ctx = document.getElementById('piechart').getContext('2d');
	var myPieChart = new Chart(ctx, {
    type: 'pie',
    // The data for our dataset
		data: {
			labels: labels,
			datasets: [{
				label: 'My First dataset',
				backgroundColor:[
				"rgba(255, 99, 132, 0.2)",
				"rgba(255, 159, 64, 0.2)",
				"rgba(255, 205, 86, 0.2)",
				"rgba(173, 255, 47, 0.2)",
				"rgba(127, 255, 0 , 0.2)"],
				borderColor:[
				"rgb(255, 99 , 132)",
				"rgb(255, 159, 64)",
				"rgb(255, 205, 86)",
				"rgb(173, 255, 47)",
				"rgb(127, 255, 0)"],
				borderWidth:1,
				data: dataArr
			}]
		},

		// Configuration options go here
		options: {
			onClick:clickHandle
		}
});
}
