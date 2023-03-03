package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Matrix {
    private double[][] data;

    public double[][] getData(){
        return this.data;
    }

    public Matrix(double[][] data){
        this.data = data;
    }

    public boolean isDiagonallyDominant(){
        for(int i = 0; i < data.length; i++){
            double diagonalElement = Math.abs(data[i][i]);
            double sumOfOthers = 0;
            for(int j = 0; j < data.length; j++){
                if(j != i){
                    sumOfOthers += Math.abs(data[i][j]);
                }
            }
            if(diagonalElement < sumOfOthers){
                return false;
            }
        }
        return true;
    }

    public void toDiagonalDominance() throws DiagonalDominanceNotPossibleException {
        if(!isDiagonallyDominant()){
            int[] indices = new int[data.length];
            for(int i = 0; i < data.length; i++){
                int currIndex = -1;
                int sum = 0;
                for(int j = 0; j < data.length; j++){
                    sum += Math.abs(data[i][j]);
                }
                for(int j = 0; j < data.length; j++){
                    if(Math.abs(data[i][j]) >= sum - Math.abs(data[i][j])){
                        currIndex = j;
                        break;
                    }
                }
                int finalCurrIndex = currIndex;
                if(currIndex == -1 || Arrays.stream(indices).allMatch((a) -> a == finalCurrIndex)){
                    throw new DiagonalDominanceNotPossibleException();
                }
                indices[i] = currIndex;
            }
            double[][] newData = new double[data.length][data.length+1];
            for(int i = 0; i < indices.length; i++){
                newData[indices[i]] = data[i];
            }
            data = newData;
        }
    }

    public Matrix forSimpleIterations() throws ZeroDiagonalException{
        for(int i = 0; i < data.length; i++){
            if(data[i][i] == 0){
                throw new ZeroDiagonalException();
            }
            for(int j = 0; j <= data.length; j++){
                if(i != j){
                    data[i][j] = (-1)*data[i][j]/data[i][i];
                }
                if(j == data.length){
                    data[i][j] = data[i][j] * (-1);
                }
            }
            data[i][i] = 0;
        }
        return new Matrix(data);
    }

    public static Matrix readFromConsole() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int size = 0;
        while (size == 0){
            try{
                System.out.println("Please enter the matrix size");
                size = Integer.parseInt(reader.readLine());
                if(size <= 0 || size > 20){
                    System.out.println("Size must be a positive integer not greater than 20");
                    size = 0;
                }
            } catch (NumberFormatException ex){
                System.out.println("Size must be an integer");
            }
        }
        System.out.println("Enter values");
        double[][] res = new double[size][size+1];
        for(int i = 0; i < size; i++){
            for(int j = 0; j <= size; j++){
                while(true){
                    try{
                        res[i][j] = Double.parseDouble(reader.readLine().replaceAll(",", "."));
                        break;
                    }
                    catch (NumberFormatException ex) {
                        System.out.println("Value must be a number");
                    }
                }
            }
        }
        return new Matrix(res);
    }

    public static Matrix readFromFile(String file) throws IOException, WrongMatrixFileFormatException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String currLine = reader.readLine();
        int size = 0;
        if(currLine == null){
            throw new WrongMatrixFileFormatException("Size needs to be specified");
        }
        try{
            size = Integer.parseInt(currLine);
            if(size <= 0 || size > 20){
                throw new WrongMatrixFileFormatException("Size must be a positive integer not greater than 20");
            }
        }
        catch (NumberFormatException ex){
            throw new WrongMatrixFileFormatException("Size must be an integer");
        }
        double[][] res = new double[size][size+1];
        currLine = reader.readLine();
        int currRowNum = 0;
        while(currLine != null){
            if(currRowNum >= size){
                throw new WrongMatrixFileFormatException("Incorrect number of rows");
            }
            String[] splitRow = currLine.split(" ");
            if(splitRow.length != size + 1){
                throw new WrongMatrixFileFormatException("Incorrect row size");
            }
            for(int i = 0; i <= size; i++){
                res[currRowNum][i] = Double.parseDouble(splitRow[i].replaceAll(",", "."));
            }
            currRowNum++;
            currLine = reader.readLine();
        }
        return new Matrix(res);
    }

    public static Matrix generateRandomMatrix(){
        int size = (int)(Math.random()* (20) + 1);
        double[][] data = new double[size][size+1];
        for(int i = 0; i < size; i++){
            double sum = 0;
            for(int j = 0; j <= size; j++){
                data[i][j] = Math.random() * (10000 - (-10000)) + (-10000);
                sum += Math.abs(data[i][j]);
            }
            data[i][i] = sum;
        }
        return new Matrix(data);
    }
}