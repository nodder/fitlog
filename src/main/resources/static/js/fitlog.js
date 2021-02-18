//2020-01-01
function formatDate(date) {
    var y = new Date(date);
    return y.getFullYear() + "年" + (y.getMonth() + 1) + "月" + y.getDate() + "日";
}

//2020年1月1日
function unformatDate(formattedDate) {
    var p = formattedDate.replace('年', "-").replace('月', "-").replace('日', "").split("-")
    return p[0] + '-' + (p[1] < 10 ? "0" + p[1] : p[1]) + "-" + (p[2] < 10 ? "0" + p[2] : p[2])
}

//2020年1月1日
function dateDiff(formattedDate, diff) {
    var dateStr = unformatDate(formattedDate)
    // alert(111 + dateStr)

    var date = new Date(dateStr);
    date.setDate(date.getDate() + diff)
    return formatDate(date)
}

function showYearMonth(yearMonth) {
    return yearMonth.replace('-', "年 ") + "月"
}

function showDateWithWeekday(date) {
    var y = new Date(date);
    return y.getFullYear() + "年" + (y.getMonth() + 1) + "月" + y.getDate() + "日 " + getWeekDay(y);
}

function getWeekDay(date) {
    var week;
    if (date.getDay() == 0) week = "星期天"
    if (date.getDay() == 1) week = "星期一"
    if (date.getDay() == 2) week = "星期二"
    if (date.getDay() == 3) week = "星期三"
    if (date.getDay() == 4) week = "星期四"
    if (date.getDay() == 5) week = "星期五"
    if (date.getDay() == 6) week = "星期六"
    return week;
}

function ajax_post(url, data) {
    if (!data) data = {}

    var result;
    $.ajax({
        type: "POST",
        url: url,
        async: false,
        data: data,
        success: function (result1) {
            result = result1;
        },
        error: function (e) {
            alert("error post url:" + url + "; status: " + e.status + "; responseText: " + e.responseText)
            console.log("error post url:" + url + "; status: " + e.status + "; responseText: " + e.responseText);
        }
    });

    return result;
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
            tempHtmp += "<div class=\"bottom-line\" style=\"margin: 0px 0px 2px 0px;padding: 0px 9px 2px 4px;padding-bottom: 2px;\">"
            tempHtmp += "<div class='light-gray' name=dailyLogs[i].fitDate>" + showDateWithWeekday(dailyLogs[i].fitDate) + "</div>\n";
        }
        tempHtmp += "            <div class=\"row\">\n" +
        "                <div class=\"col-md-1 col-lg-1 col-sm-1 col-xs-1 text-center a\" onclick=\" " + clickFunc + "\"></div>\n" +
        "                <div class=\"col-md-8 col-lg-8 col-sm-8 col-xs-8 text-left a\" onclick=\"" + clickFunc + "\" >" + dailyLogs[i].subType + "</div>\n" +
        "                <div class=\"col-md-1 col-lg-1 col-sm-1 col-xs-1 text-right a\" style=\"color: #2747e4;\" onclick=\"" + clickFunc + "\" >" + dailyLogs[i].groups + "</div>\n" +
        "                <div class=\"col-md-2 col-lg-2 col-sm-2 col-xs-2 text-right a\" style=\"color: #2747e4;\" onclick=\"" + clickFunc + ")\" >" + dailyLogs[i].times + "</div>\n" +
        "                <div style='display: none;'>" + dailyLogs[i].type + "</div>\n" +
        "                <div style='display: none;'>" + dailyLogs[i].id + "</div>\n" +
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

function assembleHtml4DailyLogDetailsWithSingleEdit(dailyLogs) {
    clickFunc = "editSingleLog(this.parentElement)"
    var innerHtml = assembleHtml(dailyLogs, clickFunc);
    return innerHtml
}

function clickLog(e) {
    for (var i = 0; i < e.parentElement.parentElement.children.length; i++) {
        e.parentElement.parentElement.children[i].style.backgroundColor = ""
    }
    e.parentElement.style.backgroundColor = "#e6e5e5"

    var fitDate = e.parentElement.children[0].innerText;
    var fitDailyLogs = [];

    for (var i = 1; i < e.parentElement.children.length; i++) {
        fitDailyLogs.push({
            // "fitDate": fitDate,
            // "id":  e.parentElement.children[i].children[5].innerHTML,
            "subType": e.parentElement.children[i].children[1].innerHTML,
            "groups": e.parentElement.children[i].children[2].innerHTML,
            "times": e.parentElement.children[i].children[3].innerHTML,
            "type": e.parentElement.children[i].children[4].innerHTML
        })
    }

    return fitDailyLogs
}

function range(start, end) {
    var arr = []
    for (var i = start; i <= end; i++) {
        arr.push(i)
    }
    return arr;
}

function clickSingleLog(e) {
    for (var i = 0; i < e.parentElement.children.length; i++) {
        e.parentElement.children[i].style.backgroundColor = ""
    }
    e.style.backgroundColor = "#e6e5e5"//

    var fitDate = e.parentElement.children[0].innerText;

    return {
        "fitDate": fitDate,
        "subType": e.children[1].innerHTML,//
        "groups": e.children[2].innerHTML,//
        "times": e.children[3].innerHTML,
        "type": e.children[4].innerHTML,
        "id": e.children[5].innerHTML
    }
}

function gradientColors(start, end, steps, gamma) {
    var parseColor = function (hexStr) {
        return hexStr.length === 4 ? hexStr.substr(1).split('').map(function (s) { return 0x11 * parseInt(s, 16); }) : [hexStr.substr(1, 2), hexStr.substr(3, 2), hexStr.substr(5, 2)].map(function (s) { return parseInt(s, 16); })
    };

    var pad = function (s) {
        return (s.length === 1) ? '0' + s : s;
    };

    var i, j, ms, me, output = [], so = [];
    gamma = gamma || 1;
    var normalize = function (channel) {
        return Math.pow(channel / 255, gamma);
    };
    start = parseColor(start).map(normalize);
    end = parseColor(end).map(normalize);
    for (i = 0; i < steps; i++) {
        ms = i / (steps - 1);
        me = 1 - ms;
        for (j = 0; j < 3; j++) {
            so[j] = pad(Math.round(Math.pow(start[j] * me + end[j] * ms, 1 / gamma) * 255).toString(16));
        }
        output.push('#' + so.join(''));
    }
    return output;
};
