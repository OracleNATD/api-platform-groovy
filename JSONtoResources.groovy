
// Sample JSON body for testing...
def messageString =
'{\
    "clientInfo": {\
        "apiAccessKey": "364735764358"\
    },\
    "request": [\
        {\
            "platformId": "NSI",\
            "action": "Query",\
            "clientRequestId": "2371264761-23891273",\
            "objectType": "Account",\
            "data": {\
                "domain": "somedomain.com",\
                "accountId": "34159006"\
            }\
        }\
    ]\
}'

// Convert to Groovy Object
def slurper = new groovy.json.JsonSlurper()
def messageBody = slurper.parseText(messageString)
println(messageBody)
println ""

// Grap the resource values from messageBody
def platformId = messageBody.request[0].platformId
def domainId = messageBody.request[0].data.domain
def accountId = messageBody.request[0].data.accountId
def clientRequestId = messageBody.request[0].clientRequestId

// Build the URL string
def urlString = "platforms/" + platformId + "/domains/" + domainId + "/accounts/" + accountId + "?clientRequestId=" + clientRequestId
println (urlString)
