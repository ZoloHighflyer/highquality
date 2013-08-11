<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Tools Shop</title>
<link rel="stylesheet" type="text/css" href="style.css" />
<!--[if IE 6]>
<link rel="stylesheet" type="text/css" href="iecss.css" />
<![endif]-->
<script type="text/javascript" src="js/boxOver.js"></script>
</head>
<body>

<div id="main_container">

	<div id="header">

       <div class="top_right">
             <div class="languages">
                    
            </div>      
            <div class="big_banner">
            <a href="#"><img src="images/banner728.jpg" alt="" title="" border="0" /></a>
            </div>
        
        </div>
    
  
        <div id="logo">
            <a href="index.html"><img src="images/logo.png" alt="" title="" border="0" width="182" height="85" /></a>
	    </div>  

        

    </div>
    
   <div id="main_content"> 
   
            <div id="menu_tab">
                    <ul class="menu">
                         <li><a href="index.action" class="nav">主页</a></li>
                         <li class="divider"></li>
                         <li><a href="cosmetic.action" class="nav">化妆品</a></li>
						 <li class="divider"></li>
                         <li><a href="#" class="nav">宝石</a></li>
                    </ul>

            </div><!-- end of menu tab -->
            
    <div class="crumb_navigation">
    当前位置: <span class="current">主页</span>
    
    </div>        
    
   <div class="left_content">
    <div class="title_box">产品分类</div>
    
        <ul class="left_menu">
        <li class="odd"><a href="details.html">宝石</a></li>
        <li class="even"><a href="details.html">化妆品</a></li>
         <li class="odd"><a href="details.html">Hand Tools</a></li>
        <li class="even"><a href="details.html">Accessories</a></li>
         <li class="odd"><a href="details.html">Workwear</a></li>
         <li class="even"><a href="details.html">Spare Parts</a></li>
        <li class="odd"><a href="details.html">Power Tools</a></li>
        <li class="even"><a href="details.html">Air Tools</a></li>
         <li class="odd"><a href="details.html">Hand Tools</a></li>
        <li class="even"><a href="details.html">Accessories</a></li>
         <li class="odd"><a href="details.html">Workwear</a></li>
         <li class="even"><a href="details.html">Spare Parts</a></li>
        </ul> 
        
        
      
     
       
        
    
   </div><!-- end of left content --> 



   <div class="center_content">  
       <c:forEach items="${ids}" var="viewentry" >        
       <div class="center_title_bar">${viewentry.panelName}</div>       	
        <table border="0">
          <c:set var="order" value="1"/>        
          <c:forEach items="${viewentry.vos}" var="entry" >
            <c:choose>
              <c:when test="${order%3==1}">                 
               <tr><td>
                    <%@ include file="viewproduct.jsp" %>
               </td>
              </c:when>
              <c:when test="${order%3!=1&&order%3!=0}">
               <td>
                  <%@ include file="viewproduct.jsp" %>
               </td>
               </c:when>
               <c:when test="${order%3==0}">
               <td>
                   <%@ include file="viewproduct.jsp" %>
               </td></tr>
               </c:when>                    
             </c:choose>          
           <c:set var="order" value="${order+1}"/>
          </c:forEach> 
          <c:set var="order" value="${order-1}"/> 
          <c:choose>
            <c:when test="${order%3==1}">
               <td></td><td></td></tr>  
            </c:when>
            <c:when test="${order%3==2}">
               <td></td></tr>  
            </c:when>
          </c:choose>
       </table>
       </c:forEach>
     
    	<div class="center_title_bar">Recomended Products</div>
    
    	<div class="prod_box">

            <div class="center_prod_box">            
                 <div class="product_title"><a href="details.html">Makita 156 MX-VL</a></div>
                 <div class="product_img"><a href="details.html"><img src="images/p7.jpg" alt="" title="" border="0" /></a></div>
                 <div class="prod_price"><span class="reduce">350$</span> <span class="price">270$</span></div>                        
            </div>
           
            <div class="prod_details_tab">
            <a href="buy.html" class="prod_buy">Add to Cart</a>          
            <a href="details.html" class="prod_details">Details</a>            
            </div>                     
        </div>
    
    	<div class="prod_box">

            <div class="center_prod_box">            
                 <div class="product_title"><a href="details.html">Bosch XC</a></div>
                 <div class="product_img"><a href="details.html"><img src="images/p1.jpg" alt="" title="" border="0" /></a></div>
                 <div class="prod_price"><span class="reduce">350$</span> <span class="price">270$</span></div>                        
            </div>
           
            <div class="prod_details_tab">
            <a href="buy.html" class="prod_buy">Add to Cart</a>          
            <a href="details.html" class="prod_details">Details</a>            
            </div>                     
        </div>    
 

     	<div class="prod_box">

            <div class="center_prod_box">            
                 <div class="product_title"><a href="details.html">Lotus PP4</a></div>
                 <div class="product_img"><a href="details.html"><img src="images/p3.jpg" alt="" title="" border="0" /></a></div>
                 <div class="prod_price"><span class="reduce">350$</span> <span class="price">270$</span></div>                        
            </div>
           
            <div class="prod_details_tab">
            <a href="buy.html" class="prod_buy">Add to Cart</a>          
            <a href="details.html" class="prod_details">Details</a>            
            </div>                     
        </div>   
    
   
   </div><!-- end of center content -->



 <div class="right_content">
 
      <div class="title_box">搜索</div>  
     <div class="border_box">
		<input type="text" name="newsletter" class="newsletter_input" value="keyword"/>
        <a href="#" class="join">search</a>
     </div>    

   	   
     
   </div><!-- end of right content -->   


            
   </div><!-- end of main content -->
   
   
   
   <div class="footer">
   

        <div class="left_footer">
       
        </div>
        
        <div class="center_footer">
        Template name. All Rights Reserved 2008<br />
        <a href="http://www.cssmoban.com/" title="free css templates">cssmoban.com</a><br />
        <img src="images/payment.gif" alt="" title="" />
        </div>
        
        <div class="right_footer">
       
        </div>   
   
   </div>                 


</div>
<!-- end of main_container -->
</body>
</html>