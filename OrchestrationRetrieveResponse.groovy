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
        
        def length = httpResponse.getHeader("Content-Length")
        if (null != length) {
            context.getClientResponse().withHeader("Content-Length", length)
        }        
        
        println "New response is:\n${respStr}"
    } else {
        println "Nothing found in storedResponse"
    }
        
}

println "\n *** ${timestamp} End Request *** \n"
