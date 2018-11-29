var ztree;

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		mapLayer: {dirId:0,dirName:'顶级目录'},
		dirId:null
	},
	methods: {
        getDir: function(dirId){
            //加载目录树
            $.get(baseURL + "map/mapdir/select", function(r){
                ztree = $.fn.zTree.init($("#menuTree"), {
                    data: {
                        simpleData: {
                            enable: true,
                            idKey: "id",
                            pIdKey: "parentId",
                            rootPId: -1
                        },
                        key: {
                            url:"nourl"
                        }
                    },
                    callback: {
                        onClick: function (event, treeId, treeNode){
                            vm.dirId = treeNode.id;
                            vm.reload();
                        }
                    }
                }, r.dirList);
            })
        },
        getDirTree: function(dirId){
            //加载目录树
            $.get(baseURL + "map/mapdir/select", function(r){
                ztree = $.fn.zTree.init($("#dirTree"), {
                    data: {
                        simpleData: {
                            enable: true,
                            idKey: "id",
                            pIdKey: "parentId",
                            rootPId: -1
                        },
                        key: {
                            url:"nourl"
                        }
                    }
                }, r.dirList);
                var node = ztree.getNodeByParam("id", vm.mapLayer.dirId);

                ztree.selectNode(node);

                vm.mapLayer.dirName = node.name;
            })
        },
        dirTree: function(){
            layer.open({
                type: 1,
                offset: '50px',
                skin: 'layui-layer-molv',
                title: "选择图层目录",
                area: ['300px', '450px'],
                shade: 0,
                shadeClose: false,
                content: jQuery("#dirLayer"),
                btn: ['确定', '取消'],
                btn1: function (index) {
                    var node = ztree.getSelectedNodes();
                    //选择上级菜单
                    vm.mapLayer.dirId = node[0].id;
                    vm.mapLayer.dirName = node[0].name;
                    layer.close(index);
                }
            });
        },
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.mapLayer = {};
            vm.getDirTree();
		},
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(id)
		},
		saveOrUpdate: function (event) {
			var url = vm.mapLayer.id == null ? "map/maplayer/save" : "map/maplayer/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.mapLayer),
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功', function(index){
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		del: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "map/maplayer/delete",
                    contentType: "application/json",
				    data: JSON.stringify(ids),
				    success: function(r){
						if(r.code == 0){
							alert('操作成功', function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		getInfo: function(id){
			$.get(baseURL + "map/maplayer/info/"+id, function(r){
                vm.mapLayer = r.mapLayer;
                vm.getDirTree();
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page,
                postData:{'dirId':vm.dirId}
            }).trigger("reloadGrid");
		}
	}
});

$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'map/maplayer/list',
        datatype: "json",
        colModel: [
            { label: 'id', name: 'id', index: 'id', width: 50, key: true },
            { label: '图层服务名', name: 'layerName', index: 'layer_name', width: 80 },
            { label: '英文名', name: 'layerEnglishName', index: 'layer_english_name', width: 80 },
            { label: '图层目录', name: 'dirName', index: 'dir_name', width: 80 },
            { label: '别名', name: 'alias', index: 'alias', width: 80 },
            { label: '来源', name: 'source', index: 'source', width: 80 },
            { label: '数据类型', name: 'layerType', index: 'layer_type', width: 80 },
            { label: '地图角度', name: 'angle', index: 'angle', width: 80 },
            { label: '排序', name: 'seq', index: 'seq', width: 80 },
            { label: '是否显示', name: 'display', index: 'display', width: 80 },
            { label: '状态', name: 'status', index: 'status', width: 80, formatter: function(item, index){
                    return item.status == 1 ? '有效' : '<i class="fa">无效</i>';
                } },
            { label: '备注', name: 'description', index: 'description', width: 80 }
        ],
        viewrecords: true,
        height: 385,
        rowNum: 10,
        rowList : [10,30,50],
        rownumbers: true,
        rownumWidth: 25,
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page",
            rows:"limit",
            order: "order"
        },
        gridComplete:function(){
            //隐藏grid底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" });
        }
    });
    vm.getDir();
});