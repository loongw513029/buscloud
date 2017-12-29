/**
 * 导航栏
 */

Ext.define("App.view.main.Nav", {
	extend: "Ext.tree.Panel",
	xtype: "nav",
	id: "nav",
	initComponent: function() {
		var navStore = Ext.create("Ext.data.TreeStore", {
			model: "Ext.data.TreeModel",
			proxy: {
				type: "ajax",
				reader: "json",
				url: "data/nav.json"
			}
			
//			root: {
//				expanded: true,
//				children: [{
//					text: "??",
//					leaf: true,
//					cls: "node-link",
//					mod: "desktop",
//					modUrl: "desktop.Desktop"
//				}, {
//					text: "????",
//					expanded: true,
//					cls: "node-link",
//					children: [{
//						text: "????",
//						leaf: true,
//						cls: "node-link",
//						mod: "role",
//						modUrl: "role.Role"
//					}, {
//						text: "????",
//						leaf: true,
//						cls: "node-link",
//						mod: "user",
//						modUrl: "user.User"
//					}]
//				}, {
//					text: "????",
//					expanded: true,
//					children: [{
//						text: "????",
//						leaf: true
//					}, {
//						text: "????",
//						leaf: true
//					}]
//				}, {
//					text: "????",
//					expanded: true,
//					children: [{
//						text: "????",
//						leaf: true
//					}, {
//						text: "????",
//						leaf: true
//					}, {
//						text: "????",
//						leaf: true
//					}]
//				}, {
//					text: "????",
//					expanded: true,
//					children: [{
//						text: "????",
//						leaf: true
//					}, {
//						text: "????",
//						leaf: true
//					}, {
//						text: "????",
//						leaf: true
//					}]
//				}]
//			}
		});
		
		Ext.apply(this, {
			title: "车辆列表",
			collapsible: true,
			//split: true,
			autoScroll: true,
			margin: "0 5 0 5",
			width: 225,
			border: true,
			rootVisible: false,
			store: navStore,
			listeners: {
				itemclick: "onMenuClick"
			}
		});
		this.callParent(arguments);
	}
});
