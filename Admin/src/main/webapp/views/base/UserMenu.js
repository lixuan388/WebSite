
layui.use(['table','form'], function(){
  var table = layui.table
  ,form = layui.form;
  var $ = layui.$;
  table.on('tool(DataGrid)', function(obj){
    var data = obj.data;
    if(obj.event === 'del'){

    } else if(obj.event === 'edit'){
      event.Edit(obj);
    }else if(obj.event === 'addChild'){
      event.AddChild(obj);
    }
  });
  table.render({
    elem: '#DataGrid'
    ,page:true
    ,url:'../../web/WebApp/UserMenuQuery.json'
    ,limit:10
    ,height: 'full-100'
    ,cols: [[
      {field:'_id',width: 80,align:'center',title:'ID'}
      ,{field:'_Status',width: 100,align:'center',title:'状态'}
      ,{field:'_Text',width: 100,align:'center',title:'名称'}
      ,{field:'_Href',align:'center',title:'链接'}
      ,{field:'_MenuID',width: 80,align:'center',title:'MenuID'}
      ,{field:'_Icon',width: 120,align:'center',title:'图标'}
      ,{field:'_ParentID',width: 80,align:'center',title:'父菜单'}
      ,{field:'_Index',width: 50,align:'center',title:'排序'}
      ,{field:'OP',width:320,align:'right', toolbar: '#TableOPBar',align:'center'}
    ]]    
    ,response: {
      statusCode: 1 //重新规定成功的状态码为 200，table 组件默认为 0
    }
    ,parseData: function(res){ //将原始数据解析成 table 组件所规定的数据
        return {
          "code": res.MsgID, //解析接口状态
          "msg": res.MsgText, //解析提示文本
          "count": res.Data.length, //解析数据长度
          "data": res.Data //解析数据列表
        };
      }
  });
  var layerarea=['80%', '600px'];
  var event = {
      Query: function(self){ //获取选中数据
        table.reload("DataGrid");
      },
      Insert:function(self)
      {
        var url="UserMenuEdit.html#/-1";
        layer.open({
          type: 2,
          title: "新增菜单",
          shadeClose: false,
          shade: 0.8,
          area: layerarea,
          //area: 'auto',
          //maxHeight:'810px',
          content: url,//iframe的url
          end: function(){ 
            event.Query();
          }
        });   
      },
      Edit:function(obj)
      {
        var data = obj.data;
        var url="UserMenuEdit.html#/"+data._id;
        layer.open({
          type: 2,
          title: "修改菜单",
          shadeClose: false,
          shade: 0.8,
          area: layerarea,
          //area: 'auto',
          //maxHeight:'810px',
          content: url,//iframe的url
          end: function(){ 
            event.Query();
          }
        });   
      },
      AddChild:function(obj)
      {
        var data = obj.data;
        var url="UserMenuEdit.html#/-1/"+data._MenuID;
        layer.open({
          type: 2,
          title: "修改菜单",
          shadeClose: false,
          shade: 0.8,
          area:layerarea,
          //area: 'auto',
          //maxHeight:'810px',
          content: url,//iframe的url
          end: function(){ 
            event.Query();
          }
        });   
      }
//      ,ProductInfoInsert:function(self)
//      { 
//        var $loadingToast = loadingToast();   
//        $.ajax({
//          url: '<%=request.getContextPath() %>/System/GetMaxID.json?d=' + new Date().getTime(),
//          type: 'get',
//          dataType: 'Json',
//          success: function (data) {
//            $loadingToast.modal("hide");
//            if (data.MsgID != 1)
//            { 
//              alert(data.MsgText);
//              return;
//            } 
//            else
//            {
//              OpenWindowLayer("新增产品","<%=request.getContextPath()%>/Content/Product/Form/ProductInfoInsert.jsp?ID=-1",null,{'width':'800px'})
//            }
//          }
//        })  
//      }
//      ,ProductInfoEdit:function(obj)
//      {
//        var id=obj.data.epi_id;
//        OpenWindowLayer("修改产品信息","<%=request.getContextPath()%>/Content/Product/Form/ProductInfoInsert.jsp?ID="+id,null,{'width':'800px'})
//        
//      }
//      ,ProductInfoDelete:function(self)
//      {
//        $("#ProductInfoDiv #ProductInfoDeleteModal [FieldName]").each(function(){
//          var FieldName=$(this).attr("FieldName");
//          $(this).val($(t).parent().data(FieldName.toLowerCase()));
//        });
//        $("#ProductInfoDiv #ProductInfoDeleteModal [FieldName=epi_status]").val('D'); 
//        $("#ProductInfoDiv #ProductInfoDeleteModal [FieldName=epi_User_Lst]").val('<%=request.getSession().getAttribute("UserName")%>');  
//        $("#ProductInfoDiv #ProductInfoDeleteModal [FieldName=epi_Date_Lst]").val(new Date().Format("yyyy-MM-dd HH:mm:ss"));
//        $("#ProductInfoDiv #ProductInfoDeleteModal [FieldName=epi_Type]").val(ProductTypeJson[$(t).parent().data("api_type")]);
//        $("#ProductInfoDiv #ProductInfoDeleteModal").modal("show");
//      }
  };


  $('[lay-event]').on('click', function(){
    var type = $(this).attr('lay-event');
    event[type] ? event[type].call(this) : '';
  });
  
});

