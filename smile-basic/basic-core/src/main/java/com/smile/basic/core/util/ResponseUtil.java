package com.smile.basic.core.util;

import cn.hutool.json.JSONUtil;
import com.smile.basic.core.base.Result;
import com.smile.basic.core.constant.CharsetsConstants;
import com.smile.basic.core.constant.MessageConstant;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ResponseUtil {




   public static void OutUnauthorized(HttpServletResponse response) throws IOException {
       response.setStatus(HttpServletResponse.SC_OK);
       response.setCharacterEncoding(CharsetsConstants.UTF_8);
       response.setContentType(MediaType.APPLICATION_JSON_VALUE);
       String body= JSONUtil.toJsonStr(Result.unauthorized(MessageConstant.TOKEN_NOT_EXIST));
       PrintWriter out = response.getWriter();
       out.write(body);

   }
}
