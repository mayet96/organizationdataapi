package ru.id61890868.OrganizationDataApi.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.id61890868.OrganizationDataApi.view.response.ErrorView;

@RestControllerAdvice
public class ExceptionController {

    /**
     * Обработка исключений
     * @param e
     * @return ErrorView
     */
    @ExceptionHandler(Exception.class)
    public ErrorView handleAllException(Exception e){
        ErrorView ev = new ErrorView(e.getMessage());
        System.out.println("/*" + ev.toString() + "*/");
        e.printStackTrace();
        return ev;
    }
}
