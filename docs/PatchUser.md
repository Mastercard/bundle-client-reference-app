

# PatchUser

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**active** | **Boolean** | A Boolean value indicating the user&#39;s administrative status. When a user is registered with a bank, it is stored in a database with active &#x3D; false flag, the user gets activated and can log in after entering an activation code. |  [optional]
**addresses** | [**List&lt;Address&gt;**](Address.md) |  |  [optional]
**displayName** | **String** | The name of the User, suitable for display to end-users. The name should be the full name of the User being described, if known. |  [optional]
**emails** | [**List&lt;Email&gt;**](Email.md) |  |  [optional]
**identifications** | [**List&lt;Identification&gt;**](Identification.md) |  |  [optional]
**ims** | [**List&lt;Im&gt;**](Im.md) |  |  [optional]
**locale** | **String** | Used to indicate the User&#39;s default location for purposes of localizing items such as currency, date time format, or numerical representations. |  [optional]
**name** | [**Name**](Name.md) |  |  [optional]
**nickName** | **String** | The casual way to address the user in real life, e.g., &#39;Bob&#39; or &#39;Bobby&#39; instead of &#39;Robert&#39;. This attribute should not be used to represent a User&#39;s username (e.g., &#39;bjensen&#39; or &#39;mpepperidge&#39;). |  [optional]
**password** | **String** |  |  [optional]
**phoneNumbers** | [**List&lt;PhoneNumber&gt;**](PhoneNumber.md) |  |  [optional]
**photos** | [**List&lt;Photo&gt;**](Photo.md) |  |  [optional]
**preferredLanguage** | **String** | Indicates Preferred spoken/written Language with/without region form - Internationalization (Locale) like zh_CN, en_GB, fr |  [optional]
**profileUrl** | **String** | A fully qualified URL pointing to a page representing the User&#39;s online profile. |  [optional]
**timezone** | **String** | The User&#39;s time zone in the &#39;Olson&#39; time zone database format, e.g., &#39;America/Los_Angeles&#39;. |  [optional]
**title** | **String** | The user&#39;s title, such as Vice President. |  [optional]
**userType** | **String** | Used to identify the relationship between the organization and the user. Typical values used might be &#39;Contractor&#39;, &#39;Employee&#39;, &#39;Intern&#39;, &#39;Temp&#39;, &#39;External&#39;, and &#39;Unknown&#39;, but any value may be used. |  [optional]
**dateOfBirth** | **String** | date of birth of user in format YYYY-MM-DD |  [optional]
**groups** | [**List&lt;ValueObject&gt;**](ValueObject.md) |  |  [optional]
**roles** | [**List&lt;ValueObject&gt;**](ValueObject.md) |  |  [optional]
**gender** | **String** | Gender of a person. Accepted values are m,f &amp; o. |  [optional]
**preferenceInformation** | [**PreferenceInformation**](PreferenceInformation.md) |  |  [optional]



