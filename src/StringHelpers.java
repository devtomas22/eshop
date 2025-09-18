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

    public static boolean partialMatch(String part, String complete){
        String substring = complete.substring(0, Math.min(part.length(), complete.length()));
        return (part.equalsIgnoreCase(substring));
    }

    public static String censorEmail(String emailAddress){
        StringBuilder sb = new StringBuilder();
        String[] components = emailAddress.split("@");
        sb.append(partiallyCensor(components[0], 2, 0));
        sb.append('@');
        String[] domainComponents = components[1].split("\\.");
        sb.append(partiallyCensor(domainComponents[0], 1, 1));
        sb.append('.');
        for (int i = 1; i < domainComponents.length; i++){
            sb.append(domainComponents[i]);
            if (i != domainComponents.length - 1) sb.append('.');
        }
        return sb.toString();
    }

    private static String partiallyCensor(String s, int revealHead, int revealedTail){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++){
            if (i < revealHead || i > s.length() - (revealedTail + 1)){
                sb.append(s.charAt(i));
            } else {
                sb.append('*');
            }
        }
        return sb.toString();
    }

    public static String endOfPhoneNumber(String phoneNumber, int numDigits){
        return phoneNumber.replaceAll("\\s+|-", "").substring(phoneNumber.length() - (numDigits + 1));
    }

    public static String endOfPhoneNumber(String phoneNumber){
        return endOfPhoneNumber(phoneNumber, 2);
    }
}
