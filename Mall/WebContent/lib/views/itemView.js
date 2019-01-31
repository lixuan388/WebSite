/**

 @Name：layuiAdmin iframe版主入口
 @Author：贤心
 @Site：http://www.layui.com/admin/
 @License：LPPL
    
 */
 
layui.extend({
  index: 'lib/index' //核心模块
}).define(['index','plugin','carousel', 'form','laytpl','flow'], function(exports){
  var setter = layui.setter
  ,element = layui.element
  ,admin = layui.admin
  ,tabsPage = admin.tabsPage
  ,view = layui.view
  ,plugin = layui.plugin
  ,carousel = layui.carousel
  ,form = layui.form
  ,laytpl = layui.laytpl
  ,flow = layui.flow
  ,$=layui.$
  ,$body = $('body')

  ,LoadGoodsInfo=function(id){
    view.req({
      type: 'get'
      ,url: '/Service/web/Goods/GetGoodsInfo.json?id='+id
      ,dataType: 'json'
      ,success: function(res){
        if (res.MsgID==1){          
          if (res.Data!=undefined){
            LoogImageList=res.Data.GoodsDescInfo.LoogImageList;
            
            SliderWrapper=$('#slider-wrapper div');
            SliderWrapper.html('');
            for (i in LoogImageList){
              img=LoogImageList[i].ImgSrc;
              ImgBackSrc=LoogImageList[i].ImgBackSrc;
              img=ImgBackSrc==null?img:ImgBackSrc
              SliderWrapper.append('<div class="slide-li"> <img  lay-src="'+img+'" ></div>');
            }
            
            carousel.render({
              elem: '#slider-wrapper'
              ,width: '26.75em'
              ,height: '26.75em'
              ,interval: 5000
            });
            flow.lazyimg();
            $('.SaleInfo .GoodsTitle').html(res.Data.GoodsDescInfo.ItemName);
            $('.SaleInfo .list3_info .list3_price1').html(res.Data.GoodsPrice1);
            if (res.Data.GoodsPrice2!=''){
              $('.SaleInfo .list3_info .list3_price2').html('.'+res.Data.GoodsPrice2);
            }

            res.Data.GoodsDescInfo.ItemDesc=res.Data.GoodsDescInfo.ItemDesc.replace(/<br\/>/g,'');
            var getTpl = $('#SaleInfoTpl').html();
            var view = $('.SaleInfo');
            
            laytpl(getTpl).render(res.Data, function(html){
              view.append(html);
            }); 
            
//            $('.SaleInfo .GoodsRemark').html(res.Data.GoodsDescInfo.ItemDesc);
//            
            $('.SaleInfo .GoodsRemark').find('img[item_init_src]').each(function(){
              //$(this).attr('lay-src',$(this).attr('item_init_src'));
              $(this).attr('src',$(this).attr('item_init_src'));
            })
            var remarkHeight=$('.SaleInfo .GoodsRemark').css('height').replace("px","");

            var Winwidth=$('body').css('width').replace("px","");
            var scale=(Winwidth / 640);

            $('.SaleInfo .GoodsRemark').css('transform','scale('+scale+ ')');
            $('.SaleInfo .GoodsRemark').css('height',remarkHeight*scale);
            plugin.render();
            
          }
//          console.log(res);
        }

      }
    });
  }
  var events ={
    //伸缩
    BuyConfirm1: function(othis){
      var BuyConfirm=$('#BuyConfirm');
      BuyConfirm.show();
      
    } 
    ,BuyConfirm2: function(othis){
      var BuyConfirm=$('#BuyConfirm');
      BuyConfirm.show();
    }
    ,CloseBuyConfirm: function(othis){
      console.log('CloseConfirm')
      var BuyConfirm=$('#BuyConfirm');
      BuyConfirm.hide();
    }
  }
  //点击事件
  $body.on('click', '*[layadmin-event]', function(){
    var othis = $(this)
    ,attrEvent = othis.attr('layadmin-event');
    events[attrEvent] && events[attrEvent].call(this, othis);
  });
  
  
  //对外输出
  exports('itemView', {
    LoadGoodsInfo:LoadGoodsInfo
  });
});
