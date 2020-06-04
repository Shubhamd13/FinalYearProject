chrome.runtime.sendMessage(
    {contentScriptQuery:"getReviews",url: window.location},
		response =>{
			console.log(response);
		}
	);