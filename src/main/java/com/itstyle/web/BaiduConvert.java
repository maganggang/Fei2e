package com.itstyle.web;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;

import com.itstyle.util.BaiduConvertUtil;
/**
 * 百度转换web入口
 * 创建者 科帮网 https://blog.52itstyle.com
 * 创建时间	2017年3月6日
 */
public class BaiduConvert extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void destroy() {
		super.destroy();
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("传语音？");
		// 因为要使用response打印，所以设置其编码
		response.setContentType("text/html;charset=utf-8");
		// 创建工厂
		DiskFileItemFactory dfif = new DiskFileItemFactory();
		// 使用工厂创建解析器对象
		ServletFileUpload fileUpload = new ServletFileUpload(dfif);
		try {
		// 使用解析器对象解析request，得到FileItem列表
		List<FileItem> list = fileUpload.parseRequest(request);
		File file=null;
		// 遍历所有表单项
		for(FileItem fileItem : list) {
		// 如果当前表单项为普通表单项
		if(fileItem.isFormField()) {
		// 获取当前表单项的字段名称
		String fieldName = fileItem.getFieldName();
		// 如果当前表单项的字段名为username
		if(fieldName.equals("username")) {
		// 打印当前表单项的内容，即用户在username表单项中输入的内容
		//response.getWriter().print("用户名：" + fileItem.getString() + "<br/>");
		}
		} else {//如果当前表单项不是普通表单项，说明就是文件字段
		String name = fileItem.getName();//获取上传文件的名称
		// 如果上传的文件名称为空，即没有指定上传文件
		if(name == null || name.isEmpty()) {
		continue;
		}
		// 获取真实路径，对应${项目目录}/uploads，当然，这个目录必须存在
		String savepath = this.getServletContext().getRealPath("/uploads");
		// 通过uploads目录和文件名称来创建File对象
		file = new File("d:/MP3/", name);
		// 把上传文件保存到指定位置
		fileItem.write(file);
		// 打印上传文件的名称
		//response.getWriter().print("上传文件名：" + name + "<br/>");
		// 打印上传文件的大小
		//response.getWriter().print("上传文件大小：" + fileItem.getSize() + "<br/>");
		// 打印上传文件的类型
		//response.getWriter().print("上传文件类型：" + fileItem.getContentType() + "<br/>");
		if(file!=null&&StringUtils.endsWith(name, ".pcm")) {
			BaiduConvertUtil util=new BaiduConvertUtil();
			String s=util.method1();
			response.getWriter().print(s);
		}
		}
		
		}
		 
		} catch (Exception e) {
		 
		throw new ServletException(e);
		 
		}

         
     }        
	}
