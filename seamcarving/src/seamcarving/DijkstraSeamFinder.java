package seamcarving;

import edu.princeton.cs.algs4.Picture;
import graphs.Edge;
import graphs.Graph;
import graphs.shortestpaths.DijkstraShortestPathFinder;
import graphs.shortestpaths.ShortestPath;
import graphs.shortestpaths.ShortestPathFinder;

import java.awt.image.PixelGrabber;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class DijkstraSeamFinder implements SeamFinder {
    //graph implementation
    private class Pixel {
        private int row;
        private int col;
        private double weight; //energy
        public Pixel(int row, int col, double weight) {
            this.row = row;
            this.col = col;
            this.weight = weight;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;
            Pixel pixel = (Pixel) o;
            return row == pixel.row &&
                col == pixel.col &&
                Double.compare(pixel.weight, weight) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col, weight);
        }
    }
    private class MyHorizontalGraph implements Graph<Pixel, Edge<Pixel>> {
        private Pixel[][] pixels;
        private Pixel dummyStart;
        private Pixel dummyEnd;
        public MyHorizontalGraph(double[][] energies){ //augment the graph in a way that allows you to choose the start and end node
            int numCol = energies.length; //6
            int numRow = energies[0].length;  //5
            this.pixels = new Pixel[numCol][numRow];
            for (int col = 0; col < numCol; col++) {
                for (int row = 0; row < numRow; row++) {
                    Pixel thisPixel = new Pixel(row, col, energies[col][row]);
                    this.pixels[col][row] = thisPixel;
                }
            }
            this.dummyStart = new Pixel(-1, -1, 0);
            this.dummyEnd = new Pixel(-2, -2, 0);
        }
        @Override
        public Collection<Edge<Pixel>> outgoingEdgesFrom(Pixel vertex) { //suppose energy can be obtained from energies[][]
            Set<Edge<Pixel>> neighbors = new HashSet<>();
            int numCol = pixels.length; //6
            int numRow = pixels[0].length;  //5
            //special case: big parent node
            if (vertex.equals(dummyStart)){
                for (int i = 0; i < numRow; i++){
                    neighbors.add(new Edge<>(vertex, pixels[0][i], pixels[0][i].weight));
                }
            } else if (!vertex.equals(dummyEnd)) { //if it's not the dummyEnd, then it has neighbors
                //edge cases: (1) right edge (2) top edge (3) bottom edge
                if (vertex.col < numCol - 1) { //(1) if the vertex is NOT on the right most edge
                    if (vertex.row > 0) { //(2) if it's NOT on the top edge, then it has a top right neighbor
                        Pixel topPixel = pixels[vertex.col + 1][vertex.row - 1];
                        neighbors.add(new Edge<>(vertex, topPixel, topPixel.weight));
                    }
                    if (vertex.row < numRow - 1) { //(3) if it's NOT on the bottom edge, then it has a bottom right neighbor
                        Pixel bottomPixel = pixels[vertex.col + 1][vertex.row + 1];
                        neighbors.add(new Edge<>(vertex, bottomPixel, bottomPixel.weight));
                    }
                    Pixel middlePixel = pixels[vertex.col + 1][vertex.row];
                    neighbors.add(new Edge<>(vertex, middlePixel, middlePixel.weight));
                } else { //if reached to the last row, connect it to a dummy node
                    neighbors.add(new Edge<>(vertex, dummyEnd, dummyEnd.weight));
                }
            }
            return neighbors;
        }
    }

    private class MyVerticalGraph implements Graph<Pixel, Edge<Pixel>> {
        private Pixel[][] pixels;
        private Pixel dummyStart;
        private Pixel dummyEnd;
        public MyVerticalGraph(double[][] energies){
            int numCol = energies.length; //6
            int numRow = energies[0].length;  //5
            this.pixels = new Pixel[numCol][numRow];
            for (int col = 0; col < numCol; col++) {
                for (int row = 0; row < numRow; row++) {
                    Pixel thisPixel = new Pixel(row, col, energies[col][row]);
                    this.pixels[col][row] = thisPixel;
                }
            }
            this.dummyStart = new Pixel(-1, -1, 0);
            this.dummyEnd = new Pixel(-2, -2, 0);
        }
        @Override
        public Collection<Edge<Pixel>> outgoingEdgesFrom(Pixel vertex) { //suppose energy can be obtained from energies[][]
            Set<Edge<Pixel>> neighbors = new HashSet<>();
            int numCol = pixels.length;
            int numRow = pixels[0].length;
            //special case: big parent node
            if (vertex.equals(dummyStart)){
                for (int i = 0; i < numCol; i++){
                    neighbors.add(new Edge<>(vertex, pixels[i][0], pixels[i][0].weight));
                }
            } else if (!vertex.equals(dummyEnd)){ //if it's not the dummyEnd, then it has neighbors
                //edge cases: (1) bottom (2) left edge (3) right edge
                if (vertex.row < numRow - 1) { //(1) if the vertex is NOT on the bottom edge
                    if (vertex.col > 0) { //(2) if it's NOT on the left edge, then it has a bottom left neighbor
                        Pixel leftPixel = pixels[vertex.col - 1][vertex.row + 1];
                        neighbors.add(new Edge<>(vertex, leftPixel, leftPixel.weight));
                    }
                    if (vertex.col < numCol - 1) { //(3) if it's NOT on the right edge, then it has a bottom right neighbor
                        Pixel rightPixel = pixels[vertex.col + 1][vertex.row + 1];
                        neighbors.add(new Edge<>(vertex, rightPixel, rightPixel.weight));
                    }
                    Pixel middlePixel = pixels[vertex.col][vertex.row + 1];
                    neighbors.add(new Edge<>(vertex, middlePixel, middlePixel.weight));
                } else { //if reached to the last row, connect it to a dummy node
                    neighbors.add(new Edge<>(vertex, dummyEnd, dummyEnd.weight));
                }
            }
            return neighbors;
        }
    }

    // TODO: replace all 4 references to "Object" on the line below with whatever vertex type
    //  you choose for your graph
    private final ShortestPathFinder<Graph<Pixel, Edge<Pixel>>, Pixel, Edge<Pixel>> pathFinder; //field

    public DijkstraSeamFinder() {
        this.pathFinder = createPathFinder();
    } //constructor

    protected <G extends Graph<V, Edge<V>>, V> ShortestPathFinder<G, V, Edge<V>> createPathFinder() { //helper method for constructor
        /*
        We override this during grading to test your code using our correct implementation so that
        you don't lose extra points if your implementation is buggy.
        */
        return new DijkstraShortestPathFinder<>(); //returns a ShortestPathFinder (a path)
    }

    @Override
    public List<Integer> findHorizontalSeam(double[][] energies) {
        int numCol = energies.length;
        int numRow = energies[0].length;
        //instantiate the graph
        MyHorizontalGraph horizontalGraph = new MyHorizontalGraph(energies);
        ShortestPath<Pixel, Edge<Pixel>> finalPath = null;
        ShortestPath<Pixel, Edge<Pixel>> thisPath = pathFinder.findShortestPath(horizontalGraph, horizontalGraph.dummyStart, horizontalGraph.dummyEnd);
        if (thisPath.exists()) {
            finalPath = thisPath;
        }
        //extract vertices from path
        List<Pixel> pathVertices = finalPath.vertices();
        //convert to return format
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < pathVertices.size(); i++) {
            Pixel thisPixel = pathVertices.get(i);
            if (thisPixel.row >= 0) {
                result.add(thisPixel.row);
            }
        }
        return result;
    }

    @Override
    public List<Integer> findVerticalSeam(double[][] energies) {
        int numCol = energies.length;
        int numRow = energies[0].length;
        //instantiate the graph
        MyVerticalGraph verticalGraph = new MyVerticalGraph(energies);
        ShortestPath<Pixel, Edge<Pixel>> finalPath = null;
        ShortestPath<Pixel, Edge<Pixel>> thisPath = pathFinder.findShortestPath(verticalGraph, verticalGraph.dummyStart, verticalGraph.dummyEnd);
        if (thisPath.exists()) {
            finalPath = thisPath;
        }
        //extract vertices from path
        List<Pixel> pathVertices = finalPath.vertices();
        //convert to return format
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < pathVertices.size(); i++) {
            Pixel thisPixel = pathVertices.get(i);
            if (thisPixel.col >= 0) {
                result.add(thisPixel.col);
            }
        }
        return result;
    }
}
