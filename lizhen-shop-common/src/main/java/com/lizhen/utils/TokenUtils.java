package com.lizhen.utils;


import com.lizhen.constants.Constants;

import java.util.UUID;

public class TokenUtils {

    public static String getMemberToken() {
        return Constants.TOKEN_MEMBER + "-" + UUID.randomUUID();
    }

}
