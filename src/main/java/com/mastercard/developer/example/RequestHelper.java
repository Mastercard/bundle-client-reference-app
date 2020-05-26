package com.mastercard.developer.example;

import com.mastercard.developer.bundle_client.model.Account;
import com.mastercard.developer.bundle_client.model.AccountExternal;
import com.mastercard.developer.bundle_client.model.Address;
import com.mastercard.developer.bundle_client.model.BundleUser;
import com.mastercard.developer.bundle_client.model.BundleUserData;
import com.mastercard.developer.bundle_client.model.BundleUserPatch;
import com.mastercard.developer.bundle_client.model.Email;
import com.mastercard.developer.bundle_client.model.Name;
import com.mastercard.developer.bundle_client.model.PatchDocument;
import com.mastercard.developer.bundle_client.model.PatchDocumentValue;
import com.mastercard.developer.bundle_client.model.ProductExternal;
import com.mastercard.developer.bundle_client.model.User;
import com.mastercard.developer.bundle_client.model.UserProduct;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

public class RequestHelper{

    private static String CONSUMER_KEY ="mastercard.bundle.client.ref.app.consumer.key";

    private static String KEYSTORE_PATH ="mastercard.bundle.client.ref.app.keystore.path";

    private static String KEYSTORE_PASSWORD ="mastercard.bundle.client.ref.app.keystore.password";

    private static String BASE_URL = "mastercard.bundle.client.ref.app.url";

    public static String SUPPORTED_PRODUCTS = "benefits,offers,wifi,rewards,airport,specialpay";

    public static String PRODUCT = "specialpay";

    public static String USER_ID = "specialpayUser";

    public static String  X_Client_CORRELEATION_ID = "bundle-reference-app-stage";


    private static Properties prop = null;

    public static PrivateKey getPrivateKey() throws Exception {

        PrivateKey privateKey = null;
        KeyStore ks = null;

        try {

            ks = KeyStore.getInstance("PKCS12");
            InputStream is = getResourceStream(prop.getProperty(KEYSTORE_PATH));
            ks.load(is, prop.getProperty(KEYSTORE_PASSWORD).toCharArray());
            Enumeration aliases = ks.aliases();
            String keyAlias = "";

            while (aliases.hasMoreElements()) {
                keyAlias = (String) aliases.nextElement();
            }

            privateKey = (PrivateKey) ks.getKey(keyAlias, prop.getProperty(KEYSTORE_PASSWORD).toCharArray());
            is.close();
        } catch (Exception e) {
        }

        return privateKey;
    }

    public static String getConsumerkey() {
        return prop.getProperty(CONSUMER_KEY);
    }

    public static String getBaseURL() {
        return prop.getProperty(BASE_URL);
    }


    private static InputStream getResourceStream(String inputString) {
        return RequestHelper.class.getClassLoader().getResourceAsStream(inputString);
    }



