# Bundle Profile Reference Application

## Table of Contents
- [Overview](#overview)
  * [Compatibility](#compatibility)
  * [References](#references)
- [Usage](#usage)
  * [Prerequisites](#prerequisites)
  * [Configuration](#configuration)
  * [Integrating with Open API Generator](#integrating-with-openapi-generator)
  * [Build and Execute](#build-and-execute)
- [Use Cases](#use-cases)
- [API Reference](#api-reference)
  * [Authorization](#authorization)
  * [Request Examples](#request-examples)
- [Support](#support)

## Overview <a name="overview"></a>
Bundle Profile is a product enrollment service that provides the ability for user enrollment into Mastercard solutions. 
Bundle Profile enables the following actions:
 * Create a new enrollment into products
 * View enrollment information
 * Update the consumer and product enrollment information
 
 This Reference application is a guide for using Bundle Profile APIs for Consumer Product Enrollment. 
 Please visit Mastercard Developer portal for more details about the API: [Mastercard Developers.](https://developer.mastercard.com/documentation/bundle-enablement)

### Compatibility <a name="compatibility"></a>
* [Java 8](http://www.oracle.com/technetwork/java/javase/downloads/index.html) or later

### References <a name="references"></a>
* [Mastercard’s OAuth Signer library](https://github.com/Mastercard/oauth1-signer-java)
* [Using OAuth 1.0a to Access Mastercard APIs](https://developer.mastercard.com/platform/documentation/using-oauth-1a-to-access-mastercard-apis/)

## Usage <a name="usage"></a>
### Prerequisites <a name="prerequisites"></a>
* [Mastercard Developers Account](https://developer.mastercard.com/dashboard) with access to "MastercardON" API
* A text editor or IDE
* [Java 8](http://www.oracle.com/technetwork/java/javase/downloads/index.html) 
* [Apache Maven 3.3+](https://maven.apache.org/download.cgi)

### Configuration <a name="configuration"></a>

* Clone the project - git clone https://github.com/Mastercard/bundle-client-ref.git.
* Create an account at [Mastercard Developers](https://developer.mastercard.com/account/sign-up).  
* Create new project and add MastercardON API to your project.  
* Configure project and download signing key. It will download the zip file.  
* Unzip the downloaded key and select .p12 file from zip and copy it to src/main/resources in the project folder.
* Open `${project.basedir}/src/main/resources/application.properties` and configure below parameters.
      
     >**mastercard.bundle.client.api.base.path=https://sandbox.api.mastercard.com**, its a static field, for making the API calls.
          
     >**mastercard.bundle.client.p12.path=BundleProfile.p12**, this refers to the p12 file name obtained from aforementioned steps.
          
     >**mastercard.bundle.client.ref.app.consumer.key=Abcdfefgjhilklmnopqrstuvwxyz-dxcq_zD7IiPa0df175e!22a7fddba56e800000000000000000**, this refers to your consumer key you get         when you create a project under API.
            
     >**mastercard.bundle.client.ref.app.keystore.password=pwd**, this refers to the password you obtain when you create the p12 key.
     
### Integrating with OpenAPI Generator <a name="integrating-with-openapi-generator"></a>
You may refer to [Open API Generator](https://github.com/OpenAPITools/openapi-generator)used by "bundle-client-ref" that generates API client libraries from  [Open API Specs](https://github.com/OAI/OpenAPI-Specification). It provides generators and library templates for supporting multiple languages and frameworks.

See also:
* [Open API Generator (maven Plugin)](https://mvnrepository.com/artifact/org.openapitools/openapi-generator-maven-plugin)
* [CONFIG OPTIONS for java](https://github.com/OpenAPITools/openapi-generator/blob/master/docs/generators/java.md)

#### OpenAPI Generator Plugin Configuration

* maven plugin to build modules from Open API - Generator from the swagger given.
```xml
<!-- https://mvnrepository.com/artifact/org.openapitools/openapi-generator-maven-plugin -->
<plugin>
    <groupId>org.openapitools</groupId>
    <artifactId>openapi-generator-maven-plugin</artifactId>
    <version>${openapi-generator.version}</version>
    <executions>
        <execution>
            <goals>
                <goal>generate</goal>
            </goals>
            <configuration>
                <inputSpec>${project.basedir}/src/main/resources/bundle-profile.yaml</inputSpec>
                <generatorName>java</generatorName>
                <library>okhttp-gson</library>
                <generateApiTests>false</generateApiTests>
                <generateModelTests>false</generateModelTests>
                <configOptions>
                    <sourceFolder>src/gen/main/java</sourceFolder>
                    <dateLibrary>java8</dateLibrary>
                </configOptions>
            </configuration>
        </execution>
    </executions>
</plugin>
```

### Build and Execute <a name="build-and-execute"></a>
Once you have added the correct properties, you are ready to build the application. You can do this by navigating to the project’s base directory from the terminal and then by running the following command.

`mvn clean install`

When the project builds successfully, you can run the following command to start the project.

`java -jar target/bundle_client-1.0.0.jar <argument as below>`
 Example : `java -jar target/bundle_client-1.0.0.jar createUser`

Argument: An argument which defines the feature user wants to run through the command line. If you don’t specify this argument, it will run all the features (create User, Read User, Update User (product / account)) one after the other. createUser, readUser, etc mentioned below are the arguments.

    * createUser - User enrollment into products .
    * readUser   - Retrieve user’s enrolled products .
    * addProduct - Add products for a user.
    * addAccount - Add a user account to the products .
    * removeAccount - Remove user's account tagged on enrolled product.
    * replaceAccount - Replace user's primary account number for all products tied to the existing primary account number.
    * replaceUser - Update the user information across products.
   
## Use Cases <a name="use-cases"></a>

> Case 1: [POST User Enrollment](https://developer.mastercard.com/documentation/bundle-enablement#post-user-enrollment) for user enrollment into products 

  - The Bundle Profile API allows you to enroll a user into products based on the product’s name passed in the request.
  - For field level information, refer to model classes.
  
    | URL | Method | Request | Response |
    | :-- | :----- | :------ | :------- |
    | `/users` | POST | [BundleUser](docs/BundleUser.md) | [BundleUserResponse](docs/BundleUserResponse.md) |
    
> Case 2: [GET Products Enrolled for User](https://developer.mastercard.com/documentation/bundle-enablement#get-products-enrolled-for-user) to Retrieve user’s enrolled products  
  - The Bundle Profile API allows you to retrieve the product’s details for a specific User ID passed in the request.
  
    | URL | Method | Request | Response |
    | :-- | :----- | :------ | :------- |
    | `/users/{userid}` | GET | NA | [BundleUserResponse](docs/BundleUserResponse.md) |
    
> Case 3: [PATCH Add User Account](https://developer.mastercard.com/documentation/bundle-enablement#patch-add-user-account) to add user account to the products.

  - The Bundle Profile API allows to add user account to the products enrolled for a specific user passed in the API Endpoint. .
  - For field level information, refer to model classes.
  
    | URL | Method | Request | Response |
    | :-- | :----- | :------ | :------- |
    | `/users/{userid}/patch` | POST | [BundleUserPatch](docs/BundleUserPatch.md) | [BundleUserResponse](docs/BundleUserResponse.md) |
    
 > Case 4: [PATCH Add Product](https://developer.mastercard.com/documentation/bundle-enablement#patch-add-product) to add products for a user.

  - The Bundle Profile API allows to add Products for a specific user passed in the API Endpoint.
  - For field level information, refer to model classes.
  
    | URL | Method | Request | Response |
    | :-- | :----- | :------ | :------- |
    | `/users/{userid}/patch` | POST | [BundleUserPatch](docs/BundleUserPatch.md) | [BundleUserResponse](docs/BundleUserResponse.md) |
    
> Case 5: [PATCH Remove Account](https://developer.mastercard.com/documentation/bundle-enablement#patch-remove-account) to remove user’s account tagged on an enrolled product.

  - The Bundle Profile API allows to remove the account tagged on an enrolled product for a specific user passed in the API Endpoint.
  - For field level information, refer to model classes.
  
    | URL | Method | Request | Response |
    | :-- | :----- | :------ | :------- |
    | `/users/{userid}/patch` | POST | [BundleUserPatch](docs/BundleUserPatch.md) | [BundleUserResponse](docs/BundleUserResponse.md) |

> Case 6: [PATCH Replace Account](https://developer.mastercard.com/documentation/bundleenablement#patch-replace-account) to Replace User’s account number for all products tied             to the existing payment card account number.

  - The Bundle Profile API allows to replace user PAN Account with the given new PAN Account on an enrolled product for a specific user passed in the API Endpoint.
  - For field level information, refer to model classes.
  
    | URL | Method | Request | Response |
    | :-- | :----- | :------ | :------- |
    | `/users/{userid}/patch` | POST | [BundleUserPatch](docs/BundleUserPatch.md) | [BundleUserResponse](docs/BundleUserResponse.md) |

> Case 7: [PATCH Update User](https://developer.mastercard.com/documentation/bundle-enablement#patch-update-user) to update user information across products.

  - The Bundle Profile API allows to update user personal information for a specific user passed in the API Endpoint.
  - For field level information, refer to model classes.
  
    | URL | Method | Request | Response |
    | :-- | :----- | :------ | :------- |
    | `/users/{userid}/patch` | POST | [BundleUserPatch](docs/BundleUserPatch.md) | [BundleUserResponse](docs/BundleUserResponse.md) |
    
    
### Authorization <a name="authorization"></a>
For configuring your API client, the`com.mastercard.developer.interceptors` package provides you some request interceptor classes. These classes will take care of adding the correct [Authorization](https://github.com/Mastercard/oauth1-signer-java) header before sending the request.

### Request Examples <a name="request-examples"></a>
You can change the default input passed to APIs, modify values in the src/main/resources/templates for POST and UPDATE Use case. {userid} field is editable in the RequestHelper.java class. Below are the static User ID values configured for the Bundle Profile Reference Application. You may pass the below User IDs for GET operation to retrieve information on user’s enrolled products.

* For Products (airport, Wi-Fi), User ID - user234 is supported
* For Products (rewards, benefits, offers), User ID - user1235 is supported
* For Product: specialpay (Note: This is a test product), User ID - specialpayUser is supported


## Support <a name="support"></a>
For any clarifications, please reach out to Digital_Enablement_Team_3@mastercard.com.

