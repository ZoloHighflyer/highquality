 <%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
 <div class="prod_box">
                     <div class="center_prod_box">            
                       <div class="product_title"><a href="details.html">${entry.productName}</a></div>
                       <div class="product_img"><a href="details.html"><img src="${pageContext.request.contextPath}/${entry.picture}" alt="" width="150" height="150" title="" border="0" /></a></div>
                       <div class="prod_price"><span class="reduce">￥${entry.price}</span> <span class="price">￥${entry.outPrice}</span></div>                        
                     </div>
           
                     <div class="prod_details_tab">
                      <a href="buy.html" class="prod_buy">Add to Cart</a>          
                      <a href="details.html" class="prod_details">Details</a>            
                     </div>                     
</div>