package com.yearn.life.common;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * Created by Vincent on 2018-08-14.
 */
@Component
public class ErrorConfiguration implements ErrorPageRegistrar{

    @Override
    public void registerErrorPages(ErrorPageRegistry errorPageRegistry) {

        ErrorPage[] errorPages=new ErrorPage[2];
        errorPages[0]=new ErrorPage(HttpStatus.NOT_FOUND,"/error/404");
        errorPages[1]=new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR,"/error/500");

        errorPageRegistry.addErrorPages(errorPages);
    }
}
