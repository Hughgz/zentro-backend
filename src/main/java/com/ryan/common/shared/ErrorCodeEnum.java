package com.ryan.common.shared;

public enum ErrorCodeEnum {
    // Common
    INTERNAL_SERVER_ERROR,
    VALIDATION_FAILED,
    RESOURCE_NOT_FOUND,

    // Identity
    USER_NOT_FOUND,
    INVALID_CREDENTIALS,

    // Catalog
    PRODUCT_NOT_FOUND,
    CATEGORY_NOT_FOUND,
    BRAND_NOT_FOUND,
}
