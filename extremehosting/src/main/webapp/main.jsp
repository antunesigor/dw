<%-- 
    Document   : profile
    Created on : Apr 6, 2017, 9:25:56 PM
    Author     : Igor-Surface
--%>

<%@page import="Model.User"%>
<%@page import="Model.Rate"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<% 
    User user = (User) request.getAttribute("user"); 
 %>

<!DOCTYPE html>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script src="js/jssor.slider-23.1.3.min.js" type="text/javascript"></script>
    <script src="js/imageslider.js" type="text/javascript"></script>
    <title>Profile - Extreme Hosting</title>
</head>
<body>
    <jsp:include page='header.jsp' />
    <div id="jssor_1">
        <!-- Loading Screen -->
        <div data-u="loading" class="loading">
            <div class="insideloading1"></div>
            <div class="insideloading2"></div>
        </div>
        <div data-u="slides" class="insidejssor">
              <% for(int i = 1; i < 6; i++) { %>
                <div>
                    <img data-u="image" src="img/photo<%=i%>.jpg" />
                    <img data-u="thumb" src="img/photo<%=i%>.jpg" />
                </div>
                <% } %>
        </div>

        <div class="profile">
            <img src="img/profile.png" />
            <h1><%=user.getName()%></h1>
            <h2><%=user.getCity()%></h2>
        </div>

        <!-- Arrow Navigator -->
        <span data-u="arrowleft" class="jssora05l" style="top:208px;left:8px;"></span>
        <span data-u="arrowright" class="jssora05r" style="top:208px;right:8px;width:40px;height:40px;"></span>
    </div>
    <script type="text/javascript">jssor_1_slider_init();</script>


    <div class="ratings">
        <ul>
            <li>
                <div class="rating">
                    <p>Personal</p>
                    <% for(float i=0;i<Float.parseFloat(request.getAttribute("media-personal").toString());i++){%>
                        <span>☆</span>
                    <%}%>
                    <div class="comment">
                        <table>
                            <% for(Rate r : (List<Rate>) request.getAttribute("rate-personal")) { %>
                                <% if(r.getType() == 0){%>
                                <tr>
                                    <th><a href="?id=<%=r.getSender().getId()%>"><img src="img/profile.png" width="50" height="50" /><%=r.getSender().getName()%></a></th>
                                    <th><% for(int i=0;i<r.getValue();i++) {%>☆<%}%></th>
                                </tr>
                                <tr>
                                    <td colspan="2"><%=r.getDescription()%></td>
                                </tr>
                                <%}%>
                            <% } %>
                        </table>
                    </div>
                </div>
            </li>
            <li>
                <div class="rating">
                    <p>Guest</p>
                    <% for(float i=0;i<Float.parseFloat(request.getAttribute("media-guest").toString());i++){%>
                        <span>☆</span>
                    <%}%>
                    <div class="comment">
                        <table>
                            <% for(Rate r : (List<Rate>) request.getAttribute("rate-guest")) { %>
                                <tr>
                                    <th><a href="?id=<%=r.getSender().getId()%>"><img src="img/profile.png" width="50" height="50" /><%=r.getSender().getName()%></a></th>
                                    <th><% for(int i=0;i<r.getValue();i++) {%>☆<%}%></th>
                                </tr>
                                <tr>
                                    <td colspan="2"><%=r.getDescription()%></td>
                                </tr>
                            <% } %>
                        </table>
                    </div>
                </div>
            </li>

           <li>
                <div class="rating">
                    <p>Host</p>
                    <% for(float i=0;i<Float.parseFloat(request.getAttribute("media-host").toString());i++){%>
                        <span>☆</span>
                    <%}%>
                    <div class="comment">
                        <table>
                            <% for(Rate r : (List<Rate>) request.getAttribute("rate-host")) { %>
                                <tr>
                                    <th><a href="?id=<%=r.getSender().getId()%>"><img src="img/profile.png" width="50" height="50" /><%=r.getSender().getName()%></a></th>
                                    <th><% for(int i=0;i<r.getValue();i++) {%>☆<%}%></th>
                                </tr>
                                <tr>
                                    <td colspan="2"><%=r.getDescription()%></td>
                                </tr>
                            <% } %>
                        </table>
                    </div>
                </div>
            </li>
        </ul>
    </div>

</body>
</html>
