package main.com.web.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.com.web.member.dto.Member;
import main.com.web.member.service.MemberService;

/**
 * Servlet implementation class MemberLoginServlet
 */
@WebServlet("/member/login")
public class MemberLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId = request.getParameter("memberId");
		String memberPwd = request.getParameter("memberPwd");
		Member m = new MemberService().memberLogin(memberId,memberPwd);
		if(m!=null) {
			if(m.getMemberId().equals("admin")) {
				HttpSession session =request.getSession();
				session.setAttribute("member", m);
				response.sendRedirect(request.getContextPath()+"/reserve/reserveupdate.do");
			}else {
			HttpSession session =request.getSession();
			session.setAttribute("member", m);
			response.sendRedirect(request.getContextPath()+"/");
			}
		}else {
		
			request.setAttribute("loginError", "비밀번호 및 아이디를 확인해주세요");
			request.getRequestDispatcher("/WEB-INF/views/member/login.jsp").forward(request, response);
		}
		
	}

}
