package com.mastercard.developer.example;


import com.google.gson.Gson;
import com.mastercard.developer.bundle_client.ApiClient;
import com.mastercard.developer.bundle_client.ApiException;
import com.mastercard.developer.bundle_client.api.BundleProfileApi;
import com.mastercard.developer.bundle_client.model.BundleUser;
import com.mastercard.developer.bundle_client.model.BundleUserPatch;
import com.mastercard.developer.bundle_client.model.BundleUserResponse;
import com.mastercard.developer.interceptors.OkHttpOAuth1Interceptor;
import java.io.IOException;
import java.io.UnsupportedEncodingException;


public class BundleProfileApiDemo {


    public static void main(String[] args) throws Exception {

        RequestHelper.loadProperties();

        ApiClient apiClient = new ApiClient();
        apiClient.setBasePath(RequestHelper.getBaseURL());
        apiClient.setHttpClient(
                apiClient.getHttpClient()
                        .newBuilder()
                        .addInterceptor(new OkHttpOAuth1Interceptor(RequestHelper.getConsumerkey(), RequestHelper.getPrivateKey()))
                        .build()
        );
        apiClient.setDebugging(false);

        BundleProfileApi bundleProfileApi = new BundleProfileApi(apiClient);


        if (runThisScenario(args, "readUser")) {
            printMessage("STARTING GET USER FOR BUNDLE PROFILE");
            executeGetBundleUserScenario(bundleProfileApi);
        }

        if (runThisScenario(args, "createUser")) {
            printMessage("STARTING CREATE USER FOR BUNDLE PROFILE");
            String[] supportedProducts = RequestHelper.SUPPORTED_PRODUCTS.split(",");
            for (String product : supportedProducts) {
                executeCreateBundleUserScenario(bundleProfileApi, product);
            }
        }

        if (runThisScenario(args, "addProduct")) {
            printMessage("STARTING ADD PRODUCT FOR BUNDLE PROFILE");
            executeAddProductBundleUserScenario(bundleProfileApi, RequestHelper.PRODUCT);
        }

        if (runThisScenario(args, "addAccount")) {
            printMessage("STARTING ADD ACCOUNT FOR BUNDLE PROFILE");
            executeAddAccountBundleUserScenario(bundleProfileApi);
        }

        if (runThisScenario(args, "removeAccount")) {
            printMessage("STARTING REMOVE ACCOUNT FOR BUNDLE PROFILE");
            executeRemoveAccountBundleUserScenario(bundleProfileApi);
        }

        if (runThisScenario(args, "replaceAccount")) {
            printMessage("STARTING REPLACE ACCOUNT FOR BUNDLE PROFILE");
            executeReplaceAccountBundleUserScenario(bundleProfileApi);
        }

        if (runThisScenario(args, "replaceUser")) {
            printMessage("STARTING REPLACE USER FOR BUNDLE PROFILE");
            executeReplaceUserDetailScenario(bundleProfileApi, RequestHelper.PRODUCT);
        }
    }


    private static void executeGetBundleUserScenario(BundleProfileApi bundleProfileApi) throws ApiException {
        Gson gson = new Gson();
        BundleUserResponse bundleUserResponse = bundleProfileApi.readUser(RequestHelper.USER_ID, RequestHelper.X_Client_CORRELEATION_ID);
        System.out.println(gson.toJson(bundleUserResponse));

    }

    private static void executeCreateBundleUserScenario(BundleProfileApi bundleProfileApi, String product) throws IOException, ApiException {
        Gson gson = new Gson();
        BundleUser createUserPayload = RequestHelper.getRegistrationObject(product);
        BundleUserResponse bundleUserResponse = bundleProfileApi.createUser(createUserPayload, RequestHelper.X_Client_CORRELEATION_ID);
        System.out.println(gson.toJson(bundleUserResponse));
    }


    private static void executeAddProductBundleUserScenario(BundleProfileApi bundleProfileApi, String addProduct) throws IOException, ApiException {
        Gson gson = new Gson();
        BundleUserPatch bundleUserPatch = RequestHelper.createAddProductPayload(addProduct);
        BundleUserResponse bundleUserResponse = bundleProfileApi.patchUser(bundleUserPatch.getPatch().get(0).getValue().getData().getUser().getUserId(), bundleUserPatch, RequestHelper.X_Client_CORRELEATION_ID);
        System.out.println(gson.toJson(bundleUserResponse));

    }

    private static void executeAddAccountBundleUserScenario(BundleProfileApi bundleProfileApi) throws ApiException, UnsupportedEncodingException {
        Gson gson = new Gson();
        BundleUserPatch bundleUserPatch = RequestHelper.createAddAccountPayload();
        BundleUserResponse bundleUserResponse = bundleProfileApi.patchUser(RequestHelper.USER_ID, bundleUserPatch, RequestHelper.X_Client_CORRELEATION_ID);
        System.out.println(gson.toJson(bundleUserResponse));
    }


    private static void executeRemoveAccountBundleUserScenario(BundleProfileApi bundleProfileApi) throws ApiException, UnsupportedEncodingException {
        Gson gson = new Gson();
        BundleUserPatch bundleUserPatch = RequestHelper.createRemoveAccountPayload();
        BundleUserResponse bundleUserResponse = bundleProfileApi.patchUser(RequestHelper.USER_ID, bundleUserPatch, RequestHelper.X_Client_CORRELEATION_ID);
        System.out.println(gson.toJson(bundleUserResponse));
    }

    private static void executeReplaceAccountBundleUserScenario(BundleProfileApi bundleProfileApi) throws ApiException, UnsupportedEncodingException {
        Gson gson = new Gson();
        BundleUserPatch bundleUserPatch = RequestHelper.createReplaceAccountPayload();
        BundleUserResponse bundleUserResponse = bundleProfileApi.patchUser(RequestHelper.USER_ID, bundleUserPatch, RequestHelper.X_Client_CORRELEATION_ID);
        System.out.println(gson.toJson(bundleUserResponse));
    }

    private static void executeReplaceUserDetailScenario(BundleProfileApi bundleProfileApi, String userUpdate) throws ApiException, IOException {
        Gson gson = new Gson();
        BundleUserPatch bundleUserPatch = RequestHelper.createReplaceUserPayload(userUpdate);
        BundleUserResponse bundleUserResponse = bundleProfileApi.patchUser(RequestHelper.USER_ID, bundleUserPatch, RequestHelper.X_Client_CORRELEATION_ID);
        System.out.println(gson.toJson(bundleUserResponse));
    }




    private static boolean runThisScenario(String[] args, String scenario) {
        return (args != null && args.length > 0 && args[0].contains(scenario)) || (args == null || args.length == 0);
    }


    private static void printMessage(String scenario) {
        System.out.println("--------------------------------------------------------------------");
        System.out.println("--------------------------------------------------------------------");
        System.out.println(scenario + "---------------");
        System.out.println("--------------------------------------------------------------------");
        System.out.println("--------------------------------------------------------------------");
    }


}
