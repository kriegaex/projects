package uk.ac.ucl.main.util.core;


public class StrTool {
    private static final int INDEX_NOT_FOUND = -1;
    private static final String PLACE_HOLDER = "{}";

    public static boolean isEmpty(String s){
        return s == null || s.length() == 0;
    }

    /**
     *
     * @param str
     * @param start : the start label
     * @param end : the end label
     * @return : substring between start and end in str
     */
    public static String subBetween(String str, String start, String end){
        if (isEmpty(str) || start == null || end == null){
            return null;
        }
        int indexStart = str.indexOf(start);

        if (indexStart != INDEX_NOT_FOUND){
            int indexEnd = str.indexOf(end, indexStart + start.length());

            if (indexEnd != INDEX_NOT_FOUND){
                return str.substring(indexStart + start.length(), indexEnd);
            }
            else { return ""; }
        }
        else { return ""; }
    }

    public static String subBetween(String str, String startend){
        return subBetween(str, startend, startend);
    }

    /**
     *
     * @param str
     * @param before : the end label
     * @return : the substring from the beginning to before
     */
    public static String subBefore(String str, String before){
        if ( str == null ){ return null; }
        int indexBefore = str.indexOf(before);
        if (indexBefore != INDEX_NOT_FOUND){
            return str.substring(0, indexBefore);
        }
        return "";
    }

    /**
     * Replace the placeholder("{}") by the given argument list
     * format("hello {}", "hello"} returns "hello world"
     * @param pattern
     * @param argArray
     * @return
     */

    public static String format(final String pattern, final Object... argArray){
        if (argArray.length == 0) { return pattern; }

        StringBuilder sb = new StringBuilder();

        int handledIndex = 0;
        for (int argIndex = 0; argIndex < argArray.length; argIndex++){
            int placeHolder = pattern.indexOf(PLACE_HOLDER, handledIndex);
            if (placeHolder == -1){
                sb.append(pattern, handledIndex, pattern.length());
                return sb.toString();
            }
            sb.append(pattern, handledIndex, placeHolder);
            sb.append(argArray[argIndex].toString());
            handledIndex = placeHolder + 2;
        }
        sb.append(pattern, handledIndex, pattern.length());
        return sb.toString();
    }

    public static void main(String[] args) {
        String test = StrTool.format("hello {}, this is format", "world");
        System.out.println(test);
    }
}
