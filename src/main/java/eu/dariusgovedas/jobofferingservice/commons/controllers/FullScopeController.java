package eu.dariusgovedas.jobofferingservice.commons.controllers;

import eu.dariusgovedas.jobofferingservice.commons.entities.CompanyInfo;
import lombok.AllArgsConstructor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;


@AllArgsConstructor
@ControllerAdvice
public class FullScopeController {

    private final CompanyInfo companyInfo;

    @ModelAttribute("companyInfo")
    public CompanyInfo addCompanyDataToModel(){
        return companyInfo;
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }


}
