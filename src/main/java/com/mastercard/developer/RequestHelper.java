package com.mastercard.developer;


import com.google.gson.Gson;
import com.mastercard.developer.interceptors.OkHttpOAuth1Interceptor;
import org.apache.commons.io.IOUtils;
import org.openapitools.client.ApiClient;
import org.openapitools.client.model.*;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

public class RequestHelper {

    private static String CONSUMER_KEY = "mastercard.bundle.client.ref.app.consumer.key";

    private static String KEYSTORE_PATH = "mastercard.bundle.client.p12.path";

    private static String KEYSTORE_PASSWORD = "mastercard.bundle.client.ref.app.keystore.password";

    public static String BASE_URL = "mastercard.bundle.client.api.base.path";

    private static String ACCESS_PATH = "/bundle/profile";

    public static String USER_ID = "specialpayUser";

    public static String X_Client_CORRELEATION_ID = "bundle-reference-app-stage";

    private static String POST_USER_PAYLOADS = "post-user-wifi.json,post-user-benefits.json,post-user-offers.json,post-user-rewards.json,post-user-airport.json,post-user-specialpay.json";

    private static String TEMPLATES = "templates/";

    private static String PAYLOAD_TO_PATCH_ADD_ONE =
            resourceContent("templates/" + "patch-add-product-airport.json");

    private static String PAYLOAD_TO_PATCH_ADD_ACCOUNT =
            resourceContent("templates/" + "patch-add-account.json");

    private static String PAYLOAD_TO_PATCH_REMOVE_ACCOUNT =
            resourceContent("templates/" + "patch-remove-account.json");

    private static String PAYLOAD_TO_PATCH_REPLACE_ACCOUNT =
            resourceContent("templates/" + "patch-replace-account.json");

    private static String PAYLOAD_TO_PATCH_UPDATE_USER =
            resourceContent("templates/" + "patch-user-update.json");

    public static String CLS_USER_ID = "clsUser";

    private static String POST_CLS_USER_PAYLOAD = resourceContent("templates/" + "post-cls-user.json");

    private static String PUT_CLS_USER_PAYLOAD = resourceContent("templates/" + "put-cls-user.json");

    private static String PATCH_ADD_CLS_USER_PAYLOAD = resourceContent("templates/" + "patches-add-cls-user.json");

    private static String PATCH_REPLACE_CLS_USER_PAYLOAD = resourceContent("templates/" + "patches-add-cls-user.json");

    private static String PATCH_REMOVE_CLS_USER_PAYLOAD = resourceContent("templates/" + "patches-add-cls-user.json");


    private static Properties prop = null;

