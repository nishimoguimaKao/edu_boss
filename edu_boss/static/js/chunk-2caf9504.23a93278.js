(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2caf9504"],{4756:function(e,t,n){"use strict";var a=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("el-card",{staticClass:"form-container",attrs:{shadow:"never"}},[n("el-form",{ref:"form",attrs:{model:e.homeAdvertise,rules:e.rules,"label-width":"150px",size:"small"}},[n("el-form-item",{attrs:{label:"广告位名称：",prop:"name"}},[n("el-input",{staticClass:"input-width",model:{value:e.homeAdvertise.name,callback:function(t){e.$set(e.homeAdvertise,"name",t)},expression:"homeAdvertise.name"}})],1),n("el-form-item",[n("el-button",{attrs:{type:"primary"},on:{click:function(t){return e.handleSave()}}},[e._v("提交")])],1)],1)],1)},i=[],r=n("ed08"),o={name:"HomeAdvertiseDetail",props:{isEdit:{type:Boolean,default:!1}},data:function(){return{homeAdvertise:{name:null},rules:{name:[{required:!0,message:"请输入广告名称",trigger:"blur"},{min:2,max:140,message:"长度在 2 到 140 个字符",trigger:"blur"}]}}},created:function(){if(this.isEdit){var e=this.$route.query.id;this.loadPromotionSpace(e)}else this.homeAdvertise={}},methods:{handleSave:function(){var e=this;this.$refs.form.validate((function(t){if(!t)return!1;r["b"].post("/PromotionSpace/saveOrUpdatePromotionSpace",e.homeAdvertise).then((function(t){e.$router.back()})).catch((function(t){e.$message("数据处理失败! !")}))}))},loadPromotionSpace:function(e){var t=this;return r["b"].get("/PromotionSpace/findPromotionSpaceById?id="+e).then((function(n){Object.assign(t.homeAdvertise,n.data.content),t.homeAdvertise.id=e})).catch((function(e){t.$message("数据处理失败! !")}))}}},s=o,c=(n("df16"),n("2877")),l=Object(c["a"])(s,a,i,!1,null,"641276c4",null);t["a"]=l.exports},6913:function(e,t,n){"use strict";n.r(t);var a=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("home-advertise-detail",{attrs:{isEdit:!0}})},i=[],r=n("4756"),o={name:"updateHomeAdvertise",title:"编辑广告位",components:{HomeAdvertiseDetail:r["a"]}},s=o,c=n("2877"),l=Object(c["a"])(s,a,i,!1,null,null,null);t["default"]=l.exports},"8a7d":function(e,t,n){},df16:function(e,t,n){"use strict";var a=n("8a7d"),i=n.n(a);i.a}}]);