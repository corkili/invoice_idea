<%--
  Created by IntelliJ IDEA.
  User: 李浩然
  Date: 2017/6/24
  Time: 14:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title><spring:message code="title.add_invoice" /></title>

    <!-- Bootstrap -->
    <link href="../vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="../vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <!-- NProgress -->
    <link href="../vendors/nprogress/nprogress.css" rel="stylesheet">
    <!-- iCheck -->
    <link href="../vendors/iCheck/skins/flat/green.css" rel="stylesheet">
    <!-- bootstrap-wysiwyg -->
    <link href="../vendors/google-code-prettify/bin/prettify.min.css" rel="stylesheet">
    <!-- Select2 -->
    <link href="../vendors/select2/dist/css/select2.min.css" rel="stylesheet">
    <!-- Switchery -->
    <link href="../vendors/switchery/dist/switchery.min.css" rel="stylesheet">
    <!-- starrr -->
    <link href="../vendors/starrr/dist/starrr.css" rel="stylesheet">
    <!-- bootstrap-daterangepicker -->
    <link href="../vendors/bootstrap-daterangepicker/daterangepicker.css" rel="stylesheet">

    <!-- Custom Theme Style -->
    <link href="../build/css/custom.min.css" rel="stylesheet">

    <style>
        .file {
            position: relative;
            display: inline-block;
            background: #D0EEFF;
            border: 1px solid #99D3F5;
            border-radius: 4px;
            padding: 4px 12px;
            overflow: hidden;
            color: #1E88C7;
            text-decoration: none;
            text-indent: 0;
            line-height: 20px;
        }
        .file input {
            position: absolute;
            font-size: 100px;
            right: 0;
            top: 0;
            opacity: 0;
        }
        .file:hover {
            background: #AADFFD;
            border-color: #78C3F3;
            color: #004974;
            text-decoration: none;
        }
    </style>

    <script type="text/javascript">
        //定义id选择器
        function Id(id){
            return document.getElementById(id);
        }
        //入口函数，两个参数分别为<input type='file'/>的id，还有一个就是图片的id，然后会自动根据文件id得到图片，然后把图片放到指定id的图片标签中
        function changeFile(fileId,filePreId){
            var file = Id(fileId);
            if(file.value==''){

            }else{
                preFileName(fileId,filePreId);
            }
        }
        //获取input[file]图片的url Important
        function getFileUrl(fileId) {
            var url;
            var file = Id(fileId);
            var agent = navigator.userAgent;
            if (agent.indexOf("MSIE")>=1) {
                url = file.value;
            } else if(agent.indexOf("Firefox")>0) {
                url = window.URL.createObjectURL(file.files.item(0));
            } else if(agent.indexOf("Chrome")>0) {
                url = window.URL.createObjectURL(file.files.item(0));
            }
            var extIndex = file.value.lastIndexOf(".");
            var ext = file.value.substring(extIndex,file.value.length).toUpperCase();
            if (ext != ".XLS" && ext != ".XLSX") {
                alert("只允许上传.XLS和.XLSX格式的文件！");
                Id('upload_file').disabled = true;
                return "";
            }
            Id('upload_file').disabled = false;
            return url;
        }
        //读取图片后预览
        function preFileName(fileId,filePreId) {
            var file = Id(fileId);
            var filePre = Id(filePreId);
            var extIndex = file.value.lastIndexOf(".");
            var ext = file.value.substring(extIndex,file.value.length).toUpperCase();
            if (ext != ".XLS" && ext != ".XLSX") {
                alert("只允许上传.XLS和.XLSX格式的文件！");
                Id('upload_file').disabled = true;
                filePre.value = "";
            } else {
                filePre.value = Id(fileId).value;
                Id('upload_file').disabled = false;
            }

        }
    </script>
</head>

