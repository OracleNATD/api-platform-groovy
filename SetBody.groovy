def apiAccessKey = [apiAccessKey: '364735764358']

def request = [
    platformId: 'NSI',
    action: 'Query',
    clientRequestId: '2371264761-23891273',
    objectType: 'Account',
    data: [
        domain:  'somedomain.com',
        accountId:   '34159006'            
    ]
]
def message = [
    clientInfo: apiAccessKey,
    request: [request]
]

def json = groovy.json.JsonOutput.toJson(message)

println(groovy.json.JsonOutput.prettyPrint(json));

context.southboundCallout.withBodyAsObject(json)