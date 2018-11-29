$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/cdpdict/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true,hidden:true },
			{ label: '编码', name: 'code', index: 'code', width: 80 }, 			
			{ label: '名称', name: 'name', index: 'name', width: 80 }, 			
			{ label: '描述', name: 'description', index: 'description', width: 80 }
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: false,
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
            $("#jqGrid tbody tr.jqgrow").contextMenu('menu', {
                bindings:{ //右键菜单绑定的事件
                    "deleteDict":function(trigger){
                        //点击add时触发事件，需要处理的需求
                        // grid.editGridRow("new",addSettings);},
                        alert(trigger.id);
                    },
                    "editDict":function (trigger) {
                        alert(trigger.id);
                    },
                    "addDictItem":function(trigger){
                        //点击del时触发事件，需要处理的需求
                        // if ($('#del').hasClass('ui-state-disabled')=== false){}
                        alert(trigger.id);
                    }
                },
                //重写onContextMenu和onShowMenu事件
                onContextMenu:function(e){//显示菜单
                    var rowId = $(e.target).closest("tr.jqgrow").attr("id");//获得RowID
                    if( $(e.target).attr("id")=="dontShow")
                        return false;
                    else return true;
                },
                onShowMenu: function(e,menu) {//显示菜单
                    return menu;
                },
                menuStyle:{ //菜单样式
                    backgroundColor:'#fcfdfd',
                    border:'1px solid #a6c9e2',
                    maxWidth:'100px',// to be sure
                    width:'100%' // to have good width of the menu
                },
                itemHoverStyle:{ //点击菜单上面的样式
                    border:'1px solid #79b7e7',
                    color:'#1d5987',
                    backgroundColor:'#d0e5f5'
                }
            });
        },
        onSelectRow:function(row){
            var rowData = $('#personxlgrid').jqGrid('getRowData',row);
            var id = getSelectedRow();
        }
    });
    vm.initItemJqGrid();
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		showModule: 1,
		title: null,
		cdpDict: {},
        cdpDictItem: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.showModule = 2;
			vm.title = "新增";
			vm.cdpDict = {};
		},
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			vm.showList = false;
            vm.showModule = 3;
            vm.title = "修改";
            
            vm.getInfo(id);
		},
		saveOrUpdate: function (event) {
			var url = vm.cdpDict.id == null ? "sys/cdpdict/save" : "sys/cdpdict/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.cdpDict),
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
				    url: baseURL + "sys/cdpdict/delete",
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
			$.get(baseURL + "sys/cdpdict/info/"+id, function(r){
                vm.cdpDict = r.cdpDict;
            });
			vm.reloadItems();
		},
		reload: function (event) {
			vm.showList = true;
            vm.showModule = 1;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		},
        addItem: function(){
            vm.showList = false;
            vm.showModule = 4;
            vm.title = "新增";
            vm.cdpDictItem = {dictId:vm.cdpDict.id};
        },
        delItem: function (event) {
            var ids = getSelectedRows();
            if(ids == null){
                return ;
            }

            confirm('确定要删除选中的记录？', function(){
                $.ajax({
                    type: "POST",
                    url: baseURL + "sys/cdpdictitem/delete",
                    contentType: "application/json",
                    data: JSON.stringify(ids),
                    success: function(r){
                        if(r.code == 0){
                            alert('操作成功', function(index){
                                $("#itemJqGrid").trigger("reloadGrid");
                            });
                        }else{
                            alert(r.msg);
                        }
                    }
                });
            });
        },
        updateItem: function (event) {
            var id = getSelectedRow($("#itemJqGrid"));
            if(id == null){
                return ;
            }
            vm.showList = false;
            vm.showModule = 4;
            vm.title = "修改";

            vm.getItemInfo(id)
        },
        getItemInfo: function(id){
            $.get(baseURL + "sys/cdpdictitem/info/"+id, function(r){
                vm.cdpDictItem = r.cdpDictItem;
            });
        },
        saveOrUpdateItem: function (event) {
            var url = vm.cdpDictItem.id == null ? "sys/cdpdictitem/save" : "sys/cdpdictitem/update";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.cdpDictItem),
                success: function(r){
                    if(r.code === 0){
                        alert('操作成功', function(index){
                            vm.reloadItems();//刷新从表列表
                        });
                    }else{
                        alert(r.msg);
                    }
                }
            });
        },
		initItemJqGrid: function(){
            $("#itemJqGrid").jqGrid({
                url: baseURL + 'sys/cdpdictitem/list',
                datatype: "local",
                colModel: [
                    { label: 'id', name: 'id', index: 'id', width: 50, key: true,hidden:true },
                    { label: '字典ID', name: 'dictId', index: 'dict_id', width: 80 },
                    { label: '条目名称', name: 'itemName', index: 'item_name', width: 80 },
                    { label: '条目值', name: 'itemValue', index: 'item_value', width: 80 },
                    { label: '状态，1有效；0无效', name: 'status', index: 'status', width: 80 },
                    { label: '顺序', name: 'orderNum', index: 'order_num', width: 80 }
                ],
                viewrecords: true,
                height: 385,
                rowNum: 10,
                rowList : [10,30,50],
                rownumbers: true,
                rownumWidth: 25,
                autowidth:true,
                multiselect: true,
                pager: "#itemJqGridPager",
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
                    $("#itemJqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" });
                }
            });
		},
        reloadItems: function (event) {
            vm.showList = true;
            vm.showModule = 3;//主表编辑页、从表列表
            var page = $("#itemJqGrid").jqGrid('getGridParam','page');
            $("#itemJqGrid").jqGrid('setGridParam',{
                datatype:'json',
                postData:{'dictId':vm.cdpDict.id}, //发送数据
                page:page
            }).trigger("reloadGrid");
            $("#itemJqGrid").jqGrid('setGridWidth',$("#jqGrid").width());
        }
	}
});