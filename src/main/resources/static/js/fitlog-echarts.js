function paintBar(element, xData, seriesData, callback){
    var myChart = echarts.init(element);

    option = {
        
        grid: {
            left: '3%',
            right: '3%'
        },
        xAxis: {
            data: xData,
            axisLabel: {
                textStyle: {
                    color: 'gray'
                },
                rotate:30
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
            max: 'dataMax',
            axisLine: {
                show: false
            },
            axisTick: {
                show: false
            },
            axisLabel: {
                textStyle: {
                    color: '#999',
                },
                show: false
            }
        },
        dataZoom: [
            {
                type: 'inside',
                startValue: Math.max(0, seriesData.length - 20),
                endValue: seriesData.length - 1
            }
        ],
        series: [
            {
                type: 'bar',
                showBackground: true,
                backgroundStyle: {
                    // color: 'rgba(22,208,144,0.15)'
                    // color: 'black'
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

    myChart.getZr().on('click', function(params) {
        var pointInPixel = [params.offsetX, params.offsetY]
        if (myChart.containPixel('grid', pointInPixel)) {
            var xIndex = myChart.convertFromPixel({ seriesIndex: 0 }, [params.offsetX, params.offsetY])[0]
            console.log(xIndex)
            callback(xIndex)
        }
    });

    myChart.setOption(option);
}

function paintPie(element, data1){
    var myChart = echarts.init(element);

    option = {
        // tooltip: {//提示
        //     trigger: 'item',
        //     formatter: "{a}：<br/>{b} : {c} ({d}%)"
        // },
        legend: {//图例
            bottom: '5%',
            left: '5%',
            textStyle: {
                fontSize: 10
            },
            itemWidth: 9,
            itemHeight: 6,
            itemGap: 5
        },
        series: [
            {
                name: '运动量',
                type: 'pie',
                radius: ['40%', '70%'],
                avoidLabelOverlap: false,
                itemStyle: {
                    //要么在这里定义颜色，要么在data中将颜色传给itemStyle
                    // color:function(params) {
                    //     //自定义每个扇区的颜色
                    //     var colorList = [
                    //         '#ff7c1e', '#3df249', '#e11111','#29e7e2',
                    //         '#4664ff', '#cf28da', '#91cc75', '#fac858',
                    //         '#ee6666', '#73c0de', '#9a60b4', '#5470c6',
                    //         '#1f7a56', '#ea7ccc', '#ef8f00', '#ddff84',
                    //         '#ffeb10', '#a2cdff', '#ff529f', '#aa932b',
                    //         '#271848', '#ffcfce', '#3ba272', '#f0fff4'
                    //     ];
                    //     return colorList[params.dataIndex % colorList.length]
                    // },
                    //边框样式
                    borderRadius: 5,
                    borderColor: '#eee',
                    borderWidth: 1
                },
                label: {//显示的标签内容
                    show: true,
                    formatter: '{c}',
                    position: 'inside'
                },
                emphasis: {//鼠标悬停
                    label: {
                        show: true,
                        fontSize: '15',
                        formatter: "{c}({d}%)"
                        // fontWeight: 'bold'
                    }
                },

                data: data1
            }
        ]
    };
    myChart.setOption(option);
}