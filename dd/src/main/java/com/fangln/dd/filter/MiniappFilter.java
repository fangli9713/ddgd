package com.fangln.dd.filter;

import com.fangln.dd.entity.UserToken;
import com.fangln.dd.util.LogUtil;
import com.fangln.dd.util.MiniappConstant;
import com.fangln.dd.util.MiniappUtil;
import com.fangln.dd.util.UserTokenUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 
 * @author fangln
 *
 */
@WebFilter(urlPatterns = "/mini/*", asyncSupported = true)
public class MiniappFilter implements Filter {

	private static final String PRIVATE_URI = "/mini/private/";

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		String contextPath = request.getContextPath();
		String requstUri = request.getRequestURI();
		try {
//			if (!request.getRequestURL().toString().startsWith("https://")) {
//				response.sendError(HttpServletResponse.SC_FORBIDDEN);
//				return;
//			}
			Map<?, ?> requestMap = MiniappUtil.getRequestMap(request);
			request.setAttribute("requestMap", requestMap);
			String zo = contextPath + PRIVATE_URI;
			if (requstUri.startsWith(zo)) {
				if (requestMap == null || requestMap.get("token") == null) {
					MiniappUtil.writeString(response, null, MiniappConstant.PARAM_ERROR, "缺少参数:token");
					return;
				}
				String token = (String) requestMap.get("token");
				try {
					boolean inValid;
					final UserToken t = UserTokenUtil.parseToken(token);
					inValid = t == null || new java.util.Date().getTime() > t.getToken_expire();
					if (inValid) {
						MiniappUtil.writeString(response, null, MiniappConstant.INVALID_TOKEN, "无效参数:token");
						return;
					}
				} catch (Exception ex) {
					MiniappUtil.writeString(response, null, MiniappConstant.INVALID_TOKEN, "无效参数:token");
					return;
				}
			}
			chain.doFilter(req, resp);
		} catch (Throwable ex) {
			MiniappUtil.writeString(response, null, MiniappConstant.ERR_INTERNAL, "服务器内部出错");
			ex.printStackTrace();
			LogUtil.getLog().warn("异常:" + ex);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
