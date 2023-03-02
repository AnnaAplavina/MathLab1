import org.example.DiagonalDominanceNotPossibleException;
import org.example.Matrix;
import org.example.WrongMatrixFileFormatException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class MatrixTests {
    @Test
    public void readFromCorrectFileTest(){
        try {
            double[][] actual = Matrix.readFromFile("TestMatrix1.txt").getData();
            double[][] expected = {{1, 2, 3}, {1, 2, 3}};
            assertArrayEquals(expected, actual);
        }
        catch (WrongMatrixFileFormatException | IOException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void readFromSizeNotSpecifiedFileTest(){
        try {
            double[][] actual = Matrix.readFromFile("TestMatrix2.txt").getData();
            fail("Size not specified not caught");
        } catch (IOException e) {
            fail(e.getMessage());
        } catch (WrongMatrixFileFormatException ignored) {

        }
    }

    @Test
    public void readSingleRowMatrixFromFileTest(){
        try{
            double[][] actual = Matrix.readFromFile("TestMatrix3.txt").getData();
            double[][] expected = {{1, 2}};
            assertArrayEquals(expected, actual);
        } catch (WrongMatrixFileFormatException | IOException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testReadBigMatrixFromFile(){
        try {
            double[][] actual = Matrix.readFromFile("BigMatrix.txt").getData();
            String actualStr = "";
            for (double[] doubles : actual) {
                for (int j = 0; j <= actual.length; j++) {
                    actualStr += doubles[j];
                    if (j != actual.length) {
                        actualStr += " ";
                    }
                }
                actualStr += "\n";
            }
            actualStr = actualStr.trim();
            String expectedStr = "5.979 4.335 7.981 -4.495 9.858 6.681 6.993 -4.609 1.06 3.157 -0.844 -5.294 -4.639 2.833 -0.445 -8.158 9.753 -7.901 -0.355 5.055 -24.38399\n" +
                    "-8.567 1.554 0.761 -4.753 -5.068 8.34 -8.692 -1.123 2.89 3.982 9.053 8.786 -8.879 -9.173 4.148 7.247 3.136 5.706 8.487 -2.594 -61.38418\n" +
                    "-3.773 1.634 -5.875 -0.379 6.67 6.041 8.905 6.806 -0.328 4.612 9.584 -9.435 1.443 4.546 -7.219 -2.78 -9.742 4.926 8.34 5.685 18.45398\n" +
                    "8.17 9.175 0.104 -4.08 -5.76 2.006 5.407 -5.405 -3.383 -3.607 1.159 -2.351 -2.857 5.206 9.92 -3.364 5.626 -1.738 -7.7 -7.597 137.90577\n" +
                    "-6.782 9.369 9.468 8.221 -0.322 -3.719 1.368 0.027 2.878 -9.22 -9.854 1.721 -5.731 -1.943 2.458 4.021 -6.299 2.43 -5.965 -6.502 21.91546\n" +
                    "-6.945 -1.781 -1.574 2.916 -5.7 9.34 -7.14 -9.416 -5.368 -3.337 6.041 3.282 -5.557 4.952 9.806 0.635 -2.301 4.562 -4.275 -5.96 39.10987\n" +
                    "3.739 -4.387 1.012 5.928 -7.126 -2.851 -2.538 2.615 1.925 5.833 8.265 8.905 9.859 -9.837 1.15 1.043 -4.695 -4.622 -3.058 -1.493 -163.20333\n" +
                    "-1.754 4.117 -5.894 8.439 9.826 3.106 2.652 -6.614 6.002 7.867 8.309 9.459 7.644 -0.683 -7.394 7.414 6.988 -8.574 2.191 5.399 -124.67531\n" +
                    "2.708 0.831 7.776 4.378 -5.416 4.788 -1.344 9.593 -7.606 -0.815 8.783 5.532 -4.876 -4.507 1.496 -3.093 7.489 -6.952 4.043 4.809 -116.22789\n" +
                    "5.923 7.965 9.955 9.446 -7.785 0.847 -1.456 3.629 -1.682 -6.049 4.751 3.109 -8.525 5.538 -6.714 -3.67 -5.939 5.241 8.317 -6.004 112.90377\n" +
                    "3.608 -1.317 9.526 -6.074 2.519 -6.176 1.109 0.507 9.498 8.378 8.147 -9.411 8.739 6.785 2.865 7.82 4.932 -7.631 -6.058 -9.888 -150.69285\n" +
                    "4.076 -4.767 -5.968 -7.576 -6.351 -0.045 -1.79 -9.423 3.429 -2.193 5.726 0.813 -4.564 -7.686 -0.718 -9.571 5.52 -0.304 -2.977 7.435 -63.06809\n" +
                    "-1.18 -4.426 6.604 2.519 -7.264 8.753 0.421 -1.491 -0.871 2.872 4.375 -6.116 7.829 9.533 -6.55 5.494 -8.498 -1.13 3.869 -7.101 -43.99048\n" +
                    "-3.93 0.57 -7.579 -0.224 3.303 4.47 -6.417 7.391 9.412 2.673 6.919 -7.322 -7.158 -4.744 9.659 -2.571 -0.095 -9.087 1.201 7.789 -87.97518\n" +
                    "-5.701 4.935 -8.649 -3.071 -2.393 -7.218 -4.167 -5.064 4.017 -9.174 3.154 4.535 3.192 -7.25 5.003 4.183 -7.257 -0.567 -5.717 -3.162 -104.52754\n" +
                    "-0.283 -9.801 7.456 -8.793 4.988 4.276 -6.21 -1.935 1.598 -2.043 -7.332 -4.89 7.863 -8.23 5.635 -8.93 -7.152 -4.303 1.689 7.75 -219.5702\n" +
                    "-4.605 1.848 -6.235 -1.858 -0.35 -7.494 -5.344 3.246 -2.839 1.481 -3.082 0.951 -9.051 4.583 4.737 -6.105 -4.278 2.619 -1.495 -7.958 118.49898\n" +
                    "-7.581 2.869 0.486 -0.575 5.329 -9.02 9.368 3.113 9.895 -6.264 2.594 -0.912 8.664 -5.678 -1.649 3.692 3.064 -4.662 1.77 9.134 -245.34377\n" +
                    "-5.564 6.476 5.75 4.159 6.914 -2.812 -9.102 7.175 7.126 3.078 -6.817 5.708 5.584 -7.024 8.979 3.279 8.717 4.32 -8.473 -4.712 -85.456\n" +
                    "-2.734 -2.255 4.324 -8.137 -7.715 0.261 -3.614 0.203 2.657 -2.719 6.735 -7.092 -7.948 7.983 6.393 4.737 5.266 8.416 3.792 8.47 -84.22593";
            assertEquals(expectedStr, actualStr);

        } catch (IOException | WrongMatrixFileFormatException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void isDiagonallyDominantTest() throws WrongMatrixFileFormatException, IOException {
        Matrix matrix = Matrix.readFromFile("DiagonallyDominantMatrix.txt");
        assertTrue(matrix.isDiagonallyDominant());
    }

    @Test
    public void isNotDiagonallyDominantTest() throws WrongMatrixFileFormatException, IOException {
        Matrix matrix = Matrix.readFromFile("TestMatrix1.txt");
        assertFalse(matrix.isDiagonallyDominant());
    }

    @Test
    public void toDiagonalDominanceTest() throws IOException, WrongMatrixFileFormatException, DiagonalDominanceNotPossibleException {
        Matrix matrix = Matrix.readFromFile("ToDiagonalDominanceMatrix.txt");
        matrix.toDiagonalDominance();
        double[][] expected = {{10, 6 , 1.5, 0},{0, 8.3, 4.521, 74},{6, 3, -10, 7}};
        assertArrayEquals(expected, matrix.getData());
    }

    @Test
    public void toDiagonalDominanceOnDominantTest() throws IOException, WrongMatrixFileFormatException, DiagonalDominanceNotPossibleException {
        Matrix matrix = Matrix.readFromFile("DiagonallyDominantMatrix.txt");
        matrix.toDiagonalDominance();
    }
}
