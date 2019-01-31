/**

 @Name：layuiAdmin iframe版主入口
 @Author：贤心
 @Site：http://www.layui.com/admin/
 @License：LPPL
    
 */
 
layui.extend({
  setter: 'config' //配置模块
  ,admin: 'lib/admin' //核心模块
  ,view: 'lib/view' //视图渲染模块
}).define(['setter', 'admin','carousel', 'form','laytpl'], function(exports){
  var setter = layui.setter
  ,element = layui.element
  ,admin = layui.admin
  ,tabsPage = admin.tabsPage
  ,view = layui.view
  ,carousel = layui.carousel
  ,form = layui.form
  ,laytpl = layui.laytpl
  ,$=layui.$


    
    
  //打开标签页
  ,openTabsPage = function(url, text){
    //遍历页签选项卡
    var matchTo
    ,tabs = $('#LAY_app_tabsheader>li')
    ,path = url.replace(/(^http(s*):)|(\?[\s\S]*$)/g, '');
    
    tabs.each(function(index){
      var li = $(this)
      ,layid = li.attr('lay-id');
      
      if(layid === url){
        matchTo = true;
        tabsPage.index = index;
      }
    });
    
    text = text || '新标签页';
    
    if(setter.pageTabs){
      //如果未在选项卡中匹配到，则追加选项卡
      if(!matchTo){
        $(APP_BODY).append([
          '<div class="layadmin-tabsbody-item layui-show">'
            ,'<iframe src="'+ url +'" frameborder="0" class="layadmin-iframe"></iframe>'
          ,'</div>'
        ].join(''));
        tabsPage.index = tabs.length;
        element.tabAdd(FILTER_TAB_TBAS, {
          title: '<span>'+ text +'</span>'
          ,id: url
          ,attr: path
        });
      }
    } else {
      var iframe = admin.tabsBody(admin.tabsPage.index).find('.layadmin-iframe');
      iframe[0].contentWindow.location.href = url;
    }
    //定位当前tabs
    element.tabChange(FILTER_TAB_TBAS, url);
    admin.tabsBodyChange(tabsPage.index, {
      url: url
      ,text: text
    });
  }
  ,LoadSliderWrapper=function(){
    view.req({
      type: 'get'
      ,url: '/Service/web/GetSliderWrapper.json'
      ,dataType: 'json'
      ,success: function(res){
        if (res.MsgID==1){          
          SliderWrapper=$('#slider-wrapper div');
          SliderWrapper.html('');
          for (i in res.Data){
            img=res.Data[i];
            SliderWrapper.append('<div class="slide-li"> <img src="'+img+'"></div>');
          }
          carousel.render({
            elem: '#slider-wrapper'
            ,width: '26.75em'
            ,height: '12.66em'
            ,interval: 5000
          });
        }

      }
    });
  }
  ,LoadBrandingWrapper=function(){
    view.req({
      type: 'get'
      ,url: '/Service/web/GetBrandingWrapper.json'
      ,dataType: 'json'
      ,success: function(res){
        if (res.MsgID==1){          
          BrandingWrapper=$('.branding_wrapper_box');
          BrandingWrapper.html('');
          for (i in res.Data){
            img=res.Data[i].Img;
            span=res.Data[i].Span;            
            a='<a class="branding_wrapper_box_a" href="javascript:void(0);"><img src="'+img+'"> <span style="color:">'+span+'</span></a>';          
            BrandingWrapper.append(a);
          }
          BrandingWrapper.append('<div style="clear: both;"></div>');

        }

      }
    });
  }
  ,LoadGoodsList=function(div,key,tpl){
    view.req({
      type: 'get'
      ,url: '/Service/web/GetGoodsList.json?key='+key
      ,dataType: 'json'
      ,success: function(res){
        if (res.MsgID==1){
          GoodsListDiv=$(div);
          GoodsListDiv.html('');
          for (i in res.Data){
            laytpl(tpl).render(res.Data[i], function(html){
              GoodsListDiv.append(html);
            }); 
          }
          GoodsListDiv.append('<div style="clear: both;"></div>');
        }
      }
    });
  }
  ,InitFontSize=function(){
    var Winwidth=$('body').css('width').replace("px","");
    var FontSize=Winwidth*14/375
    $('body').css('font-size',FontSize+'px');
    $('#loadingmask').remove();
  }
  
  
  ,APP_BODY = '#LAY_app_body', FILTER_TAB_TBAS = 'layadmin-layout-tabs'
  ,$ = layui.$, $win = $(window);
  
  //初始
  if(admin.screen() < 2) admin.sideFlexible();
  
  //将模块根路径设置为 controller 目录
  layui.config({
    base: setter.base + 'modules/'
  });
  
  //扩展 lib 目录下的其它模块
  layui.each(setter.extend, function(index, item){
    var mods = {};
    mods[item] = '{/}' + setter.base + 'lib/extend/' + item;
    layui.extend(mods);
  });
  
  view().autoRender();
  
  window.onscroll = function () {
    var scrollTop = document.documentElement.scrollTop;
    if (scrollTop > 100)
    {
      $('.search_wrapper').css('background-color', '#424242')
    } 
    else
    {
      $('.search_wrapper').css('background-color', '#42424200')
    }
  }
  //加载公共模块
  layui.use('common');

  //对外输出
  exports('index', {
    openTabsPage: openTabsPage
    ,LoadSliderWrapper:LoadSliderWrapper
    ,LoadBrandingWrapper:LoadBrandingWrapper
    ,LoadGoodsList:LoadGoodsList
    ,InitFontSize
  });
});
