//2020-01-01
function formatDate(date) {
    var y = new Date(date);
    return y.getFullYear() + "年" + (y.getMonth()+1) + "月" + y.getDate() + "日";
}
//2020年1月1日
function unformatDate(formattedDate) {
    var p = formattedDate.replace('年', "-").replace('月', "-").replace('日', "").split("-")
    return p[0]+'-' + (p[1] < 10 ? "0" + p[1] : p[1]) + "-" + (p[2] < 10 ? "0" + p[2] : p[2])
}
//2020年1月1日
function dateDiff(formattedDate, diff) {
    var dateStr = unformatDate(formattedDate)
    // alert(111 + dateStr)

    var date = new Date(dateStr);
    date.setDate(date.getDate() + diff)
    return formatDate(date)
}

function formatDateWithWeekday(date) {
    var y = new Date(date);
    return y.getFullYear() + "年" + (y.getMonth()+1) + "月" + y.getDate() + "日 " + getWeekDay(y.getDate());
}

function getWeekDay(date){
    var week;
    if(date.getDay()==0) week="星期天"
    if(date.getDay()==1) week="星期一"
    if(date.getDay()==2) week="星期二"
    if(date.getDay()==3) week="星期三"
    if(date.getDay()==4) week="星期四"
    if(date.getDay()==5) week="星期五"
    if(date.getDay()==6) week="星期六"
    return week;
}

function ajax_post(url, data){
    if(!data) data = {}

    var result;
    $.ajax({
        type : "POST",
        url : url,
        async:false,
        data : data,
        success: function(result1) {
            result = result1;
        },
        error: function(e){
            console.log(e.status);
            console.log(e.responseText);
        }
    });

    return result;
}

function paintBar(element, xData, seriesData, callback){
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(element);

    // 指定图表的配置项和数据
    option = {
        title: {
            text: '运动量',
            subtext: 'subtext'
        },
        xAxis: {
            data: xData,
            axisLabel: {
                textStyle: {
                    color: 'gray'
                }
            },
            axisTick: {
                show: false
            },
            axisLine: {
                show: false
            },
            z: 10
        },
        yAxis: {
            axisLine: {
                show: false
            },
            axisTick: {
                show: false
            },
            axisLabel: {
                textStyle: {
                    color: '#999'
                }
            }
        },
        dataZoom: [
            {
                type: 'inside',
                startValue: Math.max(0, seriesData.length - 15),
                endValue: seriesData.length - 1
            }
        ],
        series: [
            {
                type: 'bar',
                showBackground: true,
                backgroundStyle: {
                    // color: 'rgba(22,208,144,0.15)'
                    color: 'black'//设置无效，why
                },
                itemStyle: {
                    color: new echarts.graphic.LinearGradient(
                        0, 0, 0, 1,
                        [
                            {offset: 0, color: '#FF9900'},
                            {offset: 0.5, color: '#FFCC33'},
                            {offset: 1, color: '#FFFF66'}
                        ]
                    )
                },
                emphasis: {
                    itemStyle: {
                        color: new echarts.graphic.LinearGradient(
                            0, 0, 0, 1,
                            [
                                {offset: 0, color: '#2378f7'},
                                {offset: 0.7, color: '#2378f7'},
                                {offset: 1, color: '#83bff6'}
                            ]
                        )
                    }
                },
                data: seriesData
            }
        ]
    };

    // myChart.on('click', function(params) {
    //     console.log(params);
    //     var name = parseInt(params.name);
    // });

    // var clickIndex;
    myChart.getZr().on('click', function(params) {
        var pointInPixel = [params.offsetX, params.offsetY]
        if (myChart.containPixel('grid', pointInPixel)) {
            var xIndex = myChart.convertFromPixel({ seriesIndex: 0 }, [params.offsetX, params.offsetY])[0]
            console.log(xIndex)
            callback(xIndex)
        }
    });

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
    // myChart.resize();
}

function paintPie(element, data1){
    var myChart = echarts.init(element);

    option = {
        tooltip: {
            trigger: 'item'
        },
        legend: {
            bottom: '5%',
            left: 'left'
        },
        series: [
            {
                name: '运动量',
                type: 'pie',
                radius: ['40%', '60%'],
                avoidLabelOverlap: false,
                itemStyle: {
                    normal:{
                        label:{
                            show: true,
                            formatter: '{c}'},
                        position: 'center',
                        labelLine :{show:true}
                    },

                    borderRadius: 10,
                    borderColor: '#fff',
                    borderWidth: 2
                },
                label: {
                    show: false,
                    position: 'center'
                },
                emphasis: {
                    label: {
                        show: true,
                        fontSize: '30',
                        // fontWeight: 'bold'
                    }
                },
                labelLine: {
                    show: false
                },
                data: data1
            }
        ]
    };
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
    myChart.resize();
}

