package com.lina.dao;

import com.lina.bean.Customer;
import com.lina.commons.JdbcUtil;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

    public void f(String ...str) {

    }

    public int queryRows() {

        String sql = "select count(customer_id) from customer";
        Connection cn = JdbcUtil.getConnection();
        QueryRunner qr = new QueryRunner();

        ResultSetHandler<Integer> rsh = new ResultSetHandler<Integer>() {
            @Override
            public Integer handle(ResultSet rs) throws SQLException {
                if (rs.next()) {
                    return rs.getInt(1);
                }
                return -1;
            }
        };

        try {
            Integer n = qr.query(cn, sql, rsh);
            return n;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(cn!=null) {
                try {
                    cn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return -1;
    }

    public List<Customer> queryCustomer(int pageIndex, int pageRows) {

        int page = (pageIndex - 1) * pageRows;   //总页数=索引-1*每行的行数
        String sql = "select * from customer  limit  " + page + ", " + pageRows;
        Connection cn = JdbcUtil.getConnection();
        QueryRunner qr = new QueryRunner();
        try {
            ResultSetHandler<List<Customer>> rsh =
                    new BeanListHandler<Customer>(Customer.class);
            List<Customer> list = qr.query(cn, sql, rsh);
            return list;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if(cn!=null) {
                try {
                    cn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;


    }

}
