import IO.Input;
import common.People;

import java.util.ArrayList;
import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        Input input=new Input();//处理输入的类

        //peopleList是所有工人，工人记录了自身的任务等属性
        ArrayList<People> peopleList=new ArrayList<>();

        //matrix是斜对称矩阵，下标前storehouseNum个代表仓库，接下来factoryNum个是工厂，最后一个是宿舍
        int[][] matrix=input.getInput(peopleList);
        int storehouseNum=input.storehouseNum;//仓库数量
        int factoryNum=input.factoryNum;//工厂数量
        int a=1;
    }
}
