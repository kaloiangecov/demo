/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.custom.annotations;

import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author kaloyan
 */
public class LettersOnlyValidator implements ConstraintValidator<LetterOnlyValidation, String> {

    private final Pattern pattern = Pattern.compile("^[a-zA-Z]+$");
    
    @Override
    public void initialize(LetterOnlyValidation a) {
    }

    @Override
    public boolean isValid(String t, ConstraintValidatorContext cvc) {
        return pattern.matcher(t).matches();
    }

}
