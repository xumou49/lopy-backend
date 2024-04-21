package com.lopy.common.constant;

public class CommonConstant {

    private CommonConstant() {}

    public static final String SUCCESS_MSG = "success";

    public static final int STATUS_SUCCESS = 200;

    public static final int STATUS_ENABLE = 1;

    public static class API {
        private API() {}

        public static final String V1_PATH = "/api/v1";
    }

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

        public static final String LOCALE_ENGLISH = "en_US";
        public static final String LOCALE_SIMPLIFIED_CHINESE = "zh_CN";

        public static final int PLATFORM_APPLE = 1;
        public static final int PLATFORM_GOOGLE = 2;
        public static final int PLATFORM_LOPY = 3;

        public static final int DEVICE_DEFAULT = 0;
        public static final int DEVICE_IOS = 1;
        public static final int DEVICE_ANDROID = 2;
    }

    public static final class Order {
        private Order() {}

        public static final int STATUS_PENDING = 0;
        public static final int STATUS_PREPARING = 1;
        public static final int STATUS_COMPLETED = 2;
        public static final int STATUS_VOID = 3;
    }

}
