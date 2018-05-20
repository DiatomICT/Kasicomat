package com.diatom.kasicomat.util;

import java.util.List;

public class StringUtils {
    public static String mkString(List lst) {
        StringBuffer sb = new StringBuffer();
        for (Object o : lst) {
            sb.append(o.toString());
            sb.append("\n");
        }
        return sb.toString();
    }
}
