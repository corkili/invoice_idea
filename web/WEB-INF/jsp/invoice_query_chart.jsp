<%--
  Created by IntelliJ IDEA.
  User: 李浩然
  Date: 2017/6/9
  Time: 22:35
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

    <title><spring:message code="title.invoice_chart"/> </title>

    <!-- Bootstrap -->
    <link href="../vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="../vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <!-- NProgress -->
    <link href="../vendors/nprogress/nprogress.css" rel="stylesheet">

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
                        <h3><spring:message code="title.invoice_chart"/></h3>
                    </div>

                </div>
                <c:choose>
                    <c:when test="${has_authority}">
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
                                        <form action="chart_query" method="post" class="form-horizontal form-label-left">
                                            <%@ include file="invoice_query_form.jspf"%>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="clearfix"></div>

                        <div class="row">
                            <div class="col-md-12 col-sm-12 col-xs-12">
                                <div class="x_panel">
                                    <div class="x_title">
                                        <h2><spring:message code="title.chart.line"/></h2>
                                        <ul class="nav navbar-right panel_toolbox">
                                            <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                                            </li>
                                        </ul>
                                        <div class="clearfix"></div>
                                    </div>
                                    <div class="x_content">
                                        <c:choose>
                                            <c:when test="${has_result}">
                                                <div id="chart_line" style="height:350px;"></div>
                                            </c:when>
                                            <c:otherwise>
                                                <h3 style="text-align: center"><small><spring:message code="tip.no_result"/></small></h3>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-12 col-sm-12 col-xs-12">
                                <div class="x_panel">
                                    <div class="x_title">
                                        <h2><spring:message code="title.chart.bar"/></h2>
                                        <ul class="nav navbar-right panel_toolbox">
                                            <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                                            </li>
                                        </ul>
                                        <div class="clearfix"></div>
                                    </div>
                                    <div class="x_content">
                                        <c:choose>
                                            <c:when test="${has_result}">
                                                <div id="chart_bar" style="height:350px;"></div>
                                            </c:when>
                                            <c:otherwise>
                                                <h3 style="text-align: center"><small><spring:message code="tip.no_result"/></small></h3>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <div class="x_panel">
                                    <div class="x_title">
                                        <h2><spring:message code="title.chart.radar"/></h2>
                                        <ul class="nav navbar-right panel_toolbox">
                                            <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                                            </li>
                                        </ul>
                                        <div class="clearfix"></div>
                                    </div>
                                    <div class="x_content">
                                        <c:choose>
                                            <c:when test="${has_result}">
                                                <div id="chart_radar" style="height:400px;"></div>
                                            </c:when>
                                            <c:otherwise>
                                                <h3 style="text-align: center"><small><spring:message code="tip.no_result"/></small></h3>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6 col-sm6 col-xs-12">
                                <div class="x_panel">
                                    <div class="x_title">
                                        <h2><spring:message code="title.chart.pie"/></h2>
                                        <ul class="nav navbar-right panel_toolbox">
                                            <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                                            </li>
                                        </ul>
                                        <div class="clearfix"></div>
                                    </div>
                                    <div class="x_content">
                                        <c:choose>
                                            <c:when test="${has_result}">
                                                <div id="chart_pie" style="height:400px;"></div>
                                            </c:when>
                                            <c:otherwise>
                                                <h3 style="text-align: center"><small><spring:message code="tip.no_result"/></small></h3>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </div>
                            </div>
                        </div>
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
</div>

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

        // 填充数据
        <c:forEach var="date" items="${dates}" varStatus="status">
        dates.push(''+${date});
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

        var subTitle = '${dates.get(0)}' + '~' + '${dates.get(dates.size()-1)}';

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
                        label:{
                            show: false
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
                        label:{
                            show: false
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
            title: {
                text: '企业进项（左图）与销项（右图）数据<spring:message code="title.chart.pie"/>',
                subtext: subTitle,
                x: 'center',
                y: 'top',
                padding: [0,5,20,5]
            },
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