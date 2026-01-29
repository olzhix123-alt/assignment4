package repository;

import model.MenuItem;
import model.Food;
import model.Drink;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MenuItemRepository implements CrudRepository<MenuItem> {

    @Override
    public void create(MenuItem entity) {
        String sql = "INSERT INTO menu_items (name, price, type, extra_info) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, entity.getName());
            pstmt.setDouble(2, entity.getPrice());

            if (entity instanceof Food) {
                pstmt.setString(3, "Food");
                pstmt.setString(4, ((Food) entity).getIngredients());
            } else if (entity instanceof Drink) {
                pstmt.setString(3, "Drink");
                pstmt.setString(4, String.valueOf(((Drink) entity).getVolume()));
            }

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<MenuItem> findAll() {
        List<MenuItem> items = new ArrayList<>();
        String sql = "SELECT * FROM menu_items";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                String type = rs.getString("type");
                String info = rs.getString("extra_info");


                if ("Food".equals(type)) {
                    items.add(new Food(id, name, price, info));
                } else if ("Drink".equals(type)) {
                    items.add(new Drink(id, name, price, Double.parseDouble(info)));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    @Override
    public MenuItem findById(int id) {
        String sql = "SELECT * FROM menu_items WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("name");
                    double price = rs.getDouble("price");
                    String type = rs.getString("type");
                    String info = rs.getString("extra_info");

                    if ("Food".equals(type)) {
                        return new Food(id, name, price, info);
                    } else {
                        return new Drink(id, name, price, Double.parseDouble(info));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(MenuItem entity) {
        String sql = "UPDATE menu_items SET name = ?, price = ?, extra_info = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, entity.getName());
            pstmt.setDouble(2, entity.getPrice());
            if (entity instanceof Food) {
                pstmt.setString(3, ((Food) entity).getIngredients());
            } else {
                pstmt.setString(3, String.valueOf(((Drink) entity).getVolume()));
            }
            pstmt.setInt(4, entity.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM menu_items WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
