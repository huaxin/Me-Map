L.Control.Legend = L.Control.extend({

    statics: {
        TITLE: '图例'
    },
    options: {
        position: 'bottomright',
    },
    initialize: function(options) {
        L.Control.prototype.initialize.call(this, options);

    },
    onAdd: function(map) {
        var className = 'leaflet-control';
        this._container = L.DomUtil.create('div', 'leaflet-bar');
        var link = L.DomUtil.create('div', className + '-legend', this._container);
        link.title = L.Control.Legend.TITLE;

        return this._container;
    },
    toggle: function() {
        if (this.handler.enabled()) {
            this.handler.disable.call(this.handler);
        } else {
            this.handler.enable.call(this.handler);
        }
    },
});

L.Control.legend = function(options) {
    return new L.Control.Legend(options);
};

/**
 * 标准入口
 * @param map
 * @param option
 */
L.renderLegend = function (map, option) {
    if(!option||!option.data){
        console.warn("没有图例的数据或者图例数据不是数组");
        return;
    }
    L.Control.legend().addTo(map);

    var dom = document.getElementsByClassName('leaflet-control-legend')[0];
    dom.style.width = option.width || "90px";
    dom.style.height =  option.height || "140px";
    dom.style.background = option.background || "#fff";
    var myLegendChart = echarts.init(dom);
    var legendOption = {
        legend: {
            orient:option.orient || 'vertical',
            left:option.left || 'left',
            data:option.data
        },

        series: [{
            type: 'pie',
            data: [],
            label: {
                normal: {
                    show: false
                },
                emphasis: {
                    show: false
                }
            },
            lableLine: {
                normal: {
                    show: false
                },
                emphasis: {
                    show: false
                }
            },
            itemStyle: {
                normal: {
                    opacity: 0
                },
                emphasis: {
                    opacity: 0
                }
            }
        }]
    };
    for (var i = 0; i < option.data.length; i++) {
        var datai = option.data[i];
        legendOption.series[0].data.push({name:datai});
    }
    myLegendChart.setOption(legendOption);
}
