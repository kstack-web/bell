package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Yoyaku;
import util.DBUtil;

public class YoyakuDAO {
	
	private final String dbPath;
	public YoyakuDAO(String dbPath) {
		
		this.dbPath=dbPath;
	}

    public int countByTime(String date, String time){
        String sql = "SELECT COUNT(*) FROM yoyaku WHERE date=? AND time=?";
        try (Connection con = DBUtil.getConnection(dbPath);
             PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1,date); ps.setString(2,time);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) return rs.getInt(1);
        } catch(Exception e){ e.printStackTrace(); }
        return 0;
    }

    public void insert(String date, String time, int kanjaID){
    	System.out.println("DAO.insert called: " + date + ", " + time + ", " + kanjaID);
    	String sql = "INSERT INTO yoyaku(date,time,kanjaID,valid) VALUES(?,?,?,1)";
        try (Connection con = DBUtil.getConnection(dbPath);
             PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1,date); ps.setString(2,time); ps.setInt(3,kanjaID);
            int result = ps.executeUpdate();
            System.out.println("INSERT result = " + result);
                        
           // ps.executeUpdate();
        } catch(Exception e){ e.printStackTrace(); }
    }

    public List<Yoyaku> findByKanja(String date, int kanjaID){
        List<Yoyaku> list = new ArrayList<>();
        String sql = "SELECT * FROM yoyaku WHERE date=? AND kanjaID=? AND valid=1";
        try(Connection con = DBUtil.getConnection(dbPath);
            PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1,date); ps.setInt(2,kanjaID);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Yoyaku y = new Yoyaku();
                y.setId(rs.getInt("id")); y.setDate(rs.getString("date"));
                y.setTime(rs.getString("time")); y.setKanjaID(rs.getInt("kanjaID"));
                y.setValid(rs.getBoolean("valid"));
                list.add(y);
            }
        } catch(Exception e){ e.printStackTrace(); }
        return list;
    }

    public void cancel(int id){
        String sql = "UPDATE yoyaku SET valid=0 WHERE id=?";
        try(Connection con = DBUtil.getConnection(dbPath);
            PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1,id); ps.executeUpdate();
        } catch(Exception e){ e.printStackTrace(); }
    }
    // ★ここだけ差し替える
    public boolean hasTodayYoyaku(int kanjaID) {
        String sql = "SELECT COUNT(*) FROM yoyaku " +
                     "WHERE kanjaID=? AND date = date('now') AND valid=1";

        try (Connection con = DBUtil.getConnection(dbPath);
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, kanjaID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
 // 今日の予約を患者IDで取消
    public void cancelTodayByKanja(int kanjaID) {
        String sql = "UPDATE yoyaku SET valid=0 " +
                     "WHERE kanjaID=? AND date = date('now') AND valid=1";

        try (Connection con = DBUtil.getConnection(dbPath);
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, kanjaID);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 // 今日の予約を1件取得
    public Yoyaku findTodayByKanja(int kanjaID) {
        String sql = "SELECT * FROM yoyaku " +
                     "WHERE kanjaID=? AND date = date('now') AND valid=1";

        try (Connection con = DBUtil.getConnection(dbPath);
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, kanjaID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Yoyaku y = new Yoyaku();
                y.setId(rs.getInt("id"));
                y.setDate(rs.getString("date"));
                y.setTime(rs.getString("time"));
                y.setKanjaID(rs.getInt("kanjaID"));
                y.setValid(rs.getBoolean("valid"));
                return y;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean hasTodayReservation(int kanjaID, String date){
        String sql = "SELECT COUNT(*) FROM yoyaku WHERE kanjaID=? AND date=? AND valid='1'";
        try (Connection con = DBUtil.getConnection(dbPath);
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, kanjaID);
            ps.setString(2, date);

            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return rs.getInt(1) > 0;
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

}