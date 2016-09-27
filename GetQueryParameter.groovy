def value=Integer.valueOf(context.clientRequest.queryParameters.value)
try {
    println "value=" + value
} catch (Exception e) {
    println "There was an error"
}