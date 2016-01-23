# apiman-cli: A CLI for apiman [![Build Status](https://travis-ci.org/apiman/apiman-cli.svg?branch=master)](https://travis-ci.org/apiman/apiman-cli)

Manage your [apiman](http://apiman.io) instances from the command line.

Script actions, such as adding APIs and gateways, or display information about a running _apiman_ environment.

## Example

Let's assume you have an _apiman_ server running on http://localhost:8080

Step 1: Create a new API:

    $ ./apiman api create \
            --name example \
            --endpoint http://example.com \
            --initialVersion 1.0 \
            --public \
            --orgName test

Step 2: Publish it:

    $ ./apiman api publish \
            --name example \
            --version 1.0 \
            --orgName test

You're done! Hit your new API at: [http://localhost:8080/apiman-gateway/test/example/1.0](http://localhost:8080/apiman-gateway/test/example/1.0)

## Management example

You can also manage your _apiman_ server.
    
Add a gateway:

    $ ./apiman gateway create \
            --name test-gw \
            --endpoint http://localhost:1234 \
            --username apimanager \
            --password "apiman123!" \
            --type REST

Add a plugin:

    $ ./apiman plugin add \
            --groupId io.apiman.plugins \
            --artifactId apiman-plugins-test-policy \
            --version 1.1.8.Final

You can do much more - see the [Usage](#usage) section.

## Declarative API management

Whilst running commands to control your _apiman_ environment from the CLI can be helpful, sometimes you need to keep your configuration in a file that you can check into your source control system.

For this, _apiman-cli_ has **Declarative Mode**.

Here's how it works:

### Step 1: Declare your API environment

Here's a simple YAML file (you can use JSON if you want):

    # simple.yml
    ---
    org:
      name: "test"
      description: "Test organisation"
      apis:
        - name: "example"
          description: "Example API"
          initialVersion: "1.0"
          published: true
          config:
            endpoint: "http://example.com"
            endpointType: "rest"
            publicService: true
            gateway: "TheGateway"
          policies:
            - name: "CachingPolicy"
              config:
                ttl: 60

### Step 2: Apply the environment declaration

    $ ./apiman apply -f simple.yml
    INFO Loaded declaration: examples/declarative/simple.yml
    INFO Adding org: test
    INFO Adding API: example
    INFO Configuring API: example
    INFO Adding policy 'CachingPolicy' to API: example
    INFO Publishing API: example
    INFO Applied declaration

The following things just happened:

    1. an organisation named `test` was created,
    2. an API named `example` was added with the endpoint `http://example.com`,
    3. a caching policy was added to the API and configured with a TTL of 60 seconds and, finally,
    4. the API was published to the gateway.
    
Declarations also allow you to add gateways, install plugins and more.

## Using placeholders

You can also use placeholders in your declaration files. This helps you reuse declaration files across different environments. For example:

    endpoint: "${myApiEndpoint}"

...then pass them in when you run the _apply_ command:

    ./apiman apply -f simple.yml -P myApiEndpoint=http://example.com

# Requirements

  * An instance of [apiman](http://apiman.io)
  * JDK 8
  * OS X, Windows, Linux

# Usage

    apiman plugin [args...]
    apiman org [args...]
    apiman api [args...]
    apiman gateway [args...]
    apiman apply [args...]
    
    --debug                    : Log at DEBUG level (default: false)
    --help (-h)                : Display usage only (default: false)
    --server (-s) VAL          : Management API server address (default:
                                 http://localhost:8080/apiman)
    --serverPassword (-sp) VAL : Management API server password (default:
                                 admin123!)
    --serverUsername (-su) VAL : Management API server username (default: apiman)
                      
## Manage Organisations
   
    apiman org show [args...]
    apiman org create [args...]
    
### Show Org
    
    apiman org show [args...]
    
    --name (-n) VAL            : Name

### Create Org
    
    apiman org create [args...]
    
    --description (-d) VAL     : Description
    --name (-n) VAL            : Name

## Manage Gateways
   
    apiman gateway test [args...]
    apiman gateway show [args...]
    apiman gateway create [args...]
    apiman gateway list [args...]

### List Gateways
    
    apiman gateway list [args...]
    
### Create Gateway
    
    apiman gateway create [args...]
    
    --description (-d) VAL     : Description
    --endpoint (-e) VAL        : Endpoint
    --name (-n) VAL            : Name
    --password (-p) VAL        : Password
    --type (-t) [REST | SOAP]  : type (default: REST)
    --username (-u) VAL        : Username

### Show Gateway
    
    apiman gateway show [args...]
    
    --name (-n) VAL            : Name

### Test Gateway
    
    apiman gateway test [args...]
    
    --description (-d) VAL     : Description
    --endpoint (-e) VAL        : Endpoint
    --name (-n) VAL            : Name
    --password (-p) VAL        : Password
    --type (-t) [REST | SOAP]  : type (default: REST)
    --username (-u) VAL        : Username

## Manage Plugins
   
    apiman plugin show [args...]
    apiman plugin add [args...]
    apiman plugin list [args...]

### Show Plugin
   
    apiman plugin show [args...]
   
    --debug                    : Log at DEBUG level (default: false)
    --help (-h)                : Display usage only (default: false)
    --id (-i) VAL              : Plugin ID

### Add Plugin
    
    apiman plugin add [args...]
    
    --artifactId (-a) VAL      : Artifact ID
    --classifier (-c) VAL      : Classifier
    --groupId (-g) VAL         : Group ID
    --version (-v) VAL         : Version

### List Plugins
    
    apiman plugin list [args...]
      
## Manage APIs
   
    apiman api create [args...]
    apiman api list [args...]
    apiman api publish [args...]

### Create API
    
    apiman api create [args...]
    
    --endpoint (-e) VAL                 : Endpoint
    --endpointType (-t) VAL             : Endpoint type (default: rest)
    --gateway (-g) VAL                  : Gateway (default: TheGateway)
    --initialVersion (-v) VAL           : Initial version
    --name (-n) VAL                     : API name
    --orgName (-o) VAL                  : Organisation name
    --public (-p)                       : Public API
    --serverVersion (-sv) [v119 | v12x] : Management API server version (default:
                                          v119)

### List APIs
    
    apiman api list [args...]
    
    --orgName (-o) VAL                  : Organisation name
    --serverVersion (-sv) [v119 | v12x] : Management API server version (default:
                                          v119)

### Publish API
    
    apiman api publish [args...]
    
    --version (-v) VAL                  : API version
    --name (-n) VAL                     : API name
    --orgName (-o) VAL                  : Organisation name
    --serverVersion (-sv) [v119 | v12x] : Management API server version (default:
                                          v119)

## Apply declaration

    apiman apply [args...]
    
     --declarationFile (-f) PATH : Declaration file
     -P VAL                      : Set property (key=value)

# Recent changes and Roadmap

## For recent changes see the [Changelog](CHANGELOG.md)

## Roadmap

* Support reading management API configuration from environment variables
* Support adding policies to services
* Better support for non-public services
* Support deletion
* Option to skip or fail for existing items in declarative mode
* Docs - split examples into separate file
* Docs - split detailed API usage into separate file
* Docs - simplify README examples

# Contributing

Pull requests are welcome.

# Author

Pete Cornish (outofcoffee@gmail.com)
