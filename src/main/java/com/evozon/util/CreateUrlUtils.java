package com.evozon.util;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by nicoletatica on 26/07/2016.
 */
@Service
public final class CreateUrlUtils {

    public CreateUrlUtils() {
    }

    public static String CreateUrlForFilter(Integer[] categoriesArray) {
        String selectedCategoriesUrl = "";
        if (categoriesArray.length != 0) {
            for (Integer categoryValue : categoriesArray) {
                selectedCategoriesUrl += "&category=" + categoryValue;
            }
        }
        return selectedCategoriesUrl;

    }
}