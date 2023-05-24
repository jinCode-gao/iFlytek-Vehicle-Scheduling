package common;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class People {
    public int ID;
    public LocalTime startTime;

    public LocalTime endTime;//结束时间，用于输出
    public ArrayList<Integer> path;//最终确定的路径，用于检查

    public ArrayList<Integer> storehouseNeed2Go;//需要去的所有仓库

    public int factory;//要去的工厂在matrix中的下标

    public People(int ID,String startTime,ArrayList<Integer> storehouseNeed2Go,int factoryIndex){
        this.ID=ID;

        // 定义时间格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        // 解析时间字符串为LocalTime对象
        this.startTime = LocalTime.parse(startTime, formatter);

        this.storehouseNeed2Go=storehouseNeed2Go;

        this.factory=factoryIndex;

    }

}
