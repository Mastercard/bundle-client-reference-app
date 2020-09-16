package com.mastercard.developer;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
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



        if (runThisScenario(args, "createUser")) {
            printMessage("STARTING CREATE USER FOR BUNDLE PROFILE");
            executeCreateBundleUserScenario(bundleProfileApi);
        }

        if (runThisScenario(args, "readUser")) {
            printMessage("STARTING GET USER FOR BUNDLE PROFILE");
            executeGetBundleUserScenario(bundleProfileApi);
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

        if (runThisScenario(args, "updateUser")) {
            printMessage("STARTING UPDATE USER FOR BUNDLE PROFILE");
            executeReplaceUserDetailScenario(bundleProfileApi);
        }

        if (runThisScenario(args, "readClsUser")) {
            printMessage("STARTING GET CLS USER FOR BUNDLE PROFILE");
            executeGetEnrollmentUserScenario(bundleProfileApi);
        }

        if (runThisScenario(args, "createClsUser")) {
            printMessage("STARTING CREATE CLS USER FOR BUNDLE PROFILE");
            executeCreateEnrollmentUserScenario(bundleProfileApi);
        }

        if (runThisScenario(args, "deleteClsUser")) {
            printMessage("STARTING DELETE CLS USER FOR BUNDLE PROFILE");
            executeDeleteEnrollmentUserScenario(bundleProfileApi);
        }

        if (runThisScenario(args, "updateClsUser")) {
            printMessage("STARTING UPDATE CLS USER FOR BUNDLE PROFILE");
            executeUpdateEnrollmentUserScenario(bundleProfileApi);
        }

        if (runThisScenario(args, "patchAddClsUser")) {
            printMessage("STARTING PATCH ADD CLS USER FOR BUNDLE PROFILE");
            executePatchAddEnrollmentUserScenario(bundleProfileApi);
        }

        if (runThisScenario(args, "patchReplaceClsUser")) {
            printMessage("STARTING PATCH REPLACE CLS USER FOR BUNDLE PROFILE");
            executePatchReplaceEnrollmentUserScenario(bundleProfileApi);
        }

        if (runThisScenario(args, "patchRemoveClsUser")) {
            printMessage("STARTING PATCH REMOVE CLS USER FOR BUNDLE PROFILE");
            executePatchRemoveEnrollmentUserScenario(bundleProfileApi);
        }

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

    private static void executeReplaceUserDetailScenario(BundleProfileApi bundleProfileApi) throws ApiException, IOException {
        Gson gson = new Gson();
        BundleUserPatch bundleUserPatch = RequestHelper.createReplaceUserPayload();
        BundleUserResponse bundleUserResponse = bundleProfileApi.patchUser(RequestHelper.USER_ID, bundleUserPatch, RequestHelper.X_Client_CORRELEATION_ID);
        System.out.println(gson.toJson(bundleUserResponse));
    }

    private static void executeGetEnrollmentUserScenario(BundleProfileApi bundleProfileApi) throws ApiException {
        Gson gson = new Gson();
        EnrollmentUserResponse enrollmentUserResponse = bundleProfileApi.readClsUser(RequestHelper.CLS_USER_ID, RequestHelper.X_Client_CORRELEATION_ID);
        System.out.println(gson.toJson(enrollmentUserResponse));
    }

    private static void executeCreateEnrollmentUserScenario(BundleProfileApi bundleProfileApi) throws ApiException {
        EnrollmentUser enrollmentUser = RequestHelper.getClsPostBody();
        EnrollmentResponse enrollmentResponse = bundleProfileApi.createClsUser(enrollmentUser, RequestHelper.X_Client_CORRELEATION_ID);
        printEnrollmentResponseMessage(enrollmentResponse);
    }

    private static void executeDeleteEnrollmentUserScenario(BundleProfileApi bundleProfileApi) throws ApiException {
        EnrollmentResponse enrollmentResponse = bundleProfileApi.deleteClsUser(RequestHelper.CLS_USER_ID, RequestHelper.X_Client_CORRELEATION_ID, "default");
        printEnrollmentResponseMessage(enrollmentResponse);
    }

    private static void executeUpdateEnrollmentUserScenario(BundleProfileApi bundleProfileApi) throws ApiException {
        EnrollmentUser enrollmentUser = RequestHelper.getClsPutBody();
        EnrollmentResponse enrollmentResponse = bundleProfileApi.updateCls(RequestHelper.CLS_USER_ID, enrollmentUser,
                RequestHelper.X_Client_CORRELEATION_ID);
        printEnrollmentResponseMessage(enrollmentResponse);
    }

    private static void executePatchAddEnrollmentUserScenario(BundleProfileApi bundleProfileApi) throws ApiException {
        DiscretePatchesRequest discretePatchesRequest = RequestHelper.getClsPatchAddBody();
        EnrollmentResponse enrollmentResponse = bundleProfileApi.updateClsPartially(RequestHelper.CLS_USER_ID,
                discretePatchesRequest, RequestHelper.X_Client_CORRELEATION_ID);
        printEnrollmentResponseMessage(enrollmentResponse);
    }

    private static void executePatchReplaceEnrollmentUserScenario(BundleProfileApi bundleProfileApi) throws ApiException {
        DiscretePatchesRequest discretePatchesRequest = RequestHelper.getClsPatchReplaceBody();
        EnrollmentResponse enrollmentResponse = bundleProfileApi.updateClsPartially(RequestHelper.CLS_USER_ID,
                discretePatchesRequest, RequestHelper.X_Client_CORRELEATION_ID);
        printEnrollmentResponseMessage(enrollmentResponse);
    }

    private static void executePatchRemoveEnrollmentUserScenario(BundleProfileApi bundleProfileApi) throws ApiException {
        DiscretePatchesRequest discretePatchesRequest = RequestHelper.getClsPatchRemoveBody();
        EnrollmentResponse enrollmentResponse = bundleProfileApi.updateClsPartially(RequestHelper.CLS_USER_ID,
                discretePatchesRequest, RequestHelper.X_Client_CORRELEATION_ID);
        printEnrollmentResponseMessage(enrollmentResponse);
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

    private static void printEnrollmentResponseMessage(EnrollmentResponse enrollmentResponse){
        Gson gson = new Gson();
        System.out.println(gson.toJson(enrollmentResponse));
    }

}
