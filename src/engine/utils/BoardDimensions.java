package engine.utils;

public enum BoardDimensions {
    WIDTH(8),    // Width dimension of the board
    HEIGHT(8),   // Height dimension of the board
    DIAGONAL(8); // Diagonal dimension of the board

    private final int value;

    /**
     * Constructor to initialize a BoardDimensions enum value with a specific integer value.
     *
     * @param value the integer value associated with the enum dimension
     */
    BoardDimensions(int value) {
        this.value = value;
    }

    /**
     * Get the integer value associated with the enum dimension.
     *
     * @return the integer value
     */
    public int getValue() {
        return this.value;
    }
}
