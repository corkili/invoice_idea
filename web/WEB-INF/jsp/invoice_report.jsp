<%--
  Created by IntelliJ IDEA.
  User: 李浩然
  Date: 2017/6/11
  Time: 21:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title><spring:message code="title.report"/></title>


    <!-- Bootstrap -->
    <link href="../vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="../vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <!-- NProgress -->
    <link href="../vendors/nprogress/nprogress.css" rel="stylesheet">
    <!-- iCheck -->
    <link href="../vendors/iCheck/skins/flat/green.css" rel="stylesheet">

    <!-- Custom Theme Style -->
    <link href="../build/css/custom.min.css" rel="stylesheet">
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
                        <h3><spring:message code="title.report"/></h3>
                    </div>

                </div>

                <div class="clearfix"></div>

                <!-- query form -->

                <div class="row">
                    <div class="col-md-12 col-sm-12 col-xs-12">
                        <div class="x_panel">
                            <div class="x_title">
                                <h2>查询条件</h2>
                                <ul class="nav navbar-right panel_toolbox">
                                    <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                                    </li>
                                </ul>
                                <div class="clearfix"></div>
                            </div>
                            <div class="x_content">
                                <form action="report" method="post" class="form-horizontal form-label-left">
                                    <%@ include file="invoice_query_form.jspf"%>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="clearfix"></div>

                <!-- main panel -->
                <div class="row">
                    <div class="col-md-12 col-sm-12 col-xs-12">
                        <div class="x_panel">
                            <div class="x_title">
                                <h2></h2>
                                <ul class="nav navbar-right panel_toolbox">
                                    <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                                    </li>
                                </ul>
                                <div class="clearfix"></div>
                            </div>
                            <div class="x_content">
                                <c:choose>
                                    <c:when test="${has_result}">
                                        <!-- section 1 -->
                                        <h3 style="text-align: center"><spring:message code="text.report_title"/></h3><br/>
                                        <div class="clearfix"></div>
                                        <h4 style="text-align: center">
                                            <spring:message code="title.table.income"/>
                                            <small>（单位：元）</small>
                                        </h4><br/>
                                        <div class="table-responsive">
                                            <table class="table table-striped jambo_table bulk_action">
                                                <thead>
                                                <tr class="headings">
                                                    <th class="column-title">项目\<br/>月份</th>
                                                    <c:forEach var="n" items="${income_names}" varStatus="status" >
                                                        <th class="column-title" style="text-align: center">${n}</th>
                                                    </c:forEach>
                                                </tr>
                                                </thead>

                                                <tbody>
                                                <c:forEach var="line" items="${income_amounts}" varStatus="status">
                                                    <c:choose>
                                                        <c:when test="${status.index % 2 == 0}">
                                                            <tr class="even pointer">
                                                        </c:when>
                                                        <c:otherwise>
                                                            <tr class="odd pointer">
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <td class=" " style="text-align: center">${dates.get(status.index)}</td>
                                                    <c:forEach var="price" items="${line}">
                                                        <td class=" " style="text-align: center">${price}</td>
                                                    </c:forEach>
                                                    </tr>
                                                </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                        <div class="clearfix"></div>
                                        <h4 style="text-align: center">
                                            <spring:message code="title.table.outcome"/>
                                            <small>（单位：元）</small>
                                        </h4><br/>
                                        <div class="table-responsive">
                                            <table class="table table-striped jambo_table bulk_action">
                                                <thead>
                                                <tr class="headings">
                                                    <th class="column-title">项目\<br/>月份</th>
                                                    <c:forEach var="n" items="${outcome_names}" varStatus="status" >
                                                        <th class="column-title" style="text-align: center">${n}</th>
                                                    </c:forEach>
                                                </tr>
                                                </thead>

                                                <tbody>
                                                <c:forEach var="line" items="${outcome_amounts}" varStatus="status">
                                                    <c:choose>
                                                        <c:when test="${status.index % 2 == 0}">
                                                            <tr class="even pointer">
                                                        </c:when>
                                                        <c:otherwise>
                                                            <tr class="odd pointer">
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <td class=" " style="text-align: center">${dates.get(status.index)}</td>
                                                    <c:forEach var="price" items="${line}">
                                                        <td class=" " style="text-align: center">${price}</td>
                                                    </c:forEach>
                                                    </tr>
                                                </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                        <div class="clearfix"></div>
                                        <!-- charts -->
                                        <div id="income_chart_line" style="height:450px;"></div>
                                        <div class="clearfix"></div>
                                        <div id="income_chart_bar" style="height:450px;"></div>
                                        <div class="clearfix"></div>
                                        <div id="outcome_chart_line" style="height:450px;"></div>
                                        <div class="clearfix"></div>
                                        <div id="outcome_chart_bar" style="height:450px;"></div>
                                        <!-- /section 1 -->
                                        <!-- section 2 -->
                                        <div class="clearfix"></div>
                                        <h4 style="text-align: center">
                                            <spring:message code="title.table.compare"/>
                                            <small>（单位：元）</small>
                                        </h4><br/>
                                        <div class="table-responsive">
                                            <table class="table table-striped jambo_table bulk_action">
                                                <thead>
                                                <tr class="headings">
                                                    <th class="column-title"></th>
                                                    <c:forEach var="date" items="${dates}" varStatus="status" >
                                                        <th class="column-title" style="text-align: center">${date}</th>
                                                    </c:forEach>
                                                </tr>
                                                </thead>

                                                <tbody>
                                                <tr class="even pointer">
                                                    <td class=" " style="text-align: center">
                                                        <spring:message code="legend.income"/>
                                                    </td>
                                                    <c:forEach var="amount" items="${incomes}">
                                                        <td class=" " style="text-align: center">${amount}</td>
                                                    </c:forEach>
                                                </tr>
                                                <tr class="odd pointer">
                                                    <td class=" " style="text-align: center">
                                                        <spring:message code="legend.outcome"/>
                                                    </td>
                                                    <c:forEach var="amount" items="${outcomes}">
                                                        <td class=" " style="text-align: center">${amount}</td>
                                                    </c:forEach>
                                                </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                        <div class="clearfix"></div>
                                        <!-- charts -->
                                        <div id="chart_line" style="height:350px;"></div>
                                        <div class="clearfix"></div>
                                        <div id="chart_bar" style="height:350px;"></div>
                                        <div class="clearfix"></div>
                                        <div id="chart_radar" style="height:400px;"></div>
                                        <div class="clearfix"></div>
                                        <div id="chart_pie" style="height:400px;"></div>
                                        <!-- /section 2 -->
                                    </c:when>
                                    <c:otherwise>
                                        <h3 style="text-align: center"><small><spring:message code="tip.no_result"/></small></h3>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /main panel -->
            </div>
        </div>
        <!-- /page content -->

        <!-- footer content -->
        <footer>
            <div class="pull-right">

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
<!-- iCheck -->
<script src="../vendors/iCheck/icheck.min.js"></script>

