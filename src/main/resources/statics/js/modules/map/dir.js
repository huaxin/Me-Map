var setting = {
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
};
var ztree;

var vm = new Vue({
    el:'#rrapp',
    data:{
        showList: true,
        title: null,
        dir:{
            parentName:null,
            parentId:0,
            fullPath:0,
            fullPathName:'顶级目录',
            seq:0
        }
    },
    methods: {
        getDir: function(dirId){
            //加载目录树
            $.get(baseURL + "map/mapdir/select", function(r){
                ztree = $.fn.zTree.init($("#menuTree"), setting, r.dirList);
                var node = ztree.getNodeByParam("id", vm.dir.parentId);

                ztree.selectNode(node);

                vm.dir.parentName = node.name;
            })
        },
        add: function(){
            vm.showList = false;
            vm.title = "新增";
            vm.dir = {parentName:null,parentId:0,seq:0,fullPath:0,fullPathName:'顶级目录'};
            vm.getDir();
        },
        update: function () {
            var dirId = getDirId();
            if(dirId == null){
                return ;
            }

            $.get(baseURL + "map/mapdir/info/"+dirId, function(r){
                vm.showList = false;
                vm.title = "修改";
                vm.dir = r.dir;

                vm.getDir();
            });
        },
        del: function () {
            var dirId = getDirId();
            if(dirId == null){
                return ;
            }

            confirm('确定要删除选中的记录？', function(){
                $.ajax({
                    type: "POST",
                    url: baseURL + "map/mapdir/delete",
                    data: "id=" + dirId,
                    success: function(r){
                        if(r.code === 0){
                            alert('操作成功', function(){
                                vm.reload();
                            });
                        }else{
                            alert(r.msg);
                        }
                    }
                });
            });
        },
        saveOrUpdate: function () {
            if(vm.validator()){
                return ;
            }

            var url = vm.dir.id == null ? "map/mapdir/save" : "map/mapdir/update";
            $.ajax({
                type: "POST",
                url:  baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.dir),
                success: function(r){
                    if(r.code === 0){
                        alert('操作成功', function(){
                            vm.reload();
                        });
                    }else{
                        alert(r.msg);
                    }
                }
            });
        },
        menuTree: function(){
            layer.open({
                type: 1,
                offset: '50px',
                skin: 'layui-layer-molv',
                title: "选择菜单",
                area: ['300px', '450px'],
                shade: 0,
                shadeClose: false,
                content: jQuery("#menuLayer"),
                btn: ['确定', '取消'],
                btn1: function (index) {
                    var node = ztree.getSelectedNodes();
                    //选择上级菜单
                    vm.dir.parentId = node[0].id;
                    vm.dir.parentName = node[0].name;
                    vm.dir.fullPath = node[0].fullPath == null?node[0].id: node[0].fullPath+ ',' + node[0].id;
                    vm.dir.fullPathName = node[0].fullPathName == null?node[0].name: node[0].fullPathName+ ',' + node[0].name;
                    layer.close(index);
                }
            });
        },
        reload: function () {
            vm.showList = true;
            Menu.table.refresh();
        },
        validator: function () {
            if(isBlank(vm.dir.name)){
                alert("菜单名称不能为空");
                return true;
            }

            /*//菜单
            if(vm.dir.type === 1 && isBlank(vm.dir.url)){
                alert("菜单URL不能为空");
                return true;
            }*/
        }
    }
});


var Menu = {
    id: "menuTable",
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Menu.initColumn = function () {
    var columns = [
        {field: 'selectItem', radio: true},
        {title: '目录ID', field: 'id', visible: false, align: 'center', valign: 'middle', width: '80px'},
        {title: '目录名称', field: 'name', align: 'center', valign: 'middle', sortable: true, width: '180px'},
        {title: '上级目录', field: 'parentName', align: 'center', valign: 'middle', sortable: true, width: '100px'},
        {title: '类型', field: 'status', align: 'center', valign: 'middle', sortable: true, width: '80px', formatter: function(item, index){
            return !item.icon || item.icon == null ? '<i class="fa fa-file-text-o fa-lg"></i>' : '<i class="fa fa-folder-open fa-lg"></i>';
        }},
        {title: '图标', field: 'status', align: 'center', valign: 'middle', sortable: true, width: '80px', formatter: function(item, index){
                return item.status == 1 ? '有效' : '<i class="fa fa-lg">无效</i>';
        }},
        {title: '排序号', field: 'seq', align: 'center', valign: 'middle', sortable: true, width: '100px'}]
    return columns;
};


function getDirId () {
    var selected = $('#menuTable').bootstrapTreeTable('getSelections');
    if (selected.length == 0) {
        alert("请选择一条记录");
        return null;
    } else {
        return selected[0].id;
    }
}


$(function () {
    var colunms = Menu.initColumn();
    var table = new TreeTable(Menu.id, baseURL + "map/mapdir/list", colunms);
    table.setExpandColumn(2);
    table.setIdField("id");
    table.setCodeField("id");
    table.setParentCodeField("parentId");
    table.setExpandAll(false);
    table.init();
    Menu.table = table;
});
