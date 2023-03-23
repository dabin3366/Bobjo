package com.bobjo.reservation.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bobjo.basicform.action.Action;
import com.bobjo.basicform.action.ActionForward;
import com.bobjo.reservation.db.ReservationDAO;

public class ReservationAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : ReservationAction_execute 호출");
		String m_id = (String)request.getSession().getAttribute("m_id");
		ActionForward forward = new ActionForward();
		if(m_id == null) {
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			return forward;
		}
		 
        // 예약 정보를 예약 전용 페이지로 전송
		ReservationDAO dao = new ReservationDAO();
		request.setAttribute("store_no", request.getParameter("store_no"));
		request.setAttribute("store_name", dao.getStoreName(Integer.parseInt(request.getParameter("store_no"))));
		request.setAttribute("refund_policy", dao.getRefundPolicy(Integer.parseInt(request.getParameter("store_no"))));
		request.setAttribute("menu_no", request.getParameter("menu_no"));
		request.setAttribute("menu_amount", request.getParameter("menu_amount"));
		request.setAttribute("price", request.getParameter("price"));
		
		forward.setPath("./reservation/rsrv.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
