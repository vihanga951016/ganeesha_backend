package com.ganeesha.backend.services.impls;

public class Common {
    public static String formatId(Integer id){

        String userId = id.toString();
        int idLength = userId.length();

        String middleNumbers;

        if(idLength == 1) {
            middleNumbers = "000";
        } else if(idLength == 2) {
            middleNumbers = "00";
        } else if(idLength == 3) {
            middleNumbers = "0";
        } else {
            middleNumbers = "";
        }

        return middleNumbers+userId;
    }
}
