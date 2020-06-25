# Bundle Profile Reference Application

## Table of Contents
- [Overview](#overview)
  * [Compatibility](#compatibility)
  * [References](#references)
- [Usage](#usage)
  * [Prerequisites](#prerequisites)
  * [Configuration](#configuration)
  * [Integrating with OpenAPI Generator](#integrating-with-openapi-generator)
  * [Build and Execute](#build-and-execute)
- [Use Cases](#use-cases)
- [API Reference](#api-reference)
  * [Authorization](#authorization)
  * [Request Examples](#request-examples)
- [Support](#support)
- [License](#license)

## Overview <a name="overview"></a>

This Reference application is a guide for using Bundle Profile APIs for Consumer Product Enrollment. 
Please see here for more details on the API:[Mastercard Developers](https://developer.mastercard.com/documentation/bundle-enablement)

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
* Set up the `JAVA_HOME` environment variable to match the location of your Java installation.

### Configuration <a name="configuration"></a>

* Clone the project - git clone https://github.com/Mastercard/bundle-client-ref.git.
* Create an account at [Mastercard Developers](https://developer.mastercard.com/account/sign-up).  
* Create a new project and add `MastercardON` API to your project.   
* Configure project and download signing key. It will download the zip file.  
* unzip the downloaded key and Select `.p12` file from zip and copy it to `src/main/resources` in the project folder.
* Open `${project.basedir}/src/main/resources/application.properties` and configure below parameters.
      
     >**mastercard.bundle.client.api.base.path=https://sandbox.api.mastercard.com**, its a static field, for making the API calls.
          
        **Below properties will be required for authentication of API calls.**
          
     >**mastercard.bundle.client.p12.path=**,  <p12 filename> which we downloaded from above steps for eg : bundle-profile-reference.p12
          
     >**mastercard.bundle.client.ref.app.consumer.key=**, this refers to your consumer key which we get when create a project under API 
            
     >**mastercard.bundle.client.ref.app.keystore.password**, this is the default value of key password. 

### Integrating with OpenAPI Generator <a name="integrating-with-openapi-generator"></a>
[OpenAPI Generator](https://github.com/OpenAPITools/openapi-generator) generates API client libraries from [OpenAPI Specs](https://github.com/OAI/OpenAPI-Specification). 
It provides generators and library templates for supporting multiple languages and frameworks.

See also:
* [OpenAPI Generator (maven Plugin)](https://mvnrepository.com/artifact/org.openapitools/openapi-generator-maven-plugin)
* [OpenAPI Generator (executable)](https://mvnrepository.com/artifact/org.openapitools/openapi-generator-cli)
* [CONFIG OPTIONS for java](https://github.com/OpenAPITools/openapi-generator/blob/master/docs/generators/java.md)

#### OpenAPI Generator Plugin Configuration

* maven plugin to build modules from OpenAPI Generator from the given swagger.
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

#### Generating The API Client Sources
Now that you have all the dependencies you need, you can generate the sources. To do this, use one of the following two methods:

`Using IDE`
* **Method 1**<br/>
  In IntelliJ IDEA, open the Maven window **(View > Tool Windows > Maven)**. Click the icons `Reimport All Maven Projects` and `Generate Sources and Update Folders for All Projects`

* **Method 2**<br/>

  In the same menu, navigate to the commands **({Project name} > Lifecycle)**, select `clean` and `compile` then click the icon `Run Maven Build`. 

`Using Terminal`
* Navigate to the root directory of the project within a terminal window and execute `mvn clean compile` command.

### Build and Execute <a name="build-and-execute"></a>
Once you’ve added the correct properties, we can build the application. We can do this by navigating to the project’s base directory from the terminal and running the following command

`mvn clean install`

When the project builds successfully you can then run the following command to start the project

`java -jar target/bundle_client-1.0.0.jar` <Argument>

Argument: An argument which defines the feature user wants to run through command line. If you don't specify this argument, it will run all the features(createUser,ReadUser,UpdateUser(product,account)) one after the other

    * createUser - Enroll User for all the products
    * readUser   - Get User enrolled for the products 
    * addProduct - add product to the enrolled user.
    * addAccount - add account to the enrolled user.
    * removeAccount - remove Account for a given user.
    * replaceAccount - replaceAccount for a given user
    * replaceUser - update the user details.
   
## Use Cases <a name="use-cases"></a>

> Case 1: [CREATE USER FOR BUNDLE PROFILE](https://developer.mastercard.com/documentation/bundle-enablement#post-user-enrollment)

  - This endpoint provides the capability to enroll user into Product(s) based on the product name passed in the request .
  - Refer to model classes for field level information.
  
    | URL | Method | Request | Response |
    | :-- | :----- | :------ | :------- |
    | `/users` | POST | [BundleUser](docs/BundleUser.md) | [BundleUserResponse](docs/BundleUserResponse.md) |
    
> Case 2: [GET PRODUCT ENROLLED FOR USER](https://developer.mastercard.com/documentation/bundle-enablement#get-products-enrolled-for-user)

  - The Bundle Profile API allows the retrieve the Product(s) that the user is enrolled.
  
    | URL | Method | Request | Response |
    | :-- | :----- | :------ | :------- |
    | `/users/{userid}` | GET | NA | NA |
    

> Case 3: [ADD PRODUCT FOR ENROLLED USER](https://developer.mastercard.com/documentation/bundle-enablement#patch-add-product)

  - The Bundle Profile API allows to add Product(s) enrollment for user.
  - Refer to model classes for field level information.
  
    | URL | Method | Request | Response |
    | :-- | :----- | :------ | :------- |
    | `/users/{userid}/patch` | POST | [BundleUserPatch](docs/BundleUserPatch.md) | [BundleUserResponse](docs/BundleUserResponse.md) |


> Case 4: [ADD USER ACCOUNT FOR ENROLLED PRODUCT](https://developer.mastercard.com/documentation/bundle-enablement#patch-add-user-account)

  - The Bundle Profile API allows to add user account to the Product(s).
  - Refer to model classes for field level information.
  
    | URL | Method | Request | Response |
    | :-- | :----- | :------ | :------- |
    | `/users/{userid}/patch` | POST | [BundleUserPatch](docs/BundleUserPatch.md) | [BundleUserResponse](docs/BundleUserResponse.md) |
    
> Case 5: [REMOVE USER ACCOUNT FOR ENROLLED PRODUCT](https://developer.mastercard.com/documentation/bundle-enablement#patch-remove-account)

  - The Bundle Profile API allows to remove the product enrollment(s) attached to a user PAN account.
  - Refer to model classes for field level information.
  
    | URL | Method | Request | Response |
    | :-- | :----- | :------ | :------- |
    | `/users/{userid}/patch` | POST | [BundleUserPatch](docs/BundleUserPatch.md) | [BundleUserResponse](docs/BundleUserResponse.md) |

> Case 6: [REPLACE USER ACCOUNT FOR ENROLLED PRODUCT](https://developer.mastercard.com/documentation/bundle-enablement#patch-replace-account)

  - The Bundle Profile API allows to replace user PAN Account across Product(s) that are enrolled with PAN tagged to it.
  - Refer to model classes for field level information.
  
    | URL | Method | Request | Response |
    | :-- | :----- | :------ | :------- |
    | `/users/{userid}/patch` | POST | [BundleUserPatch](docs/BundleUserPatch.md) | [BundleUserResponse](docs/BundleUserResponse.md) |

> Case 5: [REPLACE USER FOR ENROLLED PRODUCT](https://developer.mastercard.com/documentation/bundle-enablement#patch-replace-account)

  - The Bundle Profile API allows to replace user PAN Account across Product(s) that are enrolled with PAN tagged to it.
  - Refer to model classes for field level information.
  
    | URL | Method | Request | Response |
    | :-- | :----- | :------ | :------- |
    | `/users/{userid}/patch` | POST | [BundleUserPatch](docs/BundleUserPatch.md) | [BundleUserResponse](docs/BundleUserResponse.md) |
    
    
### Authorization <a name="authorization"></a>
The `com.mastercard.developer.interceptors` package will provide you with some request interceptor classes you can use when configuring your API client. These classes will take care of adding the correct `Authorization` header before sending the request.

### Request Examples <a name="request-examples"></a>
You can change the default input passed to APIs, modify values in the src/main/resources/templates for POST and UPDATE Usecase.
You can change the userId value pass in endpoint in RequestHelper.java USER_ID field.
Below are the static user id value configured for the Bundle Profile Reference Application 
 * user234 for Products: airport, wifi
 * user1235 for Products: rewards, benefits, offers
 * specialpayUser for Product: specialpay 

## Support <a name="support"></a>
If you would like further information, please send an email to Digital_Enablement_Team_3@mastercard.com

## License <a name="license"></a>
Copyright 2020 Mastercard
 
Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 
       http://www.apache.org/licenses/LICENSE-2.0
 
Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