    public static void loadProperties() {
        if (prop == null || prop.isEmpty()) {
            try (InputStream input = RequestHelper.class.getClassLoader()
                    .getResourceAsStream("application.properties")) {
                prop = new Properties();
                if (input == null) {
                    return;
                }
                prop.load(input);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static BundleUserPatch createAddProductPayload(String product) {
        BundleUserPatch bundleUserPatch = new BundleUserPatch();
        List<PatchDocument> patchDocumentList = new ArrayList<PatchDocument>();
        PatchDocument patchDocument = new PatchDocument();
        PatchDocumentValue patchDocumentValue = new PatchDocumentValue();
        BundleUserData bundleUserData = new BundleUserData();
        patchDocument.setOp("add");
        patchDocument.setPath("$");
        patchDocument.setFrom("string");
        User user = new User();
        Address address = new Address();
        List<Address> addresses = getUserAddresses(address);
        user.setAddresses(addresses);

        Email email = new Email();
        List<Email> emails = getUserEmails(email);
        user.setEmails(emails);

        Name name = new Name();
        name.setGivenName("Sally");
        name.setFamilyName("Smitherines");
        user.setName(name);
        user.setPreferredLanguage("en");
        user.setUserId("specialpayUser");
        user.setUserName("user123partner1233");
        bundleUserData.setUser(user);


        List<UserProduct> userProduct = new ArrayList<UserProduct>();
        UserProduct product1 = new UserProduct();
        product1.setProduct(product);
        userProduct.add(product1);
        Account account = new Account();
        account.setPan("5000000000000000");
        account.setIca(1017L);
        account.setCvcCode("876");
        account.setCardExpiryDate("02/2022");
        account.setNameOnCard("nameOnCard");
        account.setAccountType("accountType");
        Object object = new Object();
        account.setObject(object);
        List<Account> accounts = new ArrayList<>();
        accounts.add(account);
        product1.setAccounts(accounts);
        bundleUserData.setProducts(userProduct);
        patchDocumentValue.setData(bundleUserData);
        patchDocument.setValue(patchDocumentValue);
        patchDocumentList.add(patchDocument);
        bundleUserPatch.setPatch(patchDocumentList);

        return bundleUserPatch;
    }


    public static BundleUserPatch createAddAccountPayload() {
        BundleUserPatch bundleUserPatch = new BundleUserPatch();
        List<PatchDocument> patchDocumentList = new ArrayList<PatchDocument>();
        PatchDocument patchDocument = new PatchDocument();
        PatchDocumentValue patchDocumentValue = new PatchDocumentValue();
        BundleUserData bundleUserData = new BundleUserData();
        UserProduct userProduct = new UserProduct();
        Account account = new Account();
        account.setPan("5000000000000000");
        account.setIca(1017L);
        account.setCvcCode("876");
        account.setCardExpiryDate("02/2022");
        account.setNameOnCard("nameOnCard");
        account.setAccountType("accountType");
        Object object = new Object();
        account.setObject(object);
        List<Account> accounts = new ArrayList<>();
        accounts.add(account);
        patchDocument.setOp("add");
        patchDocument.setPath("$.data.products[?(@.product=='')].account");
        patchDocument.setFrom("string");
        patchDocument.setValue(patchDocumentValue);
        patchDocumentValue.setAccounts(accounts);
        patchDocumentList.add(patchDocument);
        bundleUserPatch.setPatch(patchDocumentList);
        return bundleUserPatch;
    }

    public static BundleUserPatch createRemoveAccountPayload() {
        BundleUserPatch bundleUserPatch = new BundleUserPatch();
        List<PatchDocument> patchDocumentList = new ArrayList<PatchDocument>();
        PatchDocument patchDocument = new PatchDocument();
        PatchDocumentValue patchDocumentValue = new PatchDocumentValue();
        BundleUserData bundleUserData = new BundleUserData();
        UserProduct userProduct = new UserProduct();
        Account account = new Account();
        account.setPan("5000000000000000");
        List<Account> accounts = new ArrayList<>();
        accounts.add(account);
        userProduct.setAccounts(accounts);
        List<UserProduct> products = new ArrayList<>();
        products.add(userProduct);
        bundleUserData.setProducts(products);
        patchDocument.setOp("remove");
        patchDocument.setPath("$.data.products[?(@.product=='')].accounts[?(@.pan=='5000000000000000')]");
        patchDocument.setFrom("string");
        patchDocument.setValue(patchDocumentValue);
        patchDocumentValue.setData(bundleUserData);
        patchDocumentList.add(patchDocument);
        bundleUserPatch.setPatch(patchDocumentList);
        return bundleUserPatch;
    }

    public static BundleUserPatch createReplaceAccountPayload() {
        BundleUserPatch bundleUserPatch = new BundleUserPatch();
        List<PatchDocument> patchDocumentList = new ArrayList<PatchDocument>();
        PatchDocument patchDocument = new PatchDocument();
        PatchDocumentValue patchDocumentValue = new PatchDocumentValue();
        BundleUserData bundleUserData = new BundleUserData();
        UserProduct userProduct = new UserProduct();
        Account account = new Account();
        account.setPan("5000000000000000");
        account.setNameOnCard("XYZ Systems");
        account.setCardExpiryDate("02/2020");
        account.setCvcCode("123");
        List<Account> accounts = new ArrayList<>();
        accounts.add(account);
        userProduct.setAccounts(accounts);
        List<UserProduct> products = new ArrayList<>();
        products.add(userProduct);
        bundleUserData.setProducts(products);
        patchDocument.setOp("replace");
        patchDocument.setPath("$.data.products[?(@.product=='')].accounts[?(@.pan=='5000000000000001')]");
        patchDocument.setFrom("string");
        patchDocument.setValue(patchDocumentValue);
        patchDocumentValue.setData(bundleUserData);
        patchDocumentList.add(patchDocument);
        bundleUserPatch.setPatch(patchDocumentList);
        return bundleUserPatch;
    }

    public static BundleUserPatch createReplaceUserPayload(String product) {
        BundleUserPatch bundleUserPatch = new BundleUserPatch();
        List<PatchDocument> patchDocumentList = new ArrayList<PatchDocument>();
        PatchDocument patchDocument = new PatchDocument();
        PatchDocumentValue patchDocumentValue = new PatchDocumentValue();
        BundleUserData bundleUserData = new BundleUserData();
        patchDocument.setOp("replace");
        patchDocument.setPath("$");
        patchDocument.setFrom("string");
        User user = new User();
        Address address = new Address();
        List<Address> addresses = getUserAddresses(address);
        user.setAddresses(addresses);

        Email email = new Email();
        List<Email> emails = getUserEmails(email);
        user.setEmails(emails);

        Name name = new Name();
        name.setGivenName("Sally");
        name.setFamilyName("Smitherines");
        user.setName(name);
        user.setPreferredLanguage("en");
        user.setUserId("specialpayUser");
        user.setUserName("user123partner1233");
        bundleUserData.setUser(user);


        List<UserProduct> userProduct = new ArrayList<UserProduct>();
        UserProduct product1 = new UserProduct();
        product1.setProduct(product);
        userProduct.add(product1);
        Account account = new Account();
        account.setPan("5000000000000000");
        List<Account> accounts = new ArrayList<>();
        accounts.add(account);
        product1.setAccounts(accounts);
        bundleUserData.setProducts(userProduct);
        patchDocumentValue.setData(bundleUserData);
        patchDocument.setValue(patchDocumentValue);
        patchDocumentList.add(patchDocument);
        bundleUserPatch.setPatch(patchDocumentList);

        return bundleUserPatch;
    }


    public static BundleUser getRegistrationObject(String product) {
        BundleUser bundleUser = new BundleUser();
        BundleUserData bundleUserData = new BundleUserData();
        User user = new User();
        UserProduct userProduct = new UserProduct();

        Address address = new Address();
        List<Address> addresses = getUserAddresses(address);
        user.setAddresses(addresses);

        Email email = new Email();
        List<Email> emails = getUserEmails(email);
        user.setEmails(emails);

        Name name = new Name();
        name.setGivenName("Sally");
        name.setFamilyName("Smitherines");
        user.setName(name);
        user.setPreferredLanguage("en");
        List<Account> accounts = new ArrayList<>();
        if (product.equalsIgnoreCase("benefits")) {
            user.setUserId("user1235");
            Account account = getUserAccount(product);
            accounts.add(account);
            userProduct.setProduct("benefits");
        } else if (product.equalsIgnoreCase("offers")) {
            user.setUserId("user1235");
            Account account = getUserAccount(product);
            accounts.add(account);
            userProduct.setProduct("offers");
        } else if (product.equalsIgnoreCase("wifi")) {
            user.setUserId("user234");
            Account account = getUserAccount(product);
            accounts.add(account);
            userProduct.setProduct("wifi");
            ProductExternal productExternal = new ProductExternal();
            productExternal.setUsername("jsmith123");
            productExternal.setPassword("kIy5R5079Qv");
            userProduct.setObject(productExternal);
        } else if (product.equalsIgnoreCase("rewards")) {
            user.setUserId("user1235");
            user.setDateOfBirth("1990-06-25");
            Account account = getUserAccount(product);
            accounts.add(account);
            userProduct.setProduct("rewards");
            ProductExternal productExternal = new ProductExternal();
            productExternal.setAcceptPromoEmail(true);
            productExternal.setAcceptPromoSMS(true);
            productExternal.setBankProductCode("ABMBPCC1");
            productExternal.setBankCustomerNumber("26326444454");
            productExternal.setProgramIdentifier("PROGIDEN5870");
            productExternal.setIca(1216);
            userProduct.setObject(productExternal);
        } else if (product.equalsIgnoreCase("airport")) {
            user.setUserId("user234");
            Account account = getUserAccount(product);
            accounts.add(account);
            userProduct.setProduct("airport");
        } else if (product.equalsIgnoreCase("specialpay")) {
            user.setUserId("specialpayUser");
            Account account = getUserAccount(product);
            accounts.add(account);
            userProduct.setProduct("specialpay");
        }
        userProduct.setAccounts(accounts);
        List<UserProduct> products = new ArrayList<>();
        products.add(userProduct);

        bundleUserData.setProducts(products);
        bundleUserData.setUser(user);
        bundleUser.setData(bundleUserData);

        return bundleUser;

    }

    private static Account getUserAccount(String product) {
        Account account = new Account();
        account.setCardExpiryDate("02/2024");
        account.setNameOnCard("SALLY SMITHERINES");
        account.setPan("1234xxxxxxxx5678");
        AccountExternal accountExternal = new AccountExternal();
        accountExternal.setAccountType("SingleMember");
        accountExternal.setIca("13973");
        accountExternal.setCardProductType("Black");
        if (product.equalsIgnoreCase("benefits")) {
            accountExternal.setExternalMembershipReferenceId("7987984542126");
        } else if (product.equalsIgnoreCase("offers")) {
            accountExternal.setUserOffersId("759874278428");
        } else if (product.equalsIgnoreCase("rewards")) {
            accountExternal.setAccountStatusCode("1");
        }
        account.setObject(accountExternal);
        return account;
    }

    private static List<Email> getUserEmails(Email email) {
        List<Email> emails = new ArrayList<>();
        email.setValue("john5033379289074369@gmail.com");
        emails.add(email);
        return emails;
    }

    private static List<Address> getUserAddresses(Address address) {
        List<Address> addresses = new ArrayList<>();
        address.setCountry("US");
        address.setLocality("city");
        address.setPostalCode("10011");
        address.setRegion("NY");
        address.setStreetAddress("114 5th Ave");
        addresses.add(address);
        return addresses;
    }






}
