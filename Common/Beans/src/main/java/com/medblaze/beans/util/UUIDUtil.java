package com.medblaze.beans.util;

import java.util.UUID;

public class UUIDUtil {

    public String generateId(){
        return UUID.randomUUID().toString();
    }

    public static void main(String[] args) {
        UUIDUtil util = new UUIDUtil();
        System.out.println(util.generateId());
    }
}
