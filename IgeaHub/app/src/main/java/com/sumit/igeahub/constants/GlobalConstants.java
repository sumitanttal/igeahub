package com.sumit.igeahub.constants;

/**
 * Created by sumit on 30/10/17.
 */

public class GlobalConstants {

    /***
     *  Shared Preference constants
     */
    public final String USER_TABLE="USER_TABLE";
    public final String USER_IMAGE="USER_IMAGE";
    public final String USER_ID="USER_ID";
    public final String FIRST_NAME="FIRST_NAME";
    public final String LAST_NAME="LAST_NAME";
    public final String EMAIL="EMAIL";
    public final String JOBTITLE="JOBTITLE";
    public final String COMPANY="COMPANY";
    public final String COUNTRY="COUNTRY";
    public final String NEW_CHAT="1";
    public final String OLD_CHAT="0";

    public static GlobalConstants getInstance(){
        return new GlobalConstants();
    }
}
