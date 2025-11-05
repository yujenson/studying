package com.jenson.studying.common.core.web.controller;

import com.jenson.studying.common.core.domain.AjaxResult;

/**
 * Base controller providing common response helpers.
 */
public class BaseController {

    protected AjaxResult success() {
        return AjaxResult.success();
    }

    protected AjaxResult success(Object data) {
        return AjaxResult.success(data);
    }

    protected AjaxResult success(String msg) {
        return AjaxResult.success(msg);
    }

    protected AjaxResult success(String msg, Object data) {
        return AjaxResult.success(msg, data);
    }

    protected AjaxResult error() {
        return AjaxResult.error();
    }

    protected AjaxResult error(String msg) {
        return AjaxResult.error(msg);
    }

    protected AjaxResult warn(String msg) {
        return AjaxResult.warn(msg);
    }

    protected AjaxResult warn(String msg, Object data) {
        return AjaxResult.warn(msg, data);
    }

    protected AjaxResult error(int code, String msg) {
        return AjaxResult.error(code, msg);
    }

    protected AjaxResult toAjax(int rows) {
        return rows > 0 ? AjaxResult.success() : AjaxResult.error();
    }

    protected AjaxResult toAjax(boolean result) {
        return result ? AjaxResult.success() : AjaxResult.error();
    }
}
