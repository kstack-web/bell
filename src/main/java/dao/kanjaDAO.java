package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Kanja;
import util.DBUtil;

public class KanjaDAO {

    // 患者ID + 生年月日で検索（ログイン用）
    public Kanja login(int kanjaID, String bday) {
    	//SQL文で、kanjaテーブルの、カラム指定kanjaID bday
        String sql = "SELECT * FROM kanja WHERE kanjaID = ? AND bday = ?";
        //DB接続を取得（try resourceで、閉じてくれる。）
        try (Connection con = DBUtil.getConnection();
        	//SQL実行用オブジェクト生成
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, kanjaID);
            ps.setString(2, bday);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {//１件あるか確認
                Kanja k = new Kanja();
                k.setKanjaID(rs.getInt("kanjaID"));
                k.setBday(rs.getString("bday"));
                k.setKanjaName(rs.getString("kanjaName"));
                return k;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}