package com.rest.shoppinglist.util;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * Created by Fassil on 06/12/20.
 */
public class CustomResponseWrapper  extends HttpServletResponseWrapper {
    public CustomResponseWrapper(HttpServletResponse response) {
        super(response);
    }
}
