# BundleProfileApi

All URIs are relative to *https://api.mastercard.com/bundle/profile*

Method | HTTP request | Description
------------- | ------------- | -------------
[**createClsUser**](BundleProfileApi.md#createClsUser) | **POST** /enrollments/users | Enroll User Profile
[**createUser**](BundleProfileApi.md#createUser) | **POST** /users | Create Profile
[**deleteClsUser**](BundleProfileApi.md#deleteClsUser) | **DELETE** /enrollments/users/{user_id} | Delete Cls User Profile by Id
[**patchUser**](BundleProfileApi.md#patchUser) | **POST** /users/{user_id}/patches | Partially Update Profile
[**readClsUser**](BundleProfileApi.md#readClsUser) | **GET** /enrollments/users/{user_id} | Find User by Id
[**readUser**](BundleProfileApi.md#readUser) | **GET** /users/{user_id} | Find User by Id
[**updateCls**](BundleProfileApi.md#updateCls) | **PUT** /enrollments/users/{user_id} | Update User Profile
[**updateClsPartially**](BundleProfileApi.md#updateClsPartially) | **PATCH** /enrollments/users/{user_id} | Update User Profile Partially


<a name="createClsUser"></a>
# **createClsUser**
> EnrollmentResponse createClsUser(enrollmentUser, xClientCorrelationId)

Enroll User Profile

Enroll Profile for User

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.BundleProfileApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.mastercard.com/bundle/profile");

    BundleProfileApi apiInstance = new BundleProfileApi(defaultClient);
    EnrollmentUser enrollmentUser = new EnrollmentUser(); // EnrollmentUser | User Profile body
    String xClientCorrelationId = "xClientCorrelationId_example"; // String | Client-defined correlation ID
    try {
      EnrollmentResponse result = apiInstance.createClsUser(enrollmentUser, xClientCorrelationId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling BundleProfileApi#createClsUser");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **enrollmentUser** | [**EnrollmentUser**](EnrollmentUser.md)| User Profile body |
 **xClientCorrelationId** | **String**| Client-defined correlation ID | [optional]

### Return type

[**EnrollmentResponse**](EnrollmentResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Create resource with messages |  -  |
**0** | Unexpected error |  -  |

<a name="createUser"></a>
# **createUser**
> BundleUserResponse createUser(body, xClientCorrelationId)

Create Profile

Create Profile user and associated product service(s) consents and card account(s)

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.BundleProfileApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.mastercard.com/bundle/profile");

    BundleProfileApi apiInstance = new BundleProfileApi(defaultClient);
    BundleUser body = new BundleUser(); // BundleUser | Bundle Profile body
    String xClientCorrelationId = "xClientCorrelationId_example"; // String | Unique request identifier from the client, usually a Version 4 UUID string (36 characters long including dashes, such as \"f6fd03c6-2dfe-46ea-99f9-6fd7bc34d8d8\")
    try {
      BundleUserResponse result = apiInstance.createUser(body, xClientCorrelationId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling BundleProfileApi#createUser");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**BundleUser**](BundleUser.md)| Bundle Profile body |
 **xClientCorrelationId** | **String**| Unique request identifier from the client, usually a Version 4 UUID string (36 characters long including dashes, such as \&quot;f6fd03c6-2dfe-46ea-99f9-6fd7bc34d8d8\&quot;) | [optional]

### Return type

[**BundleUserResponse**](BundleUserResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Create resource with messages |  -  |
**201** | Successful create resource |  -  |
**0** | Unexpected error |  -  |

<a name="deleteClsUser"></a>
# **deleteClsUser**
> EnrollmentResponse deleteClsUser(userId, xClientCorrelationId, ifMatch)

Delete Cls User Profile by Id

Returns a Users on the basis of provided id as path param (https://tools.ietf.org/html/draft-ietf-scim-api-19#section-3.4.2.1)

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.BundleProfileApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.mastercard.com/bundle/profile");

    BundleProfileApi apiInstance = new BundleProfileApi(defaultClient);
    String userId = "userId_example"; // String | Opaque identifier for the consumer. Issuer/Acquirer to send it complies to IETF RFC2396; MC provisions it depends on the use case, such as user123-partnerBank
    String xClientCorrelationId = "xClientCorrelationId_example"; // String | Unique request identifier from the client, usually a Version 4 UUID string (36 characters long including dashes, such as \"f6fd03c6-2dfe-46ea-99f9-6fd7bc34d8d8\")
    String ifMatch = "ifMatch_example"; // String | version
    try {
      EnrollmentResponse result = apiInstance.deleteClsUser(userId, xClientCorrelationId, ifMatch);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling BundleProfileApi#deleteClsUser");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **userId** | **String**| Opaque identifier for the consumer. Issuer/Acquirer to send it complies to IETF RFC2396; MC provisions it depends on the use case, such as user123-partnerBank |
 **xClientCorrelationId** | **String**| Unique request identifier from the client, usually a Version 4 UUID string (36 characters long including dashes, such as \&quot;f6fd03c6-2dfe-46ea-99f9-6fd7bc34d8d8\&quot;) | [optional]
 **ifMatch** | **String**| version | [optional]

### Return type

[**EnrollmentResponse**](EnrollmentResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Delete user with returned userId |  -  |
**0** | Unexpected error |  -  |

<a name="patchUser"></a>
# **patchUser**
> BundleUserResponse patchUser(userId, body, xClientCorrelationId)

Partially Update Profile

Partial updates of Profile user and associated product service(s) consents and card account(s)

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.BundleProfileApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.mastercard.com/bundle/profile");

    BundleProfileApi apiInstance = new BundleProfileApi(defaultClient);
    String userId = "\"user1235\""; // String | Opaque identifier for the consumer. Issuer/Acquirer to send it complies to IETF RFC2396; MC provisions it depends on the use case, such as user123-partnerBank
    BundleUserPatch body = new BundleUserPatch(); // BundleUserPatch | Bundle Profile body
    String xClientCorrelationId = "xClientCorrelationId_example"; // String | Unique request identifier from the client, usually a Version 4 UUID string (36 characters long including dashes, such as \"f6fd03c6-2dfe-46ea-99f9-6fd7bc34d8d8\")
    try {
      BundleUserResponse result = apiInstance.patchUser(userId, body, xClientCorrelationId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling BundleProfileApi#patchUser");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **userId** | **String**| Opaque identifier for the consumer. Issuer/Acquirer to send it complies to IETF RFC2396; MC provisions it depends on the use case, such as user123-partnerBank | [default to &quot;user1235&quot;]
 **body** | [**BundleUserPatch**](BundleUserPatch.md)| Bundle Profile body |
 **xClientCorrelationId** | **String**| Unique request identifier from the client, usually a Version 4 UUID string (36 characters long including dashes, such as \&quot;f6fd03c6-2dfe-46ea-99f9-6fd7bc34d8d8\&quot;) | [optional]

### Return type

[**BundleUserResponse**](BundleUserResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Update resource with messages |  -  |
**0** | Unexpected error |  -  |

<a name="readClsUser"></a>
# **readClsUser**
> EnrollmentUserResponse readClsUser(userId, xClientCorrelationId)

Find User by Id

Returns a Users on the basis of provided id as path param (https://tools.ietf.org/html/draft-ietf-scim-api-19#section-3.4.2.1)

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.BundleProfileApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.mastercard.com/bundle/profile");

    BundleProfileApi apiInstance = new BundleProfileApi(defaultClient);
    String userId = "userId_example"; // String | Opaque identifier for the consumer. Issuer/Acquirer to send it complies to IETF RFC2396; MC provisions it depends on the use case, such as user123-partnerBank
    String xClientCorrelationId = "xClientCorrelationId_example"; // String | Unique request identifier from the client, usually a Version 4 UUID string (36 characters long including dashes, such as \"f6fd03c6-2dfe-46ea-99f9-6fd7bc34d8d8\")
    try {
      EnrollmentUserResponse result = apiInstance.readClsUser(userId, xClientCorrelationId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling BundleProfileApi#readClsUser");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **userId** | **String**| Opaque identifier for the consumer. Issuer/Acquirer to send it complies to IETF RFC2396; MC provisions it depends on the use case, such as user123-partnerBank |
 **xClientCorrelationId** | **String**| Unique request identifier from the client, usually a Version 4 UUID string (36 characters long including dashes, such as \&quot;f6fd03c6-2dfe-46ea-99f9-6fd7bc34d8d8\&quot;) | [optional]

### Return type

[**EnrollmentUserResponse**](EnrollmentUserResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Read resource with messages |  -  |
**0** | Unexpected error |  -  |

<a name="readUser"></a>
# **readUser**
> BundleUserResponse readUser(userId, xClientCorrelationId)

Find User by Id

Returns a Users on the basis of provided id as path param (https://tools.ietf.org/html/draft-ietf-scim-api-19#section-3.4.2.1)

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.BundleProfileApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.mastercard.com/bundle/profile");

    BundleProfileApi apiInstance = new BundleProfileApi(defaultClient);
    String userId = "\"user234\""; // String | Opaque identifier for the consumer. Issuer/Acquirer to send it complies to IETF RFC2396; MC provisions it depends on the use case, such as user123-partnerBank
    String xClientCorrelationId = "xClientCorrelationId_example"; // String | Unique request identifier from the client, usually a Version 4 UUID string (36 characters long including dashes, such as \"f6fd03c6-2dfe-46ea-99f9-6fd7bc34d8d8\")
    try {
      BundleUserResponse result = apiInstance.readUser(userId, xClientCorrelationId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling BundleProfileApi#readUser");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **userId** | **String**| Opaque identifier for the consumer. Issuer/Acquirer to send it complies to IETF RFC2396; MC provisions it depends on the use case, such as user123-partnerBank | [default to &quot;user234&quot;]
 **xClientCorrelationId** | **String**| Unique request identifier from the client, usually a Version 4 UUID string (36 characters long including dashes, such as \&quot;f6fd03c6-2dfe-46ea-99f9-6fd7bc34d8d8\&quot;) | [optional]

### Return type

[**BundleUserResponse**](BundleUserResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Read resource with messages |  -  |
**0** | Unexpected error |  -  |

<a name="updateCls"></a>
# **updateCls**
> EnrollmentResponse updateCls(userId, enrollmentUser, xClientCorrelationId)

Update User Profile

Update Profile for User

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.BundleProfileApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.mastercard.com/bundle/profile");

    BundleProfileApi apiInstance = new BundleProfileApi(defaultClient);
    String userId = "userId_example"; // String | Opaque identifier for the consumer. Issuer/Acquirer to send it complies to IETF RFC2396; MC provisions it depends on the use case, such as user123-partnerBank
    EnrollmentUser enrollmentUser = new EnrollmentUser(); // EnrollmentUser | User Profile body
    String xClientCorrelationId = "xClientCorrelationId_example"; // String | Client-defined correlation ID
    try {
      EnrollmentResponse result = apiInstance.updateCls(userId, enrollmentUser, xClientCorrelationId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling BundleProfileApi#updateCls");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **userId** | **String**| Opaque identifier for the consumer. Issuer/Acquirer to send it complies to IETF RFC2396; MC provisions it depends on the use case, such as user123-partnerBank |
 **enrollmentUser** | [**EnrollmentUser**](EnrollmentUser.md)| User Profile body |
 **xClientCorrelationId** | **String**| Client-defined correlation ID | [optional]

### Return type

[**EnrollmentResponse**](EnrollmentResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Create resource with messages |  -  |
**0** | Unexpected error |  -  |

<a name="updateClsPartially"></a>
# **updateClsPartially**
> EnrollmentResponse updateClsPartially(userId, discretePatchesRequest, xClientCorrelationId)

Update User Profile Partially

Update Profile for User Partially

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.BundleProfileApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.mastercard.com/bundle/profile");

    BundleProfileApi apiInstance = new BundleProfileApi(defaultClient);
    String userId = "userId_example"; // String | Opaque identifier for the consumer. Issuer/Acquirer to send it complies to IETF RFC2396; MC provisions it depends on the use case, such as user123-partnerBank
    DiscretePatchesRequest discretePatchesRequest = new DiscretePatchesRequest(); // DiscretePatchesRequest | User Profile body
    String xClientCorrelationId = "xClientCorrelationId_example"; // String | Client-defined correlation ID
    try {
      EnrollmentResponse result = apiInstance.updateClsPartially(userId, discretePatchesRequest, xClientCorrelationId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling BundleProfileApi#updateClsPartially");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **userId** | **String**| Opaque identifier for the consumer. Issuer/Acquirer to send it complies to IETF RFC2396; MC provisions it depends on the use case, such as user123-partnerBank |
 **discretePatchesRequest** | [**DiscretePatchesRequest**](DiscretePatchesRequest.md)| User Profile body |
 **xClientCorrelationId** | **String**| Client-defined correlation ID | [optional]

### Return type

[**EnrollmentResponse**](EnrollmentResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Create resource with messages |  -  |
**0** | Unexpected error |  -  |

