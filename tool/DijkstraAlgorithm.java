package tool;

import java.util.*;

public class DijkstraAlgorithm {
    // 表示无穷大距离，用于表示两个节点之间没有直接连接
    private static final int INFINITY = Integer.MAX_VALUE;

    // 使用邻接矩阵表示带权重的图
    private int[][] graph;
    private int numVertices;

    public DijkstraAlgorithm(int[][] graph) {
        this.graph = graph;
        this.numVertices = graph.length;
    }

    public int[] dijkstra(int startVertex) {
        // 存储从起始节点到每个节点的最短距离
        int[] distances = new int[numVertices];

        // 存储每个节点是否已经找到了最短路径
        boolean[] visited = new boolean[numVertices];

        // 初始化距离数组为无穷大
        Arrays.fill(distances, INFINITY);

        // 起始节点到自身的距离为0
        distances[startVertex] = 0;

        // 寻找最短路径
        for (int i = 0; i < numVertices - 1; i++) {
            // 找到当前距离最小的节点
            int minDistanceVertex = findMinDistanceVertex(distances, visited);

            // 将该节点标记为已访问
            visited[minDistanceVertex] = true;

            // 更新其他节点的最短距离
            for (int j = 0; j < numVertices; j++) {
                if (!visited[j] && graph[minDistanceVertex][j] != 0 &&
                        distances[minDistanceVertex] != INFINITY &&
                        distances[minDistanceVertex] + graph[minDistanceVertex][j] < distances[j]) {
                    distances[j] = distances[minDistanceVertex] + graph[minDistanceVertex][j];
                }
            }
        }

        return distances;
    }

    private int findMinDistanceVertex(int[] distances, boolean[] visited) {
        int minDistance = INFINITY;
        int minDistanceVertex = -1;

        for (int i = 0; i < numVertices; i++) {
            if (!visited[i] && distances[i] < minDistance) {
                minDistance = distances[i];
                minDistanceVertex = i;
            }
        }

        return minDistanceVertex;
    }
}
