function paintBar(element, xData, seriesData, callback){
    var myChart = echarts.init(element);

    option = {
        
        grid: {
            left: '11%'
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
            axisLine: {
                show: false
            },
            axisTick: {
                show: false
            },
            axisLabel: {
                textStyle: {
                    color: '#999',
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
    myChart.setOption(option);
    myChart.resize();
}