package Matrix;

import com.sun.media.sound.InvalidDataException;
import org.junit.Before;
import org.junit.Test;

import static Matrix.HamsterMatchers.HaveEqualsSize.haveEqualsSize;
import static Matrix.HamsterMatchers.IsEquals.isEquals;
import static Matrix.HamsterMatchers.IsInversable.isInversable;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.AllOf.allOf;

public class ModuleTest {
    Matrix rightMatrix;

    @Before
    public void setUp() throws Exception {
        rightMatrix = new Matrix(new double[][]
                {
                        {1.0, 2.0, 3.0},
                        {4.0, 5.0, 6.0},
                        {7.0, 8.0, 10.0}
                });
    }

    @Test
    public void testEqualSizeAndInversability() throws Exception {
        Matrix matrix1, matrix2;
        matrix1 = new Matrix(new double[][]
                {
                        {1.0, 2.0, 3.0},
                        {4.0, 5.0, 6.0},
                        {7.0, 8.0, 10.0},
                });
        matrix2 = new Matrix(matrix1);

        assertThat(matrix1, allOf(haveEqualsSize(matrix2), isInversable()));
    }

    @Test
    public void testRightCopy() throws Exception {
        Matrix matrix1, matrix2;
        matrix1 = new Matrix(new double[][]
                {
                        {1.0, 2.0, 3.0},
                        {4.0, 5.0, 6.0},
                        {7.0, 8.0, 10.0}
                });
        matrix2 = new Matrix(matrix1);

        assertThat("Check matrix copy constructor", matrix1, isEquals(matrix2));
    }

    @Test
    public void testTransponent() throws Exception {
        Matrix matrix1, matrix2;
        matrix1 = new Matrix(new double[][]
                {
                        {1.0, 2.0, 3.0},
                        {4.0, 5.0, 6.0},
                        {7.0, 8.0, 10.0}
                });
        matrix2 = new Matrix(new double[][]
                {
                        {1.0, 4.0, 7.0},
                        {2.0, 5.0, 8.0},
                        {3.0, 6.0, 10.0}
                });

        assertThat("Check matrix transponent operation", matrix1.GetTransponent(), isEquals(matrix2));
    }

    @Test
    public void testInverse() throws Exception {
        Matrix matrix1, matrix2;
        matrix1 = new Matrix(4, 4,
                7.5 , 3.3 , 0.3 , 7.7,
                        5.5 , 7.1 , 18.1, 6.7,
                        9.2 , 15.6, 9.9 , 2.7,
                        11.3, 17.5, 1.5 , 1.5
        );
        matrix2  = new Matrix(4, 4,
                -1.47177953388153  ,  3.299276983070184 , -6.736517521906027 ,  4.944095955642559,
                         0.8766535730957318, -1.9869780933115704,  4.047053708980599 , -2.9096828679314912,
                        -0.3412266937802935,  0.7168047094094292, -1.3467571140613417,  0.9740654646871402,
                         1.2010074962376167, -2.389946893236497 ,  4.879562507646447 , -3.60662153799369
        );
        Matrix g = matrix1.GetReverse();
        assertThat("Check matrix inversion operation", matrix1.GetReverse(), isEquals(matrix2));
    }

    @Test(expected = InvalidDataException.class)
    public void testSubstractionWithDifferentDimensions() throws InvalidDataException {
        Matrix matrix1, matrix2;
        matrix1 = new Matrix(new double[][]
                {
                        {1.0, 2.0, 3.0},
                        {4.0, 5.0, 6.0},
                        {7.0, 8.0, 9.0},
                        {7.0, 8.0, 9.0}
                });
        matrix2 = new Matrix(new double[][]
                {
                        {1.0, 2.0, 3.0},
                        {4.0, 5.0, 6.0},
                        {7.0, 8.0, 9.0}
                });
        Matrix.OpSubtraction(matrix1, matrix2);
    }

    @Test(expected = InvalidDataException.class)
    public void testWrongDimensions() throws InvalidDataException {
        Matrix matrix;
        matrix = new Matrix(0, 2, 1.0);
    }

    @Test(expected = InvalidDataException.class)
    public void testWrongDigits() throws InvalidDataException {
        Matrix matrix;
        matrix = new Matrix(2, 2, 1.0);
    }

    @Test(expected = InvalidDataException.class)
    public void testNullDigits() throws InvalidDataException {
        Matrix matrix;
        matrix = new Matrix(2, 2, null);
    }

    @Test(expected = InvalidDataException.class)
    public void testReverseWithNullDeterminant() throws InvalidDataException {
        Matrix matrix;
        matrix = new Matrix(new double[][]
                {
                        {1.0, 2.0, 3.0},
                        {4.0, 5.0, 6.0},
                        {7.0, 8.0, 9.0}
                });
        matrix.GetReverse();
    }

    @Test(expected = InvalidDataException.class)
    public void testReverseWithNotSquareMatrix() throws InvalidDataException {
        Matrix matrix;
        matrix = new Matrix(new double[][]
                {
                        {1.0, 2.0, 3.0},
                        {4.0, 5.0, 6.0},
                        {7.0, 8.0, 9.0},
                        {7.0, 8.0, 9.0}
                });
        matrix.GetReverse();
    }
}
