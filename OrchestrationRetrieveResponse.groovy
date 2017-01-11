def timestamp = new Date();
println "\n *** ${timestamp} Begin Request *** \n"
   
    /***************************************************************************
    * API Platform Call
    * Update the Response 
    ***************************************************************************/
    HttpResponse httpResponse = (HttpResponse) context.getAttribute("storedResponse")
    if (httpResponse != null) {
        def respStr = httpResponse.getBodyAsType(String.class)
        context.getClientResponse().withBodyAsObject(respStr);
        context.southboundCallout.withHeader("Content-Type", "application/json")
        context.southboundCallout.withBodyAsObject(respStr)
        println "New response is:\n${respStr}"
    } else {
        println "Nothing found in storedResponse"
    }
        
}

println "\n *** ${timestamp} End Request *** \n"
