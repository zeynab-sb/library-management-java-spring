<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
<div class="container">
    <div class="col-lg-12">
        <div class="main-box clearfix">
            <div class="table-responsive">
                <table class="table user-list">
                    <thead>
                    <tr>
                        <th><span>User</span></th>
                        <%--                            <th><span>Created</span></th>--%>
                        <th><span>Status</span></th>
                        <th><span>Role</span></th>
                        <%--                            <th><span>Email</span></th>--%>
                        <th>&nbsp;</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%--                        <tr>--%>
                    <%--                            <td>--%>
                    <%--&lt;%&ndash;                                <img src="https://bootdey.com/img/Content/avatar/avatar1.png" alt="">&ndash;%&gt;--%>
                    <%--                                <a href="#" class="user-link">Mila Kunis</a>--%>
                    <%--                                <span class="user-subhead">Admin</span>--%>
                    <%--                            </td>--%>
                    <%--                            <td>--%>
                    <%--                                2013/08/08--%>
                    <%--                            </td>--%>
                    <%--                            <td class="text-center">--%>
                    <%--                                <span class="label label-default">Inactive</span>--%>
                    <%--                            </td>--%>
                    <%--                            <td>--%>
                    <%--                                <a href="#">mila@kunis.com</a>--%>
                    <%--                            </td>--%>

<%--                    <thead>--%>
<%--                    <tr>--%>
<%--                        <th> Username</th>--%>
<%--                        <th> Status</th>--%>
<%--                        <th> Role</th>--%>



<%--                    </tr>--%>
<%--                    </thead>--%>
                    <tbody>
                    <tr th:each="user : ${users}">
                        <td><span th:text="${user.username}">  fuck   </span></td>
                        <td><span th:text="${user.enabled}"> FUck  </span></td>

                        <td style="width: 20%;">
                            <a href="#" class="table-link">
									<span class="fa-stack">
										<i class="fa fa-square fa-stack-2x"></i>
										<i class="fa fa-search-plus fa-stack-1x fa-inverse"></i>
									</span>
                            </a>
                            <a href="#" class="table-link">
									<span class="fa-stack">
										<i class="fa fa-square fa-stack-2x"></i>
										<i class="fa fa-pencil fa-stack-1x fa-inverse"></i>
									</span>
                            </a>
                            <a href="#" class="table-link danger">
									<span class="fa-stack">
										<i class="fa fa-square fa-stack-2x"></i>
										<i class="fa fa-trash-o fa-stack-1x fa-inverse"></i>
									</span>
                            </a>
                        </td>


                    </tr>
                    </tbody>
                </table>


                <%--                <ul class="pagination pull-right">--%>
                <%--                    <li><a href="#"><i class="fa fa-chevron-left"></i></a></li>--%>
                <%--                    <li><a href="#">1</a></li>--%>
                <%--                    <li><a href="#">2</a></li>--%>
                <%--                    <li><a href="#">3</a></li>--%>
                <%--                    <li><a href="#">4</a></li>--%>
                <%--                    <li><a href="#">5</a></li>--%>
                <%--                    <li><a href="#"><i class="fa fa-chevron-right"></i></a></li>--%>
                <%--                </ul>--%>
            </div>
        </div>
    </div>
</div>