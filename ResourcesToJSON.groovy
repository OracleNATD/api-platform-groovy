def url = ""
url = context.clientRequest.getApiPathInfo()
println(url)

// Sample URL for testing...
//def url = '/platform/NSI/domains/somedomain.com/accounts/34159006'

// Grap the resouces from the URL
def resources = url.tokenize('/')
println "resources = " + resources

// Put the resources into a map
def resourcesMap = [:]
def key, value = ""
resources.each {
    println "Resource: $it" 
    if (key){         
        resourcesMap.put(key, it)
        key = ""
    } else { 
        key = it
        value = ""
    }
}
println "resourcesMap = " + resourcesMap
// If we know the resources...
println "platformId = " + resourcesMap.get('platform')
println "domainId = " + resourcesMap.get('domains')
println "accountId = " + resourcesMap.get('accounts')
println ""

//Get the query parameters
def params = context.clientRequest.getQueryParameters()
println "params = " + params
def clientRequestId = params.get('clientRequestId');
println "clientRequestId = " + clientRequestId

// Define the JSON body
def apiAccessKey = [apiAccessKey: '364735764358']

def request = [
    platformId: resourcesMap.get('platform'),
    action: 'Query',
    clientRequestId: clientRequestId,
    objectType: 'Account',
    data: [
        domain: resourcesMap.get('domains'),
        accountId: resourcesMap.get('accounts')            
    ]
]
def message = [
    clientInfo: apiAccessKey,
    request: [request]
]

def json = groovy.json.JsonOutput.toJson(message)

println(groovy.json.JsonOutput.prettyPrint(json));

context.southboundCallout.withBodyAsObject(json)