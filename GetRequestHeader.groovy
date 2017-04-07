//According to the documentation (http://docs-uat.us.oracle.com/en/cloud/paas/api-platform-cloud/apfad/implementing-apis.html#GUID-324A2F7B-AABB-49B4-8F7C-7BBDD882EA53), this headers context variable should be available, but I can't get it to deploy
//dedesHeader = ${headers.DEDES}
def dedesHeader = context.apiRequest.getHeader("DEDES")
println "DEDES Header = ${dedesHeader}"