<!-- jQuery -->
<script src="../vendors/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap -->
<script src="../vendors/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- FastClick -->
<script src="../vendors/fastclick/lib/fastclick.js"></script>
<!-- NProgress -->
<script src="../vendors/nprogress/nprogress.js"></script>
<!-- ECharts -->
<script src="../vendors/echarts/dist/echarts.min.js"></script>
<!-- Custom Theme Scripts -->
<script src="../build/js/custom.min.js"></script>

<!-- generate charts -->
<c:if test="${has_result}">
    <script type="text/javascript">
        var theme = {
            color: [
                '#26B99A', '#34495E', '#BDC3C7', '#3498DB',
                '#9B59B6', '#8abb6f', '#759c6a', '#bfd3b7'
            ],

            title: {
                itemGap: 8,
                textStyle: {
                    fontWeight: 'normal',
                    color: '#408829'
                }
            },

            dataRange: {
                color: ['#1f610a', '#97b58d']
            },

            toolbox: {
                color: ['#408829', '#408829', '#408829', '#408829']
            },

            tooltip: {
                backgroundColor: 'rgba(0,0,0,0.5)',
                axisPointer: {
                    type: 'line',
                    lineStyle: {
                        color: '#408829',
                        type: 'dashed'
                    },
                    crossStyle: {
                        color: '#408829'
                    },
                    shadowStyle: {
                        color: 'rgba(200,200,200,0.3)'
                    }
                }
            },

            dataZoom: {
                dataBackgroundColor: '#eee',
                fillerColor: 'rgba(64,136,41,0.2)',
                handleColor: '#408829'
            },
            grid: {
                borderWidth: 0
            },

            categoryAxis: {
                axisLine: {
                    lineStyle: {
                        color: '#408829'
                    }
                },
                splitLine: {
                    lineStyle: {
                        color: ['#eee']
                    }
                }
            },

            valueAxis: {
                axisLine: {
                    lineStyle: {
                        color: '#408829'
                    }
                },
                splitArea: {
                    show: true,
                    areaStyle: {
                        color: ['rgba(250,250,250,0.1)', 'rgba(200,200,200,0.1)']
                    }
                },
                splitLine: {
                    lineStyle: {
                        color: ['#eee']
                    }
                }
            },
            timeline: {
                lineStyle: {
                    color: '#408829'
                },
                controlStyle: {
                    normal: {color: '#408829'},
                    emphasis: {color: '#408829'}
                }
            },

            k: {
                itemStyle: {
                    normal: {
                        color: '#68a54a',
                        color0: '#a9cba2',
                        lineStyle: {
                            width: 1,
                            color: '#408829',
                            color0: '#86b379'
                        }
                    }
                }
            },
            map: {
                itemStyle: {
                    normal: {
                        areaStyle: {
                            color: '#ddd'
                        },
                        label: {
                            textStyle: {
                                color: '#c12e34'
                            }
                        }
                    },
                    emphasis: {
                        areaStyle: {
                            color: '#99d2dd'
                        },
                        label: {
                            textStyle: {
                                color: '#c12e34'
                            }
                        }
                    }
                }
            },
            force: {
                itemStyle: {
                    normal: {
                        linkStyle: {
                            strokeColor: '#408829'
                        }
                    }
                }
            },
            chord: {
                padding: 4,
                itemStyle: {
                    normal: {
                        lineStyle: {
                            width: 1,
                            color: 'rgba(128, 128, 128, 0.5)'
                        },
                        chordStyle: {
                            lineStyle: {
                                width: 1,
                                color: 'rgba(128, 128, 128, 0.5)'
                            }
                        }
                    },
                    emphasis: {
                        lineStyle: {
                            width: 1,
                            color: 'rgba(128, 128, 128, 0.5)'
                        },
                        chordStyle: {
                            lineStyle: {
                                width: 1,
                                color: 'rgba(128, 128, 128, 0.5)'
                            }
                        }
                    }
                }
            },
            gauge: {
                startAngle: 225,
                endAngle: -45,
                axisLine: {
                    show: true,
                    lineStyle: {
                        color: [[0.2, '#86b379'], [0.8, '#68a54a'], [1, '#408829']],
                        width: 8
                    }
                },
                axisTick: {
                    splitNumber: 10,
                    length: 12,
                    lineStyle: {
                        color: 'auto'
                    }
                },
                axisLabel: {
                    textStyle: {
                        color: 'auto'
                    }
                },
                splitLine: {
                    length: 18,
                    lineStyle: {
                        color: 'auto'
                    }
                },
                pointer: {
                    length: '90%',
                    color: 'auto'
                },
                title: {
                    textStyle: {
                        color: '#333'
                    }
                },
                detail: {
                    textStyle: {
                        color: 'auto'
                    }
                }
            },
            textStyle: {
                fontFamily: 'Arial, Verdana, sans-serif'
            }
        };

        var dates = [];
        var incomes = [];
        var outcomes = [];
        var max = [];
        var indicator = [];
        var pieIncomes = [];
        var pieOutcomes = [];
        var size = 0;
        size = ${dates.size()};

        var incomeNames = [];
        var outcomeNames = [];
        var incomeLine = [];
        var incomeBar = [];
        var outcomeLine = [];
        var outcomeBar = [];

        var tmp;

        // 填充数据--进销项对比
        <c:forEach var="date" items="${dates}" varStatus="status">
        dates.push('${date}');
        incomes.push(${incomes.get(status.index)});
        outcomes.push(${outcomes.get(status.index)});
        if (incomes[${status.index}] > outcomes[${status.index}])
            max.push(incomes[${status.index}]);
        else
            max.push(outcomes[${status.index}]);
        indicator.push({
            text: dates[${status.index}],
            max: max[${status.index}]
        });
        pieIncomes.push({
            value: incomes[${status.index}],
            name: ''+dates[${status.index}]
        });
        pieOutcomes.push({
            value: outcomes[${status.index}],
            name: ''+dates[${status.index}]
        });
        </c:forEach>

        // 填充数据--进项
        <c:forEach var="i" begin="0" end="${income_names.size() - 1}" step="1">
        incomeNames.push('${income_names.get(i)}');
        tmp = [];
        <c:forEach var="j" begin="0" end="${income_amounts.size() - 1}" step="1">
        tmp.push(${income_amounts.get(j).get(i)});
        </c:forEach>
        incomeLine.push({
            name: ''+incomeNames[${i}],
            type: 'line',
            smooth: true,
            itemStyle: {
                normal: {
                    areaStyle: {
                        type: 'default'
                    }
                }
            },
            data: tmp
        });
        incomeBar.push({
            name:''+incomeNames[${i}],
            type:'bar',
            data:tmp
        });
        </c:forEach>

        // 填充数据--销项
        <c:forEach var="i" begin="0" end="${outcome_names.size() - 1}" step="1">
        outcomeNames.push('${outcome_names.get(i)}');
        tmp = [];
        <c:forEach var="j" begin="0" end="${outcome_amounts.size() - 1}" step="1">
        tmp.push(${outcome_amounts.get(j).get(i)});
        </c:forEach>
        outcomeLine.push({
            name: outcomeNames[${i}],
            type: 'line',
            smooth: true,
            itemStyle: {
                normal: {
                    areaStyle: {
                        type: 'default'
                    }
                }
            },
            data: tmp
        });
        outcomeBar.push({
            name:outcomeNames[${i}],
            type:'bar',
            data:tmp
        });
        </c:forEach>

        // income_chart_line
        var incomeLineChart = echarts.init(document.getElementById("income_chart_line"), theme);

        incomeLineChart.setOption({
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'cross',
                    crossStyle: {
                        color: '#999'
                    }
                }
            },
            toolbox: {
                feature: {
                    dataView: {show: true, readOnly: true},
                    saveAsImage: {show: true}
                }
            },
            legend: {
                x: 'center',
                y: 'bottom',
                data: incomeNames
            },
            calculable: true,
            xAxis: [{
                type: 'category',
                boundaryGap: false,
                name: '<spring:message code="axis.x"/>',
                data: dates
            }],
            yAxis: [{
                type: 'value',
                name: '<spring:message code="axis.y"/>',
            }],
            series: incomeLine
        });
        // income_chart_line

        // income_chart_bar
        var incomeBarChart = echarts.init(document.getElementById("income_chart_bar"), theme);

        incomeBarChart.setOption({
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'cross',
                    crossStyle: {
                        color: '#999'
                    }
                }
            },
            toolbox: {
                feature: {
                    dataView: {show: true, readOnly: true},
                    saveAsImage: {show: true}
                }
            },
            legend: {
                x: 'center',
                y: 'bottom',
                data:incomeNames
            },
            xAxis: [
                {
                    type: 'category',
                    name: '<spring:message code="axis.x"/>',
                    data: dates,
                    axisPointer: {
                        type: 'shadow'
                    }
                }
            ],
            yAxis: [
                {
                    type: 'value',
                    name: '<spring:message code="axis.y"/>'
                }
            ],
            series: incomeBar
        });
        // income_chart_bar

        // outcome_chart_line
        var outcomeLineChart = echarts.init(document.getElementById("outcome_chart_line"), theme);

        outcomeLineChart.setOption({
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'cross',
                    crossStyle: {
                        color: '#999'
                    }
                }
            },
            toolbox: {
                feature: {
                    dataView: {show: true, readOnly: true},
                    saveAsImage: {show: true}
                }
            },
            legend: {
                x: 'center',
                y: 'bottom',
                data: outcomeNames
            },
            calculable: true,
            xAxis: [{
                type: 'category',
                boundaryGap: false,
                name: '<spring:message code="axis.x"/>',
                data: dates
            }],
            yAxis: [{
                type: 'value',
                name: '<spring:message code="axis.y"/>',
            }],
            series: outcomeLine
        });
        // outcome_chart_line

        // outcome_chart_bar
        var outcomeBarChart = echarts.init(document.getElementById("outcome_chart_bar"), theme);

        outcomeBarChart.setOption({
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'cross',
                    crossStyle: {
                        color: '#999'
                    }
                }
            },
            toolbox: {
                feature: {
                    dataView: {show: true, readOnly: true},
                    saveAsImage: {show: true}
                }
            },
            legend: {
                x: 'center',
                y: 'bottom',
                data:outcomeNames
            },
            xAxis: [
                {
                    type: 'category',
                    name: '<spring:message code="axis.x"/>',
                    data: dates,
                    axisPointer: {
                        type: 'shadow'
                    }
                }
            ],
            yAxis: [
                {
                    type: 'value',
                    name: '<spring:message code="axis.y"/>'
                }
            ],
            series: outcomeBar
        });
        // outcome_chart_bar


        // chart_line
        var lineChart = echarts.init(document.getElementById("chart_line"), theme);

        lineChart.setOption({
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'cross',
                    crossStyle: {
                        color: '#999'
                    }
                }
            },
            toolbox: {
                feature: {
                    dataView: {show: true, readOnly: true},
                    saveAsImage: {show: true}
                }
            },
            legend: {
                data: ['<spring:message code="legend.income"/>','<spring:message code="legend.outcome"/>']
            },
            calculable: true,
            xAxis: [{
                type: 'category',
                boundaryGap: false,
                name: '<spring:message code="axis.x"/>',
                data: dates
            }],
            yAxis: [{
                type: 'value',
                name: '<spring:message code="axis.y"/>',
            }],
            series: [{
                name: '<spring:message code="legend.income"/>',
                type: 'line',
                smooth: true,
                itemStyle: {
                    normal: {
                        areaStyle: {
                            type: 'default'
                        }
                    }
                },
                data: incomes
            }, {
                name: '<spring:message code="legend.outcome"/>',
                type: 'line',
                smooth: true,
                itemStyle: {
                    normal: {
                        areaStyle: {
                            type: 'default'
                        }
                    }
                },
                data: outcomes
            }]
        });
        // chart_line

        // chart_bar
        var barChart = echarts.init(document.getElementById("chart_bar"), theme);

        barChart.setOption({
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'cross',
                    crossStyle: {
                        color: '#999'
                    }
                }
            },
            toolbox: {
                feature: {
                    dataView: {show: true, readOnly: true},
                    saveAsImage: {show: true}
                }
            },
            legend: {
                data:['<spring:message code="legend.income"/>','<spring:message code="legend.outcome"/>']
            },
            xAxis: [
                {
                    type: 'category',
                    name: '<spring:message code="axis.x"/>',
                    data: dates,
                    axisPointer: {
                        type: 'shadow'
                    }
                }
            ],
            yAxis: [
                {
                    type: 'value',
                    name: '<spring:message code="axis.y"/>'
                }
            ],
            series: [
                {
                    name:'<spring:message code="legend.income"/>',
                    type:'bar',
                    data:incomes
                },
                {
                    name:'<spring:message code="legend.outcome"/>',
                    type:'bar',
                    data:outcomes
                }
            ]
        });

        // chart_bar

        // chart_radar
        var  radarChart = echarts.init(document.getElementById("chart_radar"), theme);

        radarChart.setOption({
            tooltip: {
                trigger: 'item'
            },
            toolbox: {
                feature: {
                    dataView: {show: true, readOnly: true},
                    saveAsImage: {show: true}
                }
            },
            legend: {
                orient: 'vertical',
                x:'left',
                data: ['<spring:message code="legend.income"/>','<spring:message code="legend.outcome"/>']
            },
            polar: [{
                indicator: indicator
            }],
            calculable: true,
            series: [{
                type: 'radar',
                data: [{
                    value: incomes,
                    name: '<spring:message code="legend.income"/>'
                }, {
                    value: outcomes,
                    name: '<spring:message code="legend.outcome"/>'
                }]
            }]
        });
        // chart_radar

        // chart_pie
        var  pieChart = echarts.init(document.getElementById("chart_pie"), theme);

        pieChart.setOption({
            tooltip : {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c}元 ({d}%)"
            },
            legend: {
                x : 'center',
                y : 'bottom',
                data: dates
            },
            toolbox: {
                show : true,
                feature : {
                    mark : {show: true},
                    dataView : {show: true, readOnly: true},
                    magicType : {
                        show: true,
                        type: ['pie', 'funnel']
                    },
                    saveAsImage : {show: true}
                }
            },
            calculable : true,
            series : [
                {
                    name:'<spring:message code="legend.income"/>',
                    type:'pie',
                    radius : [30, 110],
                    center : ['25%', '50%'],
                    roseType : 'area',
                    data: pieIncomes
                },
                {
                    name:'<spring:message code="legend.outcome"/>',
                    type:'pie',
                    radius : [30, 110],
                    center : ['75%', '50%'],
                    roseType : 'area',
                    data: pieOutcomes
                }
            ]
        });
        // chart_pie

    </script>
</c:if>
</body>
</html>
