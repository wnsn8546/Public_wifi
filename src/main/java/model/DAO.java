package model;

import api.RowInfoDto;
import api.WifiDto;
import model.WifiVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAO extends JdbcManager {
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public void saveAllWifiDetailList(List<RowInfoDto> lists) throws Exception {
        String sql = "insert into wifi_info(x_swifi_mgr_no, x_swifi_wrdofc, x_swifi_main_nm, \n" +
                "                 x_swifi_adres1, x_swifi_adres2, x_swifi_instl_floor, \n" +
                "                 x_swifi_instl_ty, x_swifi_instl_mby, x_swifi_svc_se, \n" +
                "                 x_swifi_cmcwr, x_swifi_cnstc_year, x_swifi_inout_door, \n" +
                "                 x_swifi_remars3, lat, lnt, work_dttm) \n" +
                "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            con = createConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement(sql);
            int basicBactchSize = 1000;
            int count = 0;

            for (int i = 0; i < lists.size(); i++) {
                RowInfoDto infoDto = lists.get(i);
                ps.setString(1, infoDto.getMgrNo());
                ps.setString(2, infoDto.getWrdofc());
                ps.setString(3, infoDto.getMainNm());
                ps.setString(4, infoDto.getAdres1());
                ps.setString(5, infoDto.getAdres2());
                ps.setString(6, infoDto.getFloor());
                ps.setString(7, infoDto.getTy());
                ps.setString(8, infoDto.getMby());
                ps.setString(9, infoDto.getSvcSe());
                ps.setString(10, infoDto.getCmcwr());
                ps.setString(11, infoDto.getYear());
                ps.setString(12, infoDto.getDoor());
                ps.setString(13, infoDto.getRemars3());
                ps.setString(14, infoDto.getLat());
                ps.setString(15, infoDto.getLnt());
                ps.setString(16, infoDto.getDttm());

                ps.addBatch();
                count++;
                if (basicBactchSize == count) {
                    ps.executeBatch();
                    ps.clearBatch();
                    count = 0;
                }
            }

            ps.executeBatch();
            con.commit();
        } catch (Exception e) {
            con.rollback();
        } finally {
            con.setAutoCommit(true);
            closeStatement(ps);
            closeConnection(con);
        }
    }

    public List<WifiVO> search(Double latDouble, Double lntDouble) {

        String selectNearWifiQuery = "select * " +
                ", format((6371 * acos(cos(radians(" + lntDouble + ")) * cos(radians(lat)) * cos(radians(lnt) - radians(" + latDouble + ")) " +
                "+ sin(radians(" + lntDouble + ")) * sin(radians(lat)))), 4) as distance " +
                " from wifi_info " +
                " order by distance , X_SWIFI_MGR_NO" +
                " limit 20";

        try {
            con = createConnection();
            ps = con.prepareStatement(selectNearWifiQuery);
            rs = ps.executeQuery();

            List<WifiVO> list = new ArrayList<>();

            while (rs.next()) {
                WifiVO wifiVO = new WifiVO(
                        rs.getString("distance"),
                        rs.getString("X_SWIFI_MGR_NO"),
                        rs.getString("X_SWIFI_WRDOFC"),
                        rs.getString("X_SWIFI_MAIN_NM"),
                        rs.getString("X_SWIFI_ADRES1"),
                        rs.getString("X_SWIFI_ADRES2"),
                        rs.getString("X_SWIFI_INSTL_FLOOR"),
                        rs.getString("X_SWIFI_INSTL_TY"),
                        rs.getString("X_SWIFI_INSTL_MBY"),
                        rs.getString("X_SWIFI_SVC_SE"),
                        rs.getString("X_SWIFI_CMCWR"),
                        rs.getString("X_SWIFI_CNSTC_YEAR"),
                        rs.getString("X_SWIFI_INOUT_DOOR"),
                        rs.getString("X_SWIFI_REMARS3"),
                        rs.getString("LAT"),
                        rs.getString("LNT"),
                        rs.getString("WORK_DTTM")

                );
                list.add(wifiVO);
            }
            return list;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            closeResultSet(rs);
            closeStatement(ps);
            closeConnection(con);
        }
    }

    public int removeAllData(){

        Connection con = null;
        PreparedStatement ps = null;

        String removeOneQuery = "delete from wifi_info ";

        try {
            con = createConnection();
            con.setAutoCommit(false);

            ps = con.prepareStatement(removeOneQuery);

            int resultCount = ps.executeUpdate();
            con.commit();
            return resultCount;

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        } finally {
            closeStatement(ps);
            closeConnection(con);
        }
    }
}