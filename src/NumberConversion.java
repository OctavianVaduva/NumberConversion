import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

public class NumberConversion {
    static long numberConvertion(int number){
        long convertedNumber = 0L;
        int min;
        int dim;
        int figure = 0; // will have the value of the each digit (figure) of the number to be converted
        int first = 0; /* will have the value of the first digit of the converted number
                (greater by at least one unit than the number to be converted)*/
        if(number > 0 ) {
            dim = (int)Math.log10(number) + 1;
        }else if (number == 0) {
            return 1;
        }else {
            return -1;
        }

        // initializing a HashSet with the available digits
        Integer[] allNumber = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10}; /* 10 is entered to solve the problem of a
                number beginning with 10 */
        HashSet<Integer> availableNr = new HashSet<>(Arrays.asList(allNumber));

        for(int i = 0; i < dim; i++){
            figure = number % 10; // identify the current figure. The last digit found is the first digit of the number
            number /= 10;
            availableNr.remove(figure);
        }

        if(availableNr.size()>1){
            Iterator<Integer> i = availableNr.iterator();
            min = i.next();
            while(i.hasNext() )        {
                int elem = i.next();
                if(elem > figure){
                    first = elem;
                    break;
                }
            }
            if(first == 10 && !availableNr.contains(0) ) {
                first = 11 * min;
            } else if (first == 10 && !availableNr.contains(1)) {
                first = 20;
            }

            convertedNumber = (long) first * (long)Math.pow(10, dim-1);
            long add = 0;
            for (int j = 0; j < dim-1; j++) {
                add += min * (long)Math.pow(10, j);
            }
            convertedNumber += add;
            return convertedNumber;
        }
        return convertedNumber;
    }

    public static void main(String[] args) {
        int number = 123467890;
        long convertedNumber = numberConvertion(number);
        if(convertedNumber > 0) {
            System.out.println("Number " + number + " converted is: " + convertedNumber);
        } else {
            System.out.println("Number " + number + " is negative, contains all figure (0-9) or is too big and is NOT converted");
        }
    }
}

