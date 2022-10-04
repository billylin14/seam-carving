package seamcarving;

import edu.princeton.cs.algs4.Picture;
import edu.washington.cse373.BaseTest;
import graphs.Edge;
import graphs.Graph;
import graphs.shortestpaths.ShortestPathFinder;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

public class BasicDijkstraSeamFinderTests extends BaseTest {
    protected SeamFinder createSeamFinder() {
        return new DijkstraSeamFinder() {
            @Override
            protected <G extends Graph<V, Edge<V>>, V> ShortestPathFinder<G, V, Edge<V>> createPathFinder() {
                return super.createPathFinder();
            }
        };
    }

    protected EnergyFunction createEnergyFunction() {
        return new DualGradientEnergyFunction();
    }

    protected Picture loadPicture(String name) {
        return new Picture(Path.of("data").resolve(name).toFile());
    }

    protected SeamFinderAssert assertThat(SeamFinder seamFinder) {
        return new SeamFinderAssert(seamFinder);
    }

    @Test
    public void findVerticalSeam_returnsCorrectSeam_3x3() {
        Picture p = loadPicture("3x3.png");
        EnergyFunction energyFunction = createEnergyFunction();
        double[][] energies = SeamCarver.computeEnergies(p, energyFunction);

        SeamFinder seamFinder = createSeamFinder();
        assertThat(seamFinder).verticalSeam(energies).hasSameEnergyAs(2, 2, 2);
    }

    @Test
    public void findHorizontalSeam_returnsCorrectSeam_3x3() {
        Picture p = loadPicture("3x3.png");
        EnergyFunction energyFunction = createEnergyFunction();
        double[][] energies = SeamCarver.computeEnergies(p, energyFunction);

        SeamFinder seamFinder = createSeamFinder();
        assertThat(seamFinder).horizontalSeam(energies).hasSameEnergyAs(2, 2, 2);
    }

    @Test
    public void findVerticalSeam_returnsCorrectSeam_3x4() {
        Picture p = loadPicture("3x4.png");
        EnergyFunction energyFunction = createEnergyFunction();
        double[][] energies = SeamCarver.computeEnergies(p, energyFunction);

        SeamFinder seamFinder = createSeamFinder();
        assertThat(seamFinder).verticalSeam(energies).hasSameEnergyAs(2, 1, 2, 2);
    }

    @Test
    public void findHorizontalSeam_returnsCorrectSeam_3x4() {
        Picture p = loadPicture("3x4.png");
        EnergyFunction energyFunction = createEnergyFunction();
        double[][] energies = SeamCarver.computeEnergies(p, energyFunction);

        SeamFinder seamFinder = createSeamFinder();
        assertThat(seamFinder).horizontalSeam(energies).hasSameEnergyAs(2, 3, 3);
    }

    @Test
    public void findVerticalSeam_returnsCorrectSeam_3x7() {
        Picture p = loadPicture("3x7.png");
        EnergyFunction energyFunction = createEnergyFunction();
        double[][] energies = SeamCarver.computeEnergies(p, energyFunction);

        SeamFinder seamFinder = createSeamFinder();
        assertThat(seamFinder).verticalSeam(energies).hasSameEnergyAs(0, 1, 1, 1, 1, 1, 1);
    }

    @Test
    public void findHorizontalSeam_returnsCorrectSeam_3x7() {
        Picture p = loadPicture("3x7.png");
        EnergyFunction energyFunction = createEnergyFunction();
        double[][] energies = SeamCarver.computeEnergies(p, energyFunction);

        SeamFinder seamFinder = createSeamFinder();
        assertThat(seamFinder).horizontalSeam(energies).hasSameEnergyAs(1, 2, 3);
    }

    @Test
    public void findVerticalSeam_returnsCorrectSeam_3x8() {
        Picture p = loadPicture("3x8.png");
        EnergyFunction energyFunction = createEnergyFunction();
        double[][] energies = SeamCarver.computeEnergies(p, energyFunction);

        SeamFinder seamFinder = createSeamFinder();
        assertThat(seamFinder).verticalSeam(energies).hasSameEnergyAs(0, 2, 2, 2, 2, 2, 2, 2, 2);
    }

    @Test
    public void findHorizontalSeam_returnsCorrectSeam_3x8() {
        Picture p = loadPicture("3x8.png");
        EnergyFunction energyFunction = createEnergyFunction();
        double[][] energies = SeamCarver.computeEnergies(p, energyFunction);

        SeamFinder seamFinder = createSeamFinder();
        assertThat(seamFinder).horizontalSeam(energies).hasSameEnergyAs(4, 4, 4);
    }

    @Test
    public void findVerticalSeam_returnsCorrectSeam_4x6() {
        Picture p = loadPicture("4x6.png");
        EnergyFunction energyFunction = createEnergyFunction();
        double[][] energies = SeamCarver.computeEnergies(p, energyFunction);

        SeamFinder seamFinder = createSeamFinder();
        assertThat(seamFinder).verticalSeam(energies).hasSameEnergyAs(2, 2, 1, 1, 2, 2);
    }

