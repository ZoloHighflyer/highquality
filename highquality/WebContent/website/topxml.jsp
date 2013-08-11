<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<% 
response.setContentType("text/xml;charset=UTF-8");
String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
String str = "<units><unit><id>1</id><userName>陈华雄</userName>"
				+ "<message>你好</message></unit>"+
				"<unit><id>2</id><userName>陈华荣</userName>"+
				"<message>您好</message></unit>"+
				"</units>";
		System.out.println(xml + str);
		response.getWriter().write(xml + str);
%>
<%--  
<?xml version="1.0" encoding="UTF-8" ?> 
- <!--   Copyright w3school.com.cn 
  --> 
- <!--  W3School.com.cn bookstore example 
  --> 
- <bookstore>
- <book category="children">
  <title lang="en">Harry Potter</title> 
  <author>J K. Rowling</author> 
  <year>2005</year> 
  <price>29.99</price> 
  </book>
- <book category="cooking">
  <title lang="en">Everyday Italian</title> 
  <author>Giada De Laurentiis</author> 
  <year>2005</year> 
  <price>30.00</price> 
  </book>
- <book category="web" cover="paperback">
  <title lang="zh">学习 XML</title> 
  <author>Erik T. Ray</author> 
  <year>2003</year> 
  <price>39.95</price> 
  </book>
- <book category="web">
  <title lang="zh">技术研究</title> 
  <author>陈华雄</author> 
  <author>Per Bothner</author> 
  <author>Kurt Cagle</author> 
  <author>James Linn</author> 
  <author>Vaidyanathan Nagarajan</author> 
  <year>2003</year> 
  <price>49.99</price> 
  </book>
  </bookstore>
  --%>