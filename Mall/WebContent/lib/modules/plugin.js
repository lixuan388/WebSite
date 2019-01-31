
layui.define(function(exports){
  var $ = layui.jquery
  , $body = $('body')
  
  

  //请求模板文件渲染
  render = function(views, params){
    
    //CountChoose render
    $body.find('.CountChoose').each(function(){
      var that=$(this)
      var $minus=$(this).find('.minus');
      var $plus=$(this).find('.plus');
      var $value=$(this).find('.text');
      
      $minus.on('click', function(){
        var v=$value.val()*1;
        if (v>0){
          $value.val(v-1);
        }
        v=$value.val()*1;
        if (v==0){
          $minus.addClass('disable');
        }
      });
      $plus.on('click', function(){
        var v=$value.val()*1;
        $value.val(v+1);
        v=$value.val()*1;
        if (v>0){
          $minus.removeClass('disable');
        }
      });
      
    })
    
    
    return;
  };
  
  
  //接口输出
  exports('plugin', {
    render:render
  });
})