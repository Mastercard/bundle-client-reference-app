package com.mastercard.developer;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONObject;
import org.openapitools.client.ApiException;
import org.openapitools.client.api.BundleProfileApi;
import org.openapitools.client.model.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class BundleProfileApiDemo {


    public static void main(String[] args) throws Exception {

        RequestHelper.loadProperties();

        BundleProfileApi bundleProfileApi = new BundleProfileApi(RequestHelper.signRequest());


        if (runThisScenario(args, "readUser")) {
            printMessage("STARTING GET USER FOR BUNDLE PROFILE");
            executeGetBundleUserScenario(bundleProfileApi);
        }
        if (runThisScenario(args, "createUser")) {
            printMessage("STARTING CREATE USER FOR BUNDLE PROFILE");
            executeCreateBundleUserScenario(bundleProfileApi);
        }

        if (runThisScenario(args, "addProduct")) {
            printMessage("STARTING ADD PRODUCT FOR BUNDLE PROFILE");
            executeAddProductBundleUserScenario(bundleProfileApi);
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
//

    }


    private static void executeGetBundleUserScenario(BundleProfileApi bundleProfileApi) throws ApiException {
        BundleUserResponse bundleUserResponse = bundleProfileApi.readUser(RequestHelper.USER_ID, RequestHelper.X_Client_CORRELEATION_ID);
        printResponseMessage(bundleUserResponse);

    }

    private static void executeCreateBundleUserScenario(BundleProfileApi bundleProfileApi) throws IOException, ApiException {
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<InputStream> createUserPayload = RequestHelper.getPostBody();
        for (InputStream createProductBasedUser : createUserPayload) {
            BundleUser userBody = mapper.readValue(createProductBasedUser, BundleUser.class);
            BundleUserResponse bundleUserResponse = bundleProfileApi.createUser(userBody, RequestHelper.X_Client_CORRELEATION_ID);
            printResponseMessage(bundleUserResponse);
        }
    }


    private static void executeAddProductBundleUserScenario(BundleProfileApi bundleProfileApi) throws IOException, ApiException {
        BundleUserPatch bundleUserPatch = RequestHelper.createAddProductPayload();
        BundleUserResponse bundleUserResponse = bundleProfileApi.patchUser(RequestHelper.USER_ID, bundleUserPatch, RequestHelper.X_Client_CORRELEATION_ID);
        printResponseMessage(bundleUserResponse);
    }

    private static void executeAddAccountBundleUserScenario(BundleProfileApi bundleProfileApi) throws ApiException {
        BundleUserPatch bundleUserPatch = RequestHelper.createAddAccountPayload();
        BundleUserResponse bundleUserResponse = bundleProfileApi.patchUser(RequestHelper.USER_ID, bundleUserPatch, RequestHelper.X_Client_CORRELEATION_ID);
        printResponseMessage(bundleUserResponse);
    }


    private static void executeRemoveAccountBundleUserScenario(BundleProfileApi bundleProfileApi) throws ApiException {
        BundleUserPatch bundleUserPatch = RequestHelper.createRemoveAccountPayload();
        BundleUserResponse bundleUserResponse = bundleProfileApi.patchUser(RequestHelper.USER_ID, bundleUserPatch, RequestHelper.X_Client_CORRELEATION_ID);
        printResponseMessage(bundleUserResponse);
    }

    private static void executeReplaceAccountBundleUserScenario(BundleProfileApi bundleProfileApi) throws ApiException {
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

    private static void printResponseMessage(BundleUserResponse bundleUserResponse) {
        Gson gson = new Gson();
        System.out.println(gson.toJson(bundleUserResponse));
    }


}
