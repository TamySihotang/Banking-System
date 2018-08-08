package hello.util;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.regex.Pattern;

/**
 * Created by derryaditiya on 1/11/17.
 */
@Component
public class NominalUtil {

    public String numberText(BigDecimal number){
        String result;

        number= number.setScale(2);
        String numberString = number.toPlainString();


        String[] parts = numberString.split(Pattern.quote("."));
        String nominalString = parts[0];
        String decimalString = parts[1];

        Long nominal = new Long(nominalString);
        result = nominalText(nominal);

        String decimalResult = decimalText(decimalString);

        BigDecimal decimal = new BigDecimal(number.longValue());
        if(number.compareTo(decimal) != 0 && StringUtils.isNotEmpty(decimalResult)){
            result = result + "Koma" + decimalResult;
        }

        return result + " Rupiah";
    }

    public String nominalText(Long nominal) {
        String[] text = {"", "Satu", "Dua", "Tiga", "Empat", "Lima", "Enam", "Tujuh", "Delapan", "Sembilan", "Sepuluh", "Sebelas"};

        Long trillion = new Long("1000000000000");
        String result = "";

        if (nominal < 12)
            result = result + text[nominal.intValue()];
        else if (nominal < 20)
            result = result + nominalText(nominal - 10) + " Belas";
        else if (nominal < 100)
            result = result + nominalText(nominal / 10) + " Puluh " + nominalText(nominal % 10);
        else if (nominal < 200)
            result = result + "Seratus " + nominalText(nominal - 100);
        else if (nominal < 1000)
            result = result + nominalText(nominal / 100) + " Ratus " + nominalText(nominal % 100);
        else if (nominal < 2000)
            result = result + "Seribu " + nominalText(nominal - 1000);
        else if (nominal < 1000000)
            result = result + nominalText(nominal / 1000) + " Ribu " + nominalText(nominal % 1000);
        else if (nominal < 1000000000)
            result = result + nominalText(nominal / 1000000) + " Juta " + nominalText(nominal % 1000000);
        else if (nominal < trillion )
            result = result + nominalText(nominal / 1000000000) + " Milyar " + nominalText(nominal % 1000000000);
        else if (nominal >= trillion)
            result = "Angka terlalu besar, harus kurang dari 1 triliun!";
        return result;
    }

    public String decimalText(String nominal) {
        String[] text = {"Nol", "Satu", "Dua", "Tiga", "Empat", "Lima", "Enam", "Tujuh", "Delapan", "Sembilan"};
        String result = "";

        for (char c : nominal.toCharArray()){
            Integer asd = Character.getNumericValue(c);
            result = result + " " + text[asd];
        }

        return result;
    }

    public String currencySeperator(BigDecimal nominal) {
        String totalFeeInString = String.valueOf(nominal);
        String[] separatedTotalFee = totalFeeInString.split("\\.");
        if(separatedTotalFee[0].length() > 3){
            String sisa = separatedTotalFee[0];
            totalFeeInString = "";
            String blkg;
            int until = 0;
            if(separatedTotalFee[0].length() % 3 > 0){
                 until = (int)(Math.floor(separatedTotalFee[0].length() / 3)) + 1;
            }else{
                until = (int)(Math.floor(separatedTotalFee[0].length() / 3));
            }

            for (int i = 0 ; i < until ; i++){
                if(sisa.length() > 3){
                    blkg = sisa.substring(sisa.length() - 3,sisa.length());
                    sisa = sisa.substring(0,sisa.length() - 3);
                }else{
                    blkg = sisa;
                    sisa = "";
                }
                totalFeeInString = blkg + totalFeeInString;
                if(sisa.length() > 0){
                    totalFeeInString = "," + totalFeeInString;
                }
            }
            if(separatedTotalFee.length > 1){
                totalFeeInString = totalFeeInString +"."+separatedTotalFee[1].substring(0,2);
            }
        }

        return totalFeeInString;
    }
}
