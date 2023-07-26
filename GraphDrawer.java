import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class GraphDrawer extends JFrame {
    private int[][] adjacencyMatrix; // 图的邻接矩阵
    private int numVertices; // 顶点数量

    public GraphDrawer(int[][] adjacencyMatrix) {
        this.adjacencyMatrix = adjacencyMatrix;
        this.numVertices = adjacencyMatrix.length;

        setTitle("Graph Visualization");
        setSize(4000, 4000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        int radius = 15; // 顶点圆的半径
        int centerX = getWidth() / 2; // 窗口中心横坐标
        int centerY = getHeight() / 2; // 窗口中心纵坐标

        double angle = 2 * Math.PI / numVertices;
        for (int i = 0; i < numVertices; i++) {
            int x = (int) (centerX + 500 * Math.cos(i * angle)); // 顶点的横坐标
            int y = (int) (centerY + 500 * Math.sin(i * angle)); // 顶点的纵坐标

            g.setColor(Color.RED);
            g.fillOval(x - radius, y - radius, 2 * radius, 2 * radius); // 绘制顶点

            for (int j = i + 1; j < numVertices; j++) {
                if (adjacencyMatrix[i][j] != 0) {
                    int x2 = (int) (centerX + 500 * Math.cos(j * angle));
                    int y2 = (int) (centerY + 500 * Math.sin(j * angle));

                    g.setColor(Color.BLACK);
                    g.drawLine(x, y, x2, y2); // 绘制边

                    // 绘制边的权值（假设权值以整数形式存在邻接矩阵中）
                    int weight = adjacencyMatrix[i][j];
                    int labelX = (x + x2) / 2;
                    int labelY = (y + y2) / 2;
                    g.setColor(Color.BLUE);
                    g.drawString(String.valueOf(weight), labelX, labelY);
                }
            }
        }
    }

    public static void main(String[] args) {
        // 示例图的邻接矩阵表示
//        int[][] adjacencyMatrix = {
//                {0, 2, 3, 0, 0},
//                {2, 0, 4, 1, 0},
//                {3, 4, 0, 5, 2},
//                {0, 1, 5, 0, 3},
//                {0, 0, 2, 3, 0}
//        };
        int i,j;
        int [][] adjacencyMatrix=new int[20][20];
        Scanner in=new Scanner(System.in);
        for(i=0;i<20;i++){
            for(j=0;j<20;j++){
                adjacencyMatrix[i][j]=in.nextInt();
            }
        }

        SwingUtilities.invokeLater(() -> new GraphDrawer(adjacencyMatrix));
    }
}
