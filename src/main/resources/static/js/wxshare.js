(function(wx){

    var isWx = function(){
        return navigator.userAgent.match(/MicroMessenger/i) === 'micromessenger' ? true : false;
    }

    var wxshare = function(){
        if('wx' in window){
            wx.config({
                debug: false, 
                // appId: "${appid}",
                //timestamp: "${timestamp}",
                //nonceStr: "${noncestr}",
                //signature: "${signature}",
                appId: 'wx7e2bdb52e162650c',
                timestamp: '1553090415', // 必填，生成签名的时间戳--->系统自己生成的时间戳。
                nonceStr: 'a9de5f376bd14da1', // 必填，生成签名的随机串--->系统本地生成的UUID。
                signature: '94a002a02c53720f4b2187afbce90fa6e917a266',// 必填，签名，见附录1
                jsApiList: ['onMenuShareTimeline', 'onMenuShareAppMessage', 'onMenuShareQQ', 'onMenuShareWeibo', 'onMenuShareQZone'] 
            });

            // 分享内容自定义
            var shareInfo = {
                title: '分享标题',
                desc: '描述',
                link: 'www.baidu.com',
                imgUrl: 'https://avatars3.githubusercontent.com/u/5388012?v=3&u=0981ddbccf0ffbb794cddd65ea12c212a9d5e93b&s=140',
            };

            wx.ready(function(){
                //分享到朋友圈
                wx.onMenuShareTimeline({
                    title: shareInfo.title,
                    link: shareInfo.link,
                    imgUrl: shareInfo.imgUrl,
                    success: function() {

                    },
                    cancel: function() {

                    }
                });
                //分享给朋友
                wx.onMenuShareAppMessage({
                    title: shareInfo.title,
                    desc: shareInfo.desc,
                    link: shareInfo.link,
                    imgUrl: shareInfo.imgUrl,
                    success: function() {
                        alert('11111');

                    },
                    cancel: function() {
                        alert('2222222');

                    }
                });
                //分享到QQ
                wx.onMenuShareQQ({
                    title: shareInfo.title,
                    desc: shareInfo.desc,
                    link: shareInfo.link,
                    imgUrl: shareInfo.imgUrl,
                    success: function() {
                        alert('qqqqqqqqq11111');

                    },
                    cancel: function() {
                        alert('qqqqqqqqq12222');
                    }
                });
                //分享到腾讯微博
                wx.onMenuShareWeibo({
                    title: shareInfo.title,
                    desc: shareInfo.desc,
                    link: shareInfo.link,
                    imgUrl: shareInfo.imgUrl,
                    success: function() {

                    },
                    cancel: function() {

                    }
                });
                //分享到QQ空间
                wx.onMenuShareQZone({
                    title: shareInfo.title, 
                    desc: shareInfo.desc, 
                    link: shareInfo.link,
                    imgUrl: shareInfo.imgUrl,
                    success: function() {

                    },
                    cancel: function() {

                    }
                });
            });

        }else{
            console.error('请在微信客户端中打开')
        }
    }


    isWx() && wxshare();
    wxshare();
})(wx || jWeixin)