//According to the documentation (https://docs.oracle.com/en/cloud/paas/api-platform-cloud/apfad/implementing-apis.html#GUID-324A2F7B-AABB-49B4-8F7C-7BBDD882EA53), this headers context variable should be available, but I can't get it to deploy
//dedesHeader = ${headers.DEDES}
// Using https://docs.oracle.com/cd/E50778_01/doc.60/e59657/toc.htm
def dedesHeader = context.apiRequest.getHeader("DEDES")
println "DEDES Header = ${dedesHeader}"

// Get all the headers
def headersMap = (Map) context.apiRequest.getHeaders()
println "Headers = ${headersMap}"
