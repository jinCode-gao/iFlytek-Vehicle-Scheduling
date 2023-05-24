package IO;

import common.People;

import java.util.ArrayList;
import java.util.Scanner;

public class Input {
    public int storehouseNum;
    public int factoryNum;
    public int[][] getInput(ArrayList<People> peopleList){
        Scanner scanner = new Scanner(System.in);

        storehouseNum=scanner.nextInt();
        factoryNum=scanner.nextInt();

        int nodeNum=storehouseNum+factoryNum+1;

        int[][] matrix = new int[nodeNum][nodeNum];
        int peopleID=0;

        // 输入仓库之间的距离
        for (int i = 0; i < storehouseNum; i++) {
            for (int j = 0; j < storehouseNum; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        //输入宿舍到各仓库的距离
        for (int i = 0; i < storehouseNum; i++){
            int temp=scanner.nextInt();
            matrix[nodeNum-1][i]=temp;
            matrix[i][nodeNum-1]=temp;
        }

        //遍历工厂
        for(int k=0;k<factoryNum;k++){
            int peopleNum=scanner.nextInt();
            int factoryIndex=k+storehouseNum;//工厂index
            //输入每个工厂到各仓库的距离
            for(int i=0;i<storehouseNum;i++){
                int dist=scanner.nextInt();

                matrix[factoryIndex][i]=dist;
                matrix[i][factoryIndex]=dist;
            }
            //输入要去该工厂的所有工人
            for (int i=0;i<peopleNum;i++){
                int storehouseNumNeed2Go=scanner.nextInt();
                String startTime=scanner.next();
                //输入一个人的所有要去的仓库
                ArrayList<Integer> storehouseNeed2Go=new ArrayList<>();
                for (int j=0;j<storehouseNumNeed2Go;j++){
                    int storehouseIndex=scanner.nextInt();
                    storehouseNeed2Go.add(storehouseIndex);
                }
                People people=new People(peopleID,startTime,storehouseNeed2Go,factoryIndex);
                peopleList.add(people);
                peopleID++;
            }
        }

        return matrix;
    }
}
