package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.DBUtils;

public class AddUserServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String Email = req.getParameter("E-mail");
		
		
		resp.setContentType("text/html;charset=gbk");
		PrintWriter out = resp.getWriter();
		/*out.println("用户名："+username+"<br>");
		out.println("密&nbsp&nbsp&nbsp&nbsp码："+password+"<br>");
		out.println("邮&nbsp&nbsp&nbsp&nbsp箱："+Email+"<br>");*/
		
		/*
		 * 将用户信息插入到数据库
		 */
		Connection conn = null;
		PreparedStatement stat = null;
		try {
			conn = DBUtils.getConn();
			stat = conn.prepareStatement("insert into t_user "
					+ "values(null,?,?,?)");
			stat.setString(1, username);
			stat.setString(2, password);
			stat.setString(3, Email);
			out.println("添加成功");
			stat.executeUpdate();
		} catch (Exception e) {
			/*
			 * step 1、记日志
			 * 将异常的所有信息记录下来，
			 * 一般会记录到文件里面
			 */
			e.printStackTrace();
			/*
			 * step 2、看异常能否恢复，
			 * 如果不能恢复（比如 数据库服务暂停，网络终端，一般将这样的异常称为系统异常），
			 * 则提示用户稍后重试。
			 * 如果能够恢复，则立即恢复
			 */
			out.println("系统异常，稍后重试");
		}finally {
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		/*
		 * 如果没有调用out.close方法，则容器会自动调用out.close方法
		 */
//		out.close();
		
		
	}
}
