/*
 * 页面头部
 */
Ext.define("App.view.main.Top", {
	extend: "Ext.toolbar.Toolbar",
	xtype: "top",
	id: "top",
	uses: ["App.ux.XBtn"],
	initComponent: function() {
		Ext.apply(this, {
			height: 40,
			border: false,
			layout: "auto",
			items: [{
				xtype: "image",
				src: "/img/logo.png",
				cls: "main-logo"
				},'->',
				{
					text:"首页",
					glyph:0xf015,
					cls:"nav-item first"
				},
                {
                	text:"视频",
					glyph:0xf03d,
                    cls:"nav-item",
					menu:[
						{
							text:"视频预览"
						},
						{
							text:"视频回放"
						}
					]
				},{
					text:"CAN",
					glyph:0xf069,
                    cls:"nav-item",
                    menu:[{
						text:"实时数据"
					},{
						text:"统计分析"
					}]
				},{
                    text:"地图",
                    glyph:0xf041,
                    cls:"nav-item",
                    menu:[{
                        text:"实时轨迹"
                    },{
                        text:"历史轨迹"
                    }]
                },{
                    text:"设备管理",
                    glyph:0xf26c,
                    cls:"nav-item",
                    menu:[{
                        text:"设备巡检"
                    },{
                        text:"设备管理"
                    }]
                },{
                    text:"报警",
                    glyph:0xf06d,
                    cls:"nav-item",
                    menu:[{
                        text:"报警列表"
                    },{
                        text:"报警统计"
                    }]
                },
				{
                    xtype: "container",
                    cls: "top-tool",
                    items: [{
                        xtype: "label",
                        html: "<i class='icon-user'></i> 欢迎您，Neo"
                    }, {
                        xtype: "xbtn",
                        text: "退出",
                        glyph: 0xf011,
                        handler: "exitSys"
                    }]
        	}]
		}); 
		this.callParent(arguments);
	}
});
