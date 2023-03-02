import org.example.DiagonalDominanceNotPossibleException;
import org.example.Matrix;
import org.example.SimpleIterationLab;
import org.example.WrongMatrixFileFormatException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;

import static org.junit.Assert.*;


@RunWith(JUnit4.class)
public class IterationMethodTests {
    @Test
    public void simpleTest() throws DiagonalDominanceNotPossibleException {
        Matrix matrix = new Matrix(new double[][]{{1,5,4},{5,3,3}});
        double epsilon = 0.00001;
        SimpleIterationLab.SimpleIterationResult res = SimpleIterationLab.simpleIteration(matrix, epsilon);
        double[] expected = new double[]{0.13636363636, 0.77272727272};
        assertArrayEquals(expected, res.res, epsilon);
    }

    @Test
    public void randMatrixTest() throws IOException, WrongMatrixFileFormatException, DiagonalDominanceNotPossibleException {
        Matrix matrix = Matrix.generateRandomMatrix();
        double epsilon = 0.00001;
        SimpleIterationLab.SimpleIterationResult res = SimpleIterationLab.simpleIteration(matrix, epsilon);
        System.out.println();
    }

}