    private static PrivateKey getPrivateKey() throws Exception {

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

    private static String getConsumerkey() {
        return prop.getProperty(CONSUMER_KEY);
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

    public static ArrayList<InputStream> getPostBody() {
        ArrayList<InputStream> postCreationMap = new ArrayList<>();
        List<String> listOfPostPayloads = Arrays.asList(POST_USER_PAYLOADS.split(","));
        for (String productBasedPayloads : listOfPostPayloads) {
            postCreationMap.add(getResourceStream(TEMPLATES + productBasedPayloads));
        }
        return postCreationMap;

    }
    public static EnrollmentUser getClsPostBody() {
        Gson gson = new Gson();
        return gson.fromJson(POST_CLS_USER_PAYLOAD, EnrollmentUser.class);
    }

    public static EnrollmentUser getClsPutBody() {
        Gson gson = new Gson();
        return gson.fromJson(PUT_CLS_USER_PAYLOAD, EnrollmentUser.class);
    }

    public static DiscretePatchesRequest getClsPatchAddBody() {
        Gson gson = new Gson();
        return gson.fromJson(PATCH_ADD_CLS_USER_PAYLOAD, DiscretePatchesRequest.class);
    }

    public static DiscretePatchesRequest getClsPatchReplaceBody() {
        Gson gson = new Gson();
        return gson.fromJson(PATCH_REPLACE_CLS_USER_PAYLOAD, DiscretePatchesRequest.class);
    }

    public static DiscretePatchesRequest getClsPatchRemoveBody() {
        Gson gson = new Gson();
        return gson.fromJson(PATCH_REMOVE_CLS_USER_PAYLOAD, DiscretePatchesRequest.class);
    }


    public static ApiClient signRequest() throws Exception {
        ApiClient apiClient = new ApiClient();

        apiClient.setBasePath(prop.getProperty(BASE_URL).concat(ACCESS_PATH));
        apiClient.setHttpClient(
                apiClient.getHttpClient()
                        .newBuilder()
                        .addInterceptor(new OkHttpOAuth1Interceptor(getConsumerkey(), getPrivateKey()))
                        .build()
        );
        apiClient.setDebugging(false);
        return apiClient;
    }

    public static BundleUserPatch createAddProductPayload() {
        BundleUserPatch bundleUserPatch = new BundleUserPatch();
        List<PatchDocument> patchDocumentList = new ArrayList<PatchDocument>();
        PatchDocument patchDocument = new PatchDocument();
        BundleUserData bundleUserData = new BundleUserData();
        patchDocument.setOp(PatchDocument.OpEnum.ADD);
        patchDocument.setPath("$");
        patchDocument.setFrom("string");
        Gson gson = new Gson();
        patchDocument.setValue(gson.fromJson(PAYLOAD_TO_PATCH_ADD_ONE, Object.class));
        patchDocumentList.add(patchDocument);
        bundleUserPatch.setPatches(patchDocumentList);
        return bundleUserPatch;
    }

    public static BundleUserPatch createAddAccountPayload() {
        BundleUserPatch bundleUserPatch = new BundleUserPatch();
        List<PatchDocument> patchDocumentList = new ArrayList<PatchDocument>();
        PatchDocument patchDocument = new PatchDocument();
        patchDocument.setOp(PatchDocument.OpEnum.ADD);
        patchDocument.setPath("$.data.products[?(@.product=='')].account");
        patchDocument.setFrom("string");
        Gson gson = new Gson();
        patchDocument.setValue(gson.fromJson(PAYLOAD_TO_PATCH_ADD_ACCOUNT, Object.class));
        patchDocumentList.add(patchDocument);
        bundleUserPatch.setPatches(patchDocumentList);
        return bundleUserPatch;
    }

    public static BundleUserPatch createRemoveAccountPayload() {
        BundleUserPatch bundleUserPatch = new BundleUserPatch();
        List<PatchDocument> patchDocumentList = new ArrayList<PatchDocument>();
        PatchDocument patchDocument = new PatchDocument();
        patchDocument.setOp(PatchDocument.OpEnum.REMOVE);
        patchDocument.setPath("$.data.products[?(@.product=='')].accounts[?(@.pan=='5000000000000000')]");
        patchDocument.setFrom("string");
        Gson gson = new Gson();
        patchDocument.setValue(gson.fromJson(PAYLOAD_TO_PATCH_REMOVE_ACCOUNT,Object.class));
        patchDocumentList.add(patchDocument);
        bundleUserPatch.setPatches(patchDocumentList);
        return bundleUserPatch;
    }

    public static BundleUserPatch createReplaceAccountPayload() {
        BundleUserPatch bundleUserPatch = new BundleUserPatch();
        List<PatchDocument> patchDocumentList = new ArrayList<PatchDocument>();
        PatchDocument patchDocument = new PatchDocument();
        patchDocument.setOp(PatchDocument.OpEnum.REPLACE);
        patchDocument.setPath("$.data.products[?(@.product=='')].accounts[?(@.pan=='5000000000000001')]");
        patchDocument.setFrom("string");
        Gson gson = new Gson();
        patchDocument.setValue(gson.fromJson(PAYLOAD_TO_PATCH_REPLACE_ACCOUNT,Object.class));
        patchDocumentList.add(patchDocument);
        bundleUserPatch.setPatches(patchDocumentList);
        return bundleUserPatch;
    }

    public static BundleUserPatch createReplaceUserPayload() {
        BundleUserPatch bundleUserPatch = new BundleUserPatch();
        List<PatchDocument> patchDocumentList = new ArrayList<PatchDocument>();
        PatchDocument patchDocument = new PatchDocument();
        patchDocument.setOp(PatchDocument.OpEnum.REPLACE);
        patchDocument.setPath("$");
        patchDocument.setFrom("string");
        Gson gson = new Gson();
        patchDocument.setValue(gson.fromJson(PAYLOAD_TO_PATCH_UPDATE_USER,Object.class));
        patchDocumentList.add(patchDocument);
        bundleUserPatch.setPatches(patchDocumentList);
        return bundleUserPatch;
    }


    public static String resourceContent(String classpathName) {
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            return IOUtils.toString(classLoader.getResourceAsStream(classpathName), StandardCharsets.UTF_8.name());
        } catch (Exception ex) {
            throw new IllegalArgumentException("Cannot load resource from classpath '" + classpathName + "'.", ex);
        }
    }


}
