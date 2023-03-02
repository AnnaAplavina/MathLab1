package org.example;

import java.io.*;


public class SimpleIterationLab {
    public static void main(String[] args) throws IOException {
        String inputFrom = readInputType();
        if(inputFrom.equals("1")){
            Matrix matrix = Matrix.readFromConsole();
            try {
                SimpleIterationResult result = simpleIteration(matrix, readEpsilon());
                printResult(result);
            }
            catch (DiagonalDominanceNotPossibleException ex){
                System.out.println("Can not make this matrix diagonally dominant");
            }
        }
        if(inputFrom.equals("2")){
            try {
                SimpleIterationResult result = simpleIteration(Matrix.readFromFile(readFileName()), readEpsilon());
                printResult(result);
            } catch (DiagonalDominanceNotPossibleException e) {
                System.out.println("Can not make this matrix diagonally dominant");
            } catch (WrongMatrixFileFormatException e) {
                System.out.println("File format is incorrect");
            }
            catch (FileNotFoundException ex){
                System.out.println("File not found");
            }
        }
        if(inputFrom.equals("3")){
            try {
                Matrix matrix = Matrix.generateRandomMatrix();
                System.out.println("Generated matrix");
                for(int i = 0; i < matrix.getData().length; i++){
                    for(int j = 0; j <= matrix.getData().length; j++){
                        if(j == matrix.getData().length){
                            System.out.print(" | " + matrix.getData()[i][j] + "\n");
                        }
                        else {
                            System.out.print(matrix.getData()[i][j] + " ");
                        }
                    }
                }
                SimpleIterationResult result = simpleIteration(matrix, readEpsilon());
                printResult(result);
            } catch (DiagonalDominanceNotPossibleException e) {
                System.out.println("Can not make this matrix diagonally dominant");
            }
        }
    }

    private static String readInputType() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        boolean correctResponse = false;
        String userResponse;
        do{
            System.out.println("1 - from console\n2 - from file\n3 - random");
            userResponse = reader.readLine();
            if(userResponse.equals("1") || userResponse.equals("2") || userResponse.equals("3")){
                correctResponse = true;
            }
        }
        while (!correctResponse);
        return userResponse;
    }

    private static double readEpsilon() throws IOException {
        System.out.println("Enter epsilon");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        double epsilon = -1;
        while(epsilon < 0){
            try {
                epsilon = Double.parseDouble(reader.readLine());
                if(epsilon < 0){
                    System.out.println("Epsilon must be positive");
                }
            }
            catch (NumberFormatException ex){
                System.out.println("Epsilon must be a number");
            }
        }
        return epsilon;
    }

    private static String readFileName() throws IOException {
        System.out.println("Enter file");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        return reader.readLine();
    }

    private static void printResult(SimpleIterationResult result){
        for(int i = 0; i < result.res.length; i++){
            System.out.println("x" + (i+1) + " = " + result.res[i]);
        }
        System.out.println("Number of iterations " + result.iterationsNum);
    }

    public static class SimpleIterationResult{
        public int iterationsNum;
        public double[] res;

        public SimpleIterationResult(int iterationsNum, double[] res){
            this.iterationsNum = iterationsNum;
            this.res = res;
        }
    }

    public static SimpleIterationResult simpleIteration(Matrix matrix, double epsilon) throws DiagonalDominanceNotPossibleException {
        if(!matrix.isDiagonallyDominant()){
            matrix.toDiagonalDominance();
        }
        int iterationsNum = 0;
        double[] res = new double[matrix.getData().length];
        double[] previous;
        Matrix newMatrix = matrix.forSimpleIterations();
        do{
            previous = res.clone();
            for(int i = 0; i < res.length; i++){
                res[i] = newMatrix.getData()[i][res.length];
                for(int j = 0; j < res.length ; j++){
                    res[i] += newMatrix.getData()[i][j]*previous[j];
                }
            }
            iterationsNum++;
        }
        while(!isAccurate(res, previous, epsilon));
        return new SimpleIterationResult(iterationsNum, res);
    }

    private static boolean isAccurate(double[] curr, double[] prev, double eps){
        for(int i = 0; i < curr.length; i++){
            if(Math.abs(curr[i] - prev[i]) > eps){
                return false;
            }
        }
        return true;
    }
}