package com.test.project.springbatchsideproject.core.util;

import org.apache.commons.codec.digest.DigestUtils;

public class HashingUtil {

    public static String hashToSha256(String stringToBeConverted) {
        return DigestUtils.sha256Hex(stringToBeConverted);
    }
}