function assembleHtml(dailyLogs, clickFunc) {
    var innerHtml = ""
    var lastProcessFitDate = ""
    for (var i = 0; i < dailyLogs.length; i++) {
        var tempHtmp = ""
        if (lastProcessFitDate != "" && lastProcessFitDate != dailyLogs[i].fitDate) {
            tempHtmp += "</div>";
        }

        if (lastProcessFitDate != dailyLogs[i].fitDate) {
            tempHtmp += "<div class =\"\">"
            tempHtmp += "<b>" + dailyLogs[i].fitDate + "</b>\n";
        }
        tempHtmp += "            <div class=\"row\">\n" +
            "                <div class=\"col-md-1 col-lg-1 col-sm-1 col-xs-1 text-center a\" onclick=\" " + clickFunc + "\"></div>\n" +
            "                <div class=\"col-md-8 col-lg-8 col-sm-8 col-xs-8 text-left a\" onclick=\"" + clickFunc + "\" >" + dailyLogs[i].subType + "</div>\n" +
            "                <div class=\"col-md-1 col-lg-1 col-sm-1 col-xs-1 text-left a\" onclick=\"" + clickFunc + "\" >" + dailyLogs[i].groups + "</div>\n" +
            "                <div class=\"col-md-2 col-lg-2 col-sm-2 col-xs-2 text-left a\" onclick=\"" + clickFunc + ")\" >" + dailyLogs[i].times + "</div>\n" +
            "            </div>\n"

        if (i == dailyLogs.length - 1) {
            tempHtmp += "</div>";
        }

        innerHtml += tempHtmp;
        lastProcessFitDate = dailyLogs[i].fitDate
    }
    return innerHtml;
}

function assembleHtml4DailyLogDetails(dailyLogs) {
    var clickFunc = "clickLog(this.parentElement)"
    var innerHtml = assembleHtml(dailyLogs, clickFunc);
    return innerHtml
}

function assembleHtml4DailyLogDetailsWithEdit(dailyLogs) {
    clickFunc = "editLog(this.parentElement)"
    var innerHtml = assembleHtml(dailyLogs, clickFunc);
    return innerHtml
}


function assembleHtml4DailyLogDetails2(dailyLogs) {
    var innerHtml = ""
    var lastProcessFitDate = ""
    for(var i = 0; i < dailyLogs.length; i++) {
        var tempHtmp = ""
        if(lastProcessFitDate != "" && lastProcessFitDate != dailyLogs[i].fitDate) {
            tempHtmp += "</div>";
        }

        if(lastProcessFitDate != dailyLogs[i].fitDate) {
            tempHtmp += "<div class =\"\">"
            tempHtmp += "<b>" + dailyLogs[i].fitDate + "</b>\n";
        }
        tempHtmp += "            <div class=\"row\">\n" +
            "                <div class=\"col-md-1 col-lg-1 col-sm-1 col-xs-1 text-center a\" onclick=\"clickLog(this.parentElement)\"></div>\n" +
            "                <div class=\"col-md-8 col-lg-8 col-sm-8 col-xs-8 text-left a\" onclick=\"clickLog(this.parentElement)\" >" + dailyLogs[i].subType + "</div>\n" +
            "                <div class=\"col-md-1 col-lg-1 col-sm-1 col-xs-1 text-left a\" onclick=\"clickLog(this.parentElement)\" >" + dailyLogs[i].groups + "</div>\n" +
            "                <div class=\"col-md-2 col-lg-2 col-sm-2 col-xs-2 text-left a\" onclick=\"clickLog(this.parentElement)\" >" + dailyLogs[i].times + "</div>\n" +
            "            </div>\n"

        if(i == dailyLogs.length - 1) {
            tempHtmp += "</div>";
        }

        innerHtml += tempHtmp;
        lastProcessFitDate = dailyLogs[i].fitDate
    }

    return innerHtml
}

function clickLog(e) {
    for(var i = 0; i < e.parentElement.parentElement.children.length; i++) {
        e.parentElement.parentElement.children[i].style.backgroundColor=""
    }
    e.parentElement.style.backgroundColor="gray"

    var fitDate = e.parentElement.children[0].innerText;
    var fitDailyLogs = [];

    for(var i = 1; i < e.parentElement.children.length; i++) {
        fitDailyLogs.push({ "fitDate": fitDate,
            "subType":  e.parentElement.children[i].children[1].innerHTML,
            "groups":  e.parentElement.children[i].children[2].innerHTML,
            "times":  e.parentElement.children[i].children[3].innerHTML})
    }

    console.log(fitDate)
    console.log(fitDailyLogs)

    return fitDailyLogs
}