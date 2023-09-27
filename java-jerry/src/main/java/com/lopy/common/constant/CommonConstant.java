package com.lopy.common.constant;

public class CommonConstant {

    private CommonConstant() {}

    public static final String SUCCESS_MSG = "success";

    public static final int STATUS_SUCCESS = 200;

    public static class Code {
        private Code() {}

        public static final int SUCCESS = 200;
        public static final int NOT_FOUND = 404;
        public static final int BAD_REQUEST = 400;
        public static final int UNAUTHORIZED = 401;
        public static final int FORBIDDEN = 403;
        public static final int METHOD_NOT_SUPPORT = 405;
        public static final int INTERNAL_SERVER_ERROR = 500;
    }

    public static class Account {
        private Account() {}

        public static final String CUSTOMER = "customer";
        public static final String RESTAURATEUR = "restaurateur";
        public static final String ADMIN = "admin";
    }

}
