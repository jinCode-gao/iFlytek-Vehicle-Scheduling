import IO.Input;
import common.People;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class main {
    public static LocalDateTime addTime(LocalDateTime dateTime, int hours, int minutes, int seconds) {
        return dateTime.plusHours(hours).plusMinutes(minutes).plusSeconds(seconds);
    }

    public static List<List<Integer>> generatePermutations(int[] array) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> permutation = new ArrayList<>();
        boolean[] used = new boolean[array.length];

        generate(array, permutation, used, result);

        return result;
    }

    public static void generate(int[] array, List<Integer> permutation, boolean[] used, List<List<Integer>> result) {
        if (permutation.size() == array.length) {
            result.add(new ArrayList<>(permutation));
            return;
        }

        for (int i = 0; i < array.length; i++) {
            if (!used[i]) {
                permutation.add(array[i]);
                used[i] = true;
                generate(array, permutation, used, result);
                used[i] = false;
                permutation.remove(permutation.size() - 1);
            }
        }
    }
    private static void floyd(int n, int[][] dist, int [][] path) {
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        path[i][j] = k;
                    }
                }
            }
        }
    }

//    //获取点a到点b最短路的中间路径(路径中不含a，b)
//    private static void getpath(int a, int b,int idx, int[][] path, int[] anspath) {
//        if (path[a][b] == -1) {
//        }
//        else {
//            int k = path[a][b];
//            getpath(a, k,idx, path, anspath);
//            anspath[idx++] = k;
//            getpath(k, b, idx, path, anspath);
//        }
//    }

//    //将“点1沿着当前must数组中指定点的顺序走到点n的路径”保存到anspath数组
//    private static void savpath(int idx, int[] anspath, int[] must,int k, int n, int[][] path) {
//        anspath[idx++] = 1;
//        getpath(1, must[0], idx, path, anspath);
//        for (int i = 0; i < k - 1; i++) {
//            anspath[idx++] = must[i];
//            getpath(must[i], must[i + 1], idx, path, anspath);
//        }
//        anspath[idx++] = must[k - 1];
//        getpath(must[k - 1], n, idx, path, anspath);
//        anspath[idx++] = n;
//    }

    //返回 点1沿着当前must数组中指定点的顺序走到点n 的路径长度
    private static int getdis(int[][] dist, int[] must, int INF, int k, int n) {
        int distance = dist[0][must[0]];
        if (dist[0][must[0]] == INF)
            return INF + 1;
        for (int i = 0; i < k - 1; i++) {
            if (dist[must[i]][must[i + 1]] == INF)
                return INF + 1;
            distance += dist[must[i]][must[i + 1]];
        }
        if (dist[must[k - 1]][n-1] == INF)
            return INF + 1;
        distance += dist[must[k - 1]][n-1];
        return distance;
    }
    public static void writeArrayToFile(String[] array, String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (int i = 0; i < array.length; i++) {
                writer.write(String.valueOf(array[i]));
                writer.newLine();
            }
        }
    }

    public static void main(String[] args) {
        Input input=new Input();//处理输入的类

        //peopleList是所有工人，工人记录了自身的任务等属性
        ArrayList<People> peopleList=new ArrayList<>();
        int MAX=10000;
        //matrix是斜对称矩阵，下标前storehouseNum个代表仓库，接下来factoryNum个是工厂，最后一个是宿舍
        int[][] matrix=input.getInput(peopleList);


        int storehouseNum=input.storehouseNum;//仓库数量
        int factoryNum=input.factoryNum;//工厂数量

        int[][] dist=new int[storehouseNum+2][storehouseNum+2];
        int[][] factory=new int[factoryNum][storehouseNum];



        String[] result=new String[peopleList.size()];
        for(int people_i=0;people_i<peopleList.size();people_i++) {



            for(int i=0;i<storehouseNum+1;i++){
                for(int j=0;j<storehouseNum+1;j++){
                    dist[i][j]=matrix[i][j];
                }
            }
            for(int i=storehouseNum+1;i<storehouseNum+1+factoryNum;i++){
                for(int j=1;j<1+storehouseNum;j++){
                    factory[i-1-storehouseNum][j-1]=matrix[i][j];
                }
            }

            for(int i=1;i<storehouseNum+1;i++){
                dist[storehouseNum+1][i]=factory[peopleList.get(people_i).factory-1-storehouseNum][i-1];
                dist[i][storehouseNum+1]=factory[peopleList.get(people_i).factory-1-storehouseNum][i-1];
            }

            dist[storehouseNum+1][0]=MAX;
            dist[0][storehouseNum+1]=MAX;

            for(int i=0;i<storehouseNum+2;i++){
                dist[i][i]=0;
            }
            int n = dist.length;


//            for(int i=0;i<dist.length;i++){
//                for(int j=0;j<dist.length;j++){
//                    System.out.print(dist[i][j]);
//                    System.out.print(" ");
//                }
//                System.out.println();
//            }
            int k = peopleList.get(people_i).storehouseNeed2Go.size();

//            int idx;
//            int[] anspath = new int[45];
            int[] must = new int[k];       //存储指定点序列

            //int[][] dist=new int[45][45];
            int[][] path = new int[n][n];   //用于floyd存储路径

            int[][] temp=new int[dist.length][dist.length];
            for(int i=0;i<temp.length;i++){
                for(int j=0;j<temp.length;j++){
                    temp[i][j]=dist[i][j];
                }
            }
            floyd(n-1, dist, path);
//            floyd(n, temp, path);
//
//            for(int i=0;i<n;i++){
//                dist[n-1][i]=temp[n-1][i];
//                dist[i][n-1]=temp[i][n-1];
//            }
//
//            for(int i=0;i<n;i++){
//                for(int j=0;j<n;j++){
//                    if(temp[i][j]!=dist[i][j]){
//                        int kk=0;
//                    }
//                }
//            }

            for (int l = 0; l < k; l++) {
                must[l] = peopleList.get(people_i).storehouseNeed2Go.get(l);
            }

            List<List<Integer>> permutations = generatePermutations(must);

            //使用next_permutation函数前先对数组进行排序
            Arrays.sort(must, 0, k);
            int mindis = MAX;
            for (int l = 0; l < permutations.size(); l++) {
                for(int m=0;m<k;m++){
                    must[m]=permutations.get(l).get(m);
                }
                int d = getdis(dist, must, MAX, k, n);
                if (mindis > d) {//如果存在更短的路径则更新答案，并把路径存到anspath[]
                    mindis = d;
                }
            }
            LocalDateTime dateTime = peopleList.get(people_i).startTime.atDate(LocalDate.now());
            LocalDateTime newDateTime = addTime(dateTime, mindis/60, mindis%60, 0);
            System.out.print(peopleList.get(people_i).startTime);
            System.out.print("    ");
            System.out.println(newDateTime.toLocalTime());
            result[people_i]=newDateTime.toLocalTime().toString();

        }
        String filePath = "result.txt"; // 文件路径
        try {
            writeArrayToFile(result, filePath);
            System.out.println("SUCCESS");
        } catch (IOException e) {
            System.out.println("FAIL");
        }
    }
}
