package BackEnd;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WindowsSorter {

    public static File[] sortFiles(File[] filenames) {

        List<File> newFileNames = Arrays.asList(filenames);

        //adaptor for comparing files
        Collections.sort(newFileNames, new Comparator<File>() {
            private final Comparator<String> NATURAL_SORT = new WindowsExplorerComparator();

            @Override
            public int compare(File o1, File o2) {;
                return NATURAL_SORT.compare(o1.getName(), o2.getName());
            }
        });

        for (File f : newFileNames) {
            System.out.println(f);
        }

        File[] returnValue = newFileNames.toArray(new File[0]);

        return returnValue;
    }

    public static class WindowsExplorerComparator implements Comparator<String> {

        private static final Pattern splitPattern = Pattern.compile("\\d+|\\.|\\s");

        @Override
        public int compare(String str1, String str2) {
            Iterator<String> i1 = splitStringPreserveDelimiter(str1).iterator();
            Iterator<String> i2 = splitStringPreserveDelimiter(str2).iterator();
            while (true) {
                //Til here all is equal.
                if (!i1.hasNext() && !i2.hasNext()) {
                    return 0;
                }
                //first has no more parts -> comes first
                if (!i1.hasNext() && i2.hasNext()) {
                    return -1;
                }
                //first has more parts than i2 -> comes after
                if (i1.hasNext() && !i2.hasNext()) {
                    return 1;
                }

                String data1 = i1.next();
                String data2 = i2.next();
                int result;
                try {
                    //If both datas are numbers, then compare numbers
                    result = Long.compare(Long.valueOf(data1), Long.valueOf(data2));
                    //If numbers are equal than longer comes first
                    if (result == 0) {
                        result = -Integer.compare(data1.length(), data2.length());
                    }
                } catch (NumberFormatException ex) {
                    //compare text case insensitive
                    result = data1.compareToIgnoreCase(data2);
                }

                if (result != 0) {
                    return result;
                }
            }
        }

        private List<String> splitStringPreserveDelimiter(String str) {
            Matcher matcher = splitPattern.matcher(str);
            List<String> list = new ArrayList<String>();
            int pos = 0;
            while (matcher.find()) {
                list.add(str.substring(pos, matcher.start()));
                list.add(matcher.group());
                pos = matcher.end();
            }
            list.add(str.substring(pos));
            return list;
        }
    }
}
