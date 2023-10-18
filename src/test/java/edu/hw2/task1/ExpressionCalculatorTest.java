package edu.hw2.task1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import edu.hw2.task1.Expr.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("Тест калькулятора выражений")
class ExpressionCalculatorTest {
    @Test
    @DisplayName("Тест константы")
    void constantTest() {
        //given
        Expr two = new Constant(2.0);

        //when
        double result = two.evaluate();

        //then
        assertThat(result).isEqualTo(2.0);
    }

    @Test
    @DisplayName("Тест отрицания")
    void negateTest() {
        //given
        Expr negTwo = new Negate(new Constant(2.0));

        //when
        double result = negTwo.evaluate();

        //then
        assertThat(result).isEqualTo(-2.0);
    }

    @Test
    @DisplayName("Тест возведения в степень")
    void exponentTest() {
        //given
        Expr exponent1 = new Exponent(new Constant(2.0), 3);
        Expr exponent2 = new Exponent(new Constant(2.0), new Constant(3.0));

        //when
        double result1 = exponent1.evaluate();
        double result2 = exponent2.evaluate();

        //then
        assertThat(result1).isEqualTo(8.0);
        assertThat(result2).isEqualTo(8.0);
    }

    @Test
    @DisplayName("Тест сложения")
    void additionTest() {
        //given
        Expr addition = new Addition(new Constant(2.0), new Constant(3.0));

        //when
        double result = addition.evaluate();

        //then
        assertThat(result).isEqualTo(5.0);
    }

    @Test
    @DisplayName("Тест умножения")
    void multiplicationTest() {
        //given
        Expr multiplication  = new Multiplication(new Constant(2.0), new Constant(3.0));

        //when
        double result = multiplication.evaluate();

        //then
        assertThat(result).isEqualTo(6.0);
    }
}
