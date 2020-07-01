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

This Reference application is a guide for using Bundle Profile APIs for Consumer Product Enrollment. 
Please see here for more details on the API:[Mastercard Developers.](https://developer.mastercard.com/documentation/bundle-enablement)

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
          
     >**mastercard.bundle.client.p12.path=BundleProfile.p12**,this refers to the p12 file name which we obtained from above steps.
          
     >**mastercard.bundle.client.ref.app.consumer.key=Abcdfefgjhilklmnopqrstuvwxyz-dxcq_zD7IiPa0df175e!22a7fddba56e800000000000000000** this refers to your consumer key which we         get when create a project under API 
            
     >**mastercard.bundle.client.ref.app.keystore.password=pwd** this refers to the pwd which we obtained while creating the p12 key.
     
### Integrating with OpenAPI Generator <a name="integrating-with-openapi-generator"></a>
[Open API Generator](https://github.com/OpenAPITools/openapi-generator) generates API client libraries from the [Open API Specs](https://github.com/OAI/OpenAPI-Specification). 
It provides generators and library templates for supporting multiple languages and frameworks.

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
Once you’ve added the correct properties, we can build the application. We can do this by navigating to the project’s base directory from the terminal and running the following command.

`mvn clean install`

When the project builds successfully, you can then run the following command to start the project

`java -jar target/bundle_client-1.0.0.jar` <Argument>

Argument: An argument which defines the feature user wants to run through the command line. If you don’t specify this argument, it will run all the features (create User, Read User, Update User (product / account)) one after the other.

    * createUser - User enrolment into product (s) .
    * readUser   - Retrieve user’s enrolled product (s) .
    * addProduct - Add product (s) enrolment for user.
    * addAccount - Add a user account to the product (s) .
    * removeAccount - Remove user's account tagged on an enrolled product .
    * replaceAccount - Replace user's primary account number for all products tied to the existing primary account number.
    * replaceUser - Update user information across product (s).
   
## Use Cases <a name="use-cases"></a>

> Case 1: [USER ENROLMENT INTO PRODUCT(S) ](https://developer.mastercard.com/documentation/bundle-enablement#post-user-enrollment)

  - The Bundle Profile API allows you to enroll a user into product (s) based on the product (s) name passed in the request
  - For field level information, refer to model classes.
  
    | URL | Method | Request | Response |
    | :-- | :----- | :------ | :------- |
    | `/users` | POST | [BundleUser](docs/BundleUser.md) | [BundleUserResponse](docs/BundleUserResponse.md) |
    
> Case 2: [RETRIEVE USER'S ENROLLED PRODUCT(S)](https://developer.mastercard.com/documentation/bundle-enablement#get-products-enrolled-for-user)

  - The Bundle Profile API allows you to retrieve the product (s) details for a specific user passed in the request.
  
    | URL | Method | Request | Response |
    | :-- | :----- | :------ | :------- |
    | `/users/{userid}` | GET | NA | NA |
    

> Case 3: [ADD PRODUCT(S) ENROLMENT FOR USER ](https://developer.mastercard.com/documentation/bundle-enablement#patch-add-product)

  - The Bundle Profile API allows to add Product (s) for a specific user passsed in the API Endpoint.
  - For field level information, refer to model classes.
  
    | URL | Method | Request | Response |
    | :-- | :----- | :------ | :------- |
    | `/users/{userid}/patch` | POST | [BundleUserPatch](docs/BundleUserPatch.md) | [BundleUserResponse](docs/BundleUserResponse.md) |


> Case 4: [ADD A USER ACCOUNT TO THE PRODUCT(S) .](https://developer.mastercard.com/documentation/bundle-enablement#patch-add-user-account)

  - The Bundle Profile API allows to add user account to the Product(s) for a specific user passed in the API Endpoint .
  - For field level information, refer to model classes.
  
    | URL | Method | Request | Response |
    | :-- | :----- | :------ | :------- |
    | `/users/{userid}/patch` | POST | [BundleUserPatch](docs/BundleUserPatch.md) | [BundleUserResponse](docs/BundleUserResponse.md) |
    
> Case 5: [REMOVE USER'S ACCOUNT TAGGED ON AN ENROLLED PRODUCT](https://developer.mastercard.com/documentation/bundle-enablement#patch-remove-account)

  - The Bundle Profile API allows to remove the account tagged on a enrolled product for a specific user passed in the API Endpoint.
  - For field level information, refer to model classes.
  
    | URL | Method | Request | Response |
    | :-- | :----- | :------ | :------- |
    | `/users/{userid}/patch` | POST | [BundleUserPatch](docs/BundleUserPatch.md) | [BundleUserResponse](docs/BundleUserResponse.md) |

> Case 6: [REPLACE USER'S PAN FOR ALL PRODUCTS TIED TO THE EXISTING PAN](https://developer.mastercard.com/documentation/bundleenablement#patch-replace-account)

  - PAN STAND FOR PRIMARY ACCOUNT NUMBER
  - The Bundle Profile API allows to replace user PAN Account with the given new PAN Account on a enrolled product for a specific user passed in the API Endpoint.
  - For field level information, refer to model classes.
  
    | URL | Method | Request | Response |
    | :-- | :----- | :------ | :------- |
    | `/users/{userid}/patch` | POST | [BundleUserPatch](docs/BundleUserPatch.md) | [BundleUserResponse](docs/BundleUserResponse.md) |

> Case 7: [UPDATE USER INFORMATION ACROSS PRODUCT(S)](https://developer.mastercard.com/documentation/bundle-enablement#patch-update-user)

  - The Bundle Profile API allows to update user personal information for a specific user passed in the API Endpoint.
  - For field level information, refer to model classes.
  
    | URL | Method | Request | Response |
    | :-- | :----- | :------ | :------- |
    | `/users/{userid}/patch` | POST | [BundleUserPatch](docs/BundleUserPatch.md) | [BundleUserResponse](docs/BundleUserResponse.md) |
    
    
### Authorization <a name="authorization"></a>
The `com.mastercard.developer.interceptors` package will provide you with some request interceptor classes you can use when configuring your API client. These classes will take care of adding the correct [Authorization](https://github.com/Mastercard/oauth1-signer-java) header before sending the request.

### Request Examples <a name="request-examples"></a>
You can change the default input passed to APIs, modify values in the src/main/resources/templates for POST and UPDATE Usecase.
{userid} field is editable in the RequestHelper.java class.
Below are the static user id values configured for the Bundle Profile Reference Application.
 * user234 for Products: airport, wifi
 * user1235 for Products: rewards, benefits, offers
 * specialpayUser for Product: special pay

## Support <a name="support"></a>
For any clarifications, please reach out to Digital_Enablement_Team_3@mastercard.com.

