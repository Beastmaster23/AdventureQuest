package application.models.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import application.models.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


/**
 * Represents a collection of tiles in a rectangle grid
 * @author Interco
 */
public class TileMap extends GameObject {
	private Vector tileDimensions;
	private String[] paths;
	private Tile[][] tiles;
	private int[][] tileMap;
	private int rows, coloumns;

	public TileMap(double width, double height, Vector tileDimension) {
		getDimensions().setX(width);
		getDimensions().setY(height);
		tileDimensions = new Vector();
		tileDimensions.setX(tileDimension.getX());
		tileDimensions.setY(tileDimension.getY());
		rows = (int) (height / tileDimensions.getY());
		coloumns = (int) (width / tileDimensions.getX());
		paths=new String[0];
		this.setCollider(new TileMapCollider(this));
		tiles = new Tile[rows][coloumns];
		tileMap = new int[rows][coloumns];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < coloumns; j++) {
				tiles[i][j] = null;
				tileMap[i][j] = -1;
			}
		}
	}

	public void draw(GraphicsContext ctx) {
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[i].length; j++) {
				if (tiles[i][j] != null)
					ctx.drawImage(tiles[i][j].getSprite().getSprite(), j * tileDimensions.getX(),
							i * tileDimensions.getY());
			}
		}
	}
	
	/**
	 * Fills map with tiles
	 * 
	 * @param paths Image paths
	 * @throws FileNotFoundException if the image cannot be found
	 */
	public void loadMapTiles(String[] paths) throws FileNotFoundException {
		this.paths=paths;
		for (int i = 0; i < tileMap.length; i++) {
			for (int j = 0; j < tileMap[i].length; j++) {
				if (tileMap[i][j] < 0) {
					tiles[i][j] = null;
					continue;
				}
				Tile tile = new Tile();
				tile.getPosition().setX(j*tileDimensions.getX());
				tile.getPosition().setY(i*tileDimensions.getY());
				tile.getDimensions().setX(tileDimensions.getX());
				tile.getDimensions().setY(tileDimensions.getY());
				FileInputStream fin = new FileInputStream(paths[tileMap[i][j]]);
				Image img = new Image(fin, tileDimensions.getX(), tileDimensions.getY(), false, false);
				Sprite sprite = new Sprite(img);
				tile.setSprite(sprite);
				tiles[i][j] = tile;
			}
		}
	}
	public void loadMapTiles() throws FileNotFoundException {
		for (int i = 0; i < tileMap.length; i++) {
			for (int j = 0; j < tileMap[i].length; j++) {
				if (tileMap[i][j] < 0) {
					tiles[i][j] = null;
					continue;
				}
				Tile tile = new Tile();
				tile.getPosition().setX(j*tileDimensions.getX());
				tile.getPosition().setY(i*tileDimensions.getY());
				tile.getDimensions().setX(tileDimensions.getX());
				tile.getDimensions().setY(tileDimensions.getY());
				FileInputStream fin = new FileInputStream(paths[tileMap[i][j]]);
				Image img = new Image(fin, tileDimensions.getX(), tileDimensions.getY(), false, false);
				Sprite sprite = new Sprite(img);
				tile.setSprite(sprite);
				tiles[i][j] = tile;
			}
		}
	}
	/**
	 * Loads map
	 * @param map index of path array
	 * @param paths Images paths
	 */
	public void loadMap(int[][] map, String[] paths) {
		this.paths=paths;
		tiles = new Tile[map.length][map[0].length];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j] < 0) {
					tiles[i][j] = null;
					continue;
				}
				Tile tile = new Tile();
				tile.getPosition().setX(j*tileDimensions.getX());
				tile.getPosition().setY(i*tileDimensions.getY());
				tile.getDimensions().setX(tileDimensions.getX());
				tile.getDimensions().setY(tileDimensions.getY());
				Image img = new Image(paths[map[i][j]], tileDimensions.getX(), tileDimensions.getY(), false, false);
				Sprite sprite = new Sprite(img);
				tile.setSprite(sprite);
				tiles[i][j] = tile;
			}
		}
	}
	/**
	 * Saves map to path
	 * @param path
	 */
	public void saveMap(String path) {
		File f = new File(path);
		try {
			FileWriter fw = new FileWriter(f);
			String strs = "";
			for (int j = 0; j < paths.length; j++) {
				strs += paths[j];
				if (j + 1 < paths.length) {
					strs += ",";
				}
			}
			fw.write(strs + "\n");
			for (int i = 0; i < tileMap.length; i++) {
				String str = "";
				for (int j = 0; j < tileMap[i].length; j++) {
					str += "" + tileMap[i][j];
					if (j + 1 < tileMap[i].length) {
						str += ",";
					}
				}
				fw.write(str + "\n");
			}
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static TileMap loadMapByFile(String path) {
		TileMap map=null;
		File f = new File(path);
		try {
			Scanner scn = new Scanner(f);
			ArrayList<ArrayList<Integer>> buildMap = new ArrayList<ArrayList<Integer>>();
			if (!scn.hasNextLine()) {
				System.err.println("Tile map is not in the correct Format!");
				return map;
			}
			String[] size = scn.nextLine().split(",");
			int width=Integer.parseInt(size[0]), height=Integer.parseInt(size[1]);
			Vector tile=new Vector(Integer.parseInt(size[2]), Integer.parseInt(size[3]));
			map=new TileMap(width, height, tile);
			
			if (!scn.hasNextLine()) {
				System.err.println("Tile map is not in the correct Format!");
				return map;
			}
			map.paths = scn.nextLine().split(",");

			while (scn.hasNextLine()) {
				String[] arr = scn.nextLine().split(",");
				ArrayList<Integer> row = new ArrayList<Integer>();
				for (int i = 0; i < arr.length; i++) {
					row.add(Integer.parseInt(arr[i]));
				}
				buildMap.add(row);
			}
			
			map.tileMap = new int[buildMap.size()][buildMap.get(0).size()];
			for (int i = 0; i < buildMap.size(); i++) {
				for (int j = 0; j < buildMap.get(i).size(); j++) {
					map.tileMap[i][j] = buildMap.get(i).get(j);
				}
			}

			map.loadMapTiles();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}

	public void setTile(int row, int coloumn, Tile tile) {
		if (tile == null)
			tileMap[row][coloumn] = -1;
		else
			tileMap[row][coloumn] = tile.getId();
		tiles[row][coloumn] = tile;
	}

	public Vector getTileDimensions() {
		return tileDimensions;
	}

	public void setTileDimensions(Vector tileDimensions) {
		this.tileDimensions = tileDimensions;
	}

	public Tile[][] getTiles() {
		return tiles;
	}

	public void setTiles(Tile[][] tiles) {
		this.tiles = tiles;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getColoumns() {
		return coloumns;
	}

	public void setColoumns(int coloumns) {
		this.coloumns = coloumns;
	}
}
