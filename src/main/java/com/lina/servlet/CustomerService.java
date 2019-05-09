package com.lina.servlet;


import com.google.gson.Gson;
import com.lina.bean.Customer;
import com.lina.dao.CustomerDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/customerQueryService")
public class CustomerService extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String op = req.getParameter("opType");
        CustomerDAO customerDAO = new CustomerDAO();
        if("query".equalsIgnoreCase(op)) {
            //获取页码和每页行数
            String pi = req.getParameter("pageIndex");
            String pr = req.getParameter("pageRows");
            //页码和每页行数的默认值
            int pageIndex = 1;
            int pageRows = 10;
            //如果传入了页码，则重新赋值
            if(pi != null) {
               pageIndex = Integer.parseInt(pi);
            }
            //如果传入了每页行数，则重新赋值
            if(pr != null) {
                pageRows = Integer.parseInt(pr);
            }

            //总行数
            Integer totalRows = customerDAO.queryRows();
            //总页数  计算总的行数 和 总的页数
            int t = totalRows / pageRows;
            int totalPage = totalRows % pageRows;
            if(totalPage != 0 ) {
                totalPage = t + 1;
            }else {
                totalPage = t;
            }
            //页码不能小于1，如果小于1，重新赋值为1
            if(pageIndex < 1) {
                pageIndex = 1;
            }
            //页码不能大于总的页数，如果大于总的页数，将页码重新赋值为最大值
            if(pageIndex > totalPage) {
                pageIndex = totalPage;
            }

            //查询客户信息
            List<Customer> list = customerDAO.queryCustomer(pageIndex, pageRows);

            //使用ajax查询的时候
            Map<String, Object> map = new HashMap<>();
            map.put("currentPage" , pageIndex);
            map.put("totalPage", totalPage);
            map.put("data", list);

            Gson gson = new Gson();
            String json = gson.toJson(map);
            resp.setContentType("text/json;charset=utf-8");
            PrintWriter out = resp.getWriter();
            out.println(json);
            out.flush();

            //查询客户信息
           /* List<Customer> list = customerDAO.queryCustomer(pageIndex, pageRows);
            //绑定查询的结果数据 以及页码和总的页数
            req.setAttribute("CustDATA", list);
            req.setAttribute("PAGE_INDEX", pageIndex);
            req.setAttribute("T_PAGE", totalPage);
            req.getRequestDispatcher("querycustomer.jsp").forward(req, resp);*/

        }
    }
}