<body class="nav-md">
<div class="container body">
    <div class="main_container">
        <%@ include file="left_top.jspf"%>

        <!-- top navigation -->
        <%@ include file="right_top.jspf"%>
        <!-- /top navigation -->

        <!-- page content -->
        <div class="right_col" role="main">
            <div class="">
                <div class="page-title">
                    <div class="title_left">
                        <h3><spring:message code="title.add_invoice" /></h3>
                    </div>
                </div>
                <c:choose>
                    <c:when test="${has_authority}">
                        <div class="clearfix"></div>
                        <div class="row">
                            <div class="col-md-12 col-sm-12 col-xs-12">
                                <div class="x_panel">
                                    <div class="x_title">
                                        <h2><spring:message code="form.title.invoice" /></h2>
                                        <ul class="nav navbar-right panel_toolbox">
                                            <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
                                        </ul>
                                        <div class="clearfix"></div>
                                    </div>
                                    <div class="x_content">
                                        <form action="add_invoice_excel" method="post"
                                              enctype="multipart/form-data" class="form-horizontal">
                                            <div class="form-group">
                                                <div id="preview" class="col-md-8 col-sm-8 col-xs-8">
                                                    <input class="form-control has-feedback-left" disabled="disabled"
                                                           id="filePreview" name="filePreview" placeholder="尚未选择文件" required="required"/>
                                                    <span class="fa fa-file-excel-o form-control-feedback left" aria-hidden="true"></span>
                                                </div>
                                                <div class="col-md-4 col-sm-4 col-xs-4">
                                                    <a class="file"><spring:message code="tip.upload_excel"/>
                                                        <input type="file" name="invoice_excel" id="file_selector" class="btn btn-round"
                                                               placeholder="<spring:message code="tip.upload_excel"/>"
                                                               accept=".xls, .xlsx" onchange="changeFile('file_selector', 'filePreview');">
                                                    </a>
                                                </div>
                                            </div>
                                            <div class="ln_solid"></div>
                                            <div class="form-group">
                                                <div class="col-md-1 col-sm-1 col-xs-1 col-md-offset-1 col-sm-offset-1 col-xs-offset-1">
                                                    <input type="submit" value="<spring:message code="button.submit" />"
                                                           class="btn btn-round btn-success" id="upload_file">
                                                </div>
                                                <div class="col-md-10 col-sm-10 col-xs-10">
                                                    <h5>
                                                        请注意，上传的Excel文件中，必须按照给定的模板填写数据，否则无法正常导入！
                                                        <a style="color: dodgerblue;" href="javascript:downloadTemplate()"><u>点击此处下载模板</u></a>！
                                                        若下载的文件无法打开，请直接将文件的后缀名修改为.zip
                                                        <script>
                                                            function downloadTemplate() {
//                                                                window.open("invoiceDataTemplate.zip");
                                                                try {
                                                                    var elemIF = document.createElement("iframe");
                                                                    elemIF.src = "invoiceDataTemplate.zip";
                                                                    elemIF.style.display ="none";
                                                                    document.body.appendChild(elemIF);
                                                                } catch (e) {

                                                                }
                                                            }
                                                        </script>
                                                    </h5>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <c:if test="${has_file}">
                            <div class="clearfix"></div>
                            <div class="row">
                                <div class="col-md-12 col-sm-12 col-xs-12">
                                    <div class="x_panel">
                                        <div class="x_title">
                                            <h2>导入结果</h2>
                                            <ul class="nav navbar-right panel_toolbox">
                                                <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
                                            </ul>
                                            <div class="clearfix"></div>
                                        </div>
                                        <div class="x_content">
                                            <div>
                                                ${result_message}
                                            </div>
                                            <div class="table-responsive">
                                                <table id="datatable" class="table table-striped jambo_table" style="white-space: nowrap;">
                                                    <thead>
                                                    <tr class="headings">
                                                        <th class="column-title">发票代码</th>
                                                        <th class="column-title">发票号码</th>
                                                        <th class="column-title">开票日期</th>
                                                        <th class="column-title">购贷方</th>
                                                        <th class="column-title">销贷方</th>
                                                        <th class="column-title">总金额</th>
                                                        <th class="column-title">总税额</th>
                                                        <th class="column-title no-link last"><span class="nobr">操作</span></th>
                                                    </tr>
                                                    </thead>

                                                    <tbody>
                                                    <c:forEach var="invoice" items="${invoice_list.invoiceList}" varStatus="status">
                                                        <c:choose>
                                                            <c:when test="${status.index % 2 == 0}">
                                                                <tr class="even pointer">
                                                            </c:when>
                                                            <c:otherwise>
                                                                <tr class="odd pointer">
                                                            </c:otherwise>
                                                        </c:choose>
                                                        <td class=" ">${invoice.invoiceCode}</td>
                                                        <td class=" ">${invoice.invoiceId}</td>
                                                        <td class=" ">${invoice.displayDate}</td>
                                                        <td class=" ">${invoice.buyerName}</td>
                                                        <td class=" ">${invoice.sellerName}</td>
                                                        <td class=" ">￥${invoice.totalAmount}</td>
                                                        <td class=" ">￥${invoice.totalTax}</td>
                                                        <td class=" last">
                                                            <form action="view_invoice" method="post">
                                                                <input type="hidden" name="index" value="${status.index}">
                                                                <input type="hidden" name="invoice_id" value="${invoice.invoiceId}">
                                                                <input type="hidden" name="invoice_code" value="${invoice.invoiceCode}">
                                                                <input type="submit" value="<spring:message code="button.view" />"
                                                                       class="btn btn-round btn-success">
                                                            </form>
                                                        </td>
                                                        </tr>
                                                    </c:forEach>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:if>
                    </c:when>
                    <c:otherwise>
                        <%@ include file="no_authority.jspf"%>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        <!-- /page content -->

        <!-- footer content -->
        <footer>
            <div class="pull-right">
                <spring:message code="text.footer"/>
            </div>
            <div class="clearfix"></div>
        </footer>
        <!-- /footer content -->
    </div>
