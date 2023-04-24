

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.lang.Throwable;

public class InvalidNumberException extends Throwable {

    private InvalidNumberException(String invalid_) {}




    public static class ReadFileExample {
        @Override
        public int hashCode() {
            return super.hashCode();
        }

        public static void main(String[] args) {
            int[] arr = new int[10];
            try {
                Scanner scanner = new Scanner(new File("Quizi"));
                int i = 0;
                while (scanner.hasNextInt() && i < 10) {
                    int num = scanner.nextInt();
                    if (num < 0) {
                        throw new InvalidNumberException("Invalid Number Exception ");
                    }
                    arr[i] = num;
                    i++;
                }
                scanner.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            ;
            } catch (InvalidNumberException e) {
                throw new RuntimeException(e);
            }


        }



        public static int sumArray(int[] arr) {
            int sum = 0;
            for (int i = 0; i < arr.length; i++) {
                sum += arr[i];
            }
            return sum;


        }


    }








}
