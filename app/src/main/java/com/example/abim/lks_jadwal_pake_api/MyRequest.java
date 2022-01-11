package com.example.abim.lks_jadwal_pake_api;

public class MyRequest {
    private static final String baseURl = "http://192.168.1.29:5/";
    private static final String classURL = "api/classname";
    private static final String postURL = "api/addStudents";
    private static final String studentsURL = "api/students";
    private static final String loginURL = "api/admin";

    public static String getBaseURl() {
        return baseURl;
    }

    public static String getClassURL() {
        return getBaseURl() + classURL;
    }

    public static String getPostURL() {
        return getBaseURl() + postURL;
    }

    public static String getStudentsURL() {
        return getBaseURl() + studentsURL;
    }

    public static String getLoginURL() {
        return getBaseURl() + loginURL;
    }
}
