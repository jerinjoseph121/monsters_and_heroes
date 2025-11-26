package Map;

import Common.utils.Colors;
import Common.utils.Position;
import Common.utils.MapTileType;

import java.util.*;

public class Map {
    int totalRows;
    int totalCols;

    MapTile[][] mapMatrix;

    Position playerCurrentPosition;

    public Map(int row, int col, double blockProbability, double marketProbability, Position startPosition) {
        totalRows = row;
        totalCols = col;

        mapMatrix = new MapTile[totalRows][totalCols];

        playerCurrentPosition = startPosition;

        mapGeneration(blockProbability,  marketProbability);
    }

    private void mapGeneration(double blockProbability, double marketProbability) {
        if (mapMatrix == null)
            return;

        int[][] tileTypeMatrix = new int[totalRows][totalCols];

        for (int i = 0; i < totalRows; i++) {
            for (int j = 0; j < totalCols; j++) {
                tileTypeMatrix[i][j] = MapTileType.COMMON.getTypeId();
            }
        }

        int startRow = playerCurrentPosition.getRow();
        int startCol = playerCurrentPosition.getCol();

        // Total accessible cells (start with all)
        int accessibleCount = totalRows * totalCols;

        // List of all cells except start cell
        ArrayList<Position> cells = new ArrayList<>();
        for (int r = 0; r < totalRows; r++) {
            for (int c = 0; c < totalCols; c++) {
                if ((r == startRow && c == startCol)) {
                    continue;
                }
                cells.add(new  Position(r, c));
            }
        }

        // Shuffle to randomize blocking order
        Random rand = new Random();
        Collections.shuffle(cells, rand);


        // Try to block cells without breaking connectivity
        int itr = 0;
        while (itr < cells.size()) {
            // Try to block with probability p
            if (rand.nextDouble() < blockProbability) {
                Position cell = cells.get(itr);
                int r = cell.getRow();
                int c = cell.getCol();

                tileTypeMatrix[r][c] = MapTileType.INACCESSIBLE.getTypeId();  // temporarily block
                accessibleCount--;

                // Check if all remaining accessible cells are still reachable from start
                int reachable = bfsReachableCount(tileTypeMatrix, startRow, startCol);

                if (reachable != accessibleCount) {
                    // Blocking this cell isolates something → undo
                    tileTypeMatrix[r][c] = MapTileType.COMMON.getTypeId();;
                    accessibleCount++;
                }
            }
            itr++;
        }

        for (int i = 0; i < totalRows; i++) {
            for (int j = 0; j < totalCols; j++) {
                Position position = new Position(i, j);
                if (tileTypeMatrix[i][j] != MapTileType.INACCESSIBLE.getTypeId()) {
                    if (rand.nextDouble() < marketProbability) {
                        MapTile mapTile = new MapTile(MapTileType.MARKET, position);
                        mapMatrix[i][j] = mapTile;
                    }
                    else {
                        MapTile mapTile = new MapTile(MapTileType.COMMON, position);
                        mapMatrix[i][j] = mapTile;
                    }
                }
                else {
                    MapTile mapTile = new MapTile(MapTileType.INACCESSIBLE, position);
                    mapMatrix[i][j] = mapTile;
                }
            }
        }
    }

    // BFS from (sr, sc), return how many accessible cells (not tileType INACCESSIBLE) can be reached
    private static int bfsReachableCount(int[][] tileTypeMatrix, int sr, int sc) {
        int rows = tileTypeMatrix.length;
        int cols = tileTypeMatrix[0].length;

        if (tileTypeMatrix[sr][sc] == MapTileType.INACCESSIBLE.getTypeId())
            return 0; // start is blocked (shouldn't happen)

        boolean[][] visited = new boolean[rows][cols];
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{sr, sc});
        visited[sr][sc] = true;

        int count = 0;
        int[][] dirs = {{1,0}, {-1,0}, {0,1}, {0,-1}};

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0], c = cur[1];
            count++;

            for (int[] d : dirs) {
                int nr = r + d[0];
                int nc = c + d[1];

                if (nr < 0 || nr >= rows || nc < 0 || nc >= cols)
                    continue; // out of bound
                if (visited[nr][nc])
                    continue; // visited, so no need to count again
                if (tileTypeMatrix[nr][nc] == MapTileType.INACCESSIBLE.getTypeId())
                    continue; // blocked, so no counting

                visited[nr][nc] = true;
                q.add(new int[]{nr, nc});
            }
        }

        return count;
    }

    // creates the map using string builder, adds all the required color and icon for each cell
    public void displayMap() {
        System.out.println("=================== WORLD MAP ===================\n");

        StringBuilder boundary = new StringBuilder();

        for (int i = 0; i < totalCols; i++) {
            boundary.append("+=====");
        }
        boundary.append("+");

        for (int i = 0; i < totalRows; i++) {
            System.out.println(boundary);

            StringBuilder row = getRowStringBuilder(i);
            System.out.println(row);
        }

        System.out.println(boundary);
    }

    private StringBuilder getRowStringBuilder(int i) {
        StringBuilder row = new StringBuilder();
        for (int j = 0; j < totalCols; j++) {
            MapTileType mapTileType = mapMatrix[i][j].getType();
            row.append("|");
            if (i == playerCurrentPosition.getRow() && j == playerCurrentPosition.getCol()) {
                String playerBgColor = Colors.GREEN_BG;
                if (mapTileType == MapTileType.MARKET) {
                    playerBgColor = Colors.GOLD_BG;
                }

                row.append(" ").append(playerBgColor).append(" ");
                row.append(Colors.ROYAL_BLUE  + "P");
                row.append(" " + Colors.RESET + " ");
            }
            else {
                row.append("  ");
                switch (mapTileType) {
                    case INACCESSIBLE:
                        row.append(Colors.RED + "X");
                        break;
                    case MARKET:
                        row.append(Colors.YELLOW + "M");
                        break;
                    default:
                        row.append(Colors.GREEN + "_");
                }
                row.append("  " + Colors.RESET);
            }
        }
        row.append("|");
        return row;
    }

    public Position getPlayerCurrentPosition() {
        return playerCurrentPosition;
    }

    public boolean updatePlayerCurrentPosition(Position updatedPlayerPosition) {
        int currentRow = playerCurrentPosition.getRow();
        int currentCol = playerCurrentPosition.getCol();

        int updatedRow = updatedPlayerPosition.getRow();
        int updatedCol = updatedPlayerPosition.getCol();

        // updated position should be up, down, left or right that of the current position
        if (!((currentRow == updatedRow + 1 && currentCol == updatedCol) ||
                (currentRow == updatedRow - 1 && currentCol == updatedCol) ||
                (currentRow == updatedRow && currentCol == updatedCol + 1) ||
                (currentRow == updatedRow && currentCol == updatedCol - 1))) {
            return false;
        }

        if (updatedRow < 0 || updatedRow >= totalRows ||  updatedCol < 0 || updatedCol >= totalCols) {
            return false;
        }

        if (mapMatrix[updatedRow][updatedCol].getType().equals(MapTileType.INACCESSIBLE)) {
            return false;
        }

        playerCurrentPosition = updatedPlayerPosition;

        return true;
    }

    public MapTileType getPlayerMapTileType() {
        int playerRow = playerCurrentPosition.getRow();
        int playerCol = playerCurrentPosition.getCol();

        if (playerRow < 0 || playerRow >= totalRows ||  playerCol < 0 || playerCol >= totalCols) {
            return MapTileType.INACCESSIBLE;
        }

        return mapMatrix[playerRow][playerCol].getType();
    }
}