</div>



<!-- jQuery -->
<script src="../vendors/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap -->
<script src="../vendors/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- FastClick -->
<script src="../vendors/fastclick/lib/fastclick.js"></script>
<!-- NProgress -->
<script src="../vendors/nprogress/nprogress.js"></script>
<!-- bootstrap-progressbar -->
<script src="../vendors/bootstrap-progressbar/bootstrap-progressbar.min.js"></script>
<!-- iCheck -->
<script src="../vendors/iCheck/icheck.min.js"></script>
<!-- bootstrap-daterangepicker -->
<script src="../vendors/moment/min/moment.min.js"></script>
<script src="../vendors/bootstrap-daterangepicker/daterangepicker.js"></script>
<!-- bootstrap-wysiwyg -->
<script src="../vendors/bootstrap-wysiwyg/js/bootstrap-wysiwyg.min.js"></script>
<script src="../vendors/jquery.hotkeys/jquery.hotkeys.js"></script>
<script src="../vendors/google-code-prettify/src/prettify.js"></script>
<!-- jQuery Tags Input -->
<script src="../vendors/jquery.tagsinput/src/jquery.tagsinput.js"></script>
<!-- Switchery -->
<script src="../vendors/switchery/dist/switchery.min.js"></script>
<!-- Select2 -->
<script src="../vendors/select2/dist/js/select2.full.min.js"></script>
<!-- Parsley -->
<script src="../vendors/parsleyjs/dist/parsley.min.js"></script>
<!-- Autosize -->
<script src="../vendors/autosize/dist/autosize.min.js"></script>
<!-- jQuery autocomplete -->
<script src="../vendors/devbridge-autocomplete/dist/jquery.autocomplete.min.js"></script>
<!-- starrr -->
<script src="../vendors/starrr/dist/starrr.js"></script>
<!-- Datatables -->
<script src="../vendors/datatables.net/js/jquery.dataTables.min.js"></script>
<script src="../vendors/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
<script src="../vendors/datatables.net-buttons/js/dataTables.buttons.min.js"></script>
<script src="../vendors/datatables.net-buttons-bs/js/buttons.bootstrap.min.js"></script>
<script src="../vendors/datatables.net-buttons/js/buttons.flash.min.js"></script>
<script src="../vendors/datatables.net-buttons/js/buttons.html5.min.js"></script>
<script src="../vendors/datatables.net-buttons/js/buttons.print.min.js"></script>
<script src="../vendors/datatables.net-fixedheader/js/dataTables.fixedHeader.min.js"></script>
<script src="../vendors/datatables.net-keytable/js/dataTables.keyTable.min.js"></script>
<script src="../vendors/datatables.net-responsive/js/dataTables.responsive.min.js"></script>
<script src="../vendors/datatables.net-responsive-bs/js/responsive.bootstrap.js"></script>
<script src="../vendors/datatables.net-scroller/js/dataTables.scroller.min.js"></script>
<script src="../vendors/jszip/dist/jszip.min.js"></script>
<script src="../vendors/pdfmake/build/pdfmake.min.js"></script>
<script src="../vendors/pdfmake/build/vfs_fonts.js"></script>

<!-- Custom Theme Scripts -->
<script src="../build/js/custom.min.js"></script>

</body>
</html>