    @Test
    public void findHorizontalSeam_returnsCorrectSeam_4x6() {
        Picture p = loadPicture("4x6.png");
        EnergyFunction energyFunction = createEnergyFunction();
        double[][] energies = SeamCarver.computeEnergies(p, energyFunction);

        SeamFinder seamFinder = createSeamFinder();
        assertThat(seamFinder).horizontalSeam(energies).hasSameEnergyAs(3, 3, 4, 4);
    }

    @Test
    public void findVerticalSeam_returnsCorrectSeam_5x6() {
        Picture p = loadPicture("5x6.png");
        EnergyFunction energyFunction = createEnergyFunction();
        double[][] energies = SeamCarver.computeEnergies(p, energyFunction);

        SeamFinder seamFinder = createSeamFinder();
        assertThat(seamFinder).verticalSeam(energies).hasSameEnergyAs(2, 2, 2, 3, 2, 2);
    }

    @Test
    public void findHorizontalSeam_returnsCorrectSeam_5x6() {
        Picture p = loadPicture("5x6.png");
        EnergyFunction energyFunction = createEnergyFunction();
        double[][] energies = SeamCarver.computeEnergies(p, energyFunction);

        SeamFinder seamFinder = createSeamFinder();
        assertThat(seamFinder).horizontalSeam(energies).hasSameEnergyAs(2, 3, 2, 3, 2);
    }

    @Test
    public void findVerticalSeam_returnsCorrectSeam_6x5() {
        Picture p = loadPicture("6x5.png");
        EnergyFunction energyFunction = createEnergyFunction();
        double[][] energies = SeamCarver.computeEnergies(p, energyFunction);

        SeamFinder seamFinder = createSeamFinder();
        assertThat(seamFinder).verticalSeam(energies).hasSameEnergyAs(4, 4, 3, 2, 2);
    }

    @Test
    public void findHorizontalSeam_returnsCorrectSeam_6x5() {
        Picture p = loadPicture("6x5.png");
        EnergyFunction energyFunction = createEnergyFunction();
        double[][] energies = SeamCarver.computeEnergies(p, energyFunction);

        SeamFinder seamFinder = createSeamFinder();
        assertThat(seamFinder).horizontalSeam(energies).hasSameEnergyAs(2, 2, 1, 2, 1, 1);
    }

    @Test
    public void findVerticalSeam_returnsCorrectSeam_7x3() {
        Picture p = loadPicture("7x3.png");
        EnergyFunction energyFunction = createEnergyFunction();
        double[][] energies = SeamCarver.computeEnergies(p, energyFunction);

        SeamFinder seamFinder = createSeamFinder();
        assertThat(seamFinder).verticalSeam(energies).hasSameEnergyAs(2, 3, 4);
    }

    @Test
    public void findHorizontalSeam_returnsCorrectSeam_7x3() {
        Picture p = loadPicture("7x3.png");
        EnergyFunction energyFunction = createEnergyFunction();
        double[][] energies = SeamCarver.computeEnergies(p, energyFunction);

        SeamFinder seamFinder = createSeamFinder();
        assertThat(seamFinder).horizontalSeam(energies).hasSameEnergyAs(1, 1, 1, 1, 1, 1, 1);
    }

    @Test
    public void findVerticalSeam_returnsCorrectSeam_7x10() {
        Picture p = loadPicture("7x10.png");
        EnergyFunction energyFunction = createEnergyFunction();
        double[][] energies = SeamCarver.computeEnergies(p, energyFunction);

        SeamFinder seamFinder = createSeamFinder();
        assertThat(seamFinder).verticalSeam(energies).hasSameEnergyAs(4, 3, 4, 3, 4, 3, 3, 2, 3, 4);
    }

    @Test
    public void findHorizontalSeam_returnsCorrectSeam_7x10() {
        Picture p = loadPicture("7x10.png");
        EnergyFunction energyFunction = createEnergyFunction();
        double[][] energies = SeamCarver.computeEnergies(p, energyFunction);

        SeamFinder seamFinder = createSeamFinder();
        assertThat(seamFinder).horizontalSeam(energies).hasSameEnergyAs(8, 7, 7, 6, 6, 5, 4);
    }

    @Test
    public void findVerticalSeam_returnsCorrectSeam_8x3() {
        Picture p = loadPicture("8x3.png");
        EnergyFunction energyFunction = createEnergyFunction();
        double[][] energies = SeamCarver.computeEnergies(p, energyFunction);

        SeamFinder seamFinder = createSeamFinder();
        assertThat(seamFinder).verticalSeam(energies).hasSameEnergyAs(1, 1, 1);
    }

    @Test
    public void findHorizontalSeam_returnsCorrectSeam_8x3() {
        Picture p = loadPicture("8x3.png");
        EnergyFunction energyFunction = createEnergyFunction();
        double[][] energies = SeamCarver.computeEnergies(p, energyFunction);

        SeamFinder seamFinder = createSeamFinder();
        assertThat(seamFinder).horizontalSeam(energies).hasSameEnergyAs(2, 2, 2, 2, 2, 2, 2, 2);
    }

