//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.thebo.ichat.common.utils;

import org.apache.commons.beanutils.Converter;

public class BigDecimalConvert implements Converter {
    public BigDecimalConvert() {
    }

    public Object convert(Class type, Object value) {
        return value == null?null:value;
    }
}
