/**
 * Created by HB on 15/10/27.
 */
/**
 * 以下为html5代码,获取地理位置
 */
function getLocation() {

    //测试代码
    //showPosition("");
    //检查浏览器是否支持地理位置获取
    if (navigator.geolocation) {
        //若支持地理位置获取,成功调用showPosition(),失败调用showError
        tipsDialogOn("正在努力获取位置...");
        var config = {enableHighAccuracy:true,timeout:5000,maximumAge:30000};
        navigator.geolocation.getCurrentPosition(showPosition, showError,config);
    } else {
        //tipsDialog("Geolocation is not supported by this browser.");
        tipsDialog("定位失败,用户已禁用位置获取权限");
    }
}


/**
 * 获取地址位置失败[暂不处理]
 */
function showError(error) {
    switch (error.code) {
        case error.PERMISSION_DENIED:
            closeTipsDialogOn();
            tipsDialog("定位失败,用户拒绝请求地理定位");
            //x.innerHTML = "User denied the request for Geolocation.[用户拒绝请求地理定位]"
            break;
        case error.POSITION_UNAVAILABLE:
            closeTipsDialogOn();
            tipsDialog("定位失败,位置信息是不可用");
            //x.innerHTML = "Location information is unavailable.[位置信息是不可用]"
            break;
        case error.TIMEOUT:
            closeTipsDialogOn();
            tipsDialog("定位失败,请求获取用户位置超时");
            //x.innerHTML = "The request to get user location timed out.[请求获取用户位置超时]"
            break;
        case error.UNKNOWN_ERROR:
            closeTipsDialogOn();
            tipsDialog("定位失败,定位系统失效");
            //x.innerHTML = "An unknown error occurred.[未知错误]"
            break;
    }
}

/**
 * 获取地址位置成功
 */
function showPosition(position) {

    closeTipsDialogOn();
//	closeDialog();
    //获得经度纬度
    var x = position.coords.latitude;	//纬度
    var y = position.coords.longitude;	//经度

    translate(x,y);

//	getAddr(x,y);

    //测试
//	getAddr(31.21096,121.407942);



}

function translate(x,y){

    //http://apis.map.qq.com/ws/coord/v1/translate?locations=39.12,116.83;30.21,115.43&type=3&key=OB4BZ-D4W3U-B7VVO-4PJWW-6TKDJ-WPB77

    var key=getKey();
    //http://apis.map.qq.com/ws/geocoder/v1/?location=31.21096,121.407942&key=OB4BZ-D4W3U-B7VVO-4PJWW-6TKDJ-WPB77&get_poi=0&coord_type=5
    var url = "http://apis.map.qq.com/ws/coord/v1/translate?locations="+x+","+y+
        "&type=1"+
        "&key="+key+
        "&output=jsonp";
    $.ajax({
        type : "GET",
        dataType : "jsonp",
        url : url,
        callback:"callback",
        success: function(json){
            if(json){
                if(json.status == "0"){
                    var lng = json.locations[0].lng;//经度
                    var lat = json.locations[0].lat;//纬度

                    getAddr(lat,lng);
                }else{
                    tipsDialog("[x:"+x+",y:"+y+"]转换坐标失败");
                }
            }else{
                tipsDialog("[x:"+x+",y:"+y+"]转换坐标失败");
            }


        },
        error:function(XMLHttpRequest, textStatus, errorThrown){
            tipsDialog("[x:"+x+",y:"+y+"]转换坐标失败");
        }
    });
}


function getAddr(x,y){
    var key=getKey();
    //http://apis.map.qq.com/ws/geocoder/v1/?location=31.21096,121.407942&key=OB4BZ-D4W3U-B7VVO-4PJWW-6TKDJ-WPB77&get_poi=0&coord_type=5
    var url = "http://apis.map.qq.com/ws/geocoder/v1/?location="+x+","+y+
        "&key="+key+
        "&output=jsonp"+
        "&pois=0";
    //jsonp方式跨域调用
    /**
     * 相应数据实例
     {"status":0,
     "result":{
         "location":{"lng":114.057868,"lat":22.543098999645},
         "formatted_address":"广东省深圳市福田区福华一路138",
         "business":"新洲,购物公园,香蜜湖",
         "addressComponent":{
             "city":"深圳市",
             "district":"福田区",
             "province":"广东省",
             "street":"福华一路",
             "street_number":"138"
         },
         "cityCode":340}
     }
     */
    $.ajax({
        type : "GET",
        dataType : "jsonp",
        url : url,
        callback:"callback",
        success: function(json){
            if(json==null || typeof(json)=="undefined"){
                return;
            }
            if(json.status != "0"){
                return;
            }
            setAddress(json.result);
        },
        error:function(XMLHttpRequest, textStatus, errorThrown){
            tipsDialog("[x:"+x+",y:"+y+"]地址位置获取失败,请手动选择地址");
        }
    });
}
