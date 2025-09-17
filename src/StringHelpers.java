import java.util.Arrays;
import java.util.regex.Pattern;

public final class StringHelpers {
    static final String ALPHANUMERIC_REGEX = "^[a-zA-Z0-9]*$";
    static final Pattern ALPHANUMERIC_PATTERN = Pattern.compile(ALPHANUMERIC_REGEX);

    public static boolean isEmail(String s){
        String[] components = s.split("@");
        if (components.length != 2) return false;
        String[] headerComps = components[0].split("\\.");
        for (String comp : headerComps){
            if(!ALPHANUMERIC_PATTERN.matcher(comp).matches()) return false;
        }
        String[] domainComps = components[1].split("\\.");
        if(domainComps.length < 2 || domainComps.length > 3) return false;
        for (String comp : domainComps){
            if(!ALPHANUMERIC_PATTERN.matcher(comp).matches()) return false;
        }
        return true;
    }

    public static boolean isPhoneNumber(String s){
        s = s.replaceAll("\\s+|-", "");
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
