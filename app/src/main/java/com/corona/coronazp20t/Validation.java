package com.corona.coronazp20t;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
    public static final String CREDENTIALS_PATTERN="^[a-zA-Z]{3,20}$";

    public static boolean isCredentialsValid(String credentials){
        Pattern pattern=Pattern.compile(CREDENTIALS_PATTERN); //Sukuriamas šablonas pagal mūsų taisykles
        Matcher matcher=pattern.matcher(credentials); //Pagal susikurtą šabloną palyginami susikurti duomenys
        return matcher.matches(); //Gražina true jeigu atitinka, false priešingu atveju
    }
}
