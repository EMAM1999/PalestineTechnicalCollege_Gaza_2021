/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lines;
import java.util.Scanner;

/**

 @author EMAM
 */
public class Point {

      static Scanner in = new Scanner(System.in);
      static double[][] points;

      public static void main(String[] args) {
            while ( true ) {
                  displayMenu();
                  int c = getChoice();
                  System.out.println("\033[0;34m=====================\033[0;30m");
                  switch ( c ) {
                        case 1: getPoints();
                              break;
                        case 2: displayPoints();
                              break;
                        case 3: shufflePoints();
                              break;
                        case 4: displayEquations();
                              break;
                        case 5: displayRightMostLowestPoint();
                              break;
                        case 6: displaySlopes();
                              break;
                        case 7: exit();
                              break;
                        default: displayError();
                  }
                  System.out.println("\n");
            }
      }

      private static void displayMenu() {
            System.out.println("1. Get points\n2. Display points\n3. Shuffle points"
                    + "\n4. Display line equations\n5. Display right most lowest point"
                    + "\n6. Display slope of lines\n7. exit");
      }

      private static int getChoice() {
            System.out.println("Enter your choise :: ");
            return in.nextInt();
      }

      private static void getPoints() {
            int n = getNumberOfCoordinates();
            getCoordinates(n);
      }

      private static int getNumberOfCoordinates() {
            int n;
            do {
                  System.out.println("Enter the number of coordinates :: ");
                  n = in.nextInt();
                  if ( n % 4 != 0 ) {
                        displayError();
                  } else {
                        return n;
                  }
            } while ( true );
      }

      private static void getCoordinates(int n) {
            points = new double[n / 2][2];
            System.out.println("Enter the coordinates");
            for ( int i = 0; i < n / 2; i++ ) {
                  double x = in.nextDouble();
                  double y = in.nextDouble();
                  if ( exist(x, y, i) ) {
                        System.out.println("\033[0;31mDuplicates points are NOT allowed [" + x + ", " + y + "]\033[0;30m");
                        i--;
                  } else {
                        points[i][0] = x;
                        points[i][1] = y;
                  }
            }
      }

      private static boolean exist(double x, double y, int n) {
            for ( int i = 0; i < n; i++ ) {
                  if ( points[i][0] == x && points[i][1] == y ) {
                        return true;
                  }
            }
            return false;
      }

      private static void displayPoints() {
            for ( int i = 0; i < points.length; i++ ) {
                  System.out.println("point (" + (i + 1) + ") -> " + "[" + points[i][0] + ", " + points[i][1] + "]");
            }
      }

      private static void shufflePoints() {
            for ( int i = 0; i < points.length / 2; i++ ) {
                  int index = ((int)(Math.random() * points.length));
                  double temx = points[i][0];
                  points[i][0] = points[index][0];
                  points[index][0] = temx;
                  double temy = points[i][1];
                  points[i][1] = points[index][1];
                  points[index][1] = temy;
            }
            System.out.println("Shuffle Done 👍");
      }

      private static void displayEquations() {
            for ( int i = 0; i < points.length; i += 2 ) {
                  double x1 = points[i][0];
                  double y1 = points[i][1];
                  double x2 = points[i + 1][0];
                  double y2 = points[i + 1][1];
                  String eq = calulateEquation(x1, y1, x2, y2);
                  System.out.println("Line -> [" + x1 + ", " + y1 + "], [" + x2 + ", " + y2 + "]"
                          + "\n\tEquation -> [ " + eq + " ]");
            }
      }

      private static String calulateEquation(double x1, double y1, double x2, double y2) {
            double m = getSlope(x1, y1, x2, y2);
            double b = -m * x1 + y1;
            return "y = " + Math.round(m * 100) / 100. + "x + " + Math.round(b * 100) / 100.;
      }

      private static double getSlope(double x1, double y1, double x2, double y2) {
            return (y2 - y1) / (x2 - x1);
      }

      private static void displayRightMostLowestPoint() {
            int miny = 0;
            for ( int i = 0; i < points.length; i++ ) {
                  if ( points[i][1] < points[miny][1] ) {
                        miny = i;
                  } else if ( points[i][1] == points[miny][1] ) {
                        if ( points[i][0] > points[miny][0] ) {
                              miny = i;
                        }
                  }
            }
            System.out.println("The Right Most Lowest Point [" + points[miny][0] + ", " + points[miny][1] + "]");
      }

      private static void displaySlopes() {
            for ( int i = 0; i < points.length; i += 2 ) {
                  double x1 = points[i][0];
                  double y1 = points[i][1];
                  double x2 = points[i + 1][0];
                  double y2 = points[i + 1][1];
                  System.out.println("Line -> [" + x1 + ", " + y1 + "], [" + x2 + ", " + y2 + "]"
                          + "\nSlope -> [ " + getSlope(x1, y1, x2, y2) + " ]");
            }
      }

      private static void exit() {
            System.out.println("Good Bye 👋👋");
            System.exit(0);
      }

      private static void displayError() {
            System.out.println("\033[0;31mWrong Input !! Try Again\033[0;30m");
      }
}