    @Test
    public void findVerticalSeam_returnsCorrectSeam_10x10() {
        Picture p = loadPicture("10x10.png");
        EnergyFunction energyFunction = createEnergyFunction();
        double[][] energies = SeamCarver.computeEnergies(p, energyFunction);

        SeamFinder seamFinder = createSeamFinder();
        assertThat(seamFinder).verticalSeam(energies).hasSameEnergyAs(7, 7, 7, 7, 7, 7, 8, 8, 7, 6);
    }

    @Test
    public void findHorizontalSeam_returnsCorrectSeam_10x10() {
        Picture p = loadPicture("10x10.png");
        EnergyFunction energyFunction = createEnergyFunction();
        double[][] energies = SeamCarver.computeEnergies(p, energyFunction);

        SeamFinder seamFinder = createSeamFinder();
        assertThat(seamFinder).horizontalSeam(energies).hasSameEnergyAs(1, 1, 2, 3, 3, 3, 3, 2, 3, 3);
    }

    @Test
    public void findVerticalSeam_returnsCorrectSeam_10x12() {
        Picture p = loadPicture("10x12.png");
        EnergyFunction energyFunction = createEnergyFunction();
        double[][] energies = SeamCarver.computeEnergies(p, energyFunction);

        SeamFinder seamFinder = createSeamFinder();
        assertThat(seamFinder).verticalSeam(energies).hasSameEnergyAs(7, 6, 7, 8, 7, 7, 6, 7, 6, 5, 6, 6);
    }

    @Test
    public void findHorizontalSeam_returnsCorrectSeam_10x12() {
        Picture p = loadPicture("10x12.png");
        EnergyFunction energyFunction = createEnergyFunction();
        double[][] energies = SeamCarver.computeEnergies(p, energyFunction);

        SeamFinder seamFinder = createSeamFinder();
        assertThat(seamFinder).horizontalSeam(energies).hasSameEnergyAs(9, 9, 10, 10, 10, 9, 8, 7, 7, 6);
    }

    @Test
    public void findVerticalSeam_returnsCorrectSeam_12x10() {
        Picture p = loadPicture("12x10.png");
        EnergyFunction energyFunction = createEnergyFunction();
        double[][] energies = SeamCarver.computeEnergies(p, energyFunction);

        SeamFinder seamFinder = createSeamFinder();
        assertThat(seamFinder).verticalSeam(energies).hasSameEnergyAs(1, 2, 2, 1, 2, 2, 3, 4, 3, 2);
    }

    @Test
    public void findHorizontalSeam_returnsCorrectSeam_12x10() {
        Picture p = loadPicture("12x10.png");
        EnergyFunction energyFunction = createEnergyFunction();
        double[][] energies = SeamCarver.computeEnergies(p, energyFunction);

        SeamFinder seamFinder = createSeamFinder();
        assertThat(seamFinder).horizontalSeam(energies).hasSameEnergyAs(4, 4, 5, 6, 7, 6, 5, 6, 6, 5, 4, 5);
    }

    @Test
    public void findVerticalSeam_returnsCorrectSeam_diagonals() {
        Picture p = loadPicture("diagonals.png");
        EnergyFunction energyFunction = createEnergyFunction();
        double[][] energies = SeamCarver.computeEnergies(p, energyFunction);

        SeamFinder seamFinder = createSeamFinder();
        assertThat(seamFinder).verticalSeam(energies).hasSameEnergyAs(7, 6, 5, 5, 6, 7, 7, 6, 5, 4, 3, 3);
    }

    @Test
    public void findHorizontalSeam_returnsCorrectSeam_diagonals() {
        Picture p = loadPicture("diagonals.png");
        EnergyFunction energyFunction = createEnergyFunction();
        double[][] energies = SeamCarver.computeEnergies(p, energyFunction);

        SeamFinder seamFinder = createSeamFinder();
        assertThat(seamFinder).horizontalSeam(energies).hasSameEnergyAs(10, 9, 8, 7, 6, 5, 4, 3, 3);
    }

    @Test
    public void findVerticalSeam_returnsCorrectSeam_stripes() {
        Picture p = loadPicture("stripes.png");
        EnergyFunction energyFunction = createEnergyFunction();
        double[][] energies = SeamCarver.computeEnergies(p, energyFunction);

        SeamFinder seamFinder = createSeamFinder();
        assertThat(seamFinder).verticalSeam(energies).hasSameEnergyAs(2, 3, 4, 5, 6, 7, 8, 8, 8, 8, 8, 8);
    }

    @Test
    public void findHorizontalSeam_returnsCorrectSeam_stripes() {
        Picture p = loadPicture("stripes.png");
        EnergyFunction energyFunction = createEnergyFunction();
        double[][] energies = SeamCarver.computeEnergies(p, energyFunction);

        SeamFinder seamFinder = createSeamFinder();
        assertThat(seamFinder).horizontalSeam(energies).hasSameEnergyAs(2, 3, 4, 5, 6, 7, 8, 9, 10);
    }
}
